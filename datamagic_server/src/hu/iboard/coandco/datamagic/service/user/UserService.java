package hu.iboard.coandco.datamagic.service.user;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class UserService extends UserServiceBase {

	public UserService() {
	}

	public UserVO login(String userName, String password) {
		return getUserDao().login(userName, password);
	}

	@Override
	public List<UserVO> getAllUser() {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserVO.class);
		return getUserDao().findUserByCriteria(criteria);
	}

	@Override
	public void deleteUser(UserVO user) {
		getUserDao().deleteUser(user);
	}

//	@Override
//	public Integer saveUser(UserVO user) {
//		return getUserDao().saveOrUpdateUser(user);
//	}
//
//	@Override
//	public Integer updateUser(UserVO user) {
//		return getUserDao().saveOrUpdateUser(user);
//	}

	@Override
	public UserVO getUserById(Integer id) {
		if (id == null)
			return null;
		return getUserDao().loadUser(id);
	}

	@Override
	public boolean registerUser(UserVO user, Partner partner) {

		try {
			Partner partnervo = getPartnerDao().updatePartner(partner);
//			user.setReferenceId(partnervo.getVevokod());
//			user.setIsActive(false);
//			user.setModdatum(new Date());
//			Integer userId = getUserDao().saveOrUpdateUser(user);

//			if (userId != null && partnervo != null) {
//				return true;
//			}
		} catch (Exception e) {
			logger.error("Error by registering user!", e);
			return false;
		}
		return false;

	}

	@Override
	public UserVO getuserByUserName(String userName) {
		if (userName == null)
			return null;
		return getUserDao().getUserByUserName(userName);
	}

	@Override
	public Partner partnerLogin(String userName, String password) {
		return getPartnerDao().getPartnerByLogin(userName, password);
	}

	@Override
	public Dolgozo workerLogin(String userName, String password) {
		return getWorkerDao().getWorkerByLogin(userName, password);
	}

	@Override
	public Partner emergencyLogin(String userName) {
		if(userName==null)
			return null;
		return getPartnerDao().getPartnerByEmergencyLogin(userName);
	}

}
