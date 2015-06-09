package org.salt.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target(ElementType.METHOD)
public @interface Security {
	boolean validate() default true;
}
