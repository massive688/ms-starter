package tp.ms.common.data.mybatis.annotation;

public interface SqlSessionKey {
	static final String sqlSessionFactoryKey = "Session";
	
	static final String DEFAULT = "default" + sqlSessionFactoryKey;	
	static final String CS6304 = "cs6304" + sqlSessionFactoryKey;
	static final String AMSFLOW = "amsflow" + sqlSessionFactoryKey;
	static final String NC6302 = "nc6302" + sqlSessionFactoryKey;
	static final String MPROCESS = "mprocess" + sqlSessionFactoryKey;
	static final String HXTEST = "hxtest" + sqlSessionFactoryKey;
	
	
}
