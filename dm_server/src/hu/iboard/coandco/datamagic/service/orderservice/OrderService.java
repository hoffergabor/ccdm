package hu.iboard.coandco.datamagic.service.orderservice;

import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class OrderService extends OrderServiceBase {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Rendel getOrderById(String sorszam) {
		if (sorszam == null)
			return null;
		return getOrderDao().getOrderById(sorszam);
	}

	@Override
	public Rendel saveOrUpdateOrder(Rendel rendel, List<Aru> products, List<BigDecimal> mennyisegek, String aroszlop) {
		if (rendel == null)
			return null;
		try {
			String orderNum = getOrderDao().getLastSorszamFromRendel();
			float net = 0; // sums all net prices
			float gross = 0; // sums all gross prices
			float dnet = 0; // sums all net prices by forex rate
			float dgross = 0; // sums all gross prices by forex rate

			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date defaultDate = dfm.parse("1900-01-01 00:00:00");

			String forexRate = "1";
			String forex = "Ft";

			Integer i = 1;
			int count = 0;
			for (Aru vo : products) {
				float afa = (float) (vo.getAfakodok().getFordkulcs() + 100) / 100;
				BigDecimal iQuantity = mennyisegek.get(count); // mennyiseg
				BigDecimal iPrice = calculatePrice(vo, aroszlop); // egysegar
				BigDecimal iNetPrice = iPrice.multiply(mennyisegek.get(count)); // netto
				BigDecimal iGrossPrice = iNetPrice.multiply(new BigDecimal(afa)); // brutto
				// BigDecimal iTaxRate = new BigDecimal(afa); // afaszorzo

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
				tetel.setArukod(vo.getArukod());
				tetel.setAcikksz(vo.getCikkszam());
				tetel.setAmegn(vo.getMegn());
				tetel.setAfkszam(vo.getFkszam());
				tetel.setAmenny(iQuantity);
				tetel.setAmeegys(vo.getMeegys());
				tetel.setAear(iPrice);
				tetel.setAafakod(vo.getAfakod());
				tetel.setSzjvvtsz(vo.getSzjvvtsz());
				tetel.setSzjvtsz(vo.getSzjvtsz());
				tetel.setGarido(vo.getGarido());
				tetel.setKivagybe("B");
				tetel.setHufear(convertFloatToBigDecimal(hufear));
				tetel.setAmegn1(vo.getMegn1());
				tetel.setVfizhdat(defaultDate);
				tetel.setUnikazon(unikazon);
				tetel.setNertek(iNetPrice);
				tetel.setBertek(iGrossPrice);
				tetel.setAertek(iPrice);
				tetel.setHnertek(tetel.getNertek());
				tetel.setHbertek(tetel.getBertek());
				tetel.setHaertek(tetel.getAertek());
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
				tetel.setGymenny(null);
				tetel.setSarzsid(0);
				tetel.setTvighatido(defaultDate);
				tetel.setIdozona1(null);
				tetel.setIdozona2(null);
				tetel.setHatidotol(null);
				tetel.setHatidoig(null);

				i++;
				count++;
				getOrderDao().saveOrUpdateOrderItem(tetel);
			}

			// DEFAULT VALUES

			rendel.setSorsztipk("5");
			rendel.setTipus(1);
			rendel.setAlapbizt("");
			rendel.setAtvhely("");
			rendel.setAjanlatot(false);
			rendel.setBalala("");
			rendel.setBaltit("");
			rendel.setBehozta(0);
			rendel.setBetudva(false);
			rendel.setBrndid(15);
			rendel.setBruttosuly(new BigDecimal(0));
			rendel.setColli(new BigDecimal(0));
			rendel.setCsere(false);
			rendel.setEladoszla("");
			rendel.setElkuld(false);
			rendel.setElolegert(new BigDecimal(0));
			rendel.setElolegssz("");
			rendel.setEngedm(new BigDecimal(0));
			rendel.setEstat(0);
			rendel.setFizetve(defaultDate);
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
			rendel.setKozadoszam("");
			rendel.setKulrend(false);
			rendel.setLabszerv(0);
			rendel.setMegjfile(false);
			rendel.setMegrazon("");
			rendel.setMindenok(false);
			rendel.setMindokdat(defaultDate);
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
			rendel.setVfax("");
			rendel.setVfolyszam("");
			rendel.setVkapcstart("");
			rendel.setVkapcstel("");
			rendel.setVkozadosz("");
			rendel.setVorszag("");
			rendel.setSzlaszam("");
			rendel.setVighatido(defaultDate);
			rendel.setMegj("-");

			// END OF DEFAULT VALUES

			rendel.setSorszam(orderNum);
			rendel.setElotag("WS");
			rendel.setKelt(new Date());
			rendel.setTelj(rendel.getEsedkelt());
			rendel.setEsedkelt(rendel.getEsedkelt());
			rendel.setDevnem(forex);
			rendel.setArfolyam(new BigDecimal(forexRate));
			rendel.setNyelv(1);
			rendel.setStatusz("Függő");
			rendel.setKivagybe("B");
			rendel.setBrutto(convertFloatToBigDecimal(gross));
			rendel.setNetto(convertFloatToBigDecimal(net));
			rendel.setDbrutto(convertFloatToBigDecimal(dgross));
			rendel.setDnetto(convertFloatToBigDecimal(dnet));
			rendel.setVfizdat(defaultDate);
			rendel.setInsdatum(new Date());
			rendel.setModdatum(calculateModdatum());
			rendel.setDeviza("Ft");
			rendel.setBnokod("");
			rendel.setGyseBe(false);
			rendel.setDimenztio1(0);
			rendel.setVfelirdat(defaultDate);
			rendel.setCsakub(false);
			rendel.setVonalkod("");
			rendel.setFuvar(0);
			rendel.setLefejto(0);
			rendel.setSzlaprofil(0);
			rendel = getOrderDao().saveOrUpdateOrder(rendel);
		} catch (Exception e) {
			logger.error("ERROR BY SAVING ORDER!", e);
			return null;
		}
		return rendel;
	}

	private String generateUnikazon(String cikkszam) {
		String unikazon = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String str = cikkszam + dateFormat.format(new Date());
		unikazon = DigestUtils.md5Hex(str).substring(0, 10);
		return unikazon;
	}

	private BigDecimal convertFloatToBigDecimal(float value) {
		// if (value % 1 == 0)
		// return new BigDecimal(value);
		// DecimalFormat floatformat = new java.text.DecimalFormat(".##");
		// BigDecimal big = new
		// BigDecimal(floatformat.format(Float.valueOf(value).floatValue()));
		BigDecimal big = new BigDecimal(value);
		return big;
	}

	private BigDecimal calculatePrice(Aru aru, String aroszlop) {
		BigDecimal price = new BigDecimal(0);
		if (aroszlop.equals("ear"))
			price = aru.getEar();
		else if (aroszlop.equals("ear2"))
			price = aru.getEar2();
		else if (aroszlop.equals("ear2"))
			price = aru.getEar2();
		else if (aroszlop.equals("ear3"))
			price = aru.getEar3();
		else if (aroszlop.equals("ear4"))
			price = aru.getEar4();
		else if (aroszlop.equals("ear5"))
			price = aru.getEar5();
		else if (aroszlop.equals("ear6"))
			price = aru.getEar6();
		else if (aroszlop.equals("ear7"))
			price = aru.getEar7();
		else if (aroszlop.equals("ear8"))
			price = aru.getEar8();
		else if (aroszlop.equals("ear9"))
			price = aru.getEar9();
		else if (aroszlop.equals("ear10"))
			price = aru.getEar10();
		else if (aroszlop.equals("ear11"))
			price = aru.getEar11();
		else if (aroszlop.equals("ear12"))
			price = aru.getEar12();
		else if (aroszlop.equals("ear13"))
			price = aru.getEar13();
		else if (aroszlop.equals("ear14"))
			price = aru.getEar14();
		else if (aroszlop.equals("ear15"))
			price = aru.getEar15();
		else if (aroszlop.equals("dear1"))
			price = aru.getDear1();
		else if (aroszlop.equals("dear2"))
			price = aru.getDear2();
		else if (aroszlop.equals("dear3"))
			price = aru.getDear3();
		else if (aroszlop.equals("ear4"))
			price = aru.getDear4();

		return price;
	}

	private Date calculateModdatum() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MINUTE, 1);
		return date.getTime();
	}

	@Override
	public List<Rendel> getOrdersByLakosvevo(Integer lakoskod) {
		if (lakoskod == null)
			return null;
		return getOrderDao().getOrdersByLakosvevo(lakoskod);
	}

	@Override
	public List<Rendtet> getOrderItemsByOrderId(String sorszam) {
		if (sorszam == null)
			return null;
		return getOrderDao().getOrderItemsByOrderId(sorszam);
	}

	@Override
	public List<Rendel> getOrdersByPartnerId(Integer id) {
		if (id == null)
			return null;
		return getOrderDao().getOrdersByPartnerId(id);
	}
}