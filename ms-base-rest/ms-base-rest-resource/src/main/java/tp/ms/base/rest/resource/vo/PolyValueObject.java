package tp.ms.base.rest.resource.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import tp.ms.common.bean.vo.CircularlyBaseVO;

@SuppressWarnings("serial")
public abstract class PolyValueObject implements Serializable{
	public PolyValueObject() {
		super();
	}

	@SuppressWarnings("rawtypes")
	Map<Class, Integer> clzIndex = new HashMap<>();  

	


	/**
	 * 获得母表VO对象。
	 * 
	 * @return 母表VO对象
	 */
	@JSONField(serialize=false)
	public abstract CircularlyBaseVO getParentVO();


	/**
	 * 设置母表VO对象。
	 * 
	 * @param parent
	 *            母表VO对象
	 */
	public abstract void setParentVO(CircularlyBaseVO parent);

}
