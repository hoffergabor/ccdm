package hu.iboard.coandco.datamagic.dao.berbeadas;

import hu.iboard.coandco.datamagic.generated.Berbeadas;

import java.util.List;

public interface IBerbeadasDao {

	public List<Integer> getLakasByPartnerId(Integer partnerId);

	public List<Integer> getRentersIdByForRealtyArrangement();

	public Integer getBerbeadasUgyfelById(Integer berbeadasId);

	public List<Integer> getAllPartnerIdsFromBerbeadas();

	public Berbeadas getBerbeadasById(Integer id);

	public List<Berbeadas> getBerbeadasByAktBerCheckContract(List<Integer> aktBer);

}
