package van.planifolia.sensitiveword.strategy;


import van.planifolia.sensitiveword.enums.SensitiveDefaultLengthEnum;
import van.planifolia.sensitiveword.util.SensitiveInfoUtils;

/**
 * 默认脱敏策略--脱敏
 * @author yhq
 * @date 2021年9月6日 13点30分
 */
public class DefaultSensitiveStrategy implements IStrategy{

    @Override
    public String desensitization(String source, int begin, int end) {
        return SensitiveInfoUtils.password(source, SensitiveDefaultLengthEnum.DEFAULT_STRATEGY.getBegin());
    }
}
