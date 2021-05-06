public class proxystudy {
    public interface Rental {
        public void sale();
    }

    // 委托类
    public static class Entrust implements Rental {

        @Override
        public void sale() {
            System.out.println("出租房子");
        }
    }
    // 代理类
    public static class ProxyEntrust implements Rental {
        // 委托的对象
        private Entrust entrust;

        public ProxyEntrust(Rental test) {
            this.entrust = (Entrust) test;
        }

        public void ProxyEntrust(Entrust e) {
            this.entrust = e;
        }

        @Override
        public void sale() {
            System.out.println("房子的价格在1k-2k之间");
            entrust.sale();
        }
    }

    // 测试方法
    public static void consumer(Rental rental){
        rental.sale();
    }

    public static void main(String[] args) {
        // 静态代理
        Rental test = new Entrust();
        System.out.println("------使用代理前------");
        consumer(test);
        System.out.println("------使用代理后------");
        consumer(new ProxyEntrust(test));

        //
    }
}
