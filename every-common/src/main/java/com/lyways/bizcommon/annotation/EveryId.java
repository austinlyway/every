package com.lyways.bizcommon.annotation;

import com.lyways.bizcommon.SequenceRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EveryId {
    SequenceRule value();
}
