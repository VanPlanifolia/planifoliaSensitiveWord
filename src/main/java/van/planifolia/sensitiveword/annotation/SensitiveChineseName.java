package van.planifolia.sensitiveword.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 中文姓名脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = van.planifolia.sensitiveword.strategy.SensitiveChineseName.class, pattern = "(?<=.{1}).",replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveChineseName {


}
