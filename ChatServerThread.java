//Java libraries needed for use
import java.io.PrintWriter;
import java.util.Scanner;

public class ChatServerThread extends Thread {  
	//ChatServerThread fields
	private Scanner in;
	private PrintWriter out;

	//ChatServerThread constructor to take in messages and send them
	public ChatServerThread(Scanner in, PrintWriter out) {  
		this.in = in;
		this.out = out;
		this.start();
	}

	//Takes in a String and sends it to the output
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