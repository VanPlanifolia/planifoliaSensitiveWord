package van.planifolia.sensitiveword.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;
import van.planifolia.sensitiveword.annotation.EnableSensitiveWord;
import van.planifolia.sensitiveword.annotation.SensitiveWordField;
import van.planifolia.sensitiveword.compose.SensitiveWordExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 用于实现实体类指定字段违禁词处理的切面
 * @Author Van.Planifolia
 * @Date 2024/2/26
 * @Version 1.0
 */
@Aspect
@Slf4j
public class SensitiveWordReplaceAspect {

    private final SensitiveWordExecutor sensitiveWordExecutor;

    public SensitiveWordReplaceAspect(SensitiveWordExecutor sensitiveWordExecutor) {
        this.sensitiveWordExecutor = sensitiveWordExecutor;
    }

    /**
     * 数据脱敏的切点方法
     */
    @Pointcut("@annotation(van.planifolia.sensitiveword.annotation.EnableSensitiveWord)")
    public void replacePointCut() {
    }

    /**
     * 进行实体字段文字脱敏的切面方法
     *
     * @param joinPoint 连接电
     */
    @Before(value = "replacePointCut()")
    public void sensitiveWordReplace(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        // 取出切点方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targerMethod = signature.getMethod();
        // 判断切点方法是否被开启
        EnableSensitiveWord targetAnnotation = targerMethod.getAnnotation(EnableSensitiveWord.class);
        if (targetAnnotation == null) {
            return;
        }
        String replaceStr = targetAnnotation.replaceStr();
        // 获取目标的参数class
        Class<?> targetParam = targetAnnotation.targetParam();
        // 获取目标方法的所有参数信息
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            log.warn("数据脱敏注解疑似使用异常！被处理的方法参数列表不得为空！");
            return;
        }
        // 根据参数类型对比，取到被数据脱敏的对象参数,若一个都没有就直接结束方法即可
        List<Object> parameterSet = Arrays.stream(args).filter(arg -> targetParam.equals(arg.getClass())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parameterSet)) {
            log.warn("数据脱敏注解疑似使用异常！无法根据注解中提供的目标参数类型 [ Class : {} ]取到合适的参数信息！", targetParam);
            return;
        }
        // 从参数Set中取出匹配得到的参数信息
        Object argEntityObj = parameterSet.get(0);
        Field[] fields = targetParam.getDeclaredFields();
        Set<Field> targetFieldSet = Arrays.stream(fields).filter(field -> Objects.nonNull(field.getAnnotation(SensitiveWordField.class))).collect(Collectors.toSet());
        targetFieldSet.forEach(field -> {
            if (!String.class.equals(field.getType())) {
                log.warn("字段 [classInfo : {}，fieldInfo : {}] 数据类型不为字符串，无法进行处理！", field.getDeclaringClass(), field.getName());
                return;
            }
            Object argObj;
            field.setAccessible(true);
            try {
                argObj = field.get(argEntityObj);
            } catch (Exception e) {
                log.error("无法从对象 [objInfo :{}] 中取到参数类型为 [fieldInfo : {}] 的具体值！ ", argEntityObj.getClass(), field.getType());
                return;
            }
            if (argObj != null) {
                String processedWords = sensitiveWordExecutor.processSensitiveWord(argObj.toString(), replaceStr);
                try {
                    field.set(argEntityObj, processedWords);
                    log.info("敏感词汇替换完毕 {} 替换为 {}", argObj, processedWords);
                } catch (IllegalAccessException e) {
                    log.error("无法将对象 [objInfo :{}] 中的字段 [fieldInfo : {}] 进行重新设值！ ", argEntityObj.getClass(), field.getName());

                }
            }
            log.info("数据字段脱敏完毕！总处理时间耗时{}ms", System.currentTimeMillis() - start);
        });
    }
}

