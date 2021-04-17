package topic01_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import topic01_annotation.DefineAnnotationsAndClasses.Ann01;
import topic01_annotation.DefineAnnotationsAndClasses.Ann02;
import topic01_annotation.DefineAnnotationsAndClasses.Class01;
import topic01_annotation.DefineAnnotationsAndClasses.Class02;

public class GetDataFromAnnotations { 
	public void process() {
		
		// Get Annotation Data off of Class01
		getMetadataOnClass( Class01.class, 			   "ann01", "metadata01");
		getMetadataOnField( Class01.class, "field01",  "ann02", "metadata03");
		getMetadataOnMethod(Class01.class, "method01", "ann01", "metadata02");

		// Get Annotation Data off of Class02
		getMetadataOnClass( Class02.class,             "ann02", "metadata04");
		getMetadataOnField( Class02.class, "field01",  "ann01", "metadata02");
		getMetadataOnMethod(Class02.class, "method01", "ann02", "metadata03");
		
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
	
	public void prn(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		(new GetDataFromAnnotations()).process();
	}

}