package tp.ms.base.rest.typecoded.vo;

import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.IMajorVO;

public class PolyMsBaseBilltypeVO extends AbstractPolyVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void registerChildrenClass() {
		initChildrenClass(new Class[] {
				MsBaseBillCodeRule.class, 
				MsBaseBillTemplate.class
			});
		
	}

	@Override
	public Class<? extends IMajorVO> getParentClass() {
		return MsBaseBilltype.class;
	}

	@Override
	public MsBaseBilltype getParentVO() {
		return (MsBaseBilltype)this.getParent();
	}

}
