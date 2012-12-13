package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.profile;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;
import hu.iboard.coandco.datamagic.model.township.TownShip;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressServiceBase;
import hu.iboard.coandco.datamagic.service.userservice.UserServiceBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class ProfileController extends AbstractController {

	@ManagedProperty(value = "#{profileControllerData}")
	private ProfileControllerData data;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressServiceBase shippingAddressService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	
	
	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setShippingAddress(new ShippingAddress());
			getData().setShippingAddresses(new ArrayList<ShippingAddress>());
			getData().setShippingAddressId(null);
			if (getManagedPartner() != null) {
				getData().setPartner(getManagedPartner());
				getData().setShippingAddresses(
						getShippingAddressService().getShippingsByPartner(getData().getPartner()));
				if (getData().getShippingAddresses() != null && getData().getShippingAddresses().size() > 0)
					getData().setShippingAddress(getData().getShippingAddresses().get(0));
			}
			if (getManagedPartner().getSzlfizeto() != 0) {
				getData().setPartner(getUserService().getPartnerById(getManagedPartner().getSzlfizeto()));			
			}
		}
	}	

	public List<String> completeTown(String input) {
		List<String> list = new ArrayList<String>();
		List<TownShip> tsList = getShippingAddressService().getAllTownship(input);
		String name = "";
		for (TownShip townShip : tsList) {
			if (!name.equals(townShip.getName())) {
				list.add(townShip.getName());
			}
			name = townShip.getName();
		}
		return list;
	}

	public void addNewShippingAddress(ActionEvent event) {
		getData().setShippingAddress(new ShippingAddress());
	}

	public void saveUserDatas(ActionEvent event) {
		try {
			if (getData().getPartner() != null) {
				getData().getPartner().setModdatum(new Date());
				Partner partner = getUserService().saveOrUpdatePartner(getData().getPartner());
				if (partner == null) {
					addErrorMessage("", "A személyes adatok mentése sikertelen.");
					logger.error("ERROR BY SAVING PARTNER USER DATAS ON PROFILE PAGE!");
					return;
				}
			}
			if (getData().getPartner() != null) {
				getData().getPartner().setModdatum(new Date());
			}
			if (getData().getShippingAddress() == null)
				return;

			if (getData().getShippingAddress().getInsDate() == null)
				getData().getShippingAddress().setInsDate(new Date());
			getData().getShippingAddress().setModDate(new Date());
//			if (getData().getCustomer() != null)
//				getData().getShippingAddress().setCustomer(getData().getCustomer());
			if (getData().getPartner() != null)
				getData().getShippingAddress().setPartner(getData().getPartner());
			ShippingAddress shipping = getShippingAddressService().saveOrUpdateShipping(getData().getShippingAddress());
			if (shipping == null) {
				addErrorMessage("", "A szállítási adatok mentése sikertelen.");
				logger.error("ERROR BY SAVING SHIPPING DATAS ON PROFILE PAGE!");
				return;
			}
			getUserService().saveOrUpdatePartner(getData().getPartner());
			addInfoMessage("", "Az adatok mentése sikeres.");
			boolean contains = false;
			for (ShippingAddress shippingAddress : getData().getShippingAddresses()) {
				if (shippingAddress.getShippingId().equals(shipping.getShippingId()))
					contains = true;
			}
			if (!contains)
				getData().getShippingAddresses().add(shipping);

		} catch (Exception e) {
			addErrorMessage("", "Hiba történt az adatok mentése közben.");
			logger.error("ERROR BY SAVING DATAS ON PROFILE PAGE!", e);
		}
	}

	public void changeShippingAddress(ActionEvent event) {
		if (getData().getShippingAddressId() != null)
			getData().setShippingAddress(getShippingAddressService().getShippingById(getData().getShippingAddressId()));
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public ProfileControllerData getData() {
		return data;
	}

	public void setData(ProfileControllerData data) {
		this.data = data;
	}

	public ShippingAddressServiceBase getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressServiceBase shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}
}
