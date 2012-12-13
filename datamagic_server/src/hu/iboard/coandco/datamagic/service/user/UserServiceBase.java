package hu.iboard.coandco.datamagic.service.user;

import hu.iboard.coandco.datamagic.dao.partner.IPartnerDao;
import hu.iboard.coandco.datamagic.dao.user.IUserDao;
import hu.iboard.coandco.datamagic.dao.worker.IWorkerDao;
import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.List;

public abstract class UserServiceBase extends ServiceBase {

	private IUserDao userDao;
	private IPartnerDao partnerDao;
	private IWorkerDao workerDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IPartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao(IPartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	public IWorkerDao getWorkerDao() {
		return workerDao;
	}

	public void setWorkerDao(IWorkerDao workerDao) {
		this.workerDao = workerDao;
	}

	public abstract UserVO login(String userName, String password);

	public abstract List<UserVO> getAllUser();

	public abstract void deleteUser(UserVO user);

	//public abstract Integer saveUser(UserVO user);

	//public abstract Integer updateUser(UserVO user);

	public abstract UserVO getUserById(Integer id);
	
	public abstract boolean registerUser(UserVO user, Partner partner);
	
	public abstract UserVO getuserByUserName(String userName);
	
	public abstract Dolgozo workerLogin(String userName, String password);
	
	public abstract Partner partnerLogin(String userName, String password);
	
	public abstract Partner emergencyLogin(String userName);

}
