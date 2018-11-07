package day19;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̳߳�
 * @author x
 * ���򴴽������̣߳�������������ٻ��ϵͳ������ȵ���Դ���ģ��Լ������߳��л���Σ��
 * ���������̳߳صĸ��� ����ִ����֮��ɷ����̳߳�
 */
public class ThreadDemo29 {
	public static void main(String[] args) {
		//�����̳߳أ������̶���100���߳�
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		
		//�̳߳����п����̣߳���ֱ��ִ�е�ǰ����
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