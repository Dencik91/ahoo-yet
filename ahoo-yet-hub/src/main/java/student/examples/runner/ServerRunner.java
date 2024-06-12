package student.examples.runner;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServerRunner {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("student.examples.ServerApp");
        Method method = clazz.getMethod("dummyMethod");
        method.invoke(null);
    }
}
