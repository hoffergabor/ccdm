package hu.iboard.coandco.datamagic.presentation.controllers.partnerdataupdate;

import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.email.EmailServiceBase;
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
	@ManagedProperty(value = "#{emailService}")
	private EmailServiceBase emailService;

	@Override
	public void loadData() {
		if (getManagedPartner() != null) {
			getData().setPartner(getManagedPartner());
		} else {
			getData().setPartner(new Partner());
		}
		getData().setPassword1(null);
		getData().setPassword2(null);
		getData().setPaymentItems(getPaymentItems());
		getData().setEmail(getData().getPartner().getEmail());
		getData().setTel1(getData().getPartner().getTel1());
		getData().setTel2(getData().getPartner().getTel2());
		getData().setFax1(getData().getPartner().getFax1());
		getData().setFax2(getData().getPartner().getFax2());

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

	public String sendEmailWithChangedDatas() {
		String emailBody = "";
		String subject = "Partner adat változás";
		boolean changedData = false;
		try {
			if (!getData().getFax1().equals(getData().getPartner().getFax1()) || !getData().getFax2().equals(getData().getPartner().getFax2())
					|| !getData().getTel1().equals(getData().getPartner().getTel1()) || !getData().getTel2().equals(getData().getPartner().getTel2())
					|| !getData().getEmail().equals(getData().getPartner().getEmail())) {
				changedData = true;
			}
			if(changedData){
				getManagedPartner().setModdatum(new Date());
				getManagedPartner().setTel1(getData().getTel1());
				getManagedPartner().setTel2(getData().getTel2());
				getManagedPartner().setFax1(getData().getFax1());
				getManagedPartner().setFax2(getData().getFax2());
				getManagedPartner().setEmail(getData().getEmail());
				getPartnerService().updatePartner(getManagedPartner());
			}
			Partner oldPartner = getPartnerService().getPartnerByReferenceId(getManagedPartner().getVevokod());
			if (!oldPartner.getNev().equals(getData().getPartner().getNev())) {
				emailBody = emailBody + " Új név: " + getData().getPartner().getNev() + "\n";
			}
			if (!oldPartner.getIrsz().equals(getData().getPartner().getIrsz())) {
				emailBody = emailBody + " Új irányítószám: " + getData().getPartner().getIrsz() + "\n";
			}
			if (!oldPartner.getVaros().equals(getData().getPartner().getVaros())) {
				emailBody = emailBody + " Új városnévnév: " + getData().getPartner().getVaros() + "\n";
			}
			if (!oldPartner.getSzallcim().equals(getData().getPartner().getSzallcim())) {
				emailBody = emailBody + " Új szállításicím: " + getData().getPartner().getSzallcim() + "\n";
			}
			if (!oldPartner.getLevirsz().equals(getData().getPartner().getLevirsz())) {
				emailBody = emailBody + " Új levelezési irányítószám: " + getData().getPartner().getLevirsz() + "\n";
			}
			if (!oldPartner.getLevvaros().equals(getData().getPartner().getLevvaros())) {
				emailBody = emailBody + " Új levelezési városnév: " + getData().getPartner().getLevvaros() + "\n";
			}
			if (!oldPartner.getLevcim().equals(getData().getPartner().getLevcim())) {
				emailBody = emailBody + " Új levelezési cím: " + getData().getPartner().getLevcim() + "\n";
			}
			if (!oldPartner.getTel1().equals(getData().getPartner().getTel1())) {
				emailBody = emailBody + " Új telefonszám1: " + getData().getPartner().getTel1() + "\n";
			}
			if (!oldPartner.getTel2().equals(getData().getPartner().getTel2())) {
				emailBody = emailBody + " Új telefonszám2: " + getData().getPartner().getTel2() + "\n";
			}
			if (!oldPartner.getFax1().equals(getData().getPartner().getFax1())) {
				emailBody = emailBody + " Új fax1: " + getData().getPartner().getFax1() + "\n";
			}
			if (!oldPartner.getFax2().equals(getData().getPartner().getFax2())) {
				emailBody = emailBody + " Új fax2: " + getData().getPartner().getFax2() + "\n";
			}
			if (!oldPartner.getEmail().equals(getData().getPartner().getEmail())) {
				emailBody = emailBody + " Új email: " + getData().getPartner().getEmail() + "\n";
			}
			if (!oldPartner.getHonlap().equals(getData().getPartner().getHonlap())) {
				emailBody = emailBody + " Új honlap: " + getData().getPartner().getHonlap() + "\n";
			}
			if (!oldPartner.getUgyvezeto().equals(getData().getPartner().getUgyvezeto())) {
				emailBody = emailBody + " Új ügyvezető: " + getData().getPartner().getUgyvezeto() + "\n";
			}
			if (!oldPartner.getUgyvtel().equals(getData().getPartner().getUgyvtel())) {
				emailBody = emailBody + " Új ügyvezető telefonszám: " + getData().getPartner().getUgyvtel() + "\n";
			}
			if (!oldPartner.getUgyvemail().equals(getData().getPartner().getUgyvemail())) {
				emailBody = emailBody + " Új ügyvezető email: " + getData().getPartner().getUgyvemail() + "\n";
			}
			if (!oldPartner.getKapcstel().equals(getData().getPartner().getKapcstel())) {
				emailBody = emailBody + " Új kapcsolattartó telefonszám: " + getData().getPartner().getKapcstel() + "\n";
			}
			if (!oldPartner.getKapcsemail().equals(getData().getPartner().getKapcsemail())) {
				emailBody = emailBody + " Új kapcsolattartó email: " + getData().getPartner().getKapcsemail() + "\n";
			}
			if (!oldPartner.getCegjegyzek().equals(getData().getPartner().getCegjegyzek())) {
				emailBody = emailBody + " Új cégjegyzékszám: " + getData().getPartner().getCegjegyzek() + "\n";
			}
			if (!oldPartner.getAdoszam().equals(getData().getPartner().getAdoszam())) {
				emailBody = emailBody + " Új adószám: " + getData().getPartner().getAdoszam() + "\n";
			}
			if (!oldPartner.getBanknev().equals(getData().getPartner().getBanknev())) {
				emailBody = emailBody + " Új banknév: " + getData().getPartner().getBanknev() + "\n";
			}
			if (!oldPartner.getSzlaszam().equals(getData().getPartner().getSzlaszam())) {
				emailBody = emailBody + " Új számlaszám: " + getData().getPartner().getSzlaszam() + "\n";
			}
			
			boolean success = getEmailService().sendEmail(EMAIL_SMTP_HOST, EMAIL_SMTP_USER, EMAIL_SMTP_PASS, EMAIL_FROM, EMAIL_TO, subject, emailBody,
					EMAIL_SMTP_PORT);
			if (success)
				addInfoMessage("", "Adatváltozás kérést elküldtük!");
			else {
				addErrorMessage("", "Adatváltozás kérés sikertelen!");
				logger.error("Error by sending mail with changed partner datas!");
			}
		} catch (Exception e) {
			addErrorMessage("", "Adatváltozás kérés sikertelen!");
			logger.error("Error by sending mail with changed partner datas!");
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

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

}
