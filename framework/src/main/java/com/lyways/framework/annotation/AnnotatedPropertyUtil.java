package com.lyways.framework.annotation;

import java.lang.reflect.Field;

/**
 * <pre>
 */
public class AnnotatedPropertyUtil
{
	/**
	 * Set property value of domain value with DB column name. It can support embedded/embeddedId annotation.
	 * 
	 * @param annotatedClass	annotatedClass of domain model.
	 * @param entity		domain model
	 * @param property		property object, it may be Field object or string. such as AuditModel.name.
	 * @param value 		column value.
	 * @return
	 * @throws Exception 
	 */
	public static void setPropertyValue(AnnotatedClass annotatedClass, Object entity, Object property, Object value) throws Exception
	{
		// Check to see if the property is embedded object.
		if (property instanceof AnnotatedProperty)
		{
			// It is simple property without embedded annotation.
			((AnnotatedProperty) property).setValue(entity, value);
		}
		else
		{
			// It is a embedded object. for example, capTypePK.serviceProviderCode
			String[] properties = ((String) property).split("\\.");
			Object subEntity = entity;
			//Loop for each level 
			for (int i = 0; i < properties.length; i++)
			{
				// check the annotation
				if (annotatedClass == null)
				{
					annotatedClass = AnnotationConfigurationUtil.getClassAnnotation(subEntity.getClass());
				}
				AnnotatedProperty annotatedProperty = annotatedClass.getAnnotatedPropertyByName(properties[i]);
				//Null check
				if (annotatedProperty == null)
				{
					throw new Exception("Invalid property definition in class " + entity.getClass() + " " + property);
				}
				//If it is last one
				if (i == properties.length - 1)
				{
					annotatedProperty.setValue(subEntity, value);
					break;
				}
				//it has a parent model
				else
				{
					Object newSubEntity = annotatedProperty.getValue(subEntity);
					//If new sub entity is null, then new its instance.
					if (newSubEntity == null)
					{
						Class<?> subClass = annotatedProperty.getType();
						newSubEntity = subClass.newInstance();
						annotatedProperty.setValue(subEntity, newSubEntity);
					}
					subEntity = newSubEntity;
				}
				annotatedClass = null;
			}
		}
	}

	/**
	 * Get property value with property. 
	 * It support embedded annotation.
	 *
	 * @param entity			Object instance
	 * @param property			Property definition.
	 * @return
	 */
	public static Object getPropertyValue(Object entity, AnnotatedProperty property)
	{
		if(entity == null)
		{
			return null;
		}
		Object propertyValue = null;
		// Check to see if the property is embedded object.
		if (property.getParentAnnotatedProperty() == null
				|| property.getClassType() == entity.getClass())
		{
			// It is simple property without embedded annotation.
			propertyValue = property.getValue(entity);
		}
		else
		{
			AnnotatedProperty parentAnnotatedProperty = property.getParentAnnotatedProperty();
			//获取当前属性所对应的类的值.
			Object parentPropertyValue = parentAnnotatedProperty.getValue(entity);
			getPropertyValue(parentPropertyValue, property);
		}
		return propertyValue;
	}


	/**
	 * 
	 * Return property value.
	 *
	 * @param entity	domain model
	 * @param field		field object
	 * @return
	 */
	public static Object getPropertyValue(Object entity, Field field)
	{
		if (entity == null || field == null)
		{
			return null;
		}

		Class<?> fieldType = entity.getClass();
		AnnotatedClass annotatedClass = AnnotationConfigurationUtil.getClassAnnotation(fieldType);
		AnnotatedProperty annotatedProperty = annotatedClass.getAnnotatedPropertyByName(field.getName());
		Object returnObj = null;
		if (annotatedProperty != null)
		{
			returnObj = annotatedProperty.getValue(entity);
		}
		return returnObj;
	}

}
