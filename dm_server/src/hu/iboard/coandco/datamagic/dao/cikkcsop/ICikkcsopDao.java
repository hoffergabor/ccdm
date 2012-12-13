package hu.iboard.coandco.datamagic.dao.cikkcsop;

import hu.iboard.coandco.datamagic.model.cikkcsop.Cikkcsop;

import java.math.BigDecimal;
import java.util.List;

public interface ICikkcsopDao {

	public Cikkcsop getCikkcsopById(BigDecimal kod);

	public List<Object[]> getCikkcsopObjsByKod(BigDecimal kod);

	public List<Object[]> getAllCikkcsopObjs();
	
	public Boolean IsCikkcsopEmpty(Cikkcsop cikkcsop);
	
	public Boolean IsCikkcsopEmpty(BigDecimal csopkod);
}
