package hu.iboard.coandco.datamagic.service.userservice;

import hu.iboard.coandco.datamagic.dao.admin.IAdminDao;
import hu.iboard.coandco.datamagic.dao.customer.ICustomerDao;
import hu.iboard.coandco.datamagic.dao.dmvsorsz.IDmvSorszDao;
import hu.iboard.coandco.datamagic.dao.partner.IPartnerDao;
import hu.iboard.coandco.datamagic.dao.worker.IWorkerDao;
import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.dmvsorsz.DmvSorsz;
import hu.iboard.coandco.datamagic.model.dolgozo.Dolgozo;
import hu.iboard.coandco.datamagic.model.partner.Partner;

import java.util.List;

public abstract class UserServiceBase {

	private IPartnerDao partnerDao;
	private IWorkerDao workerDao;
	private ICustomerDao iCustomerDao;
	private IAdminDao adminDao;
	private IDmvSorszDao dmvSorszDao;

	public abstract Partner getPartnerById(Integer id);

	public abstract List<Object[]> getPartnersByName(String name);

	public abstract String getPartnerNameByPartnerId(Integer partnerId);

	public abstract Dolgozo getWorkerById(Integer workerId);

	public abstract Customer getCustomerById(Integer customerId);

	public abstract Dolgozo workerLogin(String userName, String password);

	public abstract Partner partnerLogin(String userName, String password);

	public abstract Customer customerLogin(String userName, String password);

	public abstract Customer saveOrUpdateCustomer(Customer customer);

	public abstract Customer getCustomerByEmail(String email);
	
	public abstract Partner getPartnerByEmail(String email);

	public abstract Integer getLastRecordFromCustomers();

	public abstract Admin getAdminByLogin(String email, String password);

	public abstract List<Admin> getAllAdmin();

	public abstract Admin saveOrUpdateAdmin(Admin admin);

	public abstract void deleteAdmin(Admin admin);

	public abstract Partner saveOrUpdatePartner(Partner partner);

	public abstract DmvSorsz saveOrUpdateDmvSorsz(DmvSorsz dmvSorsz);

	public abstract DmvSorsz getDmvSorszById(Integer id);

	public abstract Integer getPartnerIdForDmvSorsz();

	public abstract Integer getPartnerIdForDmvSorszPartszalId();
	
	public abstract Integer getLastEmptyPartnerId();

	public IPartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao (IPartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	public IWorkerDao getWorkerDao() {
		return workerDao;
	}

	public void setWorkerDao (IWorkerDao workerDao) {
		this.workerDao = workerDao;
	}

	public ICustomerDao getCustomerDao() {
		return iCustomerDao;
	}

	public void setCustomerDao(ICustomerDao iCustomerDao) {
		this.iCustomerDao = iCustomerDao;
	}

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public IDmvSorszDao getDmvSorszDao() {
		return dmvSorszDao;
	}

	public void setDmvSorszDao (IDmvSorszDao dmvSorszDao) {
		this.dmvSorszDao = dmvSorszDao;
	}

}
