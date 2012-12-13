package hu.iboard.coandco.datamagic.dao.dmvsorsz;

import hu.iboard.coandco.datamagic.model.dmvsorsz.DmvSorsz;

public interface IDmvSorszDao {

	public DmvSorsz saveOrUpdateDmvSorsz(DmvSorsz dmvSorsz);

	public DmvSorsz getDmvSorszById(Integer id);
}
