package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.product;

import hu.iboard.coandco.datamagic.model.cikkcsop.Cikkcsop;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.CompareAruMegn;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.CompareCikkcsops;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.emailservice.EmailService;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController extends AbstractController {

	@ManagedProperty(value = "#{productControllerData}")
	private ProductControllerData data;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setProducts(new ArrayList<ProductVO>());
			getData().setProductName(null);
			getData().setSelectedProduct(null);
			getData().setCategoryId(null);
			getData().setCategory2Id(null);
			getData().setCategory3Id(null);
			getData().setCategory4Id(null);
			getData().setShowProductGrid(true);
			getData().setShowProductList(false);
			/*getData().setMainMenu(generateMainMenu(null));
			if (getManagedPartner() != null && getManagedPartner().getKedvez() != null)
				getData().setKedvezmeny(getManagedPartner().getKedvez());*/
		}
	}

	public String searchProductByNameAction() {
		try {
			if (getData().getProductName() == null)
				return null;
			if (getData().getProductName().length() < 3) {
				addErrorMessage("", "A keresendő kifejezés túl rövid! (minimum 3 karakter)");
				return null;
			}
			//getData().setMainMenu(generateMainMenu(null));
			//QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),ConfigurationHandler.getAdaptDiscount());
			getData().setProducts(getProductService().getProductByName(getData().getProductName(), getQueryParamsVO()));
			/*
			List<ProductVO> aruk = cikkcsopMegnSearch(getData().getProductName());
			if (getData().getProducts() != null) {
				List<ProductVO> temp = getData().getProducts();
				if (aruk != null) {
					for (ProductVO aru : aruk) {
						boolean contains = containsAru(temp, aru);
						if (!contains)
							getData().getProducts().add(aru);
					}
				}
			} else {
				getData().setProducts(aruk);
			}*/
			Collections.sort(getData().getProducts(), new CompareAruMegn());
			getData().setSearchProducts(getData().getProducts());
			getData().setMainMenuLabel("Kategóriák");
			List<BigDecimal> ids = new ArrayList<BigDecimal>();
			for (ProductVO aru : getData().getProducts()) {
				if (!ids.contains(aru.getCsopkod()))
					ids.add(aru.getCsopkod());
			}
			List<Cikkcsop> csops = new ArrayList<Cikkcsop>();
			for (BigDecimal id : ids) {
				Cikkcsop csop = getParent(id);
				if (csop != null) {
					if (!csops.contains(csop)) {
						csops.add(csop);
					}
				}
			}

			if (csops.size() > 0)
				Collections.sort(csops, new CompareCikkcsops());

			String str = "<ul>";
			for (Cikkcsop csop : csops) {
				if (csop != null) {
					str += "<li>";
					str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kereses/" + csop.getKod()
							+ "/\">" + csop.getMegn() + "</a>";
					str += "</li>";
				}
			}
			str += "</ul>";
			getData().setMenu(str);
			if (getData().getProducts() != null && getData().getProducts().size() == 1) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termek/"
										+ getData().getProducts().get(0).getArukod() + "/");
				return null;

			}

		} catch (Exception e) {
			logger.error("ERROR GETTING PRODUCT BY NAME", e);
			addErrorMessage("", "Hiba történt a kereséskor!");
		}

		return "pretty:product";
	}

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();

		QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), null);

		List<ProductVO> aruk = getProductService().getProductByName(query, queryParamsVO);
		if (aruk == null)
			return null;
		for (ProductVO obj : aruk) {
			results.add((String) obj.getMegn());
		}
		return results;
	}

	public void handleSelect(SelectEvent event) throws IOException {
		try {
			if (getData().getProductName() == null)
				return;
			getData().setMainMenu(generateMainMenu(null));
			QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),
					ConfigurationHandler.getAdaptDiscount(), null);
			getData().setProducts(getProductService().getProductByName(getData().getProductName(), queryParamsVO));
			if (getData().getProducts() != null && getData().getProducts().size() == 1) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termek/"
										+ getData().getProducts().get(0).getArukod() + "/");
				return;

			}
			getData().setSearch(true);

		} catch (Exception e) {
			logger.error("ERROR GETTING PRODUCT BY NAME", e);
			addErrorMessage("", "Hiba történt a kereséskor.");
		}
	}

	public String showProducts() {
		getData().setProducts(new ArrayList<ProductVO>());
		getData().setCikkcsops(new ArrayList<Object[]>());
		if (getData().getCategoryId() != null) {
			if (getData().getAllCikkcsops().size() == 0)
				getData().setAllCikkcsops(getProductService().getAllCikkcsopObjs());
			Cikkcsop cikkcsop = getProductService().getCikkcsopById(getData().getCategoryId());
			if (cikkcsop == null)
				return null;
			getData().setMainMenuLabel(cikkcsop.getMegn());
			getChildren(getData().getCategoryId());
			String str = "<ul>";
			for (Object[] first : getData().getCikkcsops()) {
				if (first[2].equals(getData().getCategoryId())) {
					str += "<li>";
					str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
							+ getData().getCategoryId() + "/" + first[0] + "/\">" + first[1] + "</a>";
					str += "</li>";
				}
			}
			str += "</ul>";
			getData().setMenu(str);
			getData().setMainMenu(generateMainMenu(getData().getCategoryId()));
			List<BigDecimal> ids = new ArrayList<BigDecimal>();
			QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),
					ConfigurationHandler.getAdaptDiscount(), null);
			if (getData().getCategory2Id() == null) {
				for (Object[] obj : getData().getCikkcsops()) {
					ids.add((BigDecimal) obj[0]);
				}
				if (ids != null && ids.size() > 0)
					getData().getProducts().addAll(getProductService().getProductsByCsopkods(ids, queryParamsVO));
			} else {
				getData().getProducts().addAll(getProductService().getProductsByCsopkod(getData().getCategory2Id(), queryParamsVO));
			}
		}
		return null;
	}

	public String showProductsLevel2() {
		getData().setCikkcsops(new ArrayList<Object[]>());
		getData().setProducts(new ArrayList<ProductVO>());
		getData().setMainMenu(generateMainMenu(getData().getCategoryId()));
		if (getData().getCategory2Id() != null) {
			if (getData().getCategory2Id().toString().length() >= 4) {
				getData().setCikkcsops(new ArrayList<Object[]>());
				getChildren(getData().getCategoryId());
				String str = "<ul>";
				boolean children = false;
				for (Object[] second : getData().getCikkcsops()) {
					if ((second[0].equals(getData().getCategory2Id()))) {
						str += "<li class=\"activemenu\">";
						str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + second[0] + "/\">" + second[1] + "</a>";
					} else if (second[2].equals(getData().getCategory2Id())) {
						if (!children)
							str += "<ul>";
						str += "<li><a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + getData().getCategory2Id() + "/" + second[0] + "/\">" + second[1] + "</a></li>";
						children = true;
					} else if (second[2].equals(getData().getCategoryId())) {
						if (children) {
							str += "</ul>";
							str += "</li>";
						}
						str += "<li>";
						str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + second[0] + "/\">" + second[1] + "</a>";
						str += "</li>";
						children = false;
					}
				}
				str += "</ul>";
				getData().setMenu(str);
			} else {
				showProducts();
			}
		}
		getData().setCikkcsops(new ArrayList<Object[]>());
		getChildren(getData().getCategory2Id());
		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), null);
		if (getData().getCategory3Id() == null) {
			for (Object[] obj : getData().getCikkcsops()) {
				ids.add((BigDecimal) obj[0]);
			}
			if (ids != null && ids.size() > 0)
				getData().getProducts().addAll(getProductService().getProductsByCsopkods(ids, queryParamsVO));
		} else {
			getData().getProducts().addAll(getProductService().getProductsByCsopkod(getData().getCategory3Id(), queryParamsVO));
		}

		return null;
	}

	public String showProductsLevel3() {
		if (getData().getCategory3Id() != null) {
			getData().setMainMenu(generateMainMenu(getData().getCategoryId()));
			if (getData().getCategory3Id().toString().length() >= 4) {
				getData().setCikkcsops(new ArrayList<Object[]>());
				getChildren(getData().getCategoryId());
				String str = "<ul>";
				boolean childrenchildren = false;
				boolean opened = false;
				for (Object[] third : getData().getCikkcsops()) {
					if ((third[0].equals(getData().getCategory2Id()))) {
						str += "<li class=\"activemenu\">";
						str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + third[0] + "/\">" + third[1] + "</a>";
						str += "<ul>";
					} else if (third[2].equals(getData().getCategory2Id())) {
						if (opened) {
							str += "</ul>";
						}
						if (third[0].equals(getData().getCategory3Id())) {
							str += "<li class=\"activemenu2\"><a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
									+ "/termekek/kategoria/" + getData().getCategoryId() + "/" + getData().getCategory2Id() + "/" + third[0] + "/\">"
									+ third[1] + "</a>";
						} else {
							str += "<li><a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
									+ getData().getCategoryId() + "/" + getData().getCategory2Id() + "/" + third[0] + "/\">" + third[1] + "</a></li>";
						}
						if (opened) {
							str += "</li>";
							str += "</ul>";
							opened = false;
						}
					} else if (third[2].equals(getData().getCategory3Id())) {
						if (!childrenchildren) {
							str += "<ul>";
						}
						str += "<li><a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + getData().getCategory2Id() + "/" + third[2] + "/" + third[0] + "/\">" + third[1]
								+ "</a></li>";
						childrenchildren = true;
						opened = true;

					} else if (third[2].equals(getData().getCategoryId())) {
						if (opened) {
							str += "</ul>";
							str += "</li>";
							str += "</ul>";
							opened = false;
						}
						str += "<li>";
						str += "<a href=\"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/termekek/kategoria/"
								+ getData().getCategoryId() + "/" + third[0] + "/\">" + third[1] + "</a>";
						str += "</li>";
					}
				}
				str += "</ul>";
				getData().setMenu(str);
				getData().setCikkcsops(new ArrayList<Object[]>());
				getData().setProducts(new ArrayList<ProductVO>());
				getChildren(getData().getCategory3Id());
				QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),
						ConfigurationHandler.getAdaptDiscount(), null);
				List<BigDecimal> ids = new ArrayList<BigDecimal>();
				if (getData().getCategory4Id() == null) {
					for (Object[] obj : getData().getCikkcsops()) {
						ids.add((BigDecimal) obj[0]);
					}
					if (ids != null && ids.size() > 0)
						getData().getProducts().addAll(getProductService().getProductsByCsopkods(ids, queryParamsVO));
				} else {
					getData().getProducts().addAll(getProductService().getProductsByCsopkod(getData().getCategory4Id(), queryParamsVO));
				}
			} else {
				showProductsLevel2();
			}
		}

		return null;
	}

	public String showProductsLevel4() {
		getData().setMainMenu(generateMainMenu(getData().getCategoryId()));
		if (getData().getCategory4Id() != null) {
			getData().setProducts(new ArrayList<ProductVO>());
			QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),
					ConfigurationHandler.getAdaptDiscount(), null);
			getData().getProducts().addAll(getProductService().getProductsByCsopkod(getData().getCategory4Id(), queryParamsVO));
		}
		return null;
	}

	private void getChildren(BigDecimal kod) {
		// if (kod.toString().length() >= 4) {
		for (Object[] cikkcsop : getData().getAllCikkcsops()) {
			if (cikkcsop[2].equals(kod)) {
				getData().getCikkcsops().add(cikkcsop);
				getChildren((BigDecimal) cikkcsop[0]);
			}
		}
		// }
	}

	private Cikkcsop getParent(BigDecimal kod) {
		Cikkcsop cikkcsop = null;
		for (Object[] csop : getData().getAllCikkcsops()) {
			if (csop[0].equals(kod)) {
				cikkcsop = getProductService().getCikkcsopById((BigDecimal) csop[2]);
			}
		}
		return cikkcsop;
	}

	public String searchProductByCikkcsop() {
		if (getData().getSearchCikkcsop() == null || getData().getSearchProducts() == null || getData().getSearchProducts().size() == 0)
			return null;
		List<ProductVO> temp = new ArrayList<ProductVO>();
		for (ProductVO aru : getData().getSearchProducts())
			if (aru.getCsopkod() != null & aru.getCsopkod() != new BigDecimal(0)) {
				if (getParent(aru.getCsopkod()) != null) {
					if (getParent(aru.getCsopkod()).getKod().equals(getData().getSearchCikkcsop())) {
						temp.add(aru);
					}
				}
			}
		getData().setProducts(temp);
		return null;
	}

	public void sortListByNameAsc(ActionEvent event) {
		Collections.sort(getData().getProducts(), new ProductSortByNameAsc());
	}

	public void sortListByNameDesc(ActionEvent event) {
		Collections.sort(getData().getProducts(), new ProductSortByNameDesc());
	}

	public class ProductSortByNameAsc implements Comparator<ProductVO> {
		@Override
		public int compare(ProductVO o1, ProductVO o2) {
			String new1 = stringKonverter(o1.getMegn());
			String new2 = stringKonverter(o2.getMegn());
			return new1.compareToIgnoreCase(new2);
		}
	}

	public class ProductSortByNameDesc implements Comparator<ProductVO> {
		@Override
		public int compare(ProductVO o1, ProductVO o2) {
			String new1 = stringKonverter(o1.getMegn());
			String new2 = stringKonverter(o2.getMegn());
			return new2.compareToIgnoreCase(new1);
		}
	}

	public void sortListByPriceAsc(ActionEvent event) {
		Collections.sort(getData().getProducts(), new ProductSortByPriceAsc());
	}

	public void sortListByPriceDesc(ActionEvent event) {
		Collections.sort(getData().getProducts(), new ProductSortByPriceDesc());
	}

	public class ProductSortByPriceAsc implements Comparator<ProductVO> {
		@Override
		public int compare(ProductVO o1, ProductVO o2) {
			// return o1.getEar2().compareTo(o2.getEar2());
			return o1.getEar().compareTo(o2.getEar());
		}
	}

	public class ProductSortByPriceDesc implements Comparator<ProductVO> {
		@Override
		public int compare(ProductVO o1, ProductVO o2) {
			// return o2.getEar2().compareTo(o1.getEar2());
			return o2.getEar().compareTo(o1.getEar());
		}
	}

	public String showSelectedProduct() {
		QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), null);
		getData().setProduct(getProductService().getProductVOById(getData().getProductId(), queryParamsVO));
		getData().setProductBizkomment(getProductService().getBizkommentById("A" + getData().getProductId()));
		return null;
	}

	public void showProductList(ActionEvent event) {
		getData().setShowProductList(true);
		getData().setShowProductGrid(false);
	}

	public void showProductGrid(ActionEvent event) {
		getData().setShowProductList(false);
		getData().setShowProductGrid(true);
	}

	private String generateMainMenu(BigDecimal categoryId) {
		String menu = "";
		if (categoryId == null) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/1/\" class=\"inactiveMenuLink\">Profilsín vezetés</a>";
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/45/\" class=\"inactiveMenuLink\">Golyós orsók</a>";
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/75/\" class=\"inactiveMenuLink\">Pozícionáló rendszerek</a>";
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/70/\" class=\"inactiveMenuLink\">Elektromos emelőhenger</a>";
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/61/\" class=\"inactiveMenuLink\">Golyósperselyek</a>";

			return menu;
		}

		if (categoryId.equals(new BigDecimal(1))) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/1/\" class=\"activeMenu\">Profilsín vezetés</a>";
		} else {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/1/\" class=\"inactiveMenuLink\">Profilsín vezetés</a>";
		}
		if (categoryId.equals(new BigDecimal(45))) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/45/\" class=\"activeMenu\">Golyós orsók</a>";
		} else {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/45/\" class=\"inactiveMenuLink\">Golyós orsók</a>";
		}
		if (categoryId.equals(new BigDecimal(75))) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/75/\" class=\"activeMenu\">Pozícionáló rendszerek</a>";
		} else {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/75/\" class=\"inactiveMenuLink\">Pozícionáló rendszerek</a>";
		}
		if (categoryId.equals(new BigDecimal(70))) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/70/\" class=\"activeMenu\">Elektromos emelőhenger</a>";
		} else {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/70/\" class=\"inactiveMenuLink\">Elektromos emelőhenger</a>";
		}
		if (categoryId.equals(new BigDecimal(61))) {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/61/\" class=\"activeMenu\">Golyósperselyek</a>";
		} else {
			menu += "<a href=\"" + getCurrentUrl() + "/termekek/kategoria/61/\" class=\"inactiveMenuLink\">Golyósperselyek</a>";
		}

		return menu;
	}

	private List<ProductVO> cikkcsopMegnSearch(String megn) {
		QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), null);
		List<ProductVO> aruk = getProductService().getProductsByCikkcsopMegn(megn, queryParamsVO);
		return aruk;
	}

	private boolean containsAru(List<ProductVO> aruk, ProductVO aru) {
		for (ProductVO product : aruk) {
			if (product.getArukod() == aru.getArukod()) {
				return true;
			}
		}
		return false;
	}

	public void showRequestPopup(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer arukod = Integer.valueOf(myRequest.getParameter("arukod").toString());
		if (arukod != null) {
			getData().setRequestArukod(arukod);
		}
		getData().setRequestComment(null);
		getData().setRequestEmail(null);
		getData().setRequestName(null);
	}

	public String sendRequest() {
		if (getData().getRequestName() == null) {
			addErrorMessage("", "Kérem adja meg a nevét!");
			return null;
		}
		if (getData().getRequestEmail() == null) {
			addErrorMessage("", "Kérem adja meg az email címét!");
			return null;
		}
		try {
			boolean isSuccess = false;
			String body = "";
			body += "Név: " + getData().getRequestName();
			body += "\nEmail: " + getData().getRequestEmail();
			body += "\nMegjegyzés: " + getData().getRequestComment();
			body += "\nArukod: " + getData().getRequestArukod();
			isSuccess = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, getData().getRequestEmail(), ORDEREMAIL, "Ajánlat kérés", body);
			if (isSuccess) {
				addInfoMessage("", "Ajánlatkérés elküldve!");
			} else
				addErrorMessage("", "Hiba történt az ajánlatkérés során!");
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt az ajánlatkérés során!");
			logger.error("ERROR BY SENDING REQUEST!", e);
			return null;
		}
		return null;
	}

	public ProductControllerData getData() {
		return data;
	}

	public void setData(ProductControllerData data) {
		this.data = data;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
