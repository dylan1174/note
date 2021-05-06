import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class dynamicProxy {
    public static class testAgent implements InvocationHandler {
        // target 为委托类对象
        private Object target;
        public testAgent(Object target){
            this.target = target;
        }

        // InvocationHandler接口：负责提供调用代理操作
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 在原有方法上添加自定义的内容
            System.out.println("房子的价格在1k-2k之间");
            // 利用反射机制执行传入的method和参数
            Object result = method.invoke(target,args);
            return result;
        }

    }
    public static void main(String[] args) {
        // 委托类的实例对象
        proxystudy.Entrust entrust = new proxystudy.Entrust();
        // 获得类加载器
        ClassLoader classLoader = entrust.getClass().getClassLoader();
        // 获得所有接口方法
        Class[] interfaces = entrust.getClass().getInterfaces();
        // 获得代理类的反射处理器
        InvocationHandler invocationHandler = new testAgent(entrust);
        // 创建代理对象
        proxystudy.Rental proxy = (proxystudy.Rental) Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
        // 调用封装以后的方法
        proxy.sale();
    }
}
