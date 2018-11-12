package day22;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *	��ȡʹ��Reader ����ʹ��Writer һ����Input һ���� Output
 */
public class ChatServer {
	private ServerSocket serverSocket;
	private Map<String,PrintWriter> onlineUser;
		//���ڱ���<�ǳƣ��ַ������> �����û���Ϣ
	
	/**
	 * ���췽��
	 */
	public ChatServer() {
		onlineUser = new HashMap<String,PrintWriter>();
		try {
			serverSocket = new ServerSocket(8812);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ȴ��ͻ������ӣ������µ��̴߳���ҵ��
	 */
	public void start(){
		try {
			while(true){
				System.out.println("�ȴ��ͻ�������");
				Socket socket = serverSocket.accept();
				System.out.println("�ͻ������ӳɹ�");
				Runnable r = new  ClientHandler(socket);
				new Thread(r).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 */
	class ClientHandler implements Runnable{
		private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		private String name;
		
		/**
		 * ���췽��
		 * @param socket
		 */
		public ClientHandler(Socket socket) {
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		/**
		 * ��ͣ�Ĵ����ж�ȡ��Ϣ
		 */
		@Override
		public void run() {
			while(true){
				try {
					String msg = br.readLine();
					if(msg == null){
						return ;
					}
					if(msg.startsWith("%LOGIN%")){
						//��½ҵ����
						String []ary = msg.split("\\%");
						if(onlineUser.containsKey(ary[1])){
							pw.println("%LOGIN%���ǳ��Ѿ���ʹ��");					
						}else{
							this.name = ary[1];
							pw.println("%LOGIN%OK");
							addOnlineUser(name,pw);//��ӵ��û��б�
							sendSystemMessage(name + "������������" + new Date("HH:mm:ss"));
							sendOnlineUserList();//���͸�������һ���µ��û��б�
							
						}
					}else if(msg.startsWith("%MESS%")){
						
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					
				}
				
			}
		}
		
	}
	
	/**
	 * ����������
	 * @param args
	 */
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.start();
	}

	public void sendSystemMessage(String string) {
		// TODO Auto-generated method stub
		
	}

	public void sendOnlineUserList() {
		
	}

	public synchronized void addOnlineUser(String name, PrintWriter pw) {
		onlineUser.put(name,pw);
	}
}
