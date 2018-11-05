package day17;

import java.util.Scanner;

/**
 * IO阻塞状态
 * 当发生阻塞时，从Running切换到IO阻塞状态，
 * 	当读写准备好条件满足时，不再阻塞，
 * 	由阻塞状态，切换为到Runnable状态，
 * 	重新等待被CPU调度，等待下一次时间片分配，
 * 		才可进入Running状态
 * 
 * 当程序中涉及资源的读入，可提前缓存到内存中；
 * 	如：提前读入文件或图片；BufferedInputStream;BufferedReader
 * 当程序要向外写出时，可先缓存在内存中，然后再集中向外写出
 * 	如：内存中的字符数组；BufferedOutputStream;PrintWriter
 * 		信息队列（或队列中间件）
 * 
 * 如果某个线程调用了wait()、join()或者sleep()函数被阻塞挂起，
 * 	这个时候，调用该线程的interrupt()方法，
 * 	该线程会打断，抛出个InterruptedException异常
 * 注意：IOBlock状态，不会被interrupt()方法中断！
 * 
 * jx:
 * 只有调用sleep join wait等方法时，再在线程外调用interrupt方法才会抛出异常
 * IOBlock会阻塞当前线程。
 * 打断线程 在run方法内获取当前线程是否被打断isInterrupt  然后去做判断，用于线程间协作
 */
public class ThreadDemo13 {
	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Scanner sc = new Scanner(System.in);
                    
				System.out.println("请输入一个整数：");
				
				//for(;;){
				//int num = sc.nextInt();
				Thread currThread = Thread.currentThread();
				boolean flag = currThread.isInterrupted();
				if(flag == true){ //true表示被中断
					sc.close();
				}else{
					System.out.println("未被打断");
				}
				
				//}
				 
				  System.out.println("close");
				
			}
		});
		thread.start();
		thread.interrupt();
		
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				System.out.println("打断线程thread");
				thread.interrupt();			
			}		
		}).start();
		
		/*
		 * 如果某个线程调用了wait()、join()或�?�sleep()函数被阻塞挂起，
		 * 	这个时�?�，调用该线程的interrupt()方法�?
		 * 	该线程会打断，抛出个InterruptedException异常
		 * IOBlock状�?�，不会被interrupt()方法中断�?
		 */
	}
}



