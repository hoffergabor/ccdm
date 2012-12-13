package hu.iboard.coandco.datamagic.presentation.controllers.partnerdataupdate;

import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.user.UserServiceBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name = "partnerdataupdateController")
@RequestScoped
public class PartnerDataUpdateController extends AbstractController {

	public static final String PARTNERDATAUPDATE_ACTION = "partnerdataupdate";
	public static final String MOBILEPARTNERDATAUPDATE_ACTION = "mobilepartnerdataupdate";

	@ManagedProperty(value = "#{partnerdataupdateData}")
	private PartnerDataUpdateData data;

	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;

	@Override
	public void loadData() {
		if (getManagedPartner() != null) {
			getData().setPartner(getManagedPartner());
		} else if (getManagedRenter() != null) {
			getData().setPartner(getManagedRenter());

		} else {
			getData().setPartner(new Partner());
		}
		getData().setPassword1(null);
		getData().setPassword2(null);
		getData().setPaymentItems(getPaymentItems());

	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(PARTNERDATAUPDATE_CONTROLLER);

	}

	public void updatePartnerData(ActionEvent event) {

		if (getData().getPartner() == null) {
			return;
		}
		if (!checkPartnerData(getData().getPartner())) {
			addErrorMessage("", "Név / email megadása kötelező!");
			return;
		}
		try {
			getData().getPartner().setModdatum(new Date());
			Partner partner = getPartnerService().updatePartner(getData().getPartner());
			if (partner == null) {
				logger.error("Error by saving partner!");
				addErrorMessage("", "Partner adatok mentése sikertelen!");
				return;
			}
			logger.info(getData().getPartner().getNev() + " data is updated!");
			addInfoMessage("", "Partner adatok mentve!");
		} catch (Exception e) {
			logger.error("Error by " + getData().getPartner().getNev() + " update", e);
			addErrorMessage("", "Partner adatok mentése sikertelen!");
			return;
		}
	}

	public String addNewPartner() {

		getData().setPartner(new Partner());
		getData().setNewUser(true);
		return null;
	}

	public String changePasswordAction() {

		if (getData().getPassword2().equals("") || getData().getPassword1().equals("")) {
			addErrorMessage("", "Nincs kitöltve a jelszó mező!");
			return null;
		}
		if (!getData().getPassword1().equals(getData().getPassword2())) {
			addErrorMessage("", "A két jelszó nem egyezik meg!");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}
		try {
			if (getData().getPassword1().equals(getData().getPassword2())) {

				Partner partner = new Partner();
				if (getManagedPartner() != null)
					partner = getManagedPartner();
				if (getManagedRenter() != null)
					partner = getManagedRenter();
				partner.setWebpassw(getData().getPassword1());
				partner.setModdatum(new Date());
				getPartnerService().updatePartner(partner);
				logger.info(partner.getWeblogin() + " password is updated!");
				addInfoMessage("", "Jelszó módosítva!");
			}
		} catch (Exception e) {
			logger.error("Error by update user", e);
			addFatalMessage("", "Jelszó módosítás sikertelen!");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}
		getData().setPassword1("");
		getData().setPassword2("");
		return null;
	}

	public List<SelectItem> getPaymentItems() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Fizimod> list = getPartnerService().getPaymentTypes();
		if (list == null)
			return new ArrayList<SelectItem>();
		for (Fizimod item : list) {
			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage().equals("hu"))
				selectItemList.add(new SelectItem(item.getFizimod()));
			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage().equals("en"))
				selectItemList.add(new SelectItem(item.getFizimode()));
		}
		return selectItemList;
	}

	public String showAllPartner() {

		try {
			getData().setPartnerSearchText(null);
			getData().setAllPartner(getPartnerService().getAllPartner());
		} catch (Exception e) {
			logger.error("Cannot get all partner!", e);
			return null;
		}
		return null;
	}

	public String searchPartnerAction() {

		try {
			getData().setAllPartner(getPartnerService().getPartnersByName(getData().getPartnerSearchText()));
		} catch (Exception e) {
			logger.error("Error by searching partner!", e);
			return null;
		}
		return null;
	}

	public String showChoosenPartnerData() {

		try {
			if (getData().getSelectedPartnerId() == 0) {
				return null;
			}
			List<Partner> partners = getPartnerService().getPartnerById(getData().getSelectedPartnerId());
			if (partners == null) {
				return null;
			}
			getData().setPartner(partners.get(0));
		} catch (Exception e) {
			logger.error("Error by getting partner!", e);
			return null;
		}
		return null;
	}

	private boolean checkPartnerData(Partner partner) {
		if (partner.getNev().equals("")) {
			return false;
		}
		return true;
	}

	public PartnerDataUpdateData getData() {
		return data;
	}

	public void setData(PartnerDataUpdateData data) {
		this.data = data;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

}
