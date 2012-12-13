package hu.iboard.coandco.datamagic.service.userservice;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.dmvsorsz.DmvSorsz;
import hu.iboard.coandco.datamagic.model.dolgozo.Dolgozo;
import hu.iboard.coandco.datamagic.model.partner.Partner;

import java.util.Date;
import java.util.List;

public class UserService extends UserServiceBase {

	@Override
	public Partner getPartnerById(Integer id) {
		if (id == null)
			return null;
		return getPartnerDao().getPartnerById(id);
	}

	@Override
	public String getPartnerNameByPartnerId(Integer partnerId) {
		if (partnerId == null)
			return null;
		return getPartnerDao().getPartnerNameByPartnerId(partnerId);
	}

	@Override
	public List<Object[]> getPartnersByName(String name) {
		if (name == null)
			return null;
		return getPartnerDao().getPartnersByName(name);
	}

	@Override
	public Dolgozo getWorkerById(Integer workerId) {
		if (workerId == null)
			return null;
		return getWorkerDao().getWorkerById(workerId);
	}

	@Override
	public Partner partnerLogin(String userName, String password) {
		if (userName == null || password == null)
			return null;
		return getPartnerDao().getPartnerByLogin(userName, password);
	}

	@Override
	public Dolgozo workerLogin(String userName, String password) {
		if (userName == null || password == null)
			return null;
		return getWorkerDao().getWorkerByLogin(userName, password);
	}

	@Override
	public Customer customerLogin(String userName, String password) {
		if (userName == null || password == null)
			return null;
		return getCustomerDao().getCustomerByLogin(userName, password);
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		if (customerId == null)
			return null;
		return getCustomerDao().getCustomerById(customerId);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer == null)
			return null;
		return getCustomerDao().saveOrUpdateCustomer(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return getCustomerDao().getCustomerByEmail(email);
	}

	@Override
	public Partner getPartnerByEmail(String email) {
		return getPartnerDao().getPartnerByEmail(email);
	}	
	
	@Override
	public Integer getLastRecordFromCustomers() {
		return getCustomerDao().getLastCustomerId();
	}

	@Override
	public Admin getAdminByLogin(String email, String password) {
		return getAdminDao().getAdminByLogin(email, password);
	}

	@Override
	public List<Admin> getAllAdmin() {
		return getAdminDao().getAllAdmin();
	}

	@Override
	public Admin saveOrUpdateAdmin(Admin admin) {
		if (admin == null)
			return null;
		return getAdminDao().saveOrUpdateAdmin(admin);
	}

	@Override
	public void deleteAdmin(Admin admin) {
		if (admin == null)
			return;
		getAdminDao().deleteAdmin(admin);

	}

	@Override
	public Partner saveOrUpdatePartner(Partner partner) {
		if (partner == null)
			return null;
		partner.setFizmod("K�szp�nz");
		partner.setUgyint(9999999);
		partner.setVcsopkod(new Long(999999));
		partner.setNyelv(1);
		partner.setFkbeuex(1);
		partner.setInsdatum(new Date());
		partner.setInskod(0);
		partner.setModdatum(new Date());
		partner.setModkod(0);
		return getPartnerDao().saveOrUpdatePartner(partner);
	}

	@Override
	public DmvSorsz saveOrUpdateDmvSorsz(DmvSorsz dmvSorsz) {
		if (dmvSorsz == null)
			return null;
		return getDmvSorszDao().saveOrUpdateDmvSorsz(dmvSorsz);
	}

	@Override
	public DmvSorsz getDmvSorszById(Integer id) {
		if (id == null)
			return null;
		return getDmvSorszDao().getDmvSorszById(id);
	}

	@Override
	public Integer getPartnerIdForDmvSorsz() {
		Integer partnerId = getDmvSorszById(1).getPartner();
		Partner partner = getPartnerById(partnerId);
		if (partner == null)
			return partnerId;
		Integer vevokod = partner.getVevokod();

		while (partner != null) {
			vevokod++;
			partner = getPartnerById(vevokod);
		}
		return vevokod;
	}

	@Override
	public Integer getPartnerIdForDmvSorszPartszalId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getLastEmptyPartnerId() {
		return getPartnerDao().getLastEmptyPartnerId();
	}
}
