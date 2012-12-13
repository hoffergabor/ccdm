package hu.iboard.coandco.datamagic.service.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;


public class ChatService extends ChatServiceBase{

	protected Logger logger = Logger.getLogger(this.getClass());	
	
	@PostConstruct
	public void init() throws IOException {
		logger.info("ChatService Constructor Started...");
		listen(5011);		
	}
	
//	public static void main(String[] args) throws Exception{
//		int port = Integer.parseInt(args[0]);
//		new ChatService(port);
//	}
	
	protected void listen(Integer port) throws IOException{
		setServerSocket(new ServerSocket(port));		
		logger.info("Chat server listening on port: " + port.toString());
		
		while(true) {
			Socket socket = getServerSocket().accept();
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			getOutputStreams().put(socket, dataOutputStream);
			new ServerThread(this, socket);
		}
	}
	
	@SuppressWarnings("unchecked")
	Enumeration<DataOutputStream> getOutStreams() {
		return getOutputStreams().elements();
	}
	
	void sendToAll(String message) {
		synchronized(getOutputStreams()) {
			for (Enumeration<DataOutputStream> e = getOutStreams(); e.hasMoreElements(); ) {
				DataOutputStream dataOutputStream = (DataOutputStream)e.nextElement();
				try {
					dataOutputStream.writeUTF(message);
				} catch(IOException ioe) {
					logger.error("An error has been occured during the message sending process in method \"ChatService.SendToAll(String message)\". Error message: " + ioe.getMessage());
				}
			}
		}
	}

	void removeConnection(Socket socket) {
		synchronized (getOutputStreams()) {
			logger.info("Removing connection to " + socket);			
			getOutputStreams().remove(socket);
			try {
				socket.close();
			} catch (IOException ioe) {
				logger.error("An error has been occured during the message connection removel process in method \"ChatService.removeConnection(Socket socket)\". Error message: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
	}
}