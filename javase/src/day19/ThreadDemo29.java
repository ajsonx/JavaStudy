package day19;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * @author x
 * 程序创建大量线程，任务结束后销毁会给系统代理过度的资源消耗，以及过度线程切换的危险
 * 所以有了线程池的概念 任务执行完之后可放入线程池
 */
public class ThreadDemo29 {
	public static void main(String[] args) {
		//创建线程池，数量固定在100个线程
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		
		//线程池中有空闲线程，可直接执行当前任务
		for(int i = 0 ;i<99;i++){
			MyRunnableTask task = new MyRunnableTask();
			threadPool.execute(task); 
		}
	}
}
class MyRunnableTask implements Runnable{

	@Override
	public void run() {
		Thread currThread = Thread.currentThread();
		System.out.println(currThread);
	}
	
}