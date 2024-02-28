package van.planifolia.sensitiveword.configure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import van.planifolia.sensitiveword.aspect.SensitiveWordReplaceAspect;
import van.planifolia.sensitiveword.compose.SensitiveWordExecutor;
import van.planifolia.sensitiveword.compose.SensitiveWordFilter;
import van.planifolia.sensitiveword.compose.SensitiveWordInit;

/**
 * @Description
 * @Author Van.Planifolia
 * @Date 2024/2/26
 * @Version 1.0
 */
@EnableConfigurationProperties(SensitiveWordProperties.class)
@Configuration
public class SensitiveWordAutoConfiguration {


    /**
     * 初始化字典类并且注入spring容器
     *
     * @param sensitiveWordProperties 配置
     * @return 注册的Bean实例
     */
    @Bean
    public SensitiveWordInit sensitiveWordInit(SensitiveWordProperties sensitiveWordProperties) {
        return new SensitiveWordInit(sensitiveWordProperties.getDictPath());
    }

    /**
     * 初始化敏感词过滤器且注入spring容器
     *
     * @param sensitiveWordInit 配置
     * @return 注册的Bean实例
     */
    @Bean
    @DependsOn("sensitiveWordInit")
    public SensitiveWordFilter sensitiveWordFilter(SensitiveWordInit sensitiveWordInit) {
        return new SensitiveWordFilter(sensitiveWordInit);
    }

    /**
     * 初始化敏感词处理器并且注入spring容器
     *
     * @param sensitiveWordFilter 配置
     * @return 注册的Bean实例
     */
    @Bean
    @DependsOn("sensitiveWordFilter")
    public SensitiveWordExecutor sensitiveWordExecutor(SensitiveWordFilter sensitiveWordFilter) {
        return new SensitiveWordExecutor(sensitiveWordFilter);
    }

    /**
     * 初始化切面对象，并且注入spring容器
     *
     * @param sensitiveWordExecutor 配置
     * @return 注册的Bean实例
     */
    @Bean
    @DependsOn("sensitiveWordExecutor")
    public SensitiveWordReplaceAspect sensitiveWordReplaceAspect(SensitiveWordExecutor sensitiveWordExecutor) {
        return new SensitiveWordReplaceAspect(sensitiveWordExecutor);
    }
}
