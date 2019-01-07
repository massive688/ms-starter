package tp.ms.common.bean.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public abstract class User implements Serializable{
	
	public static final String MASTER_KEY = "_primary_";

	String pkUser;
	
	String pkGroup;
	
	String pkOrg;
	
	String userName;
	
	String userCode;
	
	String userid;

	String nickname;
	
	@JSONField(serialzeFeatures= {
			SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteMapNullValue
			})
	String headPhoto;

	Map<String,Set<String>> dp;
	
	@JSONField(serialize=false)
	public Set<String> getDept() {
		return dp.keySet();
	}
	
	@JSONField(serialize=false)
	public Set<String> getPost() {
		Set<String> result = new HashSet<>();
		Collection<Set<String>> coll = dp.values();
		for(Set<String> col: coll)
			result.addAll(col);
		return result;
	}

}
