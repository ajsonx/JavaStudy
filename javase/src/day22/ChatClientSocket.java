package day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientSocket {
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	public ChatClientSocket(){
		//创建连接，连接成功后获得pw,br
		
		try {
			this.socket = new Socket("127.0.0.1",8812);
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
