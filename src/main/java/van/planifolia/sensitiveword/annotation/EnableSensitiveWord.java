package van.planifolia.sensitiveword.annotation;

import java.lang.annotation.*;

/**
 * 用于实现风险词汇字段脱敏处理的非法
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSensitiveWord {
    /**
     *
     * 目標参数
     */
    Class<?> targetParam();

    /**
     * 被替换的字符串，默认为 **
     */
    String replaceStr() default "**";


}
