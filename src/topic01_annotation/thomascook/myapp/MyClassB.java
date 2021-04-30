package topic01_annotation.thomascook.myapp;

import topic01_annotation.thomascook.thirdparty.ThirdPartyLibrary.Summer;

@Summer(value = 10000)
public class MyClassB {

	@Summer(value = 2000)
	public String field01;

	@Summer(value = 300)
	public String field02;		
	
	@Summer(value = 40)
	public void method01() {
	}

	@Summer(value = 5)
	public void method02() {
	}
}
