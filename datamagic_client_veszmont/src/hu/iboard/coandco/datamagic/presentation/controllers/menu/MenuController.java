package hu.iboard.coandco.datamagic.presentation.controllers.menu;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.email.EmailService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController extends AbstractController {

	@ManagedProperty(value = "#{menuData}")
	private MenuData data;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;

	@PostConstruct
	public void init() {
		if (!getData().isMenuInit()) {
			initMenuColor();
			getData().setMenuInit(true);
			getData().getMenu8().setStyleClass("buttonSelected");
			getData().setEmailBody(null);
			getData().setEmailName(null);
		}
	}

	public void changeActiveMenuColor(ActionEvent event) {

		if (event.getComponent().getId().equals("menu1")) {
			if (!getData().getMenu1().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu1().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu2")) {
			if (!getData().getMenu2().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu2().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu3")) {
			if (!getData().getMenu3().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu3().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu4")) {
			if (!getData().getMenu4().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu4().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu5")) {
			if (!getData().getMenu5().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu5().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu6")) {
			if (!getData().getMenu6().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu6().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu7")) {
			if (!getData().getMenu7().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu7().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu8")) {
			if (!getData().getMenu8().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu8().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu9")) {
			if (!getData().getMenu9().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu9().setStyleClass("buttonSelected");
				return;
			}
		} else if (event.getComponent().getId().equals("menu10")) {
			if (!getData().getMenu10().getStyleClass().equals("buttonSelected")) {
				initMenuColor();
				getData().getMenu10().setStyleClass("buttonSelected");
				return;
			}
		} else {
			initMenuColor();
			return;
		}
	}

	public void initMenuColor() {
		getData().getMenu1().setStyleClass("button");
		getData().getMenu2().setStyleClass("button");
		getData().getMenu3().setStyleClass("button");
		getData().getMenu4().setStyleClass("button");
		getData().getMenu5().setStyleClass("button");
		getData().getMenu6().setStyleClass("button");
		getData().getMenu7().setStyleClass("button");
		getData().getMenu8().setStyleClass("button");
		getData().getMenu9().setStyleClass("button");
		getData().getMenu10().setStyleClass("button");
	}

	public void sendEmail() {
		String email = null;
		try {
			if (getManagedPartner() != null) {
				if (getManagedPartner().getEmail() != null && getManagedPartner().getEmail().equals("")) {
					addErrorMessage("Hiba", "Önnek nincs mentett email címe!Kérjük módosítsa személyes adatait!");
					return;
				} else {
					email = getManagedPartner().getEmail();
				}
			}
			if (getManagedWorker() != null) {
				if (getManagedWorker().getEmail() != null && getManagedWorker().getEmail().equals("")) {
					addErrorMessage("Hiba", "Önnek nincs mentett email címe!Kérjük módosítsa személyes adatait!");
					return;
				} else {
					email = getManagedWorker().getEmail();
				}
			}

			if (getData().getEmailName() == null || getData().getEmailName().equals("")) {
				addErrorMessage("Hiba", "Kérjük töltse ki a tárgy mezőt!");
				return;
			}

			if (getData().getEmailBody() == null || getData().getEmailBody().equals("")) {
				addErrorMessage("Hiba", "Kérjük töltse ki a leírás mezőt!");
				return;
			}
			boolean success = getEmailService().sendEmail("smtp.vnet.hu", "veszmont@vnet.hu", "Pilu52", email.trim(), "veszmont@vnet.hu",
					getData().getEmailName(), getData().getEmailBody());
			if (!success) {
				addErrorMessage("Hiba", "Technikai hiba miatt az üzenetküldés sikeretelen!");
				logger.error("ERROR BY SENDING EMAIL: " + email);
			} else {
				addInfoMessage("", "Üzenet elküldve!");
			}
		} catch (Exception e) {
			addErrorMessage("Hiba", "Technikai hiba miatt az üzenetküldés sikeretelen!");
			logger.error("ERROR BY SENDING EMAIL: " + email, e);
		}
	}

	public void showMailPopup(ActionEvent event) {
		getData().setEmailBody(null);
		getData().setEmailName(null);
	}

	public MenuData getData() {
		return data;
	}

	public void setData(MenuData data) {
		this.data = data;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub

	}

}
