/**
 * 
 */
package hu.iboard.coandco.datamagic.generated;

import hu.iboard.coandco.datamagic.util.HibernateUtil;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.Session;


/**
 * @author Black-Core
 *
 */
@Entity
@NamedQuery(query = "SELECT intezkedes as id, sum(round(Menny * EarForint * (1+((szolgaltat + bonyolit)/100)),0)) " +
		"as netto FROM IntezAru IntezkedesNettoAr WHERE intezkedes = :id GROUP BY intezkedes", 
		name = "intezkedesNettoArQuery")
@SqlResultSetMapping(columns = { 
			@ColumnResult(name = "id"), 
			@ColumnResult(name = "netto")},
		name = "intezkedesNettoArQuery")
public class IntezkedesNettoAr {

	private int id;
	private int netto;
	
	public IntezkedesNettoAr() {
		// TODO Auto-generated constructor stub
	}
	
	public IntezkedesNettoAr(int id) {
//		System.out.println("IntezkedesNettoAr instance");
//		this.id = id;
//		Session session = HibernateUtil.getSession();
//		session.beginTransaction();
//		List result = session.getNamedQuery("intezkedesNettoArQuery").setInteger("id", id).list();
//		session.getTransaction().commit();
//		//System.out.println("IntezkedesNettoAr: " + ((IntezkedesNettoAr)result.get(0)));
//		this.netto = ((IntezkedesNettoAr)result.get(0)).getNetto();
//		//System.out.println("IntezkedesNettoAr netto: " + ((IntezkedesNettoAr)result.get(0)).getNetto());
	}
	
	public IntezkedesNettoAr(int id, int netto) {
		this.id = id;
		this.netto = netto;
	}
	
	@Id
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNetto() {
		return this.netto;
	}
	
	public void setNetto(int netto) {
		this.netto = netto;
	}
	
	public static int getNettoAr(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.getNamedQuery("intezkedesNettoArQuery").setParameter("id", id).list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0) {
//			IntezkedesNettoAr intezkedesNettoAr = (IntezkedesNettoAr) results.get(0);
//			return intezkedesNettoAr.getNetto();
			Object[] nettoAr = (Object[]) results.get(0);
			return Integer.parseInt(nettoAr[1].toString());
		}
		return 0;
	}

}
