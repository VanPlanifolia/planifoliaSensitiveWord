package van.planifolia.sensitiveword.controller;

import lombok.Data;
import van.planifolia.sensitiveword.annotation.SensitiveWordField;

/**
 * @Description
 * @Author Van.Planifolia
 * @Date 2024/2/27
 * @Version 1.0
 */
@Data
public class A {
    @SensitiveWordField
    private String text;
}
