package com.lyways.framework.reflect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils
{
	private static final Log logger = LogFactory.getLog(GenericsUtils.class);

	public static Class<?> getSuperClassType(Class<?> clazz)
	{
		return getSuperClassGenericType(clazz, 0);
	}

	public static Class<?> getSuperClassGenericType(Class<?> clazz, int index)
	{
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType))
		{
			logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if ((index >= params.length) || (index < 0))
		{
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class))
		{
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	public static Class<?> getInterfaceGenricType(Class<?> clazz, Class<?> interfaceClazz, int index)
	{
		Type targetType = null;
		Type[] genType = clazz.getGenericInterfaces();
		for (Type type : genType)
		{
			if (type instanceof ParameterizedType || ((ParameterizedType) type).getRawType() == interfaceClazz)
			{
				targetType = type;
			}
		}
		if (targetType == null)
		{
			logger.debug("Cannot find interface for" + interfaceClazz);
			return Object.class;
		}
		Type[] params = ((ParameterizedType) targetType).getActualTypeArguments();
		if ((index >= params.length) || (index < 0))
		{
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class))
		{
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class<?>) params[index];
	}
}
