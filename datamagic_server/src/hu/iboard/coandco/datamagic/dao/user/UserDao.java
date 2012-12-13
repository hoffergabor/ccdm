package hu.iboard.coandco.datamagic.dao.user;

import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport implements IUserDao {

	public UserDao() {
	}

//	@Override
//	public Integer saveOrUpdateUser(UserVO user) {
//		getHibernateTemplate().saveOrUpdate(user);
//		return user.getUserId();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public UserVO getUserByUserName(String userName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserVO.class).add(Restrictions.eq("userName", userName));
		Collection<UserVO> user = getHibernateTemplate().findByCriteria(criteria, 0, 1);
		if (user.size() == 0) {
			return null;
		} else {
			return user.iterator().next();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findUserByCriteria(DetachedCriteria criteria) {
		List<UserVO> users = null;
		users = getHibernateTemplate().findByCriteria(criteria);
		if (users.size() == 0) {
			return null;
		}
		return users;
	}

	@Override
	public void deleteUser(UserVO user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public UserVO loadUser(Integer id) {
		return (UserVO) getHibernateTemplate().get(UserVO.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserVO login(String userName, String password) {
		List<UserVO> users = new ArrayList<UserVO>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(UserVO.class).add(
					Restrictions.eq("userName", userName)).add(Restrictions.eq("userPassword", password)).add(
					Restrictions.eq("isActive", true));

			users = getHibernateTemplate().findByCriteria(criteria);
			if (users.size() == 0) {
				logger.info("No such user in database " + userName);
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by login!!!", e);
			return null;
		}
		return users.get(0);
	}

}
