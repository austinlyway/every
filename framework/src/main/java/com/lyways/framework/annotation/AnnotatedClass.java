package com.lyways.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 */
public class AnnotatedClass
{
	/** The CGLIB class separator character "$$" */
	private static final String CGLIB_CLASS_SEPARATOR = "$$";
	
	private transient Class<?> clazz;

	/**
	 * The relationship between property name and AnnotatedProperty without embedded class. Map<propertyName,
	 * AnnotatedProperty>
	 */
	private Map<String, AnnotatedProperty> propertyList = new TreeMap<String, AnnotatedProperty>();
	/**
	 * List all properties to be declared with ManyToOne, ManyToMany, OneToMany, OneToOne.
	 */
	private List<AnnotatedProperty> subModelProperties = new ArrayList<AnnotatedProperty>();

	/**
	 * AnnotatedClass Constructor. 
	 *
	 * @param clazz class
	 */
	public AnnotatedClass(Class<?> clazz)
	{
		this.clazz = clazz;
		if (clazz.getName().indexOf(CGLIB_CLASS_SEPARATOR) != -1)
		{
			this.clazz = clazz.getSuperclass();
		}
		// Get all declared getter methods and annotation.
		Method[] publicMethods = clazz.getMethods();
		for (Method method : publicMethods)
		{
			// It is getter method ?
			if (isGetter(method))
			{
				Annotation[] annotations = method.getAnnotations();
				// It is annotated method ?
				if (annotations != null && annotations.length > 0)
				{
					putPropertyInCache(method);
				}
			}
		}
	}
	
	private void putPropertyInCache(Method getterMethod)
	{
		Method setterMethod = getSetterMethod(clazz, getterMethod);
		AnnotatedProperty property = new AnnotatedProperty(getterMethod, setterMethod, clazz);
		propertyList.put(property.getName(), property);
	}

	private boolean isGetter(Method method)
	{
		boolean isGetter = false;
		isGetter = method.getName().startsWith("get")
					&& (method.getParameterTypes() == null
					|| method.getParameterTypes().length == 0);
		return isGetter;
	}

	private Method getSetterMethod(Class<?> clazz, Method getterMethod)
	{
		String setterMethodName = "s" + getterMethod.getName().substring(1);
		Method retMethod = null;
		try
		{
			retMethod = clazz.getMethod(setterMethodName, getterMethod.getReturnType());
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		return retMethod;
	}

	/**
	 * Return class type.
	 *
	 * @return
	 */
	public Class<?> getClazz()
	{
		return clazz;
	}

	/**
	 * 
	 * Return wrapped property object with annotation.
	 *
	 * @param propertyName	property Name
	 * @return
	 */
	public AnnotatedProperty getAnnotatedPropertyByName(String propertyName)
	{
		return propertyList.get(propertyName);
	}

	public <T extends Annotation> List<AnnotatedProperty> getAnnotatedPropertyByAnnotaion(Class<T> clazz){
		List<AnnotatedProperty> annotatedPropertyList = null;
		Iterator<Map.Entry<String, AnnotatedProperty>> allProperty = propertyList.entrySet().iterator();
		while (allProperty.hasNext())
		{
			AnnotatedProperty property = allProperty.next().getValue();
			T t = property.getAnnotation(clazz);
			if(t == null){
				continue;
			}
			if(annotatedPropertyList == null){
				annotatedPropertyList = new ArrayList<>();
			}
			annotatedPropertyList.add(property);
		}
		return annotatedPropertyList;
	}
	/**
	 * 
	 * Return all embedded properties.
	 *
	 * @return
	 */
	public List<AnnotatedProperty> getSubModelProperties()
	{
		return this.subModelProperties;
	}
	/**
	 * 
	 * Return all property list.
	 *
	 * @return
	 */
	public Map<String, AnnotatedProperty> getPropertyList()
	{
		return propertyList;
	}



}
