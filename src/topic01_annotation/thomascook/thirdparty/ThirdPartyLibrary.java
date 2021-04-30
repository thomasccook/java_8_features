package topic01_annotation.thomascook.thirdparty;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

// For example ... Hibernate, Spring, Jackson, JUnit, etc.
public class ThirdPartyLibrary { 
	
	///////////////////////////////
	// Annotation

	@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Summer {
		long value() default 0;
	}

	
	///////////////////////////////
	// Add all the annotations together.
	
	public void process() {
		long sum = processPackage("topic01_annotation.thomascook.myapp");
		prn("Final Sum: " + sum);
	}
	
	public long processPackage(String pkg) {
		try {
			long sum = 0;
	        List<Class<?>> classes = getClassesForPackage(pkg);
	        for(Class c : classes){
	            sum += processClass(c);
	        }			
			return sum;	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public long processClass(Class clazz) throws Exception {
		prn("Scanning " + clazz.getName());
		
		long sum = 0;		
		Annotation[] annotations = clazz.getAnnotations();		
		for (Annotation annotation : annotations) {	
			if (annotation instanceof Summer) {
				Summer summer = (Summer) annotation;
				sum += summer.value();				
			}
		}		
		sum += processFields(clazz);
		sum += processMethods(clazz);
		return sum;
	}
	
	public long processFields(Class clazz) throws Exception {
		long sum = 0;	
		Field[] fields = clazz.getDeclaredFields();
		for (Field field: fields) {
			prn(field.getName());
			Annotation[] annotations = field.getAnnotations();		
			for (Annotation annotation : annotations) {
				Summer summer = (Summer) annotation;
				sum += summer.value();
			}			
		}
		return sum;
	}

	
	public long processMethods(Class clazz) throws Exception {
		long sum = 0;	
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method: methods) {
			prn(method.getName());
			Annotation[] annotations = method.getAnnotations();		
			for (Annotation annotation : annotations) {
				Summer summer = (Summer) annotation;
				sum += summer.value();
			}			
		}
		return sum;
	}
	

	// https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
	public static List<Class<?>> getClassesForPackage(final String pkgName) throws IOException, URISyntaxException {
	    final String pkgPath = pkgName.replace('.', '/');
	    final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
	    final ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();

	    Path root;
	    if (pkg.toString().startsWith("jar:")) {
	        try {
	            root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
	        } catch (final FileSystemNotFoundException e) {
	            root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
	        }
	    } else {
	        root = Paths.get(pkg);
	    }

	    final String extension = ".class";
	    try (final Stream<Path> allPaths = Files.walk(root)) {
	        allPaths.filter(Files::isRegularFile).forEach(file -> {
	            try {
	                final String path = file.toString().replace('/', '.');
	                final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
	                allClasses.add(Class.forName(name));
	            } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
	            }
	        });
	    }
	    return allClasses;
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