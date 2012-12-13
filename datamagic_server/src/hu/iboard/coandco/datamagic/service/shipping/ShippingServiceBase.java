package hu.iboard.coandco.datamagic.service.shipping;

import hu.iboard.coandco.datamagic.dao.shipping.IShippingDao;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Date;
import java.util.List;

public abstract class ShippingServiceBase extends ServiceBase {

	private IShippingDao shippingDao;

	public IShippingDao getShippingDao() {
		return shippingDao;
	}

	public void setShippingDao(IShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	public abstract List<ShippingVO> getInitShippings(UserVO user, Integer vevokod);

	public abstract List<ShippingVO> getShippingsByDateFiltered(Integer vevokod, Date from, Date to);

	public abstract List<Object[]> getShippingsForChart(UserVO user, Integer partnerId, Date from, Date to);

	public abstract Object[] getSummaryForChart(UserVO user, Integer partnerId);

	public abstract List<ShippingItemVO> getShippingItemsByShippingId(String sorszam);
}
