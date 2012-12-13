package hu.iboard.coandco.datamagic.dao.user;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import hu.iboard.coandco.datamagic.vo.user.UserVO;

public interface IUserDao {

	/**
	 * @param user
	 * @return
	 */
	//public Integer saveOrUpdateUser(UserVO user);

	public List<UserVO> findUserByCriteria(DetachedCriteria criteria);

	public void deleteUser(UserVO user);

	public UserVO loadUser(Integer id);

	public UserVO getUserByUserName(String userName);

	public UserVO login(String userName, String password);

}
