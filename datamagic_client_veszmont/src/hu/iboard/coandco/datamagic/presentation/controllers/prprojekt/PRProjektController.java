package hu.iboard.coandco.datamagic.presentation.controllers.prprojekt;

import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.order.OrderService;
import hu.iboard.coandco.datamagic.service.partner.PartnerService;
import hu.iboard.coandco.datamagic.service.product.ProductService;
import hu.iboard.coandco.datamagic.service.prprojekt.PRProjektService;
import hu.iboard.coandco.datamagic.service.worker.WorkerService;
import hu.iboard.coandco.datamagic.vo.prarucsatolo.PRArucsatolo;
import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

@ManagedBean(name = "prProjektController")
@RequestScoped
public class PRProjektController extends AbstractController {

	public static final String PRPROJEKT_ACTION = "projectmanager";

	@ManagedProperty(value = "#{prProjektControllerData}")
	private PRProjektControllerData data;
	@ManagedProperty(value = "#{prProjektService}")
	private PRProjektService prProjektService;
	@ManagedProperty(value = "#{workerService}")
	private WorkerService workerService;
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerService partnerService;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;

	@Override
	public void loadData() {
		getData().setSelectedPRProjekt(null);
		getData().setPrProjekt(new Object[17]);
		getData().setSelectedAlPRProjekt(null);
		getData().setEventModel(new DefaultScheduleModel());
		if (getManagedPartner() != null) {
			List<PRProjekt> projekts = getPrProjektService().getPRProjektByPartnerId(getManagedPartner().getVevokod());
			for (PRProjekt projekt : projekts) {
				getData().getEventModel().addEvent(
						new DefaultScheduleEvent(projekt.getMegn(), projekt.getKezdes(), projekt.getBefejezes(), projekt));
			}
		}
		if (getManagedWorker() != null) {
			List<PRProjekt> projekts = getPrProjektService().getPRProjektByPartnerId(null);
			for (PRProjekt projekt : projekts) {
				getData().getEventModel().addEvent(
						new DefaultScheduleEvent(projekt.getMegn(), projekt.getKezdes(), projekt.getBefejezes(), projekt));
			}
		}
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {

	}

	public void showAllProjekt() {

		List<PRProjekt> projekt = null;
		if (getManagedPartner() != null)
			projekt = getPrProjektService().getPRProjektByPartnerId(getManagedPartner().getVevokod());
		if (getManagedWorker() != null)
			projekt = getPrProjektService().getPRProjektByPartnerId(null);
		if (projekt == null)
			return;
		getData().setAllProjekt(projekt);
	}

	public String selectProjektAction() {
		Object[] obj = new Object[17];
		obj[0] = getData().getSelectedPRProjekt().getKod();
		obj[1] = getData().getSelectedPRProjekt().getMegn();
		obj[2] = getData().getSelectedPRProjekt().getKozvetett();
		obj[3] = getData().getSelectedPRProjekt().getKezdes();
		obj[4] = getData().getSelectedPRProjekt().getBefejezes();
		obj[5] = getData().getSelectedPRProjekt().getPrjkezdet();
		obj[6] = getData().getSelectedPRProjekt().getPrjveg();
		obj[7] = getData().getSelectedPRProjekt().getKeszultseg();
		Dolgozo felelos1 = getWorkerService().getWorkerByReferenceId(getData().getSelectedPRProjekt().getFelelos1());
		String dnames = "";
		if (felelos1 != null)
			dnames += felelos1.getDnev();
		Dolgozo felelos2 = getWorkerService().getWorkerByReferenceId(getData().getSelectedPRProjekt().getFelelos2());
		if (felelos2 != null)
			dnames += felelos2.getDnev();
		Dolgozo felelos3 = getWorkerService().getWorkerByReferenceId(getData().getSelectedPRProjekt().getFelelos3());
		if (felelos3 != null)
			dnames += "," + felelos3.getDnev();
		Dolgozo felelos4 = getWorkerService().getWorkerByReferenceId(getData().getSelectedPRProjekt().getFelelos4());
		if (felelos4 != null)
			dnames += "," + felelos4.getDnev();
		Dolgozo felelos5 = getWorkerService().getWorkerByReferenceId(getData().getSelectedPRProjekt().getFelelos5());
		if (felelos5 != null)
			dnames += "," + felelos5.getDnev();
		obj[8] = dnames;
		obj[12] = getData().getSelectedPRProjekt().getMegj();
		if (getManagedWorker() != null) {
			Partner partner = getPartnerService().getPartnerByReferenceId(getData().getSelectedPRProjekt().getPartner());
			if (partner != null && partner.getUgyint() != null) {
				Dolgozo worker = getWorkerService().getWorkerByReferenceId(partner.getUgyint());
				if (worker != null)
					obj[9] = worker.getDnev();
				obj[10] = partner.getNev();
				obj[11] = partner.getIrsz() + " " + partner.getVaros() + ", " + partner.getCim();
			}
		} else {
			Dolgozo worker = getWorkerService().getWorkerByReferenceId(getManagedPartner().getUgyint());
			if (worker != null)
				obj[9] = worker.getDnev();
		}

		List<Rendtet> rendtet = getOrderService().getRendtetForPRProjekt(getData().getSelectedPRProjekt().getDimenzio());
		if (rendtet != null && rendtet.size() > 0) {
			Rendel rendel = getOrderService().getRendelById(rendtet.get(0).getSorszam());
			if (rendel != null) {
				obj[13] = rendel.getSorszam();
				obj[14] = rendel.getKelt();
				obj[15] = rendel.getTelj();
			}
		}

		getData().setPrProjekt(obj);

		List<PRProjekt> projekts = getPrProjektService().getAlPRProjektByFoPRProjektId(getData().getSelectedPRProjekt().getId());
		if (projekts != null)
			getData().setAlProjekt(projekts);
		List<PRArucsatolo> arucsatolo = getPrProjektService().getPRArucsatoloByPRProjektId(getData().getSelectedPRProjekt().getId());
		if (arucsatolo != null) {
			boolean dbjpublikus = getData().getSelectedPRProjekt().getDbjpublikus();
			if (dbjpublikus ) {
			List<Object[]> tempAru = new ArrayList<Object[]>();
			for (PRArucsatolo csatolo : arucsatolo) {
				Object[] object = new Object[4];
				Aru aru = getProductService().getAruById(csatolo.getAru());
				if (aru != null) {
					object[0] = aru.getCikkszam();
					object[1] = aru.getMegn();
					object[2] = csatolo.getDarabszam();
					Integer status = csatolo.getStatus();
					switch (status) {
					case 0:
						object[3] = "Függő";
						break;
					case 1:
						object[3] = "Megrendelve";
						break;
					case 2:
						object[3] = "Raktáron";
						break;
					case 3:
						object[3] = "Gyártás alatt";
						break;
					case 4:
						object[3] = "Legyártva";
						break;
					case 5:
						object[3] = "Kooperációban";
						break;
					case 6:
						object[3] = "Legyártva-Koop.";
						break;

					}
					tempAru.add(object);
				}
			}
			getData().setProducts(tempAru);
		}}
		return null;
	}

	public void selectAlProjektAction(ActionEvent event) {

		Object[] obj = new Object[16];
		obj[0] = getData().getSelectedAlPRProjekt().getKod();
		obj[1] = getData().getSelectedAlPRProjekt().getMegn();
		obj[2] = getData().getSelectedAlPRProjekt().getKozvetett();
		obj[3] = getData().getSelectedAlPRProjekt().getKezdes();
		obj[4] = getData().getSelectedAlPRProjekt().getBefejezes();
		obj[5] = getData().getSelectedAlPRProjekt().getPrjkezdet();
		obj[6] = getData().getSelectedAlPRProjekt().getPrjveg();
		obj[7] = getData().getSelectedAlPRProjekt().getKeszultseg();
		Dolgozo felelos1 = getWorkerService().getWorkerByReferenceId(getData().getSelectedAlPRProjekt().getFelelos1());
		String dnames = "";
		if (felelos1 != null)
			dnames += felelos1.getDnev();
		Dolgozo felelos2 = getWorkerService().getWorkerByReferenceId(getData().getSelectedAlPRProjekt().getFelelos2());
		if (felelos2 != null)
			dnames += felelos2.getDnev();
		Dolgozo felelos3 = getWorkerService().getWorkerByReferenceId(getData().getSelectedAlPRProjekt().getFelelos3());
		if (felelos3 != null)
			dnames += "," + felelos3.getDnev();
		Dolgozo felelos4 = getWorkerService().getWorkerByReferenceId(getData().getSelectedAlPRProjekt().getFelelos4());
		if (felelos4 != null)
			dnames += "," + felelos4.getDnev();
		Dolgozo felelos5 = getWorkerService().getWorkerByReferenceId(getData().getSelectedAlPRProjekt().getFelelos5());
		if (felelos5 != null)
			dnames += "," + felelos5.getDnev();
		obj[8] = dnames;
		obj[12] = getData().getSelectedAlPRProjekt().getMegj();
		if (getManagedWorker() != null) {
			Partner partner = getPartnerService().getPartnerByReferenceId(getData().getSelectedAlPRProjekt().getPartner());
			if (partner != null && partner.getUgyint() != null) {
				Dolgozo worker = getWorkerService().getWorkerByReferenceId(partner.getUgyint());
				if (worker != null)
					obj[9] = worker.getDnev();
				obj[10] = partner.getNev();
				obj[11] = partner.getIrsz() + " " + partner.getVaros() + ", " + partner.getCim();
			}
		} else {
			Dolgozo worker = getWorkerService().getWorkerByReferenceId(getManagedPartner().getUgyint());
			if (worker != null)
				obj[9] = worker.getDnev();
		}

		List<Rendtet> rendtet = getOrderService().getRendtetForPRProjekt(getData().getSelectedPRProjekt().getDimenzio());
		if (rendtet != null && rendtet.size() > 0) {
			Rendel rendel = getOrderService().getRendelById(rendtet.get(0).getSorszam());
			if (rendel != null) {
				obj[13] = rendel.getSorszam();
				obj[14] = rendel.getKelt();
				obj[15] = rendel.getTelj();
			}
		}
		getData().setAlPrProjekt(obj);

		List<PRArucsatolo> arucsatolo = getPrProjektService().getPRArucsatoloByPRProjektId(getData().getSelectedAlPRProjekt().getId());
		if (arucsatolo != null) {
			List<Object[]> tempAru = new ArrayList<Object[]>();
			for (PRArucsatolo csatolo : arucsatolo) {
				Object[] object = new Object[3];
				Aru aru = getProductService().getAruById(csatolo.getAru());
				if (aru != null) {
					object[0] = aru.getCikkszam();
					object[1] = aru.getMegn();
					object[2] = csatolo.getDarabszam();
					Integer status = csatolo.getStatus();
					switch (status) {
					case 0:
						object[3] = "Függő";
						break;
					case 1:
						object[3] = "Megrendelve";
						break;
					case 2:
						object[3] = "Raktáron";
						break;
					case 3:
						object[3] = "Gyártás alatt";
						break;
					case 4:
						object[3] = "Legyártva";
						break;
					case 5:
						object[3] = "Kooperációban";
						break;
					case 6:
						object[3] = "Legyártva-Koop.";
						break;

					}
					tempAru.add(object);
				}
			}
			getData().setProducts(tempAru);
		}
	}

	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {

		ScheduleEvent event = selectEvent.getScheduleEvent();
		if (event.getData() != null) {
			getData().setSelectedPRProjekt((PRProjekt) event.getData());
			selectProjektAction();

		}
	}

	public PRProjektControllerData getData() {
		return data;
	}

	public void setData(PRProjektControllerData data) {
		this.data = data;
	}

	public PRProjektService getPrProjektService() {
		return prProjektService;
	}

	public void setPrProjektService(PRProjektService prProjektService) {
		this.prProjektService = prProjektService;
	}

	public WorkerService getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerService workerService) {
		this.workerService = workerService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
