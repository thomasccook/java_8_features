package feature03_lambda_expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Example {

	//////////////////////////////////////////
	// Before Java 8
	// Define Function in class
	
	class MyClass {
		public String 	apply1(String str)  { return str.toUpperCase(); }
		public String 	apply2(String str)  { return str + str; }
		public Boolean 	apply3(String str)  { return str.toLowerCase().contains("aaa"); }
	}
	
	public void example01() {
		MyClass myClass = new MyClass();
		String aaa = "aaa";
		prn(myClass.apply1(aaa));
		prn(myClass.apply2(aaa));
		prn(myClass.apply3(aaa)); 
		prn("---");
	}
	
	/*
	 * Output:
	 * 
	 * AAA
	 * aaaaaa
	 * true
	 * ---
	 * 
	 */
	
	
	//////////////////////////////////////////
	// Java 8+
	// Lambda Expressions
	
	// Define the input and output of your functions with specific variables
	@FunctionalInterface interface StringIn_StringOut       		{ String  apply(String str); }	
	@FunctionalInterface interface StringIn_IntegerOut      		{ Integer apply(String str);}	
	@FunctionalInterface interface StringIn_BooleanOut      		{ Boolean apply(String str);}		
	@FunctionalInterface interface StringStringIn_StringOut       	{ String  apply(String str1, String str2); }	
	@FunctionalInterface interface StringStringIn_IntegerOut      	{ Integer apply(String str1, String str2); }	
	@FunctionalInterface interface StringStringIn_BooleanOut      	{ Boolean apply(String str1, String str2); }
	@FunctionalInterface interface StringStringIn_BooleanOut2      	{ Boolean btwYouCanNameThisFunctionWhateverYouWant(String str1, String str2); }
	
	// Define the input and output of your function with generic variables
	@FunctionalInterface interface   T1In_T2Out<T1, T2>       { T2 apply(T1 t1); }
	@FunctionalInterface interface T1T2In_T3Out<T1, T2, T3>   { T3 apply(T1 t1, T2 t2); }	
	
	// Use an existing definition
	// public interface Predicate<T> 	{ public boolean test(T  t); } 
	// public interface BinaryOperator  { public T apply(T x, T y); } 
	// public interface Function 		{ public R apply(T t); }
	
	public void example02() {
		String bbb = "bbb";
		StringIn_StringOut fxnb1 =  (b) -> {return b.toUpperCase();};
		StringIn_StringOut fxnb2 =  (b) -> {return b + b;};
		StringIn_BooleanOut fxnb3 = (b) -> {return b.toLowerCase().contains("bbb");};
		prn(fxnb1.apply(bbb));
		prn(fxnb2.apply(bbb));
		prn(fxnb3.apply(bbb));
		prn("---");
		
		// Generic
		String ccc = "ccc";
		T1In_T2Out<String, String> fxnc1 =  (c) -> {return c.toUpperCase();};
		T1In_T2Out<String, String> fxnc2 =  (c) -> {return c + c;};
		T1In_T2Out<String, Boolean> fxnc3 = (c) -> {return c.toLowerCase().contains("ccc");};
		prn(fxnc1.apply(ccc));
		prn(fxnc2.apply(ccc));
		prn(fxnc3.apply(ccc));
		prn("---");
		
		String ddd = "ddd";
		Function<String, String> fxnd1 = (d) -> {return d.toUpperCase();};
		Function<String, String> fxnd2 = (d) -> {return d + d;};
		Predicate<String> fxnd3 	   = (d) -> {return d.toLowerCase().contains("ddd");};
		prn(fxnd1.apply(ddd));
		prn(fxnd2.apply(ddd));
		prn(fxnd3.test(ddd));
		prn("---");
	}
	
	/*
	 * Output:
	 * 
	 * BBB
	 * bbbbbb
	 * true
	 * ---
	 * CCC
	 * cccccc
	 * true
	 * ---
	 * DDD
	 * dddddd
	 * true
	 * 
	 */
	

	public void example03() {
    	List<String> data = new ArrayList<>();
        data.add("bbbbbb");
        data.add("bbbccc");
        data.add("bbbddd");
        data.add("cccbbb");
        data.add("cccccc");
        data.add("cccddd");  
        data.add("dddbbb");
        data.add("dddccc");
        data.add("dddddd");  
	
        // Functions we defined above
        StringIn_StringOut fxnb1 = (b)			-> {return b.toUpperCase();};
        T1In_T2Out<String, String> fxnc2 = (c)	-> {return c + c;};
        Predicate<String> fxnd3 = (c)			-> {return c.toLowerCase().contains("ddd");};
        
		/////////////////////////////////////////
		// Chaining
        data.stream()
			.map((p) 	 -> fxnb1.apply(p))
			.map((p) 	 -> fxnc2.apply(p))
			.filter((p)  -> fxnd3.test(p))
			.forEach((p) -> {prn(p);});
	}
	/*
	 * Output:
	 * 
	 * BBBDDDBBBDDD
	 * CCCDDDCCCDDD
	 * DDDBBBDDDBBB
	 * DDDCCCDDDCCC
	 * DDDDDDDDDDDD
	 */

	
	//////////////////////////////////////////
	// Supporting functions

	// A more pleasant looking print function
	private void prn(Object obj) {
		System.out.println(obj);
	}		
	
	// Program begins here
	public static void main(String[] args) {
		Example example01 = new Example();
		example01.example01();
		example01.example02();
		example01.example03();
	}		
	
}