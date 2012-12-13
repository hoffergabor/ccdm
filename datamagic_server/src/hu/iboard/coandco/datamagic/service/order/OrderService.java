/**
 * 
 */
package hu.iboard.coandco.datamagic.service.order;

import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.Rendmegj;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.util.DataMagicConstants;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PRIVATE_MEMBER;

public class OrderService extends OrderServiceBase {

	private static final String DEFAULT_STATUS = "Függő";

	public List<OrderVO> getOrders(UserVO user, Integer referenceId, String kivagybe) {

		List<OrderVO> orders = new ArrayList<OrderVO>();

		try {

			if (user != null) {

				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {

					DetachedCriteria criteria = DetachedCriteria.forClass(OrderVO.class).add(Restrictions.eq("vevokod", referenceId))
							.add(Restrictions.like("statusz", DEFAULT_STATUS)).addOrder(Order.desc("kelt"));

					if (kivagybe != null)
						criteria.add(Restrictions.eq("kivagybe", kivagybe));
					orders = getOrderDao().findOrderByCriteria(criteria);

					if (orders == null || orders.size() == 0) {

						DetachedCriteria criteria2 = DetachedCriteria.forClass(OrderVO.class).add(Restrictions.eq("vevokod", referenceId))
								.addOrder(Order.desc("kelt"));
						if (kivagybe != null)
							criteria2.add(Restrictions.eq("kivagybe", kivagybe));
						orders = getOrderDao().findOrderByCriteria(criteria2, 0, 10);
					}

				} else if (user.getUserType().equals(UserVO.WORKER)) {

					DetachedCriteria criteria = DetachedCriteria.forClass(OrderVO.class);
					criteria.add(Restrictions.like("statusz", DEFAULT_STATUS));
					criteria.addOrder(Order.desc("kelt"));
					if (kivagybe != null)
						criteria.add(Restrictions.eq("kivagybe", kivagybe));
					orders = getOrderDao().findOrderByCriteria(criteria, 0, 10);

				} else if (user.getUserType().equals(UserVO.ADMIN)) {

					DetachedCriteria criteria = DetachedCriteria.forClass(OrderVO.class).add(Restrictions.like("statusz", DEFAULT_STATUS))
							.addOrder(Order.desc("kelt"));
					if (kivagybe != null)
						criteria.add(Restrictions.eq("kivagybe", kivagybe));
					orders = getOrderDao().findOrderByCriteria(criteria, 0, 10);
				}

			} else {
				throw new Exception("bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListOrders: " + e.getMessage());
			return null;
		}

		return orders;
	}

	public List<OrderVO> getOrdersByDateFiltered(Integer partnerId, Date from, Date to, Boolean all, String kivagybe) {
		return getOrderDao().getOrdersByDate(partnerId, from, to, all, kivagybe);
	}

	@Override
	public List<OrderItemVO> getOrderItemsByOrderId(String sorszam) {
		List<OrderItemVO> orderItems = null;
		try {
			if (sorszam != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(OrderItemVO.class).add(Restrictions.eq("sorszam", sorszam))
						.addOrder(Order.desc("vfizhdat"));
				orderItems = getOrderDao().findOrderItemByCriteria(criteria);
			} else {
				throw new Exception("bad request: no such order in database");
			}
			if (orderItems == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListOrders: " + e.getMessage());
			return null;
		}
		return orderItems;
	}
	
	@Override
	public Boolean saveOrder(Rendel rendel, List<ProductVO> products, Partner partner) {
		return saveOrder(rendel, products, partner, null);
	}
	
	@Override
	public Boolean saveOrder(Rendel rendel, List<ProductVO> products, Partner partner, List<String> megj) {

		logger.info("Try to save order!");
		Boolean result = false;
		String orderNum = generateSerial();

		try {

			float net = 0; // sums all net prices
			float gross = 0; // sums all gross prices
			float dnet = 0; // sums all net prices by forex rate
			float dgross = 0; // sums all gross prices by forex rate

			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date defaultDate = dfm.parse("1900-01-01 00:00:00");

			Integer cId = Integer.valueOf(partner.getVevokod()); // "c" as
			// "customer"

			String forexRate = "1";
			String forex = "Ft";
			// if (partner.getVevocsop() != null) {
			// forexRate =
			// partner.getVevocsop().getArnev().getDeviza().getArfolyam().toString();
			// forex = partner.getVevocsop().getArnev().getDevizanev();
			// }

			Integer i = 1;
			for (ProductVO vo : products) {
				BigDecimal iQuantity = vo.getMennyiseg(); // mennyiseg
				BigDecimal iPrice = vo.getEar(); // egysegar
				BigDecimal iNetPrice = vo.getEar().multiply(vo.getMennyiseg()); // netto
				BigDecimal iGrossPrice = iNetPrice.multiply(vo.getAfa()); // brutto
				BigDecimal iTaxRate = vo.getAfa(); // afaszorzo

				float hufear = 0;
				if (forexRate != null) {
					hufear = Float.parseFloat(iPrice.toString()) / Float.parseFloat(forexRate);
				} else {
					hufear = Float.parseFloat(iPrice.toString());
				}

				String unikazon = generateUnikazon(Integer.valueOf(vo.getArukod()).toString());

				net += Float.parseFloat(iNetPrice.toString());
				gross += Float.parseFloat(iGrossPrice.toString());
				dnet += Float.parseFloat(iNetPrice.toString()) / Float.parseFloat(forexRate);
				dgross += Float.parseFloat(iGrossPrice.toString()) / Float.parseFloat(forexRate);

				Aru aru = getProductDao().getProductById(vo.getArukod());

				Rendtet tetel = new Rendtet();

				// DEFAULT TETEL VALUES

				tetel.setAlapbizt("");
				tetel.setAtadgyart(defaultDate);
				tetel.setAtadkod(0);
				tetel.setAtfutido(new Long(0));
				tetel.setAtvhely("");
				tetel.setAvar(new BigDecimal(0));
				tetel.setBizki(false);
				tetel.setBizszam("");
				tetel.setBvisszaig(defaultDate);
				tetel.setB_megj("");
				tetel.setEllenkod(0);
				tetel.setFelkeszmny(new BigDecimal(0));
				tetel.setFuvmod("");
				tetel.setGyartido(new Long(0));
				tetel.setGyartkesz(defaultDate);
				tetel.setGyartkezd(defaultDate);
				tetel.setGyartmenny(new BigDecimal(0));
				tetel.setGykezdkod(0);
				tetel.setGyszam("");
				tetel.setGyszam2("");
				tetel.setKiszallmny(new BigDecimal(0));
				tetel.setMegj(vo.getMegj() != null ? vo.getMegj() : "-");
				tetel.setMeomenny(new BigDecimal(0));
				tetel.setMkateg1(0);
				tetel.setMkateg2(0);
				tetel.setMkateg3(0);
				tetel.setMkateg4(0);
				tetel.setMkateg5(0);
				tetel.setMoreindok("");
				tetel.setMorestat(0);
				tetel.setMunkszam("");
				tetel.setMunkszuk(0);
				tetel.setNutrend(false);
				tetel.setOsszekod(0);
				tetel.setRaktaron(false);
				tetel.setRogzkod(0);
				tetel.setSajatszam("");
				tetel.setSelejtmny(new BigDecimal(0));
				tetel.setSortmegn("");
				tetel.setSzamlkelt(defaultDate);
				tetel.setSzamlsorsz("");
				tetel.setSzlevkelt(defaultDate);
				tetel.setSzlevsorsz("");
				tetel.setTeljesitve(false);
				tetel.setTmegrazon("");
				tetel.setHufvar(new BigDecimal(0));
				tetel.setKulsotelj(0);

				// END OF DEFAULT VALUES

				tetel.setSorszam(orderNum);
				tetel.setTetelszam(i);
				tetel.setArukod(aru.getArukod());
				tetel.setAcikksz(aru.getCikkszam());
				tetel.setAmegn(aru.getMegn());
				tetel.setAfkszam(aru.getFkszam());
				tetel.setAmenny(iQuantity);
				tetel.setAmeegys(aru.getMeegys());
				tetel.setAear(iPrice);
				tetel.setAafakod(aru.getAfakod());
				tetel.setSzjvvtsz(aru.getSzjvvtsz());
				tetel.setSzjvtsz(aru.getSzjvtsz());
				tetel.setGarido(aru.getGarido());
				tetel.setKivagybe("B");
				tetel.setHufear(convertFloatToBigDecimal(hufear));
				tetel.setAmegn1(aru.getMegn());
				tetel.setVfizhdat(defaultDate);
				tetel.setUnikazon(unikazon);
				tetel.setNertek(iNetPrice);
				tetel.setBertek(iGrossPrice);
				tetel.setAertek(convertFloatToBigDecimal(hufear));
				tetel.setHnertek(iQuantity.multiply(convertFloatToBigDecimal(hufear)));
				tetel.setHbertek(iQuantity.multiply(convertFloatToBigDecimal(hufear)).multiply(iTaxRate));
				tetel.setHaertek(tetel.getHbertek().subtract(tetel.getHnertek()));
				tetel.setAlapar(convertFloatToBigDecimal(hufear));
				tetel.setElszamolar(convertFloatToBigDecimal(hufear));
				tetel.setThdat(new Date());
				tetel.setKiadmenny(new BigDecimal(0));
				tetel.setSzabmenny(new BigDecimal(0));
				tetel.setVarmenny(new BigDecimal(0));
				tetel.setElkmenny(new BigDecimal(0));
				tetel.setCsommenny(new BigDecimal(0));
				tetel.setModmenny(new BigDecimal(0));
				tetel.setKeszmenny(new BigDecimal(0));
				tetel.setOsszemenny(new BigDecimal(0));
				tetel.setMlsorszam("");
				tetel.setBsrszam("");
				tetel.setBssorszam("");
				tetel.setMenny2(0);
				tetel.setMenny3(0);
				tetel.setMenny4(0);
				tetel.setMenny5(0);
				tetel.setLefejto(0);
				tetel.setKedvertek(new BigDecimal(0));
				tetel.setKedvmegn("");
				tetel.setLefejto2(0);
				tetel.setGongyazon("");
				tetel.setRegio(0);
				tetel.setGymenny(new BigDecimal(0));
				tetel.setSarzsid(0);
				tetel.setTvighatido(defaultDate);
				tetel.setIdozona1(0);
				tetel.setIdozona2(0);
				tetel.setHatidotol(0);
				tetel.setHatidoig(0);
				tetel.setAjntetazon("");
				tetel.setTsztorno(false);
				tetel.setInsdatum(new Date());
				Calendar now = Calendar.getInstance();
				now.add(Calendar.MINUTE , 1);
				tetel.setModdatum(calculateModdatum());
				tetel.setModkod(0);
				tetel.setInskod(0);

				i++;
				getOrderDao().saveOrderItem(tetel);

			}

			// DEFAULT VALUES

			rendel.setSorsztipk("5");
			rendel.setTipus(9);
			rendel.setAlapbizt("");
			rendel.setAtvhely("");
			rendel.setAjanlatot(false);
			rendel.setBalala("");
			rendel.setBaltit("");
			rendel.setBehozta(0);
			rendel.setBetudva(false);
			rendel.setBrndid(14);
			rendel.setBruttosuly(new BigDecimal(0));
			rendel.setColli(new BigDecimal(0));
			rendel.setCsere(false);
			rendel.setEladoszla("");
			rendel.setElkuld(false);
			rendel.setElolegert(new BigDecimal(0));
			rendel.setElolegssz("");
			rendel.setEngedm(new BigDecimal(0));
			rendel.setEsedkelt(defaultDate);
			rendel.setEstat(0);
			rendel.setFizetve(defaultDate);
			if (rendel.getFuvmod() == null) 
				rendel.setFuvmod("");
			rendel.setGaris(false);
			rendel.setIban("");
			rendel.setIktatoszam("");
			rendel.setInskod(0);
			rendel.setIsjavit(false);
			rendel.setJavkarb(0);
			rendel.setJobbala("");
			rendel.setJobbtit("");
			rendel.setJovair("");
			rendel.setKamionjel("");
			rendel.setKedvezm(new BigDecimal(0));
			rendel.setKerdeses(false);
			rendel.setKgyub(false);
			rendel.setKiall("");
			rendel.setKonyv(false);
			// rendel.setKozadoszam("");
			rendel.setKulrend(false);
			rendel.setLabszerv(0);
			rendel.setMegjfile(false);
			rendel.setMegrazon("");
			rendel.setMindenok(false);
			rendel.setMindokdat(defaultDate);
			rendel.setModdatum(defaultDate);
			rendel.setModizva(false);
			rendel.setModkod(0);
			rendel.setMoregari(false);
			rendel.setMunkszam("");
			rendel.setMunkszuk(0);
			rendel.setNettosuly(new BigDecimal(0));
			rendel.setPeldany(0);
			rendel.setReklamacio(false);
			rendel.setRfizetve(defaultDate);
			rendel.setRossz(new BigDecimal(0));
			rendel.setSwiftcode("");
			rendel.setSzazas(false);
			rendel.setSztorno(false);
			rendel.setStatus(0);
			if (rendel.getTelj() == null)
				rendel.setTelj(defaultDate);			
			rendel.setUgyint(0);
			rendel.setVenyes(false);
			rendel.setVenyvonal("");
			rendel.setVfax("");
			rendel.setVfolyszam("");
			rendel.setVkapcstart("");
			rendel.setVkapcstel("");
			rendel.setVkozadosz("");
			rendel.setVorszag("");
			rendel.setSzlaszam("");
			rendel.setVnev("");
			rendel.setVvaros("");
			rendel.setVcim("");
			rendel.setVirsz("");
			rendel.setVadoszam("");
			//rendel.setEsedkelt(defaultDate);
			rendel.setVighatido(defaultDate);

			// END OF DEFAULT VALUES

			rendel.setSorszam(orderNum);
			rendel.setElotag("Wsn");
			rendel.setKelt(new Date());
			rendel.setFizmod(partner.getFizmod());
			rendel.setDevnem(forex);
			rendel.setArfolyam(new BigDecimal(forexRate));
			rendel.setVevokod(cId);
			rendel.setNyelv(1);
			rendel.setStatusz("Függő");
			rendel.setKivagybe("B");
			rendel.setBrutto(convertFloatToBigDecimal(gross));
			rendel.setNetto(convertFloatToBigDecimal(net));
			rendel.setDbrutto(convertFloatToBigDecimal(dgross));
			rendel.setDnetto(convertFloatToBigDecimal(dnet));
			rendel.setVfizdat(defaultDate);
			rendel.setInsdatum(new Date());
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE , 1);
			rendel.setModdatum(calculateModdatum());
			rendel.setModizva(false);
			rendel.setModkod(0);
			rendel.setMoregari(false);
			rendel.setMunkszam("");
			rendel.setMunkszuk(0);
			rendel.setNettosuly(new BigDecimal(0));
			rendel.setPeldany(0);
			rendel.setReklamacio(false);
			rendel.setRfizetve(defaultDate);
			rendel.setRossz(new BigDecimal(0));
			rendel.setSwiftcode("");
			rendel.setSzazas(false);
			rendel.setSztorno(false);
			rendel.setStatus(0);
			rendel.setUgyint(0);
			rendel.setVenyes(false);
			rendel.setVenyvonal("");
			rendel.setVfax(partner.getFax1());
			rendel.setVfolyszam(partner.getFolyszam());
			rendel.setVkapcstart(partner.getKapcstart());
			rendel.setVkapcstel(partner.getKapcstel());
			rendel.setVkozadosz(partner.getVkozadosz());
			
			rendel.setVnev(partner.getNev());
			rendel.setVvaros(partner.getVaros());
			rendel.setVcim(partner.getCim());
			rendel.setVirsz(partner.getIrsz());
			rendel.setVadoszam(partner.getAdoszam());
			
		
			rendel.setVorszag("");
			rendel.setSzlaszam("");
			rendel.setVighatido(defaultDate);
			rendel.setLefejto(0);
			rendel.setSzlaprofil(0);
			rendel.setCsakub(false);
			rendel.setVfelirdat(defaultDate);
			rendel.setGyseBe(false);
			rendel.setDimenztio1(0);
			rendel.setDeviza("Ft");
			rendel.setBnokod("");
			rendel.setVonalkod("");
			rendel.setFuvar(0);
			rendel.setLakosvevo(0);

			getOrderDao().saveOrder(rendel);
			
			if (megj != null && megj.size() > 0)
				for (int j = 0; j < megj.size(); j++) {
					Rendmegj rendmegj = new Rendmegj();
					rendmegj.setRendel(rendel);
					rendmegj.setSorszam(orderNum);
					rendmegj.setKivagybe("B");
					rendmegj.setMegj(megj.get(j));
					rendmegj.setTsorsz(j + 1);
					rendmegj.setUnikazon(CleanRendSorsz(orderNum) + "_" + rendmegj.getTsorsz().toString());
					getOrderDao().saveRendMegj(rendmegj);
				}
			logger.info("Order saving is successful!");
			result = true;
		} catch (Exception e) {
			logger.error("Error has occured by saving order", e);
			result = false;
		}
		return result;
	}

	private Date calculateModdatum() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MINUTE, 1);
		return date.getTime();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean updateOrder(Rendel order, String orderNum, List<ProductVO> products, Partner partner) {

		logger.info("Try to update order!");
		Boolean result = false;
		try {

			float net = 0; // sums all net prices
			float gross = 0; // sums all gross prices
			float dnet = 0; // sums all net prices by forex rate
			float dgross = 0; // sums all gross prices by forex rate

			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date defaultDate = dfm.parse("1900-01-01 00:00:00");

			String forexRate = "1";
			String forex = "Ft";
			// if (partner.getVevocsop() != null) {
			// forexRate =
			// partner.getVevocsop().getArnev().getDeviza().getArfolyam().toString();
			// forex = partner.getVevocsop().getArnev().getDevizanev();
			// }

			DetachedCriteria criteria3 = DetachedCriteria.forClass(Rendtet.class).add(Restrictions.eq("sorszam", orderNum));

			List<Rendtet> rendtetList = getHibernateTemplate().findByCriteria(criteria3);

			if (rendtetList.size() > 0) {
				getOrderDao().deleteOrderItems(rendtetList);
			}

			Integer i = 1;
			for (ProductVO vo : products) {

				BigDecimal iQuantity = vo.getMennyiseg(); // mennyiseg
				BigDecimal iPrice = vo.getEar(); // egysegar
				BigDecimal iNetPrice = vo.getEar().multiply(vo.getMennyiseg()); // netto
				BigDecimal iGrossPrice = iNetPrice.multiply(vo.getAfa()); // brutto
				BigDecimal iTaxRate = vo.getAfa(); // afaszorzo

				float hufear = 0;
				if (forexRate != null) {
					hufear = Float.parseFloat(iPrice.toString()) / Float.parseFloat(forexRate);
				} else {
					hufear = Float.parseFloat(iPrice.toString());
				}

				String unikazon = generateUnikazon(Integer.valueOf(vo.getArukod()).toString());

				net += Float.parseFloat(iNetPrice.toString());
				gross += Float.parseFloat(iGrossPrice.toString());
				dnet += Float.parseFloat(iNetPrice.toString()) / Float.parseFloat(forexRate);
				dgross += Float.parseFloat(iGrossPrice.toString()) / Float.parseFloat(forexRate);

				DetachedCriteria criteria2 = DetachedCriteria.forClass(Aru.class).add(Restrictions.eq("arukod", vo.getArukod()));

				Aru aru = (Aru) getHibernateTemplate().findByCriteria(criteria2).get(0);

				Rendtet tetel = new Rendtet();

				// DEFAULT TETEL VALUES

				tetel.setAlapbizt("");
				tetel.setAtadgyart(defaultDate);
				tetel.setAtadkod(0);
				tetel.setAtfutido(new Long(0));
				tetel.setAtvhely("");
				tetel.setAvar(new BigDecimal(0));
				tetel.setBizki(false);
				tetel.setBizszam("");
				tetel.setBvisszaig(defaultDate);
				tetel.setB_megj("");
				tetel.setEllenkod(0);
				tetel.setFelkeszmny(new BigDecimal(0));
				tetel.setFuvmod("");
				tetel.setGyartido(new Long(0));
				tetel.setGyartkesz(defaultDate);
				tetel.setGyartkezd(defaultDate);
				tetel.setGyartmenny(new BigDecimal(0));
				tetel.setGykezdkod(0);
				tetel.setGyszam("");
				tetel.setGyszam2("");
				tetel.setKiszallmny(new BigDecimal(0));
				tetel.setMegj("-");
				tetel.setMeomenny(new BigDecimal(0));
				tetel.setMkateg1(0);
				tetel.setMkateg2(0);
				tetel.setMkateg3(0);
				tetel.setMkateg4(0);
				tetel.setMkateg5(0);
				tetel.setMoreindok("");
				tetel.setMorestat(0);
				tetel.setMunkszam("");
				tetel.setMunkszuk(0);
				tetel.setNutrend(false);
				tetel.setOsszekod(0);
				tetel.setRaktaron(false);
				tetel.setRogzkod(0);
				tetel.setSajatszam("");
				tetel.setSelejtmny(new BigDecimal(0));
				tetel.setSortmegn("");
				tetel.setSzamlkelt(defaultDate);
				tetel.setSzamlsorsz("");
				tetel.setSzlevkelt(defaultDate);
				tetel.setSzlevsorsz("");
				tetel.setTeljesitve(false);
				tetel.setTmegrazon("");
				tetel.setHufvar(new BigDecimal(0));
				tetel.setKulsotelj(0);

				// END OF DEFAULT VALUES

				tetel.setSorszam(orderNum);
				tetel.setTetelszam(i);
				tetel.setArukod(aru.getArukod());
				tetel.setAcikksz(aru.getCikkszam());
				tetel.setAmegn(aru.getMegn());
				tetel.setAfkszam(aru.getFkszam());
				tetel.setAmenny(iQuantity);
				tetel.setAmeegys(aru.getMeegys());
				tetel.setAear(iPrice);
				tetel.setAafakod(aru.getAfakod());
				tetel.setSzjvvtsz(aru.getSzjvvtsz());
				tetel.setSzjvtsz(aru.getSzjvtsz());
				tetel.setGarido(aru.getGarido());
				tetel.setKivagybe("B");
				tetel.setHufear(convertFloatToBigDecimal(hufear));
				tetel.setAmegn1(aru.getMegn1());
				tetel.setVfizhdat(defaultDate);
				tetel.setUnikazon(unikazon);
				tetel.setNertek(iNetPrice);
				tetel.setBertek(iGrossPrice);
				tetel.setAertek(convertFloatToBigDecimal(hufear));
				tetel.setHnertek(iQuantity.multiply(convertFloatToBigDecimal(hufear)));
				tetel.setHbertek(iQuantity.multiply(convertFloatToBigDecimal(hufear)).multiply(iTaxRate));
				tetel.setHaertek(tetel.getHbertek().subtract(tetel.getHnertek()));
				tetel.setAlapar(convertFloatToBigDecimal(hufear));
				tetel.setElszamolar(convertFloatToBigDecimal(hufear));
				tetel.setThdat(new Date());

				i++;
				getOrderDao().saveOrderItem(tetel);

			}

			Rendel rendel = getOrderDao().loadOrder(orderNum);

			rendel.setKelt(new Date());
			rendel.setTelj(order.getTelj());
			//rendel.setEsedkelt(order.getEsedkelt());
			rendel.setFizmod(partner.getFizmod());
			rendel.setDevnem(forex);
			rendel.setArfolyam(new BigDecimal(forexRate));
			rendel.setStatusz("Függő");
			rendel.setKivagybe("B");
			rendel.setBrutto(convertFloatToBigDecimal(gross));
			rendel.setNetto(convertFloatToBigDecimal(net));
			rendel.setDbrutto(convertFloatToBigDecimal(dgross));
			rendel.setDnetto(convertFloatToBigDecimal(dnet));
			rendel.setVfizdat(defaultDate);
			// rendel.setInsdatum(new Date());
			rendel.setModdatum(new Date());
			rendel.setMegj("-");

			getOrderDao().updateOrder(rendel);
			logger.info("Order updating is successful!");
			result = true;
		} catch (Exception e) {
			logger.error("Error has occured by updating order", e);
			result = false;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private synchronized String generateSerial() {

		String serial = "";

		DateFormat df = new SimpleDateFormat("yyyy");

		String year = df.format(new Date()).toString();
		serial += "Wsn" + year + "/";

		try {
			List<Rendel> orderList = getHibernateTemplate().find(
					"from Rendel r where r.sorszam like '" + serial + "%' order by r.insdatum desc");
			if (orderList.size() > 0) {
				String ssz = orderList.get(0).getSorszam();
				if (ssz.substring(3, 7).equals(year)) {
					int val = Integer.parseInt(ssz.substring(8).trim());
					serial += val + 1;
				} else {
					serial += "1";
				}
			} else {
				serial += "1";
			}

			logger.info("generated ID= " + serial);
		} catch (Exception e) {
			logger.error("Error by generate serial for order rendering!", e);
			return null;
		}
		return serial;
	}

	private String generateUnikazon(String cikkszam) {
		String unikazon = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String str = cikkszam + dateFormat.format(new Date());
		unikazon = DigestUtils.md5Hex(str).substring(0, 10);
		return unikazon;
	}
	
	private String CleanRendSorsz(String rendsorsz) {
		List<Character> validchars = new ArrayList<Character>();
		validchars.add('0');
		validchars.add('1');
		validchars.add('2');
		validchars.add('3');
		validchars.add('4');
		validchars.add('5');
		validchars.add('6');
		validchars.add('7');
		validchars.add('8');
		validchars.add('9'); //Nem hiszem el, hogy nem lehet egyszerűbben deklarálni bakker.
		
		String result = "";
		for (Character c : rendsorsz.toCharArray()) {
			if (validchars.contains(c))
				result += c.toString();				
		}
		
		return result;	
		
//		String unikazon = null;
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String str = rendsorsz + dateFormat.format(new Date());
//		unikazon = DigestUtils.md5Hex(str).substring(0, 10);
//		return unikazon;
	}	

	private BigDecimal convertFloatToBigDecimal(float value) {
		if (value % 1 == 0)
			return new BigDecimal(value);
		DecimalFormat floatformat = new java.text.DecimalFormat(".##");
		// BigDecimal big = new
		// BigDecimal(floatformat.format(Float.valueOf(value).doubleValue()));
		BigDecimal big = new BigDecimal(value);
		return big;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getOrdersInForChart(UserVO user, Integer partnerId, Date from, Date to, String kivagybe) {

		List<Object[]> chartList = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER)) {

				String query = "select year(r.kelt), month(r.kelt), sum(r.dbrutto) from Rendel r where r.kelt>='" + from + "' and r.kelt<='" + to + "'";
				if (kivagybe != null)
					query = query + "and r.kivagybe = '" + kivagybe + "'";
				query = query + " group by year(r.kelt), month(r.kelt) order by year(r.kelt) asc, month(r.kelt) asc)";
				chartList = getOrderDao().findOrderForChartByQuery(query);
			}

			else {
				String query = "select year(r.kelt), month(r.kelt), sum(r.dbrutto) from Rendel r where r.vevokod='" + partnerId + "' and r.kelt>='" + from
						+ "' and r.kelt<='" + to + "'";
				if (kivagybe != null)
					query = query + "and r.kivagybe = '" + kivagybe + "'";
				query = query + " group by year(r.kelt), month(r.kelt) order by year(r.kelt) asc, month(r.kelt) asc)";
				chartList = getOrderDao().findOrderForChartByQuery(query);
			}
			if (chartList == null)
				return null;
			Integer startMonth = from.getMonth() + 1;
			Integer startYear = from.getYear() + 1900;

			int count = getMonthDifference(from, to);
			int z = 0;
			for (int i = 0; i < count; i++) {
				if (z < chartList.size()) {
					if (chartList.get(z)[1].equals(startMonth) && Integer.valueOf(chartList.get(z)[0].toString()).equals(startYear)) {
						newList.add(chartList.get(z));
						startMonth++;
						z++;
					} else {
						while (startMonth <= 12) {
							Object[] temp = { startYear, startMonth, 0 };
							newList.add(temp);
							startMonth++;
							break;
						}
						if (startMonth == 13) {
							startMonth = 1;
							startYear = startYear + 1;
						}
					}
				} else {
					while (startMonth <= 12) {
						Object[] temp = { startYear, startMonth, 0 };
						newList.add(temp);
						startMonth++;
						break;
					}
					if (startMonth == 13) {
						startMonth = 1;
						startYear = startYear + 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("error  has occured at processing OrdersIn for charts: " + e.getMessage());
			return null;
		}
		return newList;
	}

	@SuppressWarnings("deprecation")
	public static int getMonthDifference(Date from, Date to) {
		int count = 0;
		from.setDate(1);
		while (from.compareTo(to) <= 0) {
			from.setMonth(from.getMonth() + 1);
			count++;
		}
		return count;
	}

	@Override
	public Object[] getSummaryForChart(UserVO user, Integer partnerId, String kivagybe) {

		Object[] summary = { 0, 0, 0, 0 };
		Object sumIn;
		Object debitIn;
		Object payedIn;
		Object expiredIn;

		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER)) {

				String query = "select sum(r.dbrutto) from Rendel r";
				if (kivagybe != null)
					query = query + "where r.kivagybe = '" + kivagybe + "'";
				sumIn = getOrderDao().findOrderForChartByQuery(query).get(0);

				if (sumIn == null)
					sumIn = 0;

				String query2 = "select sum(r.dbrutto) from Rendel r where r.fizetve = '1900-01-01'";
				if (kivagybe != null)
					query2 = query2 + " and r.kivagybe = '" + kivagybe + "'";
				debitIn = getOrderDao().findOrderForChartByQuery(query2).get(0);

				if (debitIn == null)
					debitIn = 0;

				String query3 = "select sum(r.dbrutto) from Rendel r where r.fizetve != '1900-01-01'";
				if (kivagybe != null)
					query3 = query3 + " and r.kivagybe = '" + kivagybe + "'";
				payedIn = getOrderDao().findOrderForChartByQuery(query3).get(0);

				if (payedIn == null)
					payedIn = 0;

				String query4 = "select sum(r.dbrutto) from Rendel r where r.esedkelt < now() and r.fizetve='1900-01-01'";
				if (kivagybe != null)
					query4 = query4 + " and r.kivagybe = '" + kivagybe + "'";
				expiredIn = getOrderDao().findOrderForChartByQuery(query4).get(0);

				if (expiredIn == null)
					expiredIn = 0;
			} else {
				String query = "select sum(r.dbrutto) from Rendel r where r.vevokod ='" + partnerId + "'";
				if (kivagybe != null)
					query = query + " and r.kivagybe = '" + kivagybe + "'";
				sumIn = getOrderDao().findOrderForChartByQuery(query).get(0);

				if (sumIn == null)
					sumIn = 0;

				String query2 = "select sum(r.dbrutto) from Rendel r where r.vevokod ='" + partnerId + "' and r.fizetve = '1900-01-01'";
				if (kivagybe != null)
					query2 = query2 + " and r.kivagybe = '" + kivagybe + "'";
				debitIn = getOrderDao().findOrderForChartByQuery(query2).get(0);

				if (debitIn == null)
					debitIn = 0;

				String query3 = "select sum(r.dbrutto) from Rendel r where r.vevokod ='" + partnerId + "' and r.fizetve != '1900-01-01'";
				if (kivagybe != null)
					query3 = query3 + " and r.kivagybe = '" + kivagybe + "'";
				payedIn = getOrderDao().findOrderForChartByQuery(query3).get(0);

				if (payedIn == null)
					payedIn = 0;

				String query4 = "select sum(r.dbrutto) from Rendel r where r.vevokod ='" + partnerId + "' and r.fizetve = '1900-01-01' and r.esedkelt < now()";
				if (kivagybe != null)
					query4 = query4 + " and r.kivagybe = '" + kivagybe + "'";
				expiredIn = getOrderDao().findOrderForChartByQuery(query4).get(0);

				if (expiredIn == null)
					expiredIn = 0;

			}

			summary[0] = sumIn;
			summary[1] = debitIn;
			summary[2] = payedIn;
			summary[3] = expiredIn;

		} catch (Exception e) {
			logger.error("error  has occured at processing summary for charts: " + e.getMessage());
			return null;
		}

		return summary;
	}

	@Override
	public List<Rendtet> getRendtetForPRProjekt(Integer mkateg1) {
		if (mkateg1 == null)
			return null;
		return getOrderDao().getRendtetForPRProjekt(mkateg1);
	}

	@Override
	public Rendel getRendelById(String sorszam) {
		if (sorszam == null)
			return null;
		return getOrderDao().getRendelById(sorszam);
	}

	@Override
	public List<String> getRendtetSzamlsorszBySorszam(String sorszam) {
		if (sorszam == null)
			return null;
		return getOrderDao().getRendtetSzamlsorszBySorszam(sorszam);
	}

	@Override
	public List<String> getRendtetSzlevsorszBySorszam(String sorszam) {
		if (sorszam == null)
			return null;
		return getOrderDao().getRendtetSzlevsorszBySorszam(sorszam);
	}

	private Date calculateModdate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 1);
		return cal.getTime();
	}

	@Override
	public List<OrderVO> getAllFuggoOrders(Integer partnerId, String kivagybe) {
		return getOrderDao().getAllFuggoOrders(partnerId, kivagybe);
	}
}
