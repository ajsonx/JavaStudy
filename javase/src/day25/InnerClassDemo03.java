package day25;

/**
 * 匿名内部类
 * 
 * 直接继承于某个类，并同时实例化对象
 * 语法简单，使用比较广泛
 * 
 * 也可直接继承于抽象类 或 直接实现某个接口，
 * 				创建一个匿名子类的实例
 * 
 * 匿名内部类，只适合创建那种只需要一次使用的类
 */
public class InnerClassDemo03 {
	public static void main(String[] args) {
		Goo goo = new Goo();//创建Goo类型的对象
		goo.test();  //day25am.Goo test()
		
		Goo g2 = new Goo() {};
		g2.test();  //day25am.InnerClassDemo03$1 test()
		//new 类型(){}，这样的写法叫作匿名内部类
		//编译器编译时，生成了一个新的Goo的子类型
		//	这个新的子类型，没有名字，匿名！
		//	{}这个空的大括号，表示直接继承父类中方法，没有重写现象
		//		{}表示子类的类体
		
		Goo g3 = new Goo() {//生成一个子类型对象，并重写了方法
			@Override
			public void test() {
				System.out.println(
					this.getClass().getName() + " test()..." );				
			}
		};
		g3.test();  //day25am.InnerClassDemo03$2 test()...
		
		//Foo foo = new Foo();//编译错误，接口不能直接实例化
		Foo foo = new Foo() {};//编译通过，匿名内部类写法
		
		//Koo koo = new Koo() {};//编译错误，没有实现接口中的方法
		Koo koo = new Koo() {
			@Override
			public void method() {
				System.out.println("Koo method()");
			}
		};
		koo.method();
	}
}

class Goo {
	public void test() {
		System.out.println(
			this.getClass().getName() + " test()" );
			//获得当前对象的类型名
	}
}

interface Foo{}
interface Koo{
	void method();
}


