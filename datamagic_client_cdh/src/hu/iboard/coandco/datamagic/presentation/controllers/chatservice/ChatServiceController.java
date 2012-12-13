package hu.iboard.coandco.datamagic.presentation.controllers.chatservice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.chat.ChatServiceBase;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "chatserviceController")
@RequestScoped
public class ChatServiceController extends AbstractController {

	@ManagedProperty(value = "#{chatserviceData}")
	private ChatServiceData data;
	@ManagedProperty(value = "#{chatService}")
	private ChatServiceBase chatService;	
	
	@Override
	public void loadData() {
		try {
			logger.info("Connecting to server...");
			getData().setSocket(new Socket(LOCALHOSTHOST, 5011));
			logger.info("Connected to server.");
			getData().setDataInputStream(new DataInputStream(getData().getSocket().getInputStream()));
			getData().setDataOutputStream(new DataOutputStream(getData().getDataOutputStream()));
			new Thread().start();
		}
		catch (IOException ioe) {
			logger.error("Connection to server failed. Error message: " + ioe.getMessage());
		}
	}
	
	@Override
	public void reloadData() {
		
	}

	@Override
	public void resetData() {
		removeBeanFromSession(CHATSERVICE_CONTROLLER);
	}

	public void processMessage(String message) {
		try {
			getData().getDataOutputStream().writeUTF(message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			while(true) {
				if (getLoginManager().getData().getLoggedInWorker() == null)
					return;
				String message = getData().getDataInputStream().readUTF();
		        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		        Date date = new Date();
				message += "\n[" + dateFormat.format(date) + "] - " + getLoginManager().getData().getLoggedInWorker().getDnev() + ": ";
				getData().setMessageText(getData().getMessageText() + message);
				//ide kell a kiíratás
			}
		} catch (IOException ioe) {
			logger.error("An error has been occured in method \"ChatServiceController.run()\" Error message: " + ioe.getMessage());
		}

	}
	
	public ChatServiceData getData() {
		return data;
	}

	public void setData(ChatServiceData data) {
		this.data = data;
	}
	

	public ChatServiceBase getChatService() {
		return chatService;
	}
	

	public void setChatService(ChatServiceBase chatService) {
		this.chatService = chatService;
	}

}