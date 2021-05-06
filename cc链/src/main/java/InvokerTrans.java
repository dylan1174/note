import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerTrans {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        InvokerTransformer invokerTransformer = new InvokerTransformer(
//                "exec",
//                new Class[]{String.class},
//                new String[]{"Calc.exe"}
//        );
//        Object input = Class.forName("java.lang.Runtime").getMethod("getRuntime").invoke(Class.forName("java.lang.Runtime"));
//        Object input2 = Runtime.getRuntime();
//        invokerTransformer.transform(input2);
//        Class runtimecls = Class.forName("java.lang.Runtime");
//        Method m = runtimecls.getMethod("getRuntime",null);
//        ((Runtime)Runtime.class.getMethod("getRuntime").invoke(null,null)).exec("calc.exe");


        // 通过反射执行exec方法
        try{
            // 获得Runtime类对象
            Class runtimeClass = Class.forName("java.lang.Runtime");
            // newInstance报错: 使用的类没有无参构造函数 使用的类是私有的
            Method method = runtimeClass.getMethod("exec", String.class);
            Method RuntimeMethod = runtimeClass.getMethod("getRuntime");
            Object m = RuntimeMethod.invoke(null);
            method.invoke(m,"Calc.exe");
        }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
