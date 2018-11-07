package day19;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 项目中，如何使用集合：
 * - 使用Collections工具，封装出一个线程安全的集合
 * 		会拿着整个整合，作为锁
 * 		同一个时间点，只能有一个线程操作这个集合
 * 
 * - 直接使用JUC包中的BlockingQueue（java.util.concurrent ）
 * 	针对于生产者和消费者模式，需要频繁操作集合
 * 	JUC包中提供了，
 * 	（1）可以多个线程同时操作的集合，都是线程安全的
 * 	（2）同时解决了线程间等待和唤醒问题
 * 		当队列中满的时候，再添加元素，对应线程会被阻塞挂起，
 * 			直到队列中有空的位置，不再阻塞，添加成功；
 * 		当队列中是空的时候，再从其中取元素，线程被阻塞挂起，
 * 			直到队列中有新的元素时，不再阻塞，取元素成功。
 * 		以上阻塞和唤醒的工作，不需要程序员使用wait和notify实现，
 * 		程序员只需要编写业务操作的代码，不需要考虑线程间的协作。
 * 	
 */
public class ThreadDemo27 {
	public static void main(String[] args) {
		
			List<Integer> list = new ArrayList<Integer>();
			list = Collections.synchronizedList(list);
			
			//效率低 同一个时间点，只能有一个线程操作该集合
			Set<Integer> set = new HashSet<Integer>(); 
			set = Collections.synchronizedSet(set);
			
			set.add(9);
			set.add(53);
			set.add(105);
			
			System.out.println(set);
			
			Map <Integer,String> map = new HashMap<Integer,String>();
			map = Collections.synchronizedMap(map);
			
			/**
			 * BlockingQueue 多个线程同时操作，是线程安全的
			 * 阻塞和唤醒的工作不需要使用 wait和notify方法
			 * 普通的queue队列不是并发安全的，需要手工进行同步
			 * 具体间Demo28
			 */
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
			
			
	}
}
