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
 *	读取使用Reader 发送使用Writer 一个套Input 一个套 Output
 */
public class ChatServer {
	private ServerSocket serverSocket;
	private Map<String,PrintWriter> onlineUser;
		//用于保存<昵称，字符输出流> 保存用户信息
	
	/**
	 * 构造方法
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
	 * 阻塞等待客户端连接，开启新的线程处理业务
	 */
	public void start(){
		try {
			while(true){
				System.out.println("等待客户端连接");
				Socket socket = serverSocket.accept();
				System.out.println("客户端连接成功");
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
		 * 构造方法
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
		 * 不停的从流中读取信息
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
						//登陆业务处理
						String []ary = msg.split("\\%");
						if(onlineUser.containsKey(ary[1])){
							pw.println("%LOGIN%该昵称已经被使用");					
						}else{
							this.name = ary[1];
							pw.println("%LOGIN%OK");
							addOnlineUser(name,pw);//添加到用户列表
							sendSystemMessage(name + "进入了聊天室" + new Date("HH:mm:ss"));
							sendOnlineUserList();//发送给所有人一份新的用户列表
							
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
	 * 主方法启动
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
