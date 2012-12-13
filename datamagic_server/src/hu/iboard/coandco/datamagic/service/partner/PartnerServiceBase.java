package hu.iboard.coandco.datamagic.service.partner;

import hu.iboard.coandco.datamagic.dao.berbeadas.IBerbeadasDao;
import hu.iboard.coandco.datamagic.dao.partner.IPartnerDao;
import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;

import java.util.List;

public abstract class PartnerServiceBase {

	private IPartnerDao partnerDao;
	private IBerbeadasDao berbeadasDao;
	
	public IPartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao(IPartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	public IBerbeadasDao getBerbeadasDao() {
		return berbeadasDao;
	}

	public void setBerbeadasDao(IBerbeadasDao berbeadasDao) {
		this.berbeadasDao = berbeadasDao;
	}

	public abstract List<Partner> getPartnerById(Integer id);

	public abstract Partner getAllPartnerDataById(Integer id);

	public abstract Partner updatePartner(Partner partner);

	public abstract List<Object[]> getAllPartner();

	public abstract List<Object[]> getPartnersByName(String name);

	public abstract List<Fizimod> getPaymentTypes();

	public abstract Partner getPartnerByReferenceId(Integer referenceId);

	public abstract List<Object[]> getPartnersNameByPartnerId(List<Integer> patnerIds);

	public abstract List<Object[]> getPartnersNameByPartnerName(List<Integer> patnerIds, String name);

	public abstract String getPartnerNameByPartnerId(Integer partnerId);

	public abstract List<Integer> getAllRenters();
	
	public abstract List<Partner> getStoresById(Integer id);


}
