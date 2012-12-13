package hu.iboard.coandco.datamagic.service.bizkomment;

import hu.iboard.coandco.datamagic.generated.Bizkomment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class BizkommentService extends BizkommentServiceBase {

	@Override
	public List<Bizkomment> getPartnerBizkomments(String bizazon) {
		List<Bizkomment> komments = new ArrayList<Bizkomment>();
		try {
			if (bizazon == null)
				return null;
			DetachedCriteria criteria = DetachedCriteria.forClass(Bizkomment.class).add(
					Restrictions.eq("bizazon", "p" + bizazon));

			komments = getBizkommentDao().findBizkommentByCriteria(criteria);

			if (komments == null || komments.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("error occured at processing getBizkomments: " + e.getMessage());
		}
		return komments;
	}

	@Override
	public List<Bizkomment> getOrderBizkomments(String bizazon) {
		List<Bizkomment> komments = new ArrayList<Bizkomment>();
		try {
			if (bizazon == null)
				return null;
			DetachedCriteria criteria = DetachedCriteria.forClass(Bizkomment.class).add(
					Restrictions.eq("bizazon", bizazon));

			komments = getBizkommentDao().findBizkommentByCriteria(criteria);

			if (komments == null || komments.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("error occured at processing getBizkomments: " + e.getMessage());
		}
		return komments;
	}

	@Override
	public void savePartnerBizkomment(Bizkomment bizkomment){

		try {
			if (bizkomment != null) {
				bizkomment.setModdatum(new Date());
				getBizkommentDao().saveBizkomment(bizkomment);
			}
		} catch (Exception e) {
			logger.error("Cannot save or update bizkommet for partner", e);
		}
	}

}
