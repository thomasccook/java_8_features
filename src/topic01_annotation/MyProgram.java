package topic01_annotation;

import topic01_annotation.ThirdPartyLibrary.Ann01;
import topic01_annotation.ThirdPartyLibrary.Ann02;

public class MyProgram {


	
	@Ann01(metadata01 = "Employee", metadata02 = "bbb")
	@Ann02(metadata03 = "ccc", metadata04 = "ddd")
	public class Class01 {

		@Ann01(metadata01 = "id", metadata02 = "fff")
		@Ann02(metadata03 = "ggg", metadata04 = "hhh")
		public String field01;

		@Ann01(metadata01 = "cmployeed_name", metadata02 = "jjj")
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
