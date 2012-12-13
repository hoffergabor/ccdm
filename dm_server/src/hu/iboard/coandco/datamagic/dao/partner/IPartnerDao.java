package hu.iboard.coandco.datamagic.dao.partner;

import hu.iboard.coandco.datamagic.model.partner.Partner;

import java.util.List;

public interface IPartnerDao {

	public Partner getPartnerById(Integer id);

	public List<Object[]> getPartnersByName(String name);

	public String getPartnerNameByPartnerId(Integer partnerId);

	public Partner getPartnerByLogin(String userName, String password);

	public Partner saveOrUpdatePartner(Partner partner);
	
	public Integer getLastEmptyPartnerId();
	
	public Partner  getPartnerByEmail(String email);

}
