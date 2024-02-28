package van.planifolia.sensitiveword.strategy;


import van.planifolia.sensitiveword.enums.SensitiveDefaultLengthEnum;
import van.planifolia.sensitiveword.util.SensitiveInfoUtils;

/**
 * 电子邮箱脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveEmail implements IStrategy {

    @Override
    public String desensitization(String email,int begin,int end) {
        if(begin != SensitiveDefaultLengthEnum.EMAIL.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.email(email,begin);
        }
        return SensitiveInfoUtils.email(email, SensitiveDefaultLengthEnum.EMAIL.getBegin());
    }

}
