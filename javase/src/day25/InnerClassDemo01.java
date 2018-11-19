package day25;

public class InnerClassDemo01 {
	public static void main(String[] args) {
		Outer outer = new Outer(50);
		outer.printTime();
		
		//以下内容，了解即可！
		//Outer.Inner inner = new Outer.Inner();//编译错误 
		Outer.Inner inner = outer.new Inner();//编译通过
			//内部类对象一般都在外部类中创建的！！
			//当前写法非常特殊，在第三个类中去创建内部类对象了
			//语法：外部类对象.new 内部类类型()
	}
}

class Outer {
	private int time;//次数
	private Inner inner;
	public Outer(int time) {
		this.time = time;
		inner = new Inner();//内部类对象通常在外部类中创建
		inner.timeIncrement();
	}
	//注意：Inner类，是定义在Outer类内部的
	//将次数累加的算法，封装到另一个类中：Inner
	class Inner {
		public void timeIncrement() {
			time++;
			System.out.println("当前对象:" + this);
				//当前对象:day25am.Outer$Inner@33909752
			System.out.println("外部类对象：" + Outer.this);
				//外部类对象：day25am.Outer@55f96302
		}
	}
	
	public void printTime() {
		System.out.println("time: " + time);
	}
}