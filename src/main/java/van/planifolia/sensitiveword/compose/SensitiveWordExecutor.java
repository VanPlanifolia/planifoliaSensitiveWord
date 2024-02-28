package van.planifolia.sensitiveword.compose;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Description
 * @Author Van.Planifolia
 * @Date 2024/2/26
 * @Version 1.0
 */
public class SensitiveWordExecutor {
   private final SensitiveWordFilter sensitiveWordFilter;

    public SensitiveWordExecutor(SensitiveWordFilter filter) {
        this.sensitiveWordFilter = filter;
    }

    /**
     * @param sourceStr  源字符串
     * @param replaceStr 替换的字符串
     * @return 处理后的字符串信息
     */
    public String processSensitiveWord(String sourceStr, String replaceStr) {
        return sensitiveWordFilter.replaceSensitiveWord(sourceStr, 1, replaceStr);
    }

}
