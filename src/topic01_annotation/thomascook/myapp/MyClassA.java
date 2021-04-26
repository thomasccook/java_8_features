package topic01_annotation.thomascook.myapp;

import topic01_annotation.thomascook.thirdparty.ThirdPartyLibrary;
import topic01_annotation.thomascook.thirdparty.ThirdPartyLibrary.Summer;

@Summer(value = 1000000000)
public class MyClassA {

	@Summer(value = 200000000)
	public String field01;

	@Summer(value = 30000000)
	public String field02;		
			

	@Summer(value = 4000000)
	public void method01() {
	}

	@Summer(value = 500000)
	public void method02() {
	}

}
