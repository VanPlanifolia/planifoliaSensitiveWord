package van.planifolia.sensitiveword.strategy;


import van.planifolia.sensitiveword.enums.SensitiveDefaultLengthEnum;
import van.planifolia.sensitiveword.util.SensitiveInfoUtils;

/**
 * 密码脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitivePassword implements IStrategy {

    @Override
    public String desensitization(String password,int begin,int end) {
        if(begin != SensitiveDefaultLengthEnum.PASSWORD.getBegin() && begin !=0){
            return SensitiveInfoUtils.password(password,begin);
        }
        return SensitiveInfoUtils.password(password, SensitiveDefaultLengthEnum.PASSWORD.getBegin());
    }

}
