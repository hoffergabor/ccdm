package hu.iboard.coandco.datamagic.dao.project;

import hu.iboard.coandco.datamagic.generated.Project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProjectDao extends HibernateDaoSupport implements IProjectDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectsByPartnerId(Integer partnerId) {

		List<Project> projects = new ArrayList<Project>();
		try {
			if (partnerId != null) {

				DetachedCriteria criteria = DetachedCriteria.forClass(Project.class).add(
						Restrictions.eq("tulajdonos", partnerId)).addOrder(Order.asc("varos")).addOrder(
						Order.asc("cim"));
				projects = getHibernateTemplate().findByCriteria(criteria);

			} else {
				throw new Exception("Bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("Error occured at processing list projects:", e);
			return null;
		}
		return projects;
	}

	@Override
	public Project getProjectById(Integer projectId) {
		if (projectId == null)
			return null;
		return (Project) getHibernateTemplate().get(Project.class, projectId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllProject() {

		List<Object[]> recordsList = null;
		try {
			recordsList = getHibernateTemplate().find(
					"select p.id,p.irsz,p.varos,p.cim from Project p where p.cim!='' and p.id in(select a.project from AruExt a) order by p.varos,p.cim asc");
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting all projects", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getProjectByAddress(String address, Integer vevokod) {
		List<Object[]> recordsList = null;
		String query = "";
		try {
			if (vevokod == null) {
				query = "select p.id,p.irsz,p.varos,p.cim from Project p where p.cim like :address order by p.varos,p.cim asc";
			} else {
				query = "select p.id,p.irsz,p.varos,p.cim from Project p where p.tulajdonos=" + vevokod
						+ " and p.cim like :address order by p.varos asc";
			}
			recordsList = getHibernateTemplate().findByNamedParam(query, "address", "%" + address + "%");
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting all projects", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getProjectAddressById(Integer projectId) {
		List<Object[]> records = null;
		String query = "";
		try {
			query = "select p.munkszam,p.irsz,p.varos,p.cim from Project p where p.id=" + projectId;
			records = getHibernateTemplate().find(query);
			if (records == null || records.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting projects address", e);
			return null;
		}
		return records.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllOwnerName() {
		List<Object[]> recordsList = null;
		String query = "";
		try {
			query = "select p.id,p.nev from Partner p where p.vevokod in (select distinct proj.tulajdonos from Project proj) order by p.nev asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting all owner names", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getOwnerByName(String name) {
		List<Object[]> recordsList = null;
		String query = "";
		try {
			query = "select p.id,p.nev from Partner p where p.vevokod in (select distinct proj.tulajdonos from Project proj) and p.nev like :name order by p.nev asc";
			recordsList = getHibernateTemplate().findByNamedParam(query, "name", "%" + name + "%");
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting all owner names", e);
			return null;
		}
		return recordsList;
	}
}
