package hu.iboard.coandco.datamagic.service.chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

	private ChatService chatService;
	
	private Socket socket;
	
	public ServerThread(ChatService chatService, Socket socket) {
		this.chatService = chatService;
		this.socket = socket;
		start();
	}
	
	public void run() {
		try {
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			while(true) {
				String message = dataInputStream.readUTF();
				chatService.sendToAll(message);
			}
		}
		catch (EOFException e) {
		}
		catch (IOException ioe) {
			//logger.error("An error has been occured during the message transmission process in method \"ServerThread.run()\". Error message: " + ioe.getMessage());
			ioe.printStackTrace();			
		}
		finally {
			chatService.removeConnection(socket);
		}
	}

}
