package hu.iboard.coandco.datamagic.dao.product;

import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDao {

	public Aru getProductById(Integer arukod);

	public List<AruKisker> getProductByName(String name);

	public List<AruKisker> getProductByNameNew(String name);

	public List<String> getOptProductByName(String name);

	public List<AruKisker> getProductsByCsopkod(BigDecimal csopkod);

	public List<AruKisker> getProductsByCsopkods(List<BigDecimal> csopkods);

	public AruKisker getProductKiskerById(Integer arukod);
	
	public List<AruKisker> getKiemeltProducts();
	
	public List<AruKisker> getProductsByCikkcsopMegn(String megn);

	//public ProductVO convertProductToVO(Aru product);
}