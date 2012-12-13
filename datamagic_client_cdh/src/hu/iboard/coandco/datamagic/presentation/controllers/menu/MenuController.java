package hu.iboard.coandco.datamagic.presentation.controllers.menu;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController {

	
	@ManagedProperty(value = "#{menuData}")
	private MenuData data;
	
	
	@PostConstruct
	public void init() {
		if (!getData().isMenuInit()) {
			initMenuColor();
			getData().setMenuInit(true);
			getData().getMenu1().setStyleClass("buttonSelected");
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

	public MenuData getData() {
		return data;
	}

	public void setData(MenuData data) {
		this.data = data;
	}

	
}
