package hu.iboard.coandco.datamagic.dao.kedvezmeny;

import hu.iboard.coandco.datamagic.generated.Kedvezmeny;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KedvezmenyDao extends HibernateDaoSupport implements IKedvezmenyDao {

	@Override
	public Kedvezmeny getKedvezmenyById(Integer id) {
		return (Kedvezmeny) getHibernateTemplate().get(Kedvezmeny.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kedvezmeny> getAktualisKedvezmeny() {
		List<Kedvezmeny> records = null;
		try {
			String query = "from Kedvezmeny k where k.toldatum <= '" + getCurrentDate() + "' and k.igdatum >= '"
					+ getCurrentDate() + "'";
			records = getHibernateTemplate().find(query);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list aktualis kedvezmeny: ", e);
			return null;
		}
		return records;
	}

	@SuppressWarnings("deprecation")
	private String getCurrentDate() {
		Date date = new Date();
		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "." + dateFromMonth + "." + dateFromDay + ".";

		return convertedDate;

	}

}
