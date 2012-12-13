/**
 * 
 */
package hu.iboard.coandco.datamagic.service.partner;

import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;

import java.util.List;

public class PartnerService extends PartnerServiceBase {

	public List<Partner> getPartnerById(Integer id) {
		return getPartnerDao().getPartnerById(id);
	}

	public Partner getAllPartnerDataById(Integer id) {
		return getPartnerDao().loadPartner(id);
	}

	@Override
	public Partner updatePartner(Partner partner) {
		return getPartnerDao().updatePartner(partner);
	}

	@Override
	public List<Object[]> getAllPartner() {
		return getPartnerDao().getAllPartner();
	}

	@Override
	public List<Object[]> getPartnersByName(String name) {
		return getPartnerDao().getPartnersByName(name);
	}

	@Override
	public List<Fizimod> getPaymentTypes() {
		return getPartnerDao().getPaymentTypes();
	}

	@Override
	public Partner getPartnerByReferenceId(Integer referenceId) {
		if(referenceId==null)
			return null;
		return getPartnerDao().getPartnerByReferenceId(referenceId);
	}

	@Override
	public List<Object[]> getPartnersNameByPartnerId(List<Integer> partnerIds) {
		if(partnerIds==null)
			return null;
		return getPartnerDao().getPartnersNameByPartnerId(partnerIds);
	}

	@Override
	public List<Object[]> getPartnersNameByPartnerName(List<Integer> partnerIds, String name) {
		if(partnerIds==null || name==null)
			return null;
		return getPartnerDao().getPartnersNameByPartnerName(partnerIds,name);
	}

	@Override
	public String getPartnerNameByPartnerId(Integer partnerId) {
		if(partnerId==null)
			return null;
		return getPartnerDao().getPartnerNameByPartnerId(partnerId);
	}

	@Override
	public List<Integer> getAllRenters() {
		return getBerbeadasDao().getAllPartnerIdsFromBerbeadas();
	}

	@Override
	public List<Partner> getStoresById(Integer id) {
		if(id==null)
			return null;
		return getPartnerDao().getStoresById(id);
	}
}
