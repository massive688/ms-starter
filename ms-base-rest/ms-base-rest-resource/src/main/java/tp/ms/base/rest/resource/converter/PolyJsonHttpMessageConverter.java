package tp.ms.base.rest.resource.converter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.AssertUtils;
import tp.ms.common.bean.vo.IChildVO;


public class PolyJsonHttpMessageConverter //
        extends FastJsonHttpMessageConverter {
	
	static final Logger logger = LoggerFactory.getLogger(PolyJsonHttpMessageConverter.class);
   
	@Override
    public Object read(Type type, //
                       Class<?> contextClass, //
                       HttpInputMessage inputMessage //
    ) throws IOException, HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();
        /*
         * 通过type contextClass得到要转换的类型 JavaType
         * 
         * 通过JavaType 得到 elementType 或class
         * 
         * 判断elementType/class 是否继续于 AbstractPolyVO 
         * 
         * 为true 则执行自定义处理
         * 
         * 为false 则执行原有的fastjson 转换
         */
        JavaType javaType = getJavaType(type, contextClass);
        Class<?> contentType;
        if(javaType instanceof SimpleType) {
        	contentType = javaType.getRawClass();
        }else {
        	contentType = javaType.getContentType().getRawClass();
        }
        if(AbstractPolyVO.class.isAssignableFrom(contentType)) {
			try {
				if(javaType  instanceof ArrayType || javaType  instanceof CollectionLikeType) {
	        		JSONArray context = JSON.parseObject(in, JSONArray.class, getFastJsonConfig().getFeatures());
	        		return converterArrayCollectionPoly(javaType, context);
	        	}else if(javaType  instanceof MapLikeType || javaType  instanceof SimpleType) {
	            	JSONObject context = JSON.parseObject(in, JSONObject.class, getFastJsonConfig().getFeatures());
	            	return converterSinglePoly(javaType, context);
	        	}else {
	        		//放在这看的做参考
//	            	JSONObject context = JSON.parseObject(in, JSONObject.class, getFastJsonConfig().getFeatures());
	        	}
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			}
        }
    	return super.read(type, contextClass, inputMessage);
    }
    

    private AbstractPolyVO converterSinglePoly(JavaType javaType, JSONObject context) throws InstantiationException, IllegalAccessException {
    	if(javaType  instanceof MapLikeType) {
//    		 if (type instanceof ParameterizedType) {
//	            ParameterizedType parameterizedType = (ParameterizedType) type;
//	            Type keyType = parameterizedType.getActualTypeArguments()[0];
//	            Type valueType = parameterizedType.getActualTypeArguments()[1];
//
//	            if (String.class == keyType) {
//	                return parseMap(parser, (Map<String, Object>) map, valueType, fieldName);
//	            } else {
//	                return parseMap(parser, map, keyType, valueType, fieldName);
//	            }
//	        } else {
//	            return parser.parseObject(map, fieldName);
//	        }
		}else if(javaType  instanceof SimpleType)  {
			Class<?> clazz = javaType.getRawClass();
			AbstractPolyVO povo = (AbstractPolyVO) clazz.newInstance();
			Class<?> parentClass = povo.getParentClass();
			MajorBaseVO pvo = (MajorBaseVO) JSON.toJavaObject(context.getJSONObject("parent"), parentClass);
			povo.setParentVO(pvo);
			JSONObject childes = context.getJSONObject("childes");
			Iterator<Entry<String, Object>> it = childes.entrySet().iterator();
			
			for(;it.hasNext();) {
				Entry<String, Object> child = it.next();
				String className = child.getKey();
				JSONArray valueArray = (JSONArray) child.getValue();
				if(povo.cntainerClass(className)) {
					Class<? extends IChildVO> childClass = povo.getChildClass(className);
					AssertUtils.notNull(childClass, "key mapper error , 请检查子表传入的 Key值");
	    			ChildBaseVO[] childvos = (ChildBaseVO[]) Array.newInstance(childClass, valueArray.size());
					for(int j=0 ; j<valueArray.size(); j ++) {
						JSONObject childValue = valueArray.getJSONObject(j);
						childvos[j] = (ChildBaseVO) JSON.toJavaObject(childValue, childClass);
					}
					povo.setChildren(childClass, childvos);
				}
			}
			return povo;
		}
		return null;
	}


	private Object converterArrayCollectionPoly(JavaType javaType, JSONArray context) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, InstantiationException, IllegalAccessException {
		int length = context.size();
		Class<?> componentType = javaType.getContentType().getRawClass();
		List<AbstractPolyVO> list = new ArrayList<AbstractPolyVO>();
		for(int i=0; i<length; i++) {
			list.add(converterSinglePoly(
					TypeFactory.defaultInstance().constructType(componentType), 
					context.getJSONObject(i)
					));		
		}
		if(javaType instanceof ArrayType) {
			Object[] objArray = (Object[]) Array.newInstance(componentType, length);
			return  list.toArray(objArray);
		}
		return list;
	}


	private ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType contextType) {
		ResolvableType resolvedType;
		if (contextType.hasGenerics()) {
			resolvedType = ResolvableType.forType(typeVariable, contextType);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}

		ResolvableType superType = contextType.getSuperType();
		if (superType != ResolvableType.NONE) {
			resolvedType = resolveVariable(typeVariable, superType);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}
		for (ResolvableType ifc : contextType.getInterfaces()) {
			resolvedType = resolveVariable(typeVariable, ifc);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}
		return ResolvableType.NONE;
	}
	
	
	private JavaType getJavaType(Type type, //
            Class<?> contextClass){
		ArrayType resolv = null;
        if (contextClass != null) {
			ResolvableType resolvedType = ResolvableType.forType(type);
			if (type instanceof TypeVariable) {
				ResolvableType resolvedTypeVariable = resolveVariable(
						(TypeVariable<?>) type, ResolvableType.forClass(contextClass));
				if (resolvedTypeVariable != ResolvableType.NONE) {
					return TypeFactory.defaultInstance().constructType(resolvedTypeVariable.resolve());
				}
			}
			else if (type instanceof ParameterizedType && resolvedType.hasUnresolvableGenerics()) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?>[] generics = new Class<?>[parameterizedType.getActualTypeArguments().length];
				Type[] typeArguments = parameterizedType.getActualTypeArguments();
				for (int i = 0; i < typeArguments.length; i++) {
					Type typeArgument = typeArguments[i];
					if (typeArgument instanceof TypeVariable) {
						ResolvableType resolvedTypeArgument = resolveVariable(
								(TypeVariable<?>) typeArgument, ResolvableType.forClass(contextClass));
						if (resolvedTypeArgument != ResolvableType.NONE) {
							generics[i] = resolvedTypeArgument.resolve();
						}
						else {
							generics[i] = ResolvableType.forType(typeArgument).resolve();
						}
					}
					else {
						generics[i] = ResolvableType.forType(typeArgument).resolve();
					}
				}
				return TypeFactory.defaultInstance().constructType(ResolvableType.
						forClassWithGenerics(resolvedType.getRawClass(), generics).getType());
			}else if(type instanceof GenericArrayType) {
				GenericArrayType genericArrayType = (GenericArrayType) type;
				Type typeGene = genericArrayType.getGenericComponentType();
				if (typeGene instanceof TypeVariable) {
					TypeVariable<?> typevar = (TypeVariable<?>) typeGene;
					
					ResolvableType resolvedTypeVariable = resolveVariable(
							typevar, ResolvableType.forClass(contextClass));
					
					
					if (resolvedTypeVariable != ResolvableType.NONE) {
					}
					return TypeFactory.defaultInstance().constructArrayType(resolvedTypeVariable.resolve());
				}else {
					return resolv;
				}
			}
		}
		return TypeFactory.defaultInstance().constructType(type);
	}
}
