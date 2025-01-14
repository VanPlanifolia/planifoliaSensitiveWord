package van.planifolia.sensitiveword.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 地址脱敏
 * @author yhq
 * @date 2021年9月7日 08点51分
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = van.planifolia.sensitiveword.strategy.SensitiveAddress.class, pattern = "(.{5}).+(.{4})",replaceChar = "$1*****$2")
@JacksonAnnotationsInside
public @interface SensitiveAddress {

}
