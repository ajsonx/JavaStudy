package day17;

/**
 * 可使用synchronized，实现线程安全，保证代码的原子性操作
 * 
 * 1. Java中每个对象都有一把锁（同步监视器锁）
 * 2. 可以使用同步代码块，保证一组代码原子性操作
 * 注意：要求多个线程看到的 锁对象 必须是同一个对象，否则无效
		synchronized (锁对象的引用) {
			//code 1
			//code 2
		}
 * 3. 当整个方法中的代码都需要线程安全时，直接可以加到方法签名上
 * 4. 选择合适的锁对象
 * 	要保证多个线程看到的锁对象，是同一个对象。
 * （1）在可执行任务中，定义一个实例变量
 * 			private Object obj = new Object();
 * （2）更多时候，直接使用this作为锁对象
 * （3）使用一个静态的锁对象
 * 			private static Object obj = new Object()
 * （4）在静态方法上，加上 synchronized 关键字
 * 
 * 5. 选择合适的锁住的范围
 * 	在安全情况下，尽可能减少同步范围，
 * 	以便提高线程并发访问效率！
 * 	即：
 * 		同一个临界资源操作的多行代码，括住即可；
 * 		其他代码安全情况下，不括住！
 * 
 * 6. 如何获得锁
 * （1）当线程执行时，进入同步代码块时就 自动获得锁
 * （2）并且在退出同步代码块时，自动释放锁
 * 
 */
public class ThreadDemo18 {
	public static void main(String[] args) {
		MySyncTask task = new MySyncTask();
		
		Thread thread1 = new Thread(task, "thread1");
		Thread thread2 = new Thread(task, "thread2");
		Thread thread3 = new Thread(task, "thread3");
		Thread thread4 = new Thread(task, "thread4");
		Thread thread5 = new Thread(task, "thread5");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
	}
}

class MySyncTask implements Runnable {
	//对象属性
	private int count = 5;
	Object obj = new Object();

	//3. 借用this当作锁对象
	@Override
	public void run() {
		synchronized (this) {
			count--;
			System.out.println(
					Thread.currentThread().getName() 
					+ " count: " + count);			
		}
	}
//	public synchronized static void method01() {
//		count++;
//		Thread.yield();//只是教学测试用，实战不用
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//		}
//		System.out.println("访问次数：" + count);
//	}
	/*
	//2. 使用同步代码块，实现同步访问
	//同步代码块加在 针对同一个临界资源 访问的所有代码上
	@Override
	public void run() {
		System.out.println("使用同步代码块，实现安全访问...");
		synchronized (obj) {
			count--;
			System.out.println(
					Thread.currentThread().getName() 
					+ " count: " + count);			
		}
		
		System.out.println("使用同步代码块，完成方法访问。");
	}
	*/
	/*
	//1. 可使用synchronized，实现线程安全，保证代码的原子性操作
	//加在方法上，就是指方法中代码要么都执行，要么都不执行
	//（1）将方法中所有的代码放在同步代码块中
	//（2）该同步代码块，会借 this当前这个对象 身上的锁
	//	要保证多个线程看到的锁是同一把锁
	//	那当前对象有几个呢？MyTask对象目前只有一个，是同一个
	@Override
	public synchronized void run() {
		count--;
		System.out.println(
				Thread.currentThread().getName() 
				+ " count: " + count);
	}
	*/
	
	
}