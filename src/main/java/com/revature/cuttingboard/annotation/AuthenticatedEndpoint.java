package com.revature.cuttingboard.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation to mark authenticated enpoints. Used for authAspect to determine which enpoints
 * need authentication. 
 * @author nom.com
 * @since 1.0
 *
 */
@Target(ElementType.METHOD)
public @interface AuthenticatedEndpoint {

}
