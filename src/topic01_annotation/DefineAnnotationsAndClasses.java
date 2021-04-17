package topic01_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DefineAnnotationsAndClasses {

	
	
	///////////////////////////////
	//Annotations

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
	
	
	////////////////////////////
	//Classes Using Annotations

	@Ann01(metadata01 = "aaa", metadata02 = "bbb")
	@Ann02(metadata03 = "ccc", metadata04 = "ddd")
	public class Class01 {

		@Ann01(metadata01 = "eee", metadata02 = "fff")
		@Ann02(metadata03 = "ggg", metadata04 = "hhh")
		public String field01;

		@Ann01(metadata01 = "iii", metadata02 = "jjj")
		@Ann02(metadata03 = "kkk", metadata04 = "lll")
		public void method01() {
		}
	}

	@Ann01
	@Ann02
	public class Class02 {

		@Ann01
		@Ann02
		public String field01;

		@Ann01
		@Ann02
		public void method01() {
		}
	}
	
	
}
