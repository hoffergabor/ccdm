package hu.iboard.coandco.datamagic.dao.cikkcsop;

import hu.iboard.coandco.datamagic.generated.Cikkcsop;

import java.math.BigDecimal;
import java.util.List;

public interface ICikkcsopDao {

	public Cikkcsop getCikkcsopById(BigDecimal kod);

	public List<Object[]> getCikkcsopObjsByKod(BigDecimal kod);

	public List<Object[]> getAllCikkcsopObjs();
	
	public Boolean isCikkcsopEmpty(Cikkcsop cikkcsop);
	
	public Boolean isCikkcsopEmpty(BigDecimal csopkod);
}
