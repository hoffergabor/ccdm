package hu.iboard.coandco.datamagic.dao.vevoarak;

import hu.iboard.coandco.datamagic.generated.Vevoarak;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VevoarakDao extends HibernateDaoSupport implements IVevoarakDao {

	@Override
	public Vevoarak getVevoarakById(Integer id) {
		return (Vevoarak) getHibernateTemplate().get(Vevoarak.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vevoarak> getVevoarakByKedvezmenyForPartner(Integer id, Integer vevokod) {
		List<Vevoarak> records = null;
		try {
			String query = "from Vevoarak v where v.kedvezmeny='" + id + "' and v.vevokod='" + vevokod + "'";
			records = getHibernateTemplate().find(query);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list vevoarak for partner by kedvezmeny: ", e);
			return null;
		}
		return records;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vevoarak> getActualVevoarakForPartner(Integer vevokod) {
		List<Vevoarak> records = null;
		try {
			String query = "from Vevoarak v where v.vevokod='" + vevokod + "'";
			query = query + " and v.toldatum<='" + new Timestamp(System.currentTimeMillis()) + "' and v.igdatum>='" + new Timestamp(System.currentTimeMillis())
					+ "'";
			records = getHibernateTemplate().find(query);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list vevoarak for partner: ", e);
			return null;
		}
		return records;
	}
}
