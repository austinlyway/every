package com.lyways.framework.annotation;


import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 */
public class AnnotationConfigurationUtil
{
	/** The CGLIB class separator character "$$" */
	private static final String CGLIB_CLASS_SEPARATOR = "$$";

	private static Map<Class<?>, AnnotatedClass> classAnnotations = new HashMap<Class<?>, AnnotatedClass>();

	/**
	 * Get class annotation.
	 * 
	 * @param clazz class 
	 * @return
	 */
	public static AnnotatedClass getClassAnnotation(final Class<?> clazz)
	{
		Class<?> theClass = clazz;
		if (isCGLIBClass(clazz))
		{
			theClass = clazz.getSuperclass();
		}
				
		AnnotatedClass annotatedClass = classAnnotations.get(theClass);
		if (annotatedClass == null)
		{
			annotatedClass = addAnnotatedClass(theClass);
		}
		return annotatedClass;
	}

	/**
	 * Add annotated class into configuration.
	 * 
	 * @param clazz class
	 * @return
	 */
	public static AnnotatedClass addAnnotatedClass(final Class<?> clazz)
	{
		AnnotatedClass classAnnotation = classAnnotations.get(clazz);
		if (classAnnotation == null)
		{
			classAnnotation = parseAnnotatedClassWithLock(clazz);
		}
		return classAnnotation;
	}

	/**
	 * Parses the annotated class with lock.
	 *
	 * @param clazz the clazz
	 * @return the annotated class
	 */
	private synchronized static AnnotatedClass parseAnnotatedClassWithLock(final Class<?> clazz)
	{
		parseAnnotatedClass(clazz);
		return classAnnotations.get(clazz);
	}

	/**
	 * Parse class to AnnotatedClass.
	 * 
	 * @param clazz
	 * @return
	 */
	private static AnnotatedClass parseAnnotatedClass(final Class<?> clazz)
	{
		final Annotation[] annotations = clazz.getAnnotations();
		if (annotations == null || annotations.length == 0)
		{
			final AnnotatedClass classAnnotation = new AnnotatedClass(clazz);
			classAnnotations.put(clazz, classAnnotation);
			return classAnnotation;
		}
		AnnotatedClass classAnnotation = null;
		classAnnotation = classAnnotations.get(clazz);
		if (classAnnotation == null)
		{
			// 1. Parse class.
			classAnnotation = new AnnotatedClass(clazz);
			classAnnotations.put(clazz, classAnnotation);

		}
		return classAnnotation;
	}

	private static boolean isCGLIBClass(final Class<?> clazz)
	{
		boolean isCGLIBClass = false;
		if (clazz.getName().indexOf(CGLIB_CLASS_SEPARATOR) != -1)
		{
			isCGLIBClass = true;
		}
		return isCGLIBClass;
	}

}
