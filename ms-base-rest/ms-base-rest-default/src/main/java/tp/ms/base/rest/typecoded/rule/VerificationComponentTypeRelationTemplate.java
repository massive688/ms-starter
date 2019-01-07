package tp.ms.base.rest.typecoded.rule;

import tp.ms.base.rest.resource.service.ace.IRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.base.rest.typecoded.vo.PolyMsBaseBilltypeVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;

public class VerificationComponentTypeRelationTemplate implements IRule<PolyMsBaseBilltypeVO> {

	@Override
	public void process(PolyMsBaseBilltypeVO pvo) throws ADBusinessException {

		MsBaseBilltype mvo = pvo.getParentVO();
		Integer ct = mvo.getComponentType();
		//当组件为0 代表只是单表 
		//单表时 template 模板 columnModuleName 只允许有parent 或者空的存在
		//只有有一个字段出现 childes 就抛出异常
		if(ct == 0) {
			MsBaseBillTemplate[] childrens = pvo.getChildren(MsBaseBillTemplate.class);
			for(MsBaseBillTemplate children : childrens) {
				if((ObjectUtilms.isEmpty(children.getColumnModuleName()))
					|| ObjectUtilms.isEqual("parent", children.getColumnModuleName())) {
					continue;
				}
				throw new ADBusinessException("单表模板 字段所属模型块名 只允许有parent 或者空的存在");
			}
		}else if(ct == 1) {
			
		}
		
		
		
	}

}
