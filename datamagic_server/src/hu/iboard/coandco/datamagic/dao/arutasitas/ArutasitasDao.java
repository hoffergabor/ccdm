package hu.iboard.coandco.datamagic.dao.arutasitas;

import hu.iboard.coandco.datamagic.generated.Arutasitas;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ArutasitasDao extends HibernateDaoSupport implements IArutasitasDao{

	@Override
	public Arutasitas getArutasitasById(Integer id) {
		return (Arutasitas) getHibernateTemplate().get(Arutasitas.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Arutasitas> getAktualisAkcio() {
		List<Arutasitas> records = null;
		try {
			String query = "from Arutasitas a where a.kezdes <= '" + getCurrentDate() + "' and a.zaras >= '"
					+ getCurrentDate() + "'";
			records = getHibernateTemplate().find(query);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list arutasitas aktualis akcio: ", e);
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
