package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

public class ApplicationContext {
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();
	
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	public void addBean(String name, Object obj) {
		objTable.put(name, obj);
	}
	
	public ApplicationContext() {
	}

	public void prepareObjectByProperties(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		Context ctx = new InitialContext();
		String key = null;
		String value = null;
		
		for (Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			if (key.startsWith("jndi.")) {
				objTable.put(key, ctx.lookup(value));
			} else {
				System.out.println("value:" + value + ", key:" + key);
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}
	
	public void injectDependency() throws Exception {
		for (String key : objTable.keySet()) {
			if (!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));	// ???
			}
		}
	}

	private void callSetter(Object object) throws Exception {
		Object dependency = null;
		for (Method m : object.getClass().getMethods()) {
			if (m.getName().startsWith("set")) {
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if (dependency != null) {
					m.invoke(object, dependency);
				}
			}
		}
	}

	private Object findObjectByType(Class<?> type) {
		for (Object object : objTable.values()) {
			if (type.isInstance(object)) {
				return object;
			}
		}
		return null;
	}

	public void prepareObjectByAnnotation(String basePackage) throws Exception {
		Reflections reflector = new Reflections(basePackage);
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		for (Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
	}
	
}
