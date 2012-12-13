package hu.iboard.coandco.datamagic.service.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ChatServiceBase {

	private ServerSocket serverSocket;
	
	private Hashtable outputStreams = new Hashtable();

	protected abstract void listen(Integer port) throws IOException;
	
	abstract Enumeration<DataOutputStream> getOutStreams(); 

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Hashtable getOutputStreams() {
		return outputStreams;
	}

	public void setOutputStreams(Hashtable outputStreams) {
		this.outputStreams = outputStreams;
	}
}