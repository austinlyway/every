package com.lyways.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <pre>
 * 
 */
public class AnnotatedProperty
{

	private String name;

	private boolean isField;

	private Field field;

	private Method getterMethod;

	private Method setterMethod;

	private AnnotatedProperty parentAnnotatedProperty;

	/**
	 * 所属类的Class类型
	 */
	private Class<?> classType;
	/**
	 * Construct Property with Field.
	 * 
	 * @param field field
	 */
	public AnnotatedProperty(Field field, Class<?> classType)
	{
		this.field = field;
		this.classType = classType;
		this.field.setAccessible(true);
		isField = true;
		name = field.getName();
	}

	/**
	 * @return the getterMethod
	 */

	public Method getGetterMethod()
	{
		return getterMethod;
	}

	/**
	 * @param getterMethod the getterMethod to set
	 */
	public void setGetterMethod(Method getterMethod)
	{
		this.getterMethod = getterMethod;
	}

	/**
	 * @return the setterMethod
	 */

	public Method getSetterMethod()
	{
		return setterMethod;
	}

	/**
	 * @param setterMethod the setterMethod to set
	 */
	public void setSetterMethod(Method setterMethod)
	{
		this.setterMethod = setterMethod;
	}

	/**
	 * Construct property with pair getter/setter method.
	 * 
	 * @param getterMethod	getter method
	 * @param setterMethod	setter method
	 */
	public AnnotatedProperty(Method getterMethod, Method setterMethod, Class<?> classType)
	{
		this.getterMethod = getterMethod;
		this.setterMethod = setterMethod;
		this.classType =classType;
		isField = false;
		this.name = getterMethod.getName().substring(3);
		this.name = name.substring(0, 1).toLowerCase() + name.substring(1);
		this.getterMethod.setAccessible(true);
		if (setterMethod != null)
		{
			this.setterMethod.setAccessible(true);
		}
	}

	public Field getField()
	{
		if(this.field == null)
		{
			this.field = getField(this.classType, this.name);
		}
		return this.field;
	}
	private Field getField(Class clazz, String name)
	{
		Field field = null;
		try
		{
			field = clazz.getDeclaredField(name);
		}
		catch (NoSuchFieldException e)
		{
			field = null;
			if(clazz.getSuperclass()!=null)
			{
				Class clsSup = clazz.getSuperclass();
				field = getField(clsSup, name);
			}
		}
		return field;
	}
	/**
	 * Get property value of object instance with reflection mechanism.
	 * 
	 * @param instance	object instance.
	 * @return
	 */
	public Object getValue(Object instance)
	{
		// check parameter
		if (instance == null)
		{
			return null;
		}

		// get the property value
		Object value = null;
		try
		{
			if (isField)
			{
				value = this.field.get(instance);
			}
			else
			{
				value = this.getterMethod.invoke(instance);
			}
		}
		catch (Exception ex)
		{
			System.out.println(instance);
			ex.printStackTrace();
		}
		return value;
	}

	/**
	 * Set property value of object instance with reflection mechanism.
	 * 
	 * @param instance	object instance
	 * @param value		value
	 */
	public void setValue(Object instance, Object value)
	{
		if (instance == null)
		{
			return;
		}
		try
		{
			if (isField)
			{
				this.field.set(instance, value);
			}
			else
			{
				if (this.setterMethod == null)
				{
					throw new IllegalArgumentException("Not Found setter Property (" + getFullName() + ")");
				}
				this.setterMethod.invoke(instance, value);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Return declared annotation instance of Class.
	 * 
	 * @param <A> annotation class type
	 * @param annotationClass	annotation class
	 * @return declared annotation instance
	 */
	public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
	{
		if (isField)
		{
			return field.getAnnotation(annotationClass);
		}
		return getterMethod.getAnnotation(annotationClass);
	}

	/**
	 * Return property name.
	 * 
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Return property Type.
	 * 
	 * @return
	 */
	public Class<?> getType()
	{
		if (isField)
		{
			return field.getType();
		}
		return getterMethod.getReturnType();
	}

	private String getFullName()
	{
		String result = name;
		if (isField && field != null)
		{
			result = field.toString() + " name";
		}
		else
		{
			if (this.setterMethod != null)
			{
				result = setterMethod.toString() + " name";
			}
			else
			{
				result = getterMethod.toString() + " name";
			}
		}
		return result;
	}

	public AnnotatedProperty getParentAnnotatedProperty() {
		return parentAnnotatedProperty;
	}

	public void setParentAnnotatedProperty(AnnotatedProperty parentAnnotatedProperty) {
		this.parentAnnotatedProperty = parentAnnotatedProperty;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}
}
