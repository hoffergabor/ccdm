// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package hu.iboard.coandco.datamagic.service.document;

import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;
import hu.iboard.coandco.datamagic.vo.document.OrderDocVO;
import hu.iboard.coandco.datamagic.vo.document.OrderItemDocVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.log4j.Logger;

public class DocumentService extends DocumentServiceBase {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public byte[] handleGenerateOrder(String sorszam) {
		byte[] resultInBytes = null;
		String xsl = "orderlist";
		List<OrderItemDocVO> items = new ArrayList<OrderItemDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);
			String xmlContent = "";
			Rendel rendel = getOrderDao().getOrderById(sorszam);

			OrderDocVO orderDoc = new OrderDocVO();

			orderDoc.setSorszam(sorszam);
			orderDoc.setKelt(convertDateTosString(rendel.getKelt()));

			orderDoc.setSzallito("-");
			orderDoc.setScim(rendel.getScim());
			orderDoc.setSirsz(rendel.getSirsz());
			orderDoc.setSvaros(rendel.getSvaros());
			orderDoc.setTel("");
			orderDoc.setEmail("");
			orderDoc.setAdoszam("");
			orderDoc.setKozadoszam("-");

			orderDoc.setMegj("");

			orderDoc.setVnev(rendel.getVnev());
			orderDoc.setVirsz(rendel.getVirsz());
			orderDoc.setVvaros(rendel.getVvaros());
			orderDoc.setVcim(rendel.getVcim());
			orderDoc.setVkapcstel(rendel.getVkapcstel());
			orderDoc.setUgyint(rendel.getUgyint());
			orderDoc.setAzon("-");
			orderDoc.setRaktar("-");
			orderDoc.setFizmod(rendel.getFizmod());
			orderDoc.setFuvmod(rendel.getFuvmod());
			orderDoc.setAtvevo(rendel.getMegj());
			orderDoc.setTelj(convertDateTosString(rendel.getTelj()));
			orderDoc.setEsedkelt(convertDateTosString(rendel.getEsedkelt()));

			if (rendel.getRendtets().size() > 0) {
				for (Rendtet rendtet : rendel.getRendtets()) {

					OrderItemDocVO item = new OrderItemDocVO();
					item.setAcikksz(rendtet.getAcikksz());
					item.setAmegn(rendtet.getAmegn());
					item.setAmenny(customFormat(pattern, rendtet.getAmenny()));
					item.setEgysegar(customFormat(pattern, rendtet.getAear()));
					item.setNertek(customFormat(pattern, rendtet.getNertek()));
					item.setBertek(customFormat(pattern, rendtet.getBertek()));
					// item.setThdat(convertDateTosString(rendtet.getThdat()));
					item.setUnikazon(rendtet.getUnikazon());
					item.setSorszam(rendtet.getSorszam());
					// item.setEgysegar(customFormat(pattern, rendtet.get))

					tempNetto = tempNetto.add(rendtet.getNertek());
					tempBrutto = tempBrutto.add(rendtet.getBertek());

					items.add(item);
				}

				orderDoc.setAllNetto(customFormat(pattern, tempNetto));
				orderDoc.setAllBrutto(customFormat(pattern, tempBrutto));
				orderDoc.setItems(rendel.getRendtets().size());

				xmlContent = generateXMLFromObject("main", orderDoc, "item", items, null, null, null, null, null, null);
			} else {
				xmlContent = generateXMLFromObject("main", orderDoc, null, null, null, null, null, null, null, null);
			}
			ByteArrayOutputStream generatedDocument = documentGenerator(xmlContent, xsl);
			if (generatedDocument == null) {
				return null;
			}

			resultInBytes = generatedDocument.toByteArray();
		} catch (Exception e) {
			String err = "Error creating document";
			logger.error(err, e);

		}
		return resultInBytes;
	}

	/**
	 * �talak�tja az XML sz�veget rtf vagy pdf dokumentumm�, egy xsl-fo mint�t
	 * �s az Apache FOP-t haszn�lva
	 * 
	 * @param xmlContent
	 *            - a haszn�lt XML
	 * @param xsl
	 *            - az xsl-fo minta
	 * @param documentType
	 *            - a dokumentum t�pusa
	 * 
	 * @return a gener�lt dokumentum b�jtfolyamk�nt
	 */
	protected ByteArrayOutputStream documentGenerator(String xmlContent, String xsl) throws Exception {
		String documentType = MimeConstants.MIME_PDF;
		String xslTransformation = xsl + "_pdf.xsl";
		String retailRootFOP = "fop";
		ByteArrayOutputStream document = new ByteArrayOutputStream();
		try {
			// Setup FOP
			FopFactory fopFactory = FopFactory.newInstance();
			fopFactory.setUserConfig(new File(retailRootFOP + "/fop.xconf"));
			fopFactory.setFontBaseURL(retailRootFOP);
			Fop fop = fopFactory.newFop(documentType, document);
			Source xsltSrc = new StreamSource(new File(retailRootFOP + "/" + xslTransformation));
			Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSrc);

			// Make sure the XSL transformation's result is piped
			// through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Setup input
			Source xmlSrc = new StreamSource(new StringReader(xmlContent));

			// Start the transformation and rendering process
			transformer.transform(xmlSrc, res);
		} catch (Exception e) {
			logger.error("FOP Error", e);
			throw e;
		}

		return document;
	}

	/**
	 * XML sz�veget gener�l egy objektumb�l
	 * 
	 * @param mainDataName
	 *            - a gener�lt XML legfels� tag-j�nek a neve
	 * @param mainData
	 *            - a haszn�lt objektum
	 * 
	 * @return
	 */
	protected String generateXMLFromObject(String mainDataName, Object mainData, String itemDataName, Object itemData, String allNettoName, Object allNetto,
			String allBruttoName, Object allBrutto, String allItemName, Object allItems) throws Exception {
		String generatedXML = null;

		StringWriter outputWriter = new StringWriter();

		outputWriter.write("<?xml version='1.0' encoding=\"utf-8\"?>\n");
		outputWriter.write("<list>");

		// Create a BeanWriter which writes to our prepared
		// stream
		BeanWriter beanWriter = new BeanWriter(outputWriter);

		// Configure betwixt
		// For more details see java docs or later in the main
		// documentation

		beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
		beanWriter.enablePrettyPrint();
		beanWriter.getBindingConfiguration().setMapIDs(false);
		beanWriter.write(mainDataName, mainData);
		if (itemData != null) {
			beanWriter.write(itemDataName, itemData);
		}
		if (allNetto != null) {
			beanWriter.write(allNettoName, allNetto);
		}
		if (allBrutto != null) {
			beanWriter.write(allBruttoName, allBrutto);
		}
		beanWriter.write("now", convertDateTosString(new Date()));
		if (allItems != null) {
			beanWriter.write(allItemName, allItems);
		}
		outputWriter.write("</list>");
		generatedXML = outputWriter.toString();
		outputWriter.close();

		return generatedXML;
	}

	private String customFormat(String pattern, BigDecimal value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}

	@SuppressWarnings("deprecation")
	private String convertDateTosString(Date date) {
		if (date == null)
			return "";
		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "." + dateFromMonth + "." + dateFromDay;
		if (convertedDate.equals("1900.01.01")) {
			convertedDate = "-";
		}

		return convertedDate;
	}

	@SuppressWarnings("deprecation")
	private String convertDateTimeTosString(Date date) {
		if (date == null)
			return "";
		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "." + dateFromMonth + "." + dateFromDay + " " + date.getHours() + ":" + date.getMinutes();

		return convertedDate;
	}

}