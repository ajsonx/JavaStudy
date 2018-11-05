package day17;

/**
 * 多线程异步同时操作资源，会有并发安全问题
 * 
 * 当多个线程并发读写同一个临界资源时，会出现这样的问题
 * 
 * 常见的临界资源：
 * （1）多个线程共享的实例变量
 * （2）多个线程共享公共的静态变量
 * 
 * 如果想解决线程安全问题，需要将异步的操作 转变为 同步操作
 * - 异步操作：多个线程并发的操作，各线程各干各干的
 * - 同步操作：有先后顺序的执行，你先做，再转交给我做，你休息
 * 
 * 在java中可以使用 synchronized 关键字帮着实现同步操作
 */
public class ThreadDemo17 {
	
	public static void main(String[] args) {
		MyTask task = new MyTask();
		
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

class MyTask implements Runnable {
	//对象属�??
	private int count = 5;
	
	@Override
	public void run() {
		count--;
		System.out.println(
				Thread.currentThread().getName() 
				+ " count: " + count);
		Thread.yield();
	}
}
/*
thread2 count: 2
thread5 count: 0
thread1 count: 2
thread4 count: 1
thread3 count: 2
*/