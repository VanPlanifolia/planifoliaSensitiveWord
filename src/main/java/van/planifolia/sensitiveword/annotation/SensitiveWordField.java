package van.planifolia.sensitiveword.annotation;

import java.lang.annotation.*;

/**
 * 用于实现风险词汇字段脱敏处理的非法
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveWordField {
}
