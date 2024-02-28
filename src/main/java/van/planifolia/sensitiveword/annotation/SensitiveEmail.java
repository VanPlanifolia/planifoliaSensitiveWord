package van.planifolia.sensitiveword.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 邮箱脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = van.planifolia.sensitiveword.strategy.SensitiveEmail.class,pattern = "(\\w+)\\w{3}@(\\w+)",replaceChar = "$1***@$2")
@JacksonAnnotationsInside
public @interface SensitiveEmail {

}
