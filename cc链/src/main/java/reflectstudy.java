import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 反射学习 三种方法弹出计算器
public class reflectstudy {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c = Class.forName("java.lang.Runtime");
//        Object o = c.newInstance();
        Constructor constructor = c.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();
        Method method = c.getMethod("exec", String.class);
        method.invoke(o,"Calc.exe");

//        Method method1 = c.getMethod("getRuntime");
//        Object o1 = method1.invoke(c);
//        method.invoke(o1,"Calc.exe");
    }
}
