package hu.iboard.coandco.datamagic.dao.admin;

import hu.iboard.coandco.datamagic.model.admin.Admin;

import java.util.List;

public interface IAdminDao {

	public Admin getAdminByLogin(String email, String password);

	public List<Admin> getAllAdmin();

	public Admin saveOrUpdateAdmin(Admin admin);

	public void deleteAdmin(Admin admin);

}
