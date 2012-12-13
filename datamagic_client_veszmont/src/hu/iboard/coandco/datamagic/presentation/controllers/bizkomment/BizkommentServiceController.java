package hu.iboard.coandco.datamagic.presentation.controllers.bizkomment;

import hu.iboard.coandco.datamagic.generated.Bizkomment;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.bizkomment.BizkommentServiceBase;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "bizkommentserviceController")
@RequestScoped
public class BizkommentServiceController extends AbstractController{

	public static final String BIZKOMMENTSERVICE_ACTION = "bizkomment";
	
	@ManagedProperty(value = "#{bizkommentserviceData}")
	private BizkommentServiceData data;
	@ManagedProperty(value = "#{bizkommentService}")
	private BizkommentServiceBase bizkommentService;
	
	

	@Override
	public void loadData() {
		List<Bizkomment> komments = getBizkommentService().getPartnerBizkomments(
				Integer.valueOf(getManagedPartner().getVevokod()).toString());
		if (komments != null) {
			getData().setPartnerBizkomment(komments.get(0));
		} else {
			getData().setPartnerBizkomment(new Bizkomment());
		}
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(BIZKOMMENTSERVICE_CONTROLLER);
	}

	public String savePartnerBizkomment() {

		if (getData().getPartnerBizkomment() == null)
			return null;
		try {
			if (getData().getPartnerBizkomment().getBizazon() == null) {
				getData().getPartnerBizkomment().setBizazon("p" + Integer.valueOf(getManagedPartner().getVevokod()).toString());
			}
			getBizkommentService().savePartnerBizkomment(getData().getPartnerBizkomment());
			addInfoMessage("PARTNER KOMMENT MENTVE!", "");
		} catch (Exception e) {
			logger.error("Cannot save or update bizkomment for partner", e);
			addFatalMessage("PARTNER KOMMENT MENTESE SIKERTELEN!", "");
			return null;
		}
		return null;
	}


	public BizkommentServiceBase getBizkommentService() {
		return bizkommentService;
	}

	public void setBizkommentService(BizkommentServiceBase bizkommentService) {
		this.bizkommentService = bizkommentService;
	}

	public BizkommentServiceData getData() {
		return data;
	}

	public void setData(BizkommentServiceData data) {
		this.data = data;
	}
	
	

}
