package loader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import shape.Shape;

public class ShapeLoader {
	
	private String path;
	
	public ShapeLoader(String path) {
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();

			// Step 3: Load the class
			Class<Shape> myClass = (Class<Shape>) myClassLoader.loadClass(path);

			// Step 4: create a new instance of that class
			Object whatInstance = myClass.newInstance();

			String methodParameter = "a quick brown fox";

			// Step 5: get the method, with proper parameter signature.
			// The second parameter is the parameter type.
			// There can be multiple parameters for the method we are trying to
			// call,
			// hence the use of array.

			Method myMethod = myClass.getMethod("demoMethod",
					new Class[] { String.class });

			// Step 6:
			// Calling the real method. Passing methodParameter as
			// parameter. You can pass multiple parameters based on
			// the signature of the method you are calling. Hence
			// there is an array.

			String returnValue = (String) myMethod.invoke(whatInstance,
					new Object[] { methodParameter });

			System.out.println("The value returned from the method is:"
					+ returnValue);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
