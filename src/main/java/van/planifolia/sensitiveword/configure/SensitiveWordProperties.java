package van.planifolia.sensitiveword.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author Van.Planifolia
 * @Date 2024/2/26
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "sw.conf")
@Data
public class SensitiveWordProperties {
    private String dictPath;
}
