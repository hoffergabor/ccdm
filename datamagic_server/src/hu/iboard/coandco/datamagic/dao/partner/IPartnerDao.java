package hu.iboard.coandco.datamagic.dao.partner;

import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;

import java.util.List;

public interface IPartnerDao {

	/**
	 * @param user
	 * @return
	 */

	public Partner loadPartner(Integer id);

	public Partner updatePartner(Partner partner);

	public List<Partner> getPartnerById(Integer id);

	public List<Object[]> getAllPartner();

	public List<Object[]> getPartnersByName(String name);

	public List<Fizimod> getPaymentTypes();

	public Partner getPartnerByReferenceId(Integer referenceId);

	public List<Object[]> getPartnersNameByPartnerId(List<Integer> partnerIds);

	public String getPartnerNameByPartnerId(Integer partnerId);

	public List<Object[]> getPartnersNameByPartnerName(List<Integer> partnerIds, String name);

	public Partner getPartnerByLogin(String userName, String password);
	
	public Partner getPartnerByEmergencyLogin(String userName);
	
	public List<Partner> getStoresById(Integer id);

}
