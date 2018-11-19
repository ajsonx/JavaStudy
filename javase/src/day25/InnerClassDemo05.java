package day25;

/**
 * 静态内部类（了解即可）
 */
public class InnerClassDemo05 {
	public static void main(String[] args) {
		MyOuter outer = new MyOuter();
		//MyOuter.MyInner inner = outer.new MyInner();
						//编译错误，以上写法是成员内部类写法
		MyOuter.MyInner inner = new MyOuter.MyInner();
			//静态内部类，是属于外部类的一个静态成员
			// 因此，直接通过类型来操作即可！
	}
}
class MyOuter{
	private int a = 10;
	private static int b = 20;
	
	public static class MyInner{
		private int c = 30;
		
		//静态内部类中的方法，只能访问到外部类中的静态属性
		public void test1() {
			//a++;//编译错误
			b++;//编译通过
		}
	}
}






