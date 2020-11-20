import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient extends Thread {  

	private Socket socket;
	private Scanner userIn;
	private PrintWriter serverOut;
	private Scanner serverIn;
	private String name;

	public ChatClient(String name) {  

		try {

			socket = new Socket("cs.hopku.net", 14445);
			userIn = new Scanner(System.in);
			serverOut = new PrintWriter(socket.getOutputStream(), true);
			serverIn = new Scanner(socket.getInputStream());
			this.name = name;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void run() {  
		while (true) {
			send();
		}
	}


	public void send() {  
		serverOut.println(name + ": " + userIn.nextLine());
	}


	public void update() {
		String message = serverIn.nextLine();
		if (!message.startsWith(name))
			System.out.println(message);
	}


	public static void main(String args[]) {
		ChatClient client = new ChatClient(args[0]);
		client.start();
		
		while (true) {
			client.update();
		}
	}
}
