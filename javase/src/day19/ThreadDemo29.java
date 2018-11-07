package day19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 
 * 创建一些线程，集合在一起，可称作为线程池。
 * 当接收到一个处理任务，从线程池中取出一个空闲的线程，
 * 来处理该任务；任务执行完之后，不直接关闭线程，不回收；
 * 将该线程转换给线程池，继续维护着！
 * 
 * 避免了原先，当一个程序若创建大量线程，任务结束后销毁，
 * 会给系统带来过度的资源消耗，以及过度的线程切换的危险，
 * 从而可能导致系统崩溃！
 * 
 * 线程池设计，需要明确以下几点：
 * （1）一个线程同时只能执行一个任务
 * （2）线程的重用，是指某个线程执行完毕后，被调度执行一个新任务
 * （3）线程池中的线程数量，并不是越多越好，尽可能避免较少的空闲线程
 * 
 * 
 * java在JUC包中提供了线程池的实现
 * 1. 基本API的介绍
 * Executors
 * 		工具类，提供了很多的工厂方法，用于创建线程池
 * Executor
 * 		执行器，接口，可将一个Runnable任务提交给执行器
 * ExecutorService
 * 		线程池，接口
 * 
 * 2. 常见线程池实现类介绍
 * （1）Executors.newFixedThreadPool(3);
 * 		创建一个规定数量的线程池，可控制线程最大的并发数
 * 		超出的任务，在队列中等待
 * （2）Executors.newCachedThreadPool()
 * 		创建一个可根据需要创建新线程的线程池
 * 		如果现有线程没有可用的，则创建一个新线程并添加到池中。
 * 		终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
 * 		因此，长时间保持空闲的线程池不会使用任何资源。
 * 		注意：手工判断和管理线程池中线程的数量
 * （3）Executors.newScheduledThreadPool(corePoolSize)
 * 		创建一个支持延时、定时及周期性任务执行的线程池
 * （4）Executors.newSingleThreadScheduledExecutor()
 * 		创建一个单线程的计划任务线程池
 * 		可保证顺序地执行各个任务，
 * 		并且在任意给定的时间不会有多个线程是活动的.
 * 	可以借助这个线程池，可顺序执行：FIFO、LIFO、优先级等顺序
 */
public class ThreadDemo29 {
	public static void main(String[] args) {
		
		//创建一个线程池，大小固定在3个线程：
		ExecutorService threadPool = 
			Executors.newFixedThreadPool(3);
				
		//向线程池，添加执行任务
		//当线程池中有空闲线程，可直接执行当作任务
		//	对于没有更多可用的线程，
		//	此时对于FixedThreadPool池，任务阻塞等待
		for (int i = 0; i < 10; i++) {
			MyRunnableTask task = new MyRunnableTask();
			threadPool.execute(task);
				//调用线程池，添加任务，进行执行
		}
	}
}

class MyRunnableTask implements Runnable{
	@Override
	public void run() {
		Thread currThread = Thread.currentThread();
		System.out.println(currThread + "正在运行！");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace(); }
		
		System.out.println(currThread + "运行结束.");
	}
}

