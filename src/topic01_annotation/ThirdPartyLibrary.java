package topic01_annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import topic01_annotation.MyProgram.Class01;
import topic01_annotation.MyProgram.Class02;

// For Example ... Spring, Hibernate, etc.
public class ThirdPartyLibrary { 
	
	///////////////////////////////
	// Defining Annotations

	@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Ann01 {
		String metadata01() default "www";
		String metadata02() default "xxx";
	}

	@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Ann02 {
		String metadata03() default "yyy";
		String metadata04() default "zzz";
	}	
	
	///////////////////////////////
	// Getting Annotation Metadata	
	
	public void process() {
		
		// Get Annotation Data off of Class01
		getMetadataOnClass( Class01.class, 			   "ann01", "metadata01");
		getMetadataOnField( Class01.class, "field01",  "ann02", "metadata03");
		getMetadataOnMethod(Class01.class, "method01", "ann01", "metadata02");

		// Get Annotation Data off of Class02
		getMetadataOnClass( Class02.class,             "ann02", "metadata04");
		getMetadataOnField( Class02.class, "field01",  "ann01", "metadata02");
		getMetadataOnMethod(Class02.class, "method01", "ann02", "metadata03");
		
//getMetadataOnField("scan for classes", "scan for fields", "expected annotation", "expected metadata");
		
	}

	/////////////////////////////////////////
	// Getting Values off of annotations

	private void getMetadataOnClass(Class clazz, String annName, String annMetafield) {
		String value = getValue(clazz, annName, annMetafield);
		prn(clazz.getSimpleName() + " : " + annName + " : " + annMetafield + " = " + value);
	}
	
	private void getMetadataOnField(Class clazz, String strField, String annName, String annMetafield) {
		try {
			Field field = clazz.getField(strField);
			String value = getValue(field, annName, annMetafield);			
			prn(clazz.getSimpleName() + " : " + strField + " : " + annName + " : " + annMetafield + " = " + value);
		} catch (Exception e) {
			
		}
	}

	private void getMetadataOnMethod(Class clazz, String strMethod, String annName, String annMetafield) {
		try {
			Method method = clazz.getMethod(strMethod, new Class[] {});
			String value = getValue(method, annName, annMetafield);			
			prn(clazz.getSimpleName() + " : " + strMethod + " : " + annName + " : " + annMetafield + " = " + value);
		} catch (Exception e) {
			
		}
	}
	
	// Use Java Reflection to scan an AnnotatedElement for annotations
	private String getValue(AnnotatedElement annElement, String annName, String annMetafield) {

		Annotation[] annotations = annElement.getAnnotations();
		for (Annotation annotation : annotations) {
		
			if (annName.equals("ann01") && annotation instanceof Ann01) {
				Ann01 ann01 = (Ann01) annotation;
				switch (annMetafield) {
				case "metadata01": return ann01.metadata01();
				case "metadata02": return ann01.metadata02();
				}
			}
			
			if (annName.equals("ann02") && annotation instanceof Ann02) {
				Ann02 ann = (Ann02) annotation;
				switch (annMetafield) {
				case "metadata03": return ann.metadata03();
				case "metadata04": return ann.metadata04();
				}
			}
		}
		return null;
	}
	
	/////////////////////////////////////////
	// Support Functions
	
	public void prn(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		(new ThirdPartyLibrary()).process();
	}

}