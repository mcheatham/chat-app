import java.io.PrintWriter;
import java.util.Scanner;

public class ChatServerThread extends Thread {  

	private Scanner in;
	private PrintWriter out;


	public ChatServerThread(Scanner in, PrintWriter out) {  
		this.in = in;
		this.out = out;
		this.start();
	}


	public void send(String msg) {   
		try {  
			out.println(msg);
		} catch(Exception e) {  
			e.printStackTrace();
		}
	}


	public void run() {
		while (true) {
			ChatServer.handle(in.nextLine());
		}
	}
}