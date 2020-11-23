//Java libraries needed for use
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ChatServer {  
	//ArrayList of clients connected to server
	private static ArrayList<ChatServerThread> clients = new ArrayList<>();

	//A synchronized method that takes in Strings and sends them out
	public static synchronized void handle(String message) {
		for (ChatServerThread client: clients) {
			client.send(message);
		} 
	}


	public static void main(String args[]) throws Exception {  
		
		@SuppressWarnings("resource")
		//Opens server on Socket 14445
		ServerSocket server = new ServerSocket(14445); 
		//Constantly checking for clients to connect to server
		while (true) {
			Socket socket = server.accept();
			ChatServerThread client = new ChatServerThread(
					new Scanner(socket.getInputStream()), 
					new PrintWriter(socket.getOutputStream(), true));
			clients.add(client);
		}
	}
}
