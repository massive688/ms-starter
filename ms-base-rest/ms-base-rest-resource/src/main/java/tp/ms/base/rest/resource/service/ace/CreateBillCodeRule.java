package tp.ms.base.rest.resource.service.ace;

import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.result.ResultSupport;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.ReflectionUtils;
import tp.ms.common.bean.utils.ReplaceNull;

public class CreateBillCodeRule<T extends MajorBaseVO> implements IRule<T>{

	//单据类型的属性标识
	String attr;

	public CreateBillCodeRule(String attr) {
		if(ObjectUtilms.isEmptyWithTrim(attr)) {
			AExceptionUtils.unSupported("["+attr+"]:传入属性不可空");
		}
		this.attr = attr;
	}


	@Override
	public void process(T vo) throws ADBusinessException {
		String billtype = null;
		if(ObjectUtilms.isNotEmpty(vo)) {
			try {
				//获取属性标识的单据类型值
//					Field f = ReflectionUtils.getDeclaredField(vo.getParent(), attr);
				billtype = ReplaceNull.string(ReflectionUtils.getFieldValue(vo, attr));
				//通过单据类型得到单据编码 因为单据类型内部有对应的编码生成规则
				vo.setCode(SpringContextHolder.getBean(BeanHelperEnv.class).generateBatchCodes(billtype));
			} catch (SecurityException | IllegalArgumentException e) {
				throw new ADBusinessException(ResultSupport.dataError(attr).setMessage("["+attr+"]:传入属性不可用"));
			}
		}
	}
	
}
