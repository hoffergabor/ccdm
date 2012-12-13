package hu.iboard.coandco.datamagic.presentation.controllers.chatservice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "chatserviceData")
@SessionScoped
public class ChatServiceData implements Serializable{

	private static final long serialVersionUID = -3286401377260901782L;
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private String messageText;
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}
	
	public void setDataOutputStream(DataOutputStream dataOutputStream) {
		this.dataOutputStream = dataOutputStream;
	}
	
	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}
	
	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
}
