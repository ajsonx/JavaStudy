package day19;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 直接使用BlockingQueue实现生产者消费者模式
 * 
 * 1. BlockingQueue是一个队列
 * - 普通的Queue队列不是并发安全的，需要手工的进行同步
 * - BlockingQueue是并发安全的，
 * - put和take操作，自身带有阻塞和唤醒通知功能
 * 
 * 2. BlockingQueue常见的实现类：
 * ArrayBlockingQueue
 * 	一个由数组支持的有界阻塞队列。
 * 		此队列按 FIFO（先进先出）原则对元素进行排序。
 * 	这是一个典型的“有界缓存区”，
 * 		固定大小的数组在其中保持生产者插入的元素
 * 		和使用者提取的元素。
 * 	ArrayBlockingQueue(int capacity) 
 * 
 * LinkedBlockingQueue
 * 	一个基于已链接节点的、
 * 	范围任意的 blocking queue，无界队列（21亿）。
 * 	此队列按 FIFO（先进先出）排序元素。
 * 
 * DelayQueue
 * 	delayed 元素的一个无界阻塞队列，
 * 	只有在延迟期满时才能从中提取元素。
 * 
 * PriorityBlockingQueue
 * 	一个无界阻塞队列，
 * 	它使用与类 PriorityQueue 相同的顺序规则，
 * 	并且提供了阻塞获取操作。
 * 
 * SynchronousQueue
 * 	同步队列，该队列只保存一个元素
 * 	一种阻塞队列，其中每个插入操作必须等待
 * 另一个线程的对应移除操作
 * 
 */
public class ThreadDemo28 {
	
	public static int MAX_SIZE = 6;
	//Queue<Integer> queue = new LinkedList<Integer>();
	BlockingQueue<Integer> queue = 
			new LinkedBlockingQueue<Integer>(MAX_SIZE);
			//new ArrayBlockingQueue<Integer>(MAX_SIZE);
			//new SynchronousQueue<Integer>();
	
	public static void main(String[] args) {
		//创建一个外部类对象
		ThreadDemo28 obj = new ThreadDemo28();
		
		//内部类对象，依赖于并附着于外部类对象身上
		Thread producer = obj.new Producer();
		Thread producer2 = obj.new Producer();
		
		Thread consumer = obj.new Consumer();
		Thread consumer2 = obj.new Consumer();
		Thread consumer3 = obj.new Consumer();
		Thread consumer4 = obj.new Consumer();
		Thread consumer5 = obj.new Consumer();
		
		//启动线程
		producer.start();
//		producer2.start();
		
		consumer.start();
//		consumer2.start();
//		consumer3.start();
//		consumer4.start();
//		consumer5.start();
	}
	
	//生产者线程
	class Producer extends Thread {

		@Override
		public void run() {
			Random random = new Random();
			
			while(true) {
				if(queue.size()==MAX_SIZE) {
					//如果货架满了，当前生产者线程等待消费者来消费
					System.out.println("货架满了，"
						+ "生产者等待消费者消费取走一个商品");
					
				}
				
				//生产出一个商品，添加到货架
				int i = random.nextInt();
				System.out.println("生产出新商品：" + i);
				//queue.add(i);
				try {
					queue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//消费者线程
	class Consumer extends Thread {
		@Override
		public void run() {
			while(true) {
				if(queue.isEmpty()) {
					System.out.println("货架上空了，"
						+ "消费者等着生产者生产商品放入货架。");
				}
				
				//货架上有商品，开始消费
				//int i = queue.remove();
				int i = 0;
				try {
					i = queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(
						"消费者消费取下了一个商品" + i);

			}
		}
	}
}
