//Java libraries needed for use
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient extends Thread {  
	//Fields for the ChatClient
	private Socket socket;
	private Scanner userIn;
	private PrintWriter serverOut;
	private Scanner serverIn;
	private String name;

	//ChatClient constructor, takes in the name for the user
	public ChatClient(String name) {  

		try {
			//Connects to the server
			socket = new Socket("cs.hopku.net", 14445);
			userIn = new Scanner(System.in);
			serverOut = new PrintWriter(socket.getOutputStream(), true);
			serverIn = new Scanner(socket.getInputStream());
			this.name = name;

		} catch (Exception e) {
			//Prints out exception and what line it occured on
			e.printStackTrace();
		}
	}

	//Listens for messages to send to server
	public void run() {  
		while (true) {
			send();
		}
	}

	//Sends messages to the server
	public void send() {  
		serverOut.println(name + ": " + userIn.nextLine());
	}

	//Outputs messages from the server
	public void update() {
		String message = serverIn.nextLine();
		if (!message.startsWith(name))
			System.out.println(message);
	}


	public static void main(String args[]) {
		ChatClient client = new ChatClient(args[0]);
		client.start();
		//Constatly updates the output for the user
		while (true) {
			client.update();
		}
	}
}
