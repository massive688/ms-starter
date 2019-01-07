package tp.ms.base.rest.resource.http;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import tp.ms.common.bean.utils.ObjectUtilms;

@Data
public class Pager {
	

	public Pager() {
	}

	@SuppressWarnings("rawtypes")
	@JSONField(serialize=false)
	PageInfo pageInfo;
	
	public <T> Pager(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
		init();
	}


	int current;
	
	int showNum;
	
	long total;
	
	Object condition;
	
	Object data;

	private void init() {
		current = pageInfo.getPageNum();
		
		showNum = pageInfo.getPageSize();
		
		total = pageInfo.getTotal();
		if(ObjectUtilms.isNotEmpty(pageInfo.getList())) {
			data = pageInfo.getList();
		}else {
			data = new ArrayList<Object>();
		}
	}
}
