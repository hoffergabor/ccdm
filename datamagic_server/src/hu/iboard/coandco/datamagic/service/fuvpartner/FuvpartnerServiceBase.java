package hu.iboard.coandco.datamagic.service.fuvpartner;

import hu.iboard.coandco.datamagic.dao.fuvpartner.IFuvpartnerDao;
import hu.iboard.coandco.datamagic.dao.fuvtura.IFuvturaDao;
import hu.iboard.coandco.datamagic.generated.Fuvpartner;
import hu.iboard.coandco.datamagic.generated.Fuvtura;

import java.util.Date;
import java.util.List;

public abstract class FuvpartnerServiceBase {

	private IFuvpartnerDao fuvpartnerDao;

	private IFuvturaDao fuvturaDao;

	public abstract Fuvpartner getFuvpartnerById(Integer id);

	public abstract List<Fuvpartner> getFuvpartnersByPartnerId(Integer id);

	public abstract Fuvtura getFuvturaById(Integer id);

	public abstract List<Fuvtura> getFuvturaByPartnerUtvonal(Integer id);

	public abstract List<Date> loadAvaibleFuvDates(Integer vevokod, Integer tura);

	public IFuvpartnerDao getFuvpartnerDao() {
		return fuvpartnerDao;
	}

	public void setFuvpartnerDao(IFuvpartnerDao fuvpartnerDao) {
		this.fuvpartnerDao = fuvpartnerDao;
	}

	public IFuvturaDao getFuvturaDao() {
		return fuvturaDao;
	}

	public void setFuvturaDao(IFuvturaDao fuvturaDao) {
		this.fuvturaDao = fuvturaDao;
	}

}
