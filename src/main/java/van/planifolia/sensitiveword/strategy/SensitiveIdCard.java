package van.planifolia.sensitiveword.strategy;


import van.planifolia.sensitiveword.enums.SensitiveDefaultLengthEnum;
import van.planifolia.sensitiveword.util.SensitiveInfoUtils;

/**
 * 身份证号脱敏
 * @author yhq
 * @date 2021年9月6日 16点13分
 **/
public class SensitiveIdCard implements IStrategy {

    @Override
    public String desensitization(String idCardNum,int begin ,int end) {
        if(begin != SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin() && begin !=0 &&
                end != SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd() && end !=0){
            return SensitiveInfoUtils.idCardNum(idCardNum,begin,end);
        }
        return SensitiveInfoUtils.idCardNum(idCardNum, SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin(), SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd());
    }

}
