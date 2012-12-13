package hu.iboard.coandco.datamagic.presentation.controllers.realtyarrangementservice;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;
import hu.iboard.coandco.datamagic.generated.Munkszam2;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Project;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.aruext.AruExtServiceBase;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.project.ProjectServiceBase;
import hu.iboard.coandco.datamagic.service.realty.RealtyArrangementServiceBase;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipOutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "realtyarrangementserviceController")
@RequestScoped
public class RealtyArrangementServiceController extends AbstractController {

	public static final String REALTYARRANGEMENTSERVICE_ACTION = "realtyarrangementlist";
	public static final String MOBILEREALTYARRANGEMENTSERVICE_ACTION = "mobilerealtyarrangementlist";

	@ManagedProperty(value = "#{realtyarrangementserviceData}")
	private RealtyArrangementData data;
	@ManagedProperty(value = "#{realtyArrangementService}")
	private RealtyArrangementServiceBase realtyArrangementService;
	@ManagedProperty(value = "#{projectService}")
	private ProjectServiceBase projectService;
	@ManagedProperty(value = "#{aruExtService}")
	private AruExtServiceBase aruExtService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;

	@Override
	public void loadData() {

		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		} else if (getManagedRenter() != null) {
			getData().setLakasIds(getRealtyArrangementService().getLakasIdByPartnerId(getManagedRenter().getVevokod()));
			getData().setVevokod(getManagedRenter().getVevokod());
		} else {
			getData().setVevokod(null);
		}
		// List<RealtyArrangementVO> realtyVOs =
		// getRealtyArrangementService().getInitRealtyArrangementNew(
		// getManagedUser(), getData().getVevokod(), getData().getLakasIds());
		// if (realtyVOs != null) {
		// getData().setRealtyArrangements(loadRealtyArrangementVOs(realtyVOs));
		// initSummary();
		// initDate();
		// }
		getData().setFrom(new Date());
		getData().setTo(new Date());
		initFromAndTo();
		getData().setRealtyArrangements(new ArrayList<RealtyArrangementVO>());
		getData().setFilteredText("");
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		getData().setFlatItems(new ArrayList<SelectItem>());
		getData().setAllBejelento(new ArrayList<String>());
		getData().setRealtyFiltered(new ArrayList<RealtyArrangementVO>());
		getData().setChoosenDateTypeFiled("datum");
		getData().setNetto(new Long(0));
		if (getManagedRenter() != null) {
			getData().setRealtyArrangements(
					getRealtyArrangementService().getRealtyArrangementByDateFilteredNew(getManagedUser(), getData().getVevokod(), null, null, null,
							getData().getLakasIds()));
			getData().setRealtyArrangements(loadRealtyArrangementVOs(getData().getRealtyArrangements()));
			initSummary();
			initDate();
		}
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(REALTYARRANGEMENTSERVICE_CONTROLLER);

	}

	@SuppressWarnings("deprecation")
	private void initFromAndTo() {
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	private void initDate() {
		if (getData().getRealtyArrangements() == null || getData().getRealtyArrangements().size() == 0) {
			getData().setTo(new Date());
			getData().setFrom(new Date());
			return;
		}
		getData().setTo(getData().getRealtyArrangements().get(0).getLetrehozva());
		getData().setFrom(getData().getRealtyArrangements().get(getData().getRealtyArrangements().size() - 1).getLetrehozva());
		Timestamp newDate = new Timestamp(getData().getTo().getTime());
		newDate.setHours(23);
		newDate.setMinutes(59);
		newDate.setSeconds(59);

	}

	private void initSummary() {

		Long tempNetto = new Long(0);
		if (getData().getRealtyArrangements() == null)
			return;
		for (RealtyArrangementVO vo : getData().getRealtyArrangements()) {
			tempNetto += vo.getNettoAr();
		}
		getData().setNetto(tempNetto);
	}

	@SuppressWarnings("deprecation")
	public void realtyDateFromChange(DateSelectEvent event) {
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void realtyDateToChange(DateSelectEvent event) {
		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	public void realtyDateChange(ActionEvent event) {

		if (getData().getSelectedRenterId() != 0) {
			if (getData().getRealtyArrangements() != null && getData().getRealtyArrangements().size() > 0) {
				List<RealtyArrangementVO> list = new ArrayList<RealtyArrangementVO>();
				for (RealtyArrangementVO vo : getData().getRealtyArrangements()) {
					if (getData().getChoosenDateTypeFiled().equals("letrehozva")) {
						if (vo.getLetrehozva().getTime() >= getData().getFrom().getTime() && vo.getLetrehozva().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("datum")) {
						if (vo.getDatum().getTime() >= getData().getFrom().getTime() && vo.getDatum().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("hatarido")) {
						if (vo.getHatarido().getTime() >= getData().getFrom().getTime() && vo.getHatarido().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("befejezes")) {
						if (vo.getBefejezes().getTime() >= getData().getFrom().getTime() && vo.getBefejezes().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
				}
				getData().setRealtyArrangements(list);
				initSummary();
				return;
			} else {
				addInfoMessage("", "Üres intézkedéslista!");
				initSummary();
				initDate();
				return;
			}
		}

		if (getData().getSelectedProjectId() != 0) {
			if (getData().getRealtyArrangements() != null && getData().getRealtyArrangements().size() > 0) {
				List<RealtyArrangementVO> list = new ArrayList<RealtyArrangementVO>();
				for (RealtyArrangementVO vo : getData().getRealtyArrangements()) {
					if (getData().getChoosenDateTypeFiled().equals("letrehozva")) {
						if (vo.getLetrehozva().getTime() >= getData().getFrom().getTime() && vo.getLetrehozva().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("datum")) {
						if (vo.getDatum().getTime() >= getData().getFrom().getTime() && vo.getDatum().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("hatarido")) {
						if (vo.getHatarido().getTime() >= getData().getFrom().getTime() && vo.getHatarido().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("befejezes")) {
						if (vo.getBefejezes().getTime() >= getData().getFrom().getTime() && vo.getBefejezes().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
				}
				getData().setRealtyArrangements(list);
				initSummary();
				return;
			} else {
				addInfoMessage("", "Üres intézkedéslista!");
				initSummary();
				initDate();
				return;
			}
		}

		if (getData().getSelectedOwnerId() != 0) {
			if (getData().getRealtyArrangements() != null && getData().getRealtyArrangements().size() > 0) {
				List<RealtyArrangementVO> list = new ArrayList<RealtyArrangementVO>();
				for (RealtyArrangementVO vo : getData().getRealtyArrangements()) {
					if (getData().getChoosenDateTypeFiled().equals("letrehozva")) {
						if (vo.getLetrehozva().getTime() >= getData().getFrom().getTime() && vo.getLetrehozva().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("datum")) {
						if (vo.getDatum().getTime() >= getData().getFrom().getTime() && vo.getDatum().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("hatarido")) {
						if (vo.getHatarido().getTime() >= getData().getFrom().getTime() && vo.getHatarido().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("befejezes")) {
						if (vo.getBefejezes().getTime() >= getData().getFrom().getTime() && vo.getBefejezes().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
				}
				getData().setRealtyArrangements(list);
				initSummary();
				return;
			} else {
				addInfoMessage("", "Üres intézkedéslista!");
				initSummary();
				initDate();
				return;
			}
		}

		if (!getData().getSelectedBejelento().equals("")) {
			if (getData().getRealtyArrangements() != null && getData().getRealtyArrangements().size() > 0) {
				List<RealtyArrangementVO> list = new ArrayList<RealtyArrangementVO>();
				for (RealtyArrangementVO vo : getData().getRealtyArrangements()) {
					if (getData().getChoosenDateTypeFiled().equals("letrehozva")) {
						if (vo.getLetrehozva().getTime() >= getData().getFrom().getTime() && vo.getLetrehozva().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("datum")) {
						if (vo.getDatum().getTime() >= getData().getFrom().getTime() && vo.getDatum().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("hatarido")) {
						if (vo.getHatarido().getTime() >= getData().getFrom().getTime() && vo.getHatarido().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
					if (getData().getChoosenDateTypeFiled().equals("befejezes")) {
						if (vo.getBefejezes().getTime() >= getData().getFrom().getTime() && vo.getBefejezes().getTime() <= getData().getTo().getTime()) {
							list.add(vo);
						}
					}
				}
				getData().setRealtyArrangements(list);
				initSummary();
				return;
			} else {
				addInfoMessage("", "Üres intézkedéslista!");
				initSummary();
				initDate();
				return;
			}
		}
		/*
		 * Calendar cal1 = Calendar.getInstance(); Calendar cal2 =
		 * Calendar.getInstance(); cal1.setTime(getData().getFrom());
		 * cal2.setTime(getData().getTo()); int yearDiff =
		 * cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); int monthDiff =
		 * yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		 * 
		 * if (getManagedUser().getUserType().equals(WORKER)) { if (monthDiff >
		 * 3) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!3 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return; } } if (!getManagedUser().getUserType().equals(WORKER)) {
		 * if (monthDiff > 12) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!12 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return; } }
		 */
		getData().setRealtyArrangements(
				getRealtyArrangementService().getRealtyArrangementByDateFilteredNew(getManagedUser(), getData().getVevokod(),
						new Timestamp(getData().getFrom().getTime()), new Timestamp(getData().getTo().getTime()), getData().getChoosenDateTypeFiled(),
						getData().getLakasIds()));
		getData().setRealtyArrangements(loadRealtyArrangementVOs(getData().getRealtyArrangements()));
		if (getData().getRealtyArrangements().size() == 0)
			addInfoMessage("", "Üres intézkedéslista!");
		if (getData().getRealtyArrangements().size() >= 500)
			addInfoMessage("", "A kérés túl sok adatot eredményezett!Kérjük kisebb időintervallumot adjon meg!A legutóbbi 500 adatsor látható!");
		initSummary();
	}

	public String realtyItemsAction() {
		List<Aattachs> aattachs = new ArrayList<Aattachs>();
		getData().setAttachs(new ArrayList<Aattachs>());
		try {
			if (getData().getSelectedRealty().getId() != null) {
				aattachs = getRealtyArrangementService().getAttachsForRealtyArrangement(getData().getSelectedRealty().getId().toString());
				if (aattachs != null) {
					for (Aattachs aattach : aattachs) {
						getData().getAttachs().add(aattach);
					}
				}
				List<IntezAru> aruk = getRealtyArrangementService().getIntezAruListByIntezkedes(getData().getSelectedRealty().getId());
				if (aruk != null) {
					getData().setIntezaruk(new ArrayList<Object[]>());
					for (IntezAru intezAru : aruk) {
						BigDecimal menny = ((BigDecimal) intezAru.getMenny() != null ? (BigDecimal) intezAru.getMenny() : new BigDecimal(0));
						BigDecimal earForint = ((BigDecimal) intezAru.getEarForint() != null ? (BigDecimal) intezAru.getEarForint() : new BigDecimal(0));
						BigDecimal szolgaltat = ((BigDecimal) intezAru.getSzolgaltat() != null ? (BigDecimal) intezAru.getSzolgaltat() : new BigDecimal(0));
						BigDecimal bonyolit = ((BigDecimal) intezAru.getBonyolit() != null ? (BigDecimal) intezAru.getBonyolit() : new BigDecimal(0));
						BigDecimal szorz1 = szolgaltat.divide(new BigDecimal(100));
						BigDecimal szorz2 = bonyolit.divide(new BigDecimal(100));
						BigDecimal anyagigaz = menny.multiply(earForint).multiply(szorz1);
						BigDecimal bony = menny.multiply(earForint).multiply(szorz2);

						Object[] object = new Object[9];
						object[0] = intezAru.getId();
						object[1] = intezAru.getParentAru().getMegn();
						object[2] = intezAru.getMegj();
						object[3] = intezAru.getBizSzam();
						object[4] = intezAru.getMenny();
						object[5] = intezAru.getEarForint();
						object[6] = bony;
						object[7] = anyagigaz;
						object[8] = anyagigaz.add(bony).add((intezAru.getMenny().multiply(intezAru.getEarForint())));
						getData().getIntezaruk().add(object);
					}
				}
				String munkszam = getData().getSelectedRealty().getEvad() + "/" + getData().getSelectedRealty().getSorszam();
				List<Munkszam2> munkszamok = getRealtyArrangementService().getMunkszam2ByMunkszam(munkszam);
				if (munkszamok != null && munkszamok.size() > 0) {
					Munkszam2 munkszam2 = munkszamok.get(0);
					List<Ktetel_CDH> ktetels = getRealtyArrangementService().getKtetelByMkateg4(munkszam2.getId());
					if (ktetels != null && ktetels.size() > 0) {
						for (Ktetel_CDH ktetel : ktetels) {
							BigDecimal menny = ((BigDecimal) ktetel.getAmenny() != null ? (BigDecimal) ktetel.getAmenny() : new BigDecimal(0));
							BigDecimal earForint = ((BigDecimal) ktetel.getAear() != null ? (BigDecimal) ktetel.getAear() : new BigDecimal(0));
							BigDecimal szolgaltat = ((BigDecimal) ktetel.getSzolgszaz() != null ? (BigDecimal) ktetel.getSzolgszaz() : new BigDecimal(0));
							BigDecimal bonyolit = ((BigDecimal) ktetel.getBonyszaz() != null ? (BigDecimal) ktetel.getBonyszaz() : new BigDecimal(0));
							BigDecimal szorz1 = szolgaltat.divide(new BigDecimal(100));
							BigDecimal szorz2 = bonyolit.divide(new BigDecimal(100));
							BigDecimal anyagigaz = menny.multiply(earForint).multiply(szorz1);
							BigDecimal bony = menny.multiply(earForint).multiply(szorz2);

							Object[] object = new Object[9];
							object[0] = ktetel.getUnikazon();
							object[1] = ktetel.getParentAru().getMegn();
							object[2] = ktetel.getMegj();
							object[3] = "";
							object[4] = ktetel.getAmenny();
							object[5] = ktetel.getAear();
							object[6] = bony;
							object[7] = anyagigaz;
							object[8] = anyagigaz.add(bony).add((ktetel.getAmenny().multiply(ktetel.getAear())));
							getData().getIntezaruk().add(object);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error by getting attachs for realty arraengement", e);
			return null;
		}
		return null;
	}

	public void downloadAttach(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String value = (String) requestMap.get("aattachid");
		getData().setSelectedAattach(getRealtyArrangementService().getAattachById((Integer.valueOf(value))));
		if (getData().getSelectedAattach() != null) {
			getRealtyArrangementService().downloadAttachForRealtyArrangement(getData().getSelectedAattach(), getRealPath());
		}
		try {
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			int index = request.getRequestURL().toString().indexOf("pages");
			String url = request.getRequestURL().toString().substring(0, index);
			context.getExternalContext().redirect(url + "downloads/" + URLstringKonverter(getData().getSelectedAattach().getName()));
		} catch (IOException e) {
			logger.error("ERROR OPENING URL BY REALTY", e);
			return;
		}
	}

	private List<RealtyArrangementVO> loadRealtyArrangementVOs(List<RealtyArrangementVO> realtyVOs) {

		List<RealtyArrangementVO> voList = new ArrayList<RealtyArrangementVO>();

		if (realtyVOs == null)
			return new ArrayList<RealtyArrangementVO>();
		for (RealtyArrangementVO record : realtyVOs) {

			// status
			if (record.isLezart())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_CLOSED", MSGFILE));
			else if (record.isElszamolt())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_CHARGED", MSGFILE));
			else if (record.isJovahagy())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_APPROVED", MSGFILE));
			else if (record.isVegrehajt())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_DOING", MSGFILE));
			else if (record.isMegbizott())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_ASSIGNED", MSGFILE));
			else if (record.isUjIntezk())
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_NEW", MSGFILE));
			else
				record.setStatus(getTextFromPropertiesFile("LIST_REALTY_FILTER_UNKNOWN", MSGFILE));

			// minosites

			if (record.getMinosites() != null) {
				if (record.getMinosites() == 0)
					record.setQulification(getTextFromPropertiesFile("LIST_REALTY_FILTER_EVALUATE_UNKNOWN", MSGFILE));
				else if (record.getMinosites() == 1)
					record.setQulification(getTextFromPropertiesFile("LIST_REALTY_EVALUATE_ACCEPTABLE", MSGFILE));
				else if (record.getMinosites() == 2)
					record.setQulification(getTextFromPropertiesFile("LIST_REALTY_FILTER_EVALUATE_GOOD", MSGFILE));
				else if (record.getMinosites() == 3)
					record.setQulification(getTextFromPropertiesFile("LIST_REALTY_FILTER_OUTSTANDING", MSGFILE));
				else if (record.getMinosites() == 4)
					record.setQulification(getTextFromPropertiesFile("LIST_REALTY_FILTER_NEEDSREPAIR", MSGFILE));
			}
			String owner = "";
			owner = getPartnerService().getPartnerNameByPartnerId(record.getProjectId());
			if (owner != null) {
				record.setOwnerName(owner);
			}
			AruExt aruext = getAruExtService().getAruExtById(record.getAruExtId());
			if (aruext != null) {
				if (aruext.getBerbeadas() != null && aruext.getBerbeadas().getUgyfel() != 0)
					record.setRenterName(aruext.getBerbeadas().getPartner().getNev());
				if (aruext.getLakasTipus() != null && aruext.getTipus() != 0)
					record.setFlatTypeName(aruext.getLakasTipus().getNev());
			}

			Long tempNetto = new Long(0);
			List<IntezAru> intezaruk = getRealtyArrangementService().getIntezAruListByIntezkedes(record.getId());
			if (intezaruk != null && intezaruk.size() > 0) {
				for (IntezAru intezAru : intezaruk) {
					BigDecimal menny = ((BigDecimal) intezAru.getMenny() != null ? (BigDecimal) intezAru.getMenny() : new BigDecimal(0));
					BigDecimal earForint = ((BigDecimal) intezAru.getEarForint() != null ? (BigDecimal) intezAru.getEarForint() : new BigDecimal(0));
					BigDecimal szolgaltat = ((BigDecimal) intezAru.getSzolgaltat() != null ? (BigDecimal) intezAru.getSzolgaltat() : new BigDecimal(0));
					BigDecimal bonyolit = ((BigDecimal) intezAru.getBonyolit() != null ? (BigDecimal) intezAru.getBonyolit() : new BigDecimal(0));
					BigDecimal szorz = (szolgaltat.add(bonyolit)).divide(new BigDecimal(100));
					BigDecimal round = new BigDecimal(1).add(szorz);
					BigDecimal ar = menny.multiply(earForint).multiply(round);
					Long netto = Integer.valueOf(Math.round(new Float(ar.toString()))).longValue();
					tempNetto += netto;
				}
			}
			String munkszam = record.getEvad() + "/" + record.getSorszam();
			List<Munkszam2> munkszamok = getRealtyArrangementService().getMunkszam2ByMunkszam(munkszam);
			if (munkszamok != null && munkszamok.size() > 0) {
				Munkszam2 munkszam2 = munkszamok.get(0);
				List<Ktetel_CDH> ktetels = getRealtyArrangementService().getKtetelByMkateg4(munkszam2.getId());
				if (ktetels != null && ktetels.size() > 0) {
					for (Ktetel_CDH ktetel : ktetels) {
						BigDecimal menny = ((BigDecimal) ktetel.getAmenny() != null ? (BigDecimal) ktetel.getAmenny() : new BigDecimal(0));
						BigDecimal earForint = ((BigDecimal) ktetel.getAear() != null ? (BigDecimal) ktetel.getAear() : new BigDecimal(0));
						BigDecimal szolgaltat = ((BigDecimal) ktetel.getSzolgszaz() != null ? (BigDecimal) ktetel.getSzolgszaz() : new BigDecimal(0));
						BigDecimal bonyolit = ((BigDecimal) ktetel.getBonyszaz() != null ? (BigDecimal) ktetel.getBonyszaz() : new BigDecimal(0));
						BigDecimal szorz = (szolgaltat.add(bonyolit)).divide(new BigDecimal(100));
						BigDecimal round = new BigDecimal(1).add(szorz);
						BigDecimal ar = menny.multiply(earForint).multiply(round);
						Long netto = Integer.valueOf(Math.round(new Float(ar.toString()))).longValue();
						tempNetto += netto;
					}
				}
			}
			record.setNettoAr(tempNetto);
			voList.add(record);
		}

		return voList;
	}

	public void dateTypeChange(ActionEvent event) {

		if (getData().getRealtyFiltered() == null || getData().getRealtyFiltered().size() == 0)
			return;
		List<RealtyArrangementVO> list = new ArrayList<RealtyArrangementVO>();
		for (RealtyArrangementVO vo : getData().getRealtyFiltered()) {
			if (getData().getChoosenDateTypeFiled().equals("letrehozva")) {
				if (vo.getLetrehozva().getTime() >= getData().getFrom().getTime() && vo.getLetrehozva().getTime() <= getData().getTo().getTime()) {
					list.add(vo);
				}
			}
			if (getData().getChoosenDateTypeFiled().equals("datum")) {
				if (vo.getDatum().getTime() >= getData().getFrom().getTime() && vo.getDatum().getTime() <= getData().getTo().getTime()) {
					list.add(vo);
				}
			}
			if (getData().getChoosenDateTypeFiled().equals("hatarido")) {
				if (vo.getHatarido().getTime() >= getData().getFrom().getTime() && vo.getHatarido().getTime() <= getData().getTo().getTime()) {
					list.add(vo);
				}
			}
			if (getData().getChoosenDateTypeFiled().equals("befejezes")) {
				if (vo.getBefejezes().getTime() >= getData().getFrom().getTime() && vo.getBefejezes().getTime() <= getData().getTo().getTime()) {
					list.add(vo);
				}
			}
		}
		getData().setRealtyArrangements(list);
		initSummary();

	}

	public void resetAction(ActionEvent event) {
		loadData();
	}

	public String showRealtys() {
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setSelectedProjectId(0);
		getData().setRealtySearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getManagedUser().getUserType().equals(WORKER)) {
			List<Object[]> list = getProjectService().getAllProject();
			if (list == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			for (Object[] object : list) {
				selectItemList.add(new SelectItem(object[0], (String) object[1] + " " + (String) object[2] + ", " + (String) object[3]));
			}
		}
		if (getManagedUser().getUserType().equals(PARTNER)) {
			List<Project> list = getProjectService().getProjectByPartnerId(getManagedPartner().getVevokod());
			if (list == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			for (Project item : list) {
				selectItemList.add(new SelectItem(item.getId(), item.getIrsz() + " " + item.getVaros() + ", " + item.getCim()));
			}
		}
		if (getManagedRenter() != null) {
			List<Object[]> aruexts = getAruExtService().getAruExtForRenter(getManagedRenter().getVevokod());
			if (aruexts == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			Integer lastProjId = 0;
			for (Object[] aruext : aruexts) {
				Integer projectId = (Integer) aruext[1];
				if (!projectId.equals(lastProjId)) {
					if (projectId != null) {
						Project project = getProjectService().getProjectById(projectId);
						if (project != null)
							selectItemList.add(new SelectItem(project.getId(), project.getIrsz() + " " + project.getVaros() + ", " + project.getCim()));
					}
				}
				lastProjId = projectId;
			}
		}
		getData().setFlatItems(selectItemList);
		getData().setAllFlatItems(selectItemList);
		return null;
	}

	public String searchRealtyAction() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllFlatItems() == null || getData().getAllFlatItems().size() == 0)
			return null;
		for (SelectItem selectItem : getData().getAllFlatItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getRealtySearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getRealtySearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setFlatItems(selectItemList);
		return null;
	}

	public void selectRealtyAction(ActionEvent event) {

		getData().setRealtyArrangements(
				loadRealtyArrangementVOs(getRealtyArrangementService().getRealtyArrangementByProjectIdNew(getManagedUser(), getData().getVevokod(),
						getData().getSelectedProjectId(), getData().getLakasIds())));
		Project project = getProjectService().getProjectById(getData().getSelectedProjectId());
		if (project != null)
			getData().setFilteredText(project.getIrsz() + " " + project.getVaros() + ", " + project.getCim());
		initDate();
		initSummary();
		getData().setRealtyFiltered(getData().getRealtyArrangements());
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		if (getData().getRealtyArrangements().size() == 0)
			addInfoMessage("", "Üres intézkedéslista!");

	}

	public void showOwners(ActionEvent event) {
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setSelectedOwnerId(0);
		getData().setSelectedRenterId(0);
		getData().setOwnerSearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> names = getRealtyArrangementService().getAllOwnersFromRealtyArrangement();
		if (names == null)
			return;
		for (Object[] object : names) {
			selectItemList.add(new SelectItem((Integer) object[0], (String) object[1]));
		}
		getData().setOwnerItems(selectItemList);
		getData().setAllOwnerItems(selectItemList);

	}

	public void selectOwnerAction(ActionEvent event) {

		getData().setRealtyArrangements(
				loadRealtyArrangementVOs(getRealtyArrangementService().getRealtyArrangementByTulajdonos(getData().getSelectedOwnerId())));
		Partner owner = getPartnerService().getAllPartnerDataById(getData().getSelectedOwnerId());
		if (owner != null)
			getData().setFilteredText(owner.getNev());
		initDate();
		initSummary();
		getData().setRealtyFiltered(getData().getRealtyArrangements());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedBejelento("");
		if (getData().getRealtyArrangements().size() == 0)
			addInfoMessage("", "Üres intézkedéslista!");
	}

	public void searchOwnerAction(ActionEvent event) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllRentalItems() == null || getData().getAllOwnerItems().size() == 0)
			return;
		for (SelectItem selectItem : getData().getAllOwnerItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getOwnerSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getOwnerSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setOwnerItems(selectItemList);
	}

	public void showRenters(ActionEvent event) {
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setSelectedRenterId(0);
		getData().setRenterSearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getManagedUser().getUserType().equals(PARTNER) || getManagedUser().getUserType().equals(SALES)) {
			List<Integer> aktbers = new ArrayList<Integer>();
			if (getData().getSelectedProjectId() == null || getData().getSelectedProjectId() == 0) {
				List<Project> projects = getProjectService().getProjectByPartnerId(getManagedPartner().getVevokod());
				if (projects == null) {
					return;
				}
				for (Project project : projects) {
					List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(project.getId());
					if (aruexts != null) {
						for (Object[] object : aruexts) {
							Integer aktber = (Integer) object[5];
							if (aktber != 0)
								aktbers.add(aktber);
						}
					}
				}
				if (aktbers.size() > 0) {
					List<Integer> berbeadasok = getAruExtService().getBerbeadasByAktBer(aktbers);
					if (berbeadasok != null) {
						List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(berbeadasok);
						if (partners != null && partners.size() > 0) {
							for (Object[] name : partners) {
								selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
							}
						}
					}
				}
			} else {
				List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(getData().getSelectedProjectId());
				if (aruexts != null) {
					for (Object[] object : aruexts) {
						Integer aktber = (Integer) object[5];
						if (aktber != 0)
							aktbers.add(aktber);
					}
					if (aktbers.size() > 0) {
						List<Integer> berbeadasok = getAruExtService().getBerbeadasByAktBer(aktbers);
						if (berbeadasok != null) {
							List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(berbeadasok);
							if (partners == null || partners.size() == 0)
								return;
							for (Object[] name : partners) {
								selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
							}
						}
					}
				}
			}
		} else {
			List<Integer> ids = getRealtyArrangementService().getRenterIdsForRealtyArrangement();
			if (ids == null)
				return;
			List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(ids);
			if (partners == null || partners.size() == 0)
				return;
			for (Object[] name : partners) {
				selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));

			}
		}
		getData().setRenterItems(selectItemList);
		getData().setAllRentalItems(selectItemList);
	}

	public void selectRenterAction(ActionEvent event) {
		List<RealtyArrangementVO> vos = getRealtyArrangementService().getRealtyArrangementByRenterNew(getData().getSelectedRenterId());
		if (vos == null) {
			getData().setRealtyArrangements(new ArrayList<RealtyArrangementVO>());
			return;
		}
		getData().setRealtyArrangements(loadRealtyArrangementVOs(vos));
		Partner renter = getPartnerService().getAllPartnerDataById(getData().getSelectedRenterId());
		if (renter != null)
			getData().setFilteredText(renter.getNev());
		initDate();
		initSummary();
		getData().setRealtyFiltered(getData().getRealtyArrangements());
		getData().setSelectedProjectId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		if (getData().getRealtyArrangements().size() == 0)
			addInfoMessage("", "Üres intézkedéslista!");
	}

	public void printList(ActionEvent event) {
		try {
			if (getData().getRealtyArrangements().size() == 0) {
				addFatalMessage("", "Üres intezkedések lista!");
				return;
			}
			byte[] file = getDocumentService().handleGenerateRealtyArrangementList(getData().getRealtyArrangements());
			fileDownloader(file, "realtyarrangement_list.pdf");
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt az intezkedés lista nyomtatásakor!");
			logger.error("Error by printing realty arrangement list!", e);
			return;
		}
		return;
	}

	public void printPayOff(ActionEvent event) {
		if (getData().getSelectedRealty() == null) {
			return;
		}
		try {
			byte[] stream = getDocumentService().handleGenerateRealtyArrangementPayOff(getData().getSelectedRealty());
			fileDownloader(stream, "realtyarrangement_payoff.pdf");
		} catch (Exception e) {
			addFatalMessage("", "Hiba tortent az elszamolas nyomtatasakor!");
			logger.error("Error by printing realty arrangement payoff!", e);
			return;
		}
	}

	public void printWorkSheet(ActionEvent event) {
		if (getData().getSelectedRealty() == null) {
			return;
		}
		try {
			byte[] stream = getDocumentService().handleGenerateRealtyArrangementWorkSheet(getData().getSelectedRealty());
			fileDownloader(stream, "realtyarrangement_worksheet.pdf");
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt a munkalap nyomtatásakor!");
			logger.error("Error by printing realty arrangement worksheet!", e);
			return;
		}
	}

	public void downloadAllAttach(ActionEvent event) {

		List<String> filenames = new ArrayList<String>();
		try {
			List<RealtyArrangementVO> realty = getData().getRealtyArrangements();
			if (realty == null)
				return;
			for (RealtyArrangementVO vo : realty) {
				List<Aattachs> aattachs = getRealtyArrangementService().getAttachsForRealtyArrangement(vo.getId().toString());
				if (aattachs != null) {
					for (Aattachs aattach : aattachs) {

						List<String> files = getRealtyArrangementService().downloadAttachForRealtyArrangement(aattach, getRealPath());
						filenames.addAll(files);
					}
				}
			}
			if (filenames != null && filenames.size() > 0) {

				long time = new Date().getTime();
				InputStream in = null;
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(getRealPath() + "downloads/elszamolas_" + time + ".zip"));
				out.setLevel(Deflater.DEFAULT_COMPRESSION);
				byte[] buffer = new byte[8096];
				List<String> temp = new ArrayList<String>();
				for (String file : filenames) {
					if (!temp.contains(file)) {
						in = new FileInputStream(getRealPath() + "downloads/" + file);
						out.putNextEntry(new java.util.zip.ZipEntry(file));
						int len;
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						temp.add(file);
					}
				}
				out.closeEntry();
				out.close();
				in.close();

				commonFileDownloader("elszamolas_" + time + ".zip", getRealPath() + "downloads/");

				File f = new File(getRealPath() + "downloads/elszamolas_" + time + ".zip");
//				if (f.exists())
//					f.delete();
			} else {
				addErrorMessage("", "Nincsenek csatolmányok!");
				return;
			}
		} catch (Exception e) {
			logger.error("Error getting by all attachment for realtys", e);
			addErrorMessage("Hiba a letöltéskor!");
			return;
		}
	}

	public void searchRenterAction(ActionEvent event) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllRentalItems() == null || getData().getAllRentalItems().size() == 0)
			return;
		for (SelectItem selectItem : getData().getAllRentalItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setRenterItems(selectItemList);
	}

	public void showAllBejelento(ActionEvent event) {
		getData().setAllBejelento(new ArrayList<String>());
		getData().setBejelentoSearchText("");
		getData().setSelectedBejelento("");
		List<String> names = getRealtyArrangementService().getAllBejelento();
		if (names == null) {
			return;
		}
		getData().setAllBejelento(names);
	}

	public void searchBejelentoAction(ActionEvent event) {
		List<String> selectItemList = new ArrayList<String>();
		if (getData().getAllBejelento() == null || getData().getAllBejelento().size() == 0)
			return;
		for (String selectItem : getData().getAllBejelento()) {
			if (selectItem.matches("(?i).*" + getData().getBejelentoSearchText().toLowerCase() + ".*")
					|| selectItem.matches("(?i).*" + getData().getBejelentoSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setAllBejelento(selectItemList);
	}

	public String selectBejelentoAction() {

		if (getData().getSelectedBejelento() == null || getData().getSelectedBejelento().equals(""))
			return null;
		List<RealtyArrangementVO> vos = getRealtyArrangementService().getRealtyArrangementByBejelento(getData().getSelectedBejelento());
		if (vos == null) {
			getData().setRealtyArrangements(new ArrayList<RealtyArrangementVO>());
			return null;
		}
		getData().setRealtyArrangements(loadRealtyArrangementVOs(vos));
		getData().setFilteredText(getData().getSelectedBejelento());
		initDate();
		initSummary();
		getData().setRealtyFiltered(getData().getRealtyArrangements());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		if (getData().getRealtyArrangements().size() == 0)
			addInfoMessage("", "Üres intézkedéslista!");
		return null;
	}

	public RealtyArrangementData getData() {
		return data;
	}

	public void setData(RealtyArrangementData data) {
		this.data = data;
	}

	public RealtyArrangementServiceBase getRealtyArrangementService() {
		return realtyArrangementService;
	}

	public void setRealtyArrangementService(RealtyArrangementServiceBase realtyArrangementService) {
		this.realtyArrangementService = realtyArrangementService;
	}

	public ProjectServiceBase getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectServiceBase projectService) {
		this.projectService = projectService;
	}

	public AruExtServiceBase getAruExtService() {
		return aruExtService;
	}

	public void setAruExtService(AruExtServiceBase aruExtService) {
		this.aruExtService = aruExtService;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public DocumentServiceBase getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentServiceBase documentService) {
		this.documentService = documentService;
	}

}
