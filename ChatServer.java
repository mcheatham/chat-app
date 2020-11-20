import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ChatServer {  

	private static ArrayList<ChatServerThread> clients = new ArrayList<>();

	
	public static synchronized void handle(String message) {
		for (ChatServerThread client: clients) {
			client.send(message);
		} 
	}


	public static void main(String args[]) throws Exception {  
		
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(14445); 
		
		while (true) {
			Socket socket = server.accept();
			ChatServerThread client = new ChatServerThread(
					new Scanner(socket.getInputStream()), 
					new PrintWriter(socket.getOutputStream(), true));
			clients.add(client);
		}
	}
}
