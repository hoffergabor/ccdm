package hu.iboard.coandco.datamagic.service.bizkomment;

import hu.iboard.coandco.datamagic.dao.bizkomment.IBizkommentDao;
import hu.iboard.coandco.datamagic.generated.Bizkomment;
import hu.iboard.coandco.datamagic.service.ServiceBase;

import java.util.List;

public abstract class BizkommentServiceBase extends ServiceBase {

	private IBizkommentDao bizkommentDao;

	public abstract List<Bizkomment> getPartnerBizkomments(String bizazon);

	public abstract List<Bizkomment> getOrderBizkomments(String bizazon);

	public abstract void savePartnerBizkomment(Bizkomment bizkomment);

	public IBizkommentDao getBizkommentDao() {
		return bizkommentDao;
	}

	public void setBizkommentDao(IBizkommentDao bizkommentDao) {
		this.bizkommentDao = bizkommentDao;
	}

}
