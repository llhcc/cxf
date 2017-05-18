package com.llh.dubbo.extension;

@Test(value = "")
public @interface Test {

	String value();
	
	String value1() default "";
}
