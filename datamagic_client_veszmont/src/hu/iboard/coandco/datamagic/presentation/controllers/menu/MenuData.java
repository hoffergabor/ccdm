package hu.iboard.coandco.datamagic.presentation.controllers.menu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.commandbutton.CommandButton;

@ManagedBean(name = "menuData")
@SessionScoped
public class MenuData implements Serializable {
	
	private static final long serialVersionUID = 2228295789377717468L;
	
	private CommandButton menu1 = new CommandButton();
	private CommandButton menu2 = new CommandButton();
	private CommandButton menu3 = new CommandButton();
	private CommandButton menu4 = new CommandButton();
	private CommandButton menu5 = new CommandButton();
	private CommandButton menu6 = new CommandButton();
	private CommandButton menu7 = new CommandButton();
	private CommandButton menu8 = new CommandButton();
	private CommandButton menu9 = new CommandButton();
	private CommandButton menu10 = new CommandButton();
	private boolean isMenuInit = false;
	private String emailName;
	private String emailBody;
	
	public CommandButton getMenu1() {
		return menu1;
	}
	public void setMenu1(CommandButton menu1) {
		this.menu1 = menu1;
	}
	public CommandButton getMenu2() {
		return menu2;
	}
	public void setMenu2(CommandButton menu2) {
		this.menu2 = menu2;
	}
	public CommandButton getMenu3() {
		return menu3;
	}
	public void setMenu3(CommandButton menu3) {
		this.menu3 = menu3;
	}
	public CommandButton getMenu4() {
		return menu4;
	}
	public void setMenu4(CommandButton menu4) {
		this.menu4 = menu4;
	}
	public CommandButton getMenu5() {
		return menu5;
	}
	public void setMenu5(CommandButton menu5) {
		this.menu5 = menu5;
	}
	public CommandButton getMenu6() {
		return menu6;
	}
	public void setMenu6(CommandButton menu6) {
		this.menu6 = menu6;
	}
	public CommandButton getMenu7() {
		return menu7;
	}
	public void setMenu7(CommandButton menu7) {
		this.menu7 = menu7;
	}
	public CommandButton getMenu8() {
		return menu8;
	}
	public void setMenu8(CommandButton menu8) {
		this.menu8 = menu8;
	}
	public CommandButton getMenu9() {
		return menu9;
	}
	public void setMenu9(CommandButton menu9) {
		this.menu9 = menu9;
	}
	public CommandButton getMenu10() {
		return menu10;
	}
	public void setMenu10(CommandButton menu10) {
		this.menu10 = menu10;
	}
	public boolean isMenuInit() {
		return isMenuInit;
	}
	public void setMenuInit(boolean isMenuInit) {
		this.isMenuInit = isMenuInit;
	}
	public String getEmailName() {
		return emailName;
	}
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

}
