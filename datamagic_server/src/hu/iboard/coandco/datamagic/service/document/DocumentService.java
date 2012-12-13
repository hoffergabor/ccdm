// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package hu.iboard.coandco.datamagic.service.document;

import hu.iboard.coandco.datamagic.generated.IntMunkEl;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;
import hu.iboard.coandco.datamagic.generated.Munkszam2;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendmegj;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.vo.document.InvoiceDocVO;
import hu.iboard.coandco.datamagic.vo.document.OrderDocVO;
import hu.iboard.coandco.datamagic.vo.document.OrderItemDocVO;
import hu.iboard.coandco.datamagic.vo.document.RealtyArrangementDocVO;
import hu.iboard.coandco.datamagic.vo.document.RealtyArrangementPayOffAnyagDocVO;
import hu.iboard.coandco.datamagic.vo.document.RealtyArrangementPayOffDocVO;
import hu.iboard.coandco.datamagic.vo.document.RealtyArrangementPayOffDolgDocVO;
import hu.iboard.coandco.datamagic.vo.document.RealtyArrangementWorkSheetVO;
import hu.iboard.coandco.datamagic.vo.document.ShippingDocVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DocumentService extends DocumentServiceBase {

	@Override
	public byte[] handleGenerateInvoiceList(List<InvoiceVO> invoiceList) {
		byte[] resultInBytes = null;
		String xsl = "invoicelist";
		List<InvoiceDocVO> docList = new ArrayList<InvoiceDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			for (InvoiceVO invoiceVO : invoiceList) {

				InvoiceDocVO doc = new InvoiceDocVO();
				doc.setCegnev(invoiceVO.getCegnev());
				doc.setSorszam(invoiceVO.getSorszam());
				doc.setTelj(convertDateTosString(invoiceVO.getTelj()));
				doc.setFizetve(convertDateTosString(invoiceVO.getFizetve()));
				doc.setEsedkelt(convertDateTosString(invoiceVO.getEsedkelt()));
				doc.setReleased(convertDateTosString(invoiceVO.getRealesed()));
				doc.setNetto(customFormat(pattern, invoiceVO.getNetto()));
				doc.setBrutto(customFormat(pattern, invoiceVO.getBrutto()));
				doc.setTelephely(invoiceVO.getTelephely().getNev());
				tempNetto = tempNetto.add(invoiceVO.getNetto());
				tempBrutto = tempBrutto.add(invoiceVO.getBrutto());
				docList.add(doc);
			}

			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

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
			Rendel rendel = getOrderDao().loadOrder(sorszam);
			List<Rendmegj> rendmegj = getOrderDao().loadRendMegj(sorszam);
			
			
//			if (megj != null && megj.size() > 0)
//				for (int j = 0; j < megj.size(); j++) {
//					Rendmegj rendmegj = new Rendmegj();
//					//rendmegj.setRendel(rendel);
//					rendmegj.setSorszam(orderNum);
//					rendmegj.setKivagybe("B");
//					rendmegj.setMegj(megj.get(j));
//					rendmegj.setTsorsz(j + 1);
//					rendmegj.setUnikazon(CleanRendSorsz(orderNum) + "_" + rendmegj.getTsorsz().toString());
//					getOrderDao().saveRendMegj(rendmegj);
//				}
			String megj = "";

			if (rendmegj != null)
				for (Rendmegj rm : rendmegj) {
					megj += rm.getMegj();
				}
			
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

			orderDoc.setMegj(megj);

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
					//item.setThdat(convertDateTosString(rendtet.getThdat()));
					item.setUnikazon(rendtet.getUnikazon());
					item.setSorszam(rendtet.getSorszam());
					//item.setEgysegar(customFormat(pattern, rendtet.get))

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
		String jobportalRootFOP = "fop";
		ByteArrayOutputStream document = new ByteArrayOutputStream();
		try {
			// Setup FOP
			FopFactory fopFactory = FopFactory.newInstance();
			fopFactory.setUserConfig(new File(jobportalRootFOP + "/fop.xconf"));
			fopFactory.setFontBaseURL(jobportalRootFOP);
			Fop fop = fopFactory.newFop(documentType, document);
			Source xsltSrc = new StreamSource(new File(jobportalRootFOP + "/" + xslTransformation));
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

	protected ByteArrayOutputStream documentGeneratorForExcel(String xmlContent, String xsl) throws Exception {
		String documentType = MimeConstants.MIME_PLAIN_TEXT;
		String xslTransformation = xsl + "_xls.xsl";
		String jobportalRootFOP = "fop";
		ByteArrayOutputStream document = new ByteArrayOutputStream();
		try {
			// Setup FOP
			FopFactory fopFactory = FopFactory.newInstance();
			fopFactory.setUserConfig(new File(jobportalRootFOP + "/fop.xconf"));
			fopFactory.setFontBaseURL(jobportalRootFOP);
			Fop fop = fopFactory.newFop(documentType, document);
			Source xsltSrc = new StreamSource(new File(jobportalRootFOP + "/" + xslTransformation));
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

	@Override
	public byte[] handleGenerateOrderExcel(String sorszam) {

		byte[] resultInBytes = null;
		List<OrderItemDocVO> items = new ArrayList<OrderItemDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);
			String xmlContent = "";
			Rendel rendel = getOrderDao().loadOrder(sorszam);
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
			orderDoc.setMegj(rendel.getMegj());

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
			orderDoc.setTelj(convertDateTosString(rendel.getEsedkelt()));

			if (rendel.getRendtets().size() > 0) {
				for (Rendtet rendtet : rendel.getRendtets()) {

					OrderItemDocVO item = new OrderItemDocVO();
					item.setAcikksz(rendtet.getAcikksz());
					item.setAmegn(rendtet.getAmegn());
					item.setAmenny(customFormat(pattern, rendtet.getAmenny()));
					item.setNertek(customFormat(pattern, rendtet.getNertek()));
					item.setBertek(customFormat(pattern, rendtet.getBertek()));
					//item.setThdat(convertDateTosString(rendtet.getThdat()));
					item.setUnikazon(rendtet.getUnikazon());
					item.setSorszam(rendtet.getSorszam());

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

			ByteArrayOutputStream generatedDocument = excelGenerator(xmlContent);
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

	@SuppressWarnings("deprecation")
	public ByteArrayOutputStream excelGenerator(String xml) {

		ByteArrayOutputStream doc = new ByteArrayOutputStream();

		try {
			// Creating a Workbook
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet spreadSheet = wb.createSheet("spreadSheet");

			spreadSheet.setColumnWidth((short) 0, (short) (512 * 25));
			spreadSheet.setColumnWidth((short) 1, (short) (256 * 25));
			spreadSheet.setColumnWidth((short) 2, (short) (256 * 25));
			spreadSheet.setColumnWidth((short) 3, (short) (256 * 25));
			spreadSheet.setColumnWidth((short) 4, (short) (256 * 25));
			spreadSheet.setColumnWidth((short) 5, (short) (256 * 25));

			// Parsing XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			NodeList nodeList = document.getElementsByTagName("main");
			// Creating Rows
			HSSFRow row0 = spreadSheet.createRow(0);

			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setFontName("Arial");
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

			CellStyle style = wb.createCellStyle();
			style.setFont(font);

			HSSFCell cell = row0.createCell((short) 0);
			if (((Element) (nodeList.item(0))).getElementsByTagName("vnev").item(0).getFirstChild() != null) {
				cell.setCellValue(((Element) (nodeList.item(0))).getElementsByTagName("vnev").item(0).getFirstChild().getNodeValue());
				cell.setCellStyle(style);
			}
			if (((Element) (nodeList.item(0))).getElementsByTagName("kelt").item(0).getFirstChild() != null) {
				cell = row0.createCell((short) 1);
				cell.setCellValue(((Element) (nodeList.item(0))).getElementsByTagName("kelt").item(0).getFirstChild().getNodeValue());
				cell.setCellStyle(style);
			}

			HSSFRow row1 = spreadSheet.createRow(1);

			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

			cell = row1.createCell((short) 0);
			cell.setCellValue("Cikkszám");
			cell.setCellStyle(cellStyle);
			cell = row1.createCell((short) 1);
			cell.setCellValue("Megnevezés");
			cell.setCellStyle(cellStyle);
			cell = row1.createCell((short) 2);
			cell.setCellValue("Mennyiség");
			cell.setCellStyle(cellStyle);
			cell = row1.createCell((short) 3);
			cell.setCellValue("Érték (nettó)");
			cell.setCellStyle(cellStyle);
			cell = row1.createCell((short) 4);
			cell.setCellValue("Érték (bruttó)");
			cell.setCellStyle(cellStyle);
			cell = row1.createCell((short) 5);
			cell.setCellValue("Szállítási határidő");
			cell.setCellStyle(cellStyle);

			NodeList itemList = document.getElementsByTagName("item");

			int numberOfElements = itemList.getLength();

			for (int i = 0; i < numberOfElements; i++) {

				HSSFRow row = spreadSheet.createRow(2 + i);
				NodeList itemList2 = ((Element) (itemList.item(i))).getElementsByTagName("OrderItemDocVO");

				for (int j = 0; j < itemList2.getLength(); j++) {

					if (((Element) (itemList2.item(j))).getElementsByTagName("acikksz").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 0);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("acikksz").item(0).getFirstChild().getNodeValue());
					}
					if (((Element) (itemList2.item(j))).getElementsByTagName("amegn").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 1);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("amegn").item(0).getFirstChild().getNodeValue());
					}
					if (((Element) (itemList2.item(j))).getElementsByTagName("amenny").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 2);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("amenny").item(0).getFirstChild().getNodeValue());
					}
					if (((Element) (itemList2.item(j))).getElementsByTagName("nertek").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 3);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("nertek").item(0).getFirstChild().getNodeValue());
					}
					if (((Element) (itemList2.item(j))).getElementsByTagName("bertek").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 4);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("bertek").item(0).getFirstChild().getNodeValue());
					}
					if (((Element) (itemList2.item(j))).getElementsByTagName("thdat").item(0).getFirstChild() != null) {
						cell = row.createCell((short) 5);
						cell.setCellValue(((Element) (itemList2.item(j))).getElementsByTagName("thdat").item(0).getFirstChild().getNodeValue());
					}
				}
			}

			int lastElementIndex = numberOfElements + 2;
			HSSFRow row = spreadSheet.createRow(lastElementIndex);

			cell = row.createCell((short) 0);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = row.createCell((short) 1);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = row.createCell((short) 2);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = row.createCell((short) 3);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = row.createCell((short) 4);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);
			cell = row.createCell((short) 5);
			cell.setCellValue("");
			cell.setCellStyle(cellStyle);

			HSSFRow row3 = spreadSheet.createRow(lastElementIndex + 1);

			cell = row3.createCell((short) 3);
			cell.setCellValue("Összesen (nettó)");
			cell = row3.createCell((short) 4);
			cell.setCellValue("Összesen (bruttó)");

			HSSFRow row4 = spreadSheet.createRow(lastElementIndex + 2);

			if (((Element) (nodeList.item(0))).getElementsByTagName("allNetto").item(0).getFirstChild() != null) {
				cell = row4.createCell((short) 3);
				cell.setCellValue(((Element) (nodeList.item(0))).getElementsByTagName("allNetto").item(0).getFirstChild().getNodeValue());
				cell.setCellStyle(style);
			}
			if (((Element) (nodeList.item(0))).getElementsByTagName("allBrutto").item(0).getFirstChild() != null) {
				cell = row4.createCell((short) 4);
				cell.setCellValue(((Element) (nodeList.item(0))).getElementsByTagName("allBrutto").item(0).getFirstChild().getNodeValue());
				cell.setCellStyle(style);
			}
			// Outputting to Excel spreadsheet
			wb.write(doc);
			doc.flush();
			doc.close();
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfigurationException " + e.getMessage());
		} catch (SAXException e) {
			System.out.println("SAXException " + e.getMessage());
		}

		return doc;
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

	@Override
	public byte[] handleGenerateRealtyArrangementList(List<RealtyArrangementVO> realtyarrangementList) {
		byte[] resultInBytes = null;
		String xsl = "realtyarrangementlist";
		List<RealtyArrangementDocVO> docList = new ArrayList<RealtyArrangementDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			for (RealtyArrangementVO raVO : realtyarrangementList) {

				RealtyArrangementDocVO doc = new RealtyArrangementDocVO();
				doc.setSorszam(raVO.getItemNumber());
				doc.setLakas(raVO.getFlataddress());
				doc.setBejelentve(convertDateTosString(raVO.getBejelentes()));
				doc.setBejelento(raVO.getBejelento());
				doc.setHatarido(convertDateTosString(raVO.getHatarido()));
				doc.setBefejezve(convertDateTosString(raVO.getBefejezes()));
				doc.setNetto(customFormat(pattern, new BigDecimal(raVO.getNettoAr())));
				tempNetto = tempNetto.add(new BigDecimal(raVO.getNettoAr()));
				docList.add(doc);
			}

			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

			ByteArrayOutputStream generatedDocument = documentGenerator(xmlContent, xsl);
			if (generatedDocument == null) {
				return null;
			}

			resultInBytes = generatedDocument.toByteArray();
		} catch (Exception e) {
			String err = "Error creating document";
			logger.error(err, e);
			return null;
		}
		return resultInBytes;
	}

	@Override
	public byte[] handleGenerateRealtyArrangementPayOff(RealtyArrangementVO realty) {
		byte[] resultInBytes = null;
		String xsl = "realtyarrangement_payoff";
		List<RealtyArrangementPayOffDocVO> docList = new ArrayList<RealtyArrangementPayOffDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			Intezkedes intezkedes = getRealtyArrangementDao().getRealtyArrangementById(realty.getId());
			if (intezkedes == null)
				return null;

			RealtyArrangementPayOffDocVO vo = new RealtyArrangementPayOffDocVO();
			vo.setTulajdonos(getPartnerDao().getPartnerNameByPartnerId(intezkedes.getTulajdonos()));
			vo.setSorszam(intezkedes.getEvad() + "/" + intezkedes.getSorszam().toString());
			vo.setSzCimzett(getPartnerDao().getPartnerNameByPartnerId(intezkedes.getVevo()));
			int index = realty.getFlataddress().indexOf("]");
			String address = realty.getFlataddress().substring(index + 2, realty.getFlataddress().length());
			vo.setIngCim(address);
			vo.setBejelento(realty.getBejelento());
			vo.setBejelentesIdo(convertDateTosString(realty.getBejelentes()));
			vo.setHibaLeiras(realty.getMegjegyzes());
			vo.setMunkaLeiras(realty.getMegj4());
			vo.setBizonylatok(realty.getMegj2());
			vo.setOsszNettoAr(customFormat(pattern, new BigDecimal(realty.getNettoAr())));
			vo.setTeljesites(convertDateTosString(realty.getBefejezes()));
			vo.setDolgozo(new ArrayList<RealtyArrangementPayOffDolgDocVO>());
			vo.setAnyag(new ArrayList<RealtyArrangementPayOffAnyagDocVO>());

			List<IntMunkEl> munkaels = getRealtyArrangementDao().getIntMunkaElByIntezkedesId(realty.getId());
			if (munkaels != null && munkaels.size() > 0) {
				for (IntMunkEl intMunkEl : munkaels) {
					RealtyArrangementPayOffDolgDocVO dolg = new RealtyArrangementPayOffDolgDocVO();
					dolg.setDolgNev(intMunkEl.getParentIntMunkav().getNev());
					dolg.setDolgDatum(convertDateTosString(intMunkEl.getDatum()));
					dolg.setDolgKezd(convertDateTosString(intMunkEl.getKezdes()));
					dolg.setDolgBefej(convertDateTosString(intMunkEl.getBefejezes()));
					dolg.setDolgMegj(intMunkEl.getMegj());
					vo.getDolgozo().add(dolg);
				}
			} else {
				vo.getDolgozo().add(new RealtyArrangementPayOffDolgDocVO());
			}

			List<IntezAru> intaru = getRealtyArrangementDao().getIntezAruByIntezkedesId(realty.getId());
			if (intaru != null && intaru.size() > 0) {
				for (IntezAru intezAru : intaru) {

					BigDecimal menny = ((BigDecimal) intezAru.getMenny() != null ? (BigDecimal) intezAru.getMenny() : new BigDecimal(0));
					BigDecimal earForint = ((BigDecimal) intezAru.getEarForint() != null ? (BigDecimal) intezAru.getEarForint() : new BigDecimal(0));
					BigDecimal szolgaltat = ((BigDecimal) intezAru.getSzolgaltat() != null ? (BigDecimal) intezAru.getSzolgaltat() : new BigDecimal(0));
					BigDecimal bonyolit = ((BigDecimal) intezAru.getBonyolit() != null ? (BigDecimal) intezAru.getBonyolit() : new BigDecimal(0));
					BigDecimal szorz1 = szolgaltat.divide(new BigDecimal(100));
					BigDecimal szorz2 = bonyolit.divide(new BigDecimal(100));
					BigDecimal anyagigaz = menny.multiply(earForint).multiply(szorz1);
					BigDecimal bony = menny.multiply(earForint).multiply(szorz2);

					RealtyArrangementPayOffAnyagDocVO anyag = new RealtyArrangementPayOffAnyagDocVO();
					anyag.setAnyagMegn(intezAru.getParentAru().getMegn());
					anyag.setAnyagMenny(customFormat(pattern, intezAru.getMenny()));
					anyag.setAnyagME(intezAru.getParentAru().getMeegys());
					anyag.setAnyagEar(customFormat(pattern, intezAru.getEarForint()));
					anyag.setAnyagBonyolit(customFormat(pattern, bony));
					anyag.setAnyagIg(customFormat(pattern, anyagigaz));
					anyag.setAnyagOsszNetto(customFormat(pattern, anyagigaz.add(bony).add((intezAru.getMenny().multiply(intezAru.getEarForint())))));
					vo.getAnyag().add(anyag);
				}
			} else {
				vo.getAnyag().add(new RealtyArrangementPayOffAnyagDocVO());
			}
			String munkszam = realty.getEvad() + "/" + realty.getSorszam();
			List<Munkszam2> munkszamok = getMunkszam2Dao().getMunkszam2ByMunkszam(munkszam);
			if (munkszamok != null && munkszamok.size() > 0) {
				Munkszam2 munkszam2 = munkszamok.get(0);
				List<Ktetel_CDH> ktetels = getKtetelDao().getKtetelByMkateg4(munkszam2.getId());
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

						RealtyArrangementPayOffAnyagDocVO anyag = new RealtyArrangementPayOffAnyagDocVO();
						anyag.setAnyagMegn(ktetel.getParentAru().getMegn());
						anyag.setAnyagMenny(customFormat(pattern, ktetel.getAmenny()));
						anyag.setAnyagME(ktetel.getAmeegys());
						anyag.setAnyagEar(customFormat(pattern, ktetel.getAear()));
						anyag.setAnyagBonyolit(customFormat(pattern, bony));
						anyag.setAnyagIg(customFormat(pattern, anyagigaz));
						anyag.setAnyagOsszNetto(customFormat(pattern, anyagigaz.add(bony).add((ktetel.getAmenny().multiply(ktetel.getAear())))));
						vo.getAnyag().add(anyag);
					}
				}
			}

			docList.add(vo);
			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

			ByteArrayOutputStream generatedDocument = documentGenerator(xmlContent, xsl);
			if (generatedDocument == null) {
				return null;
			}
			resultInBytes = generatedDocument.toByteArray();
		} catch (Exception e) {
			String err = "Error creating document";
			logger.error(err, e);
			return null;
		}
		return resultInBytes;
	}

	@Override
	public byte[] handleGenerateRealtyArrangementWorkSheet(RealtyArrangementVO realty) {
		byte[] resultInBytes = null;
		String xsl = "realtyarrangement_worksheet";
		List<RealtyArrangementWorkSheetVO> docList = new ArrayList<RealtyArrangementWorkSheetVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			Intezkedes intezkedes = getRealtyArrangementDao().getRealtyArrangementById(realty.getId());
			if (intezkedes == null)
				return null;

			RealtyArrangementWorkSheetVO vo = new RealtyArrangementWorkSheetVO();

			vo.setMunkalapSzama(realty.getItemNumber());
			vo.setKiallitasDatuma(convertDateTimeTosString(new Date()));
			vo.setHibabejelentoNeve(realty.getBejelento());
			vo.setBejelentesModja(intezkedes.getBejelenMod());
			vo.setBejelentesDatuma(convertDateTimeTosString(realty.getBejelentes()));
			vo.setBejelentettHiba(intezkedes.getMegjegyzes());
			vo.setMunkatElrendelte(intezkedes.getElrendelte());
			vo.setMunkaFajta(intezkedes.getMunkafajta());
			vo.setPrioritas(intezkedes.isSurgos() ? "Sűrgős" : "Normál");
			vo.setMunkavegzesHatarideje(convertDateTosString(realty.getHatarido()));
			vo.setElvegzettMunka(intezkedes.getMegj4());
			vo.setMunkavegzesHelye(realty.getFlataddress());
			vo.setIngatlanTulajdonos(getPartnerDao().getPartnerNameByPartnerId(intezkedes.getTulajdonos()));

			docList.add(vo);

			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

			ByteArrayOutputStream generatedDocument = documentGenerator(xmlContent, xsl);
			if (generatedDocument == null) {
				return null;
			}
			resultInBytes = generatedDocument.toByteArray();
		} catch (Exception e) {
			String err = "Error creating document";
			logger.error(err, e);
			return null;
		}
		return resultInBytes;
	}

	@Override
	public byte[] handleGenerateShippingList(List<ShippingVO> shippingList) {
		byte[] resultInBytes = null;
		String xsl = "shippinglist";
		List<ShippingDocVO> docList = new ArrayList<ShippingDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			for (ShippingVO shipping : shippingList) {
				ShippingDocVO doc = new ShippingDocVO();
				doc.setNev(shipping.getVnev());
				doc.setSorszam(shipping.getSorszam());
				doc.setKelt(convertDateTosString(shipping.getKelt()));
				doc.setEsedkelt(convertDateTosString(shipping.getEsedkelt()));
				doc.setFizetve(convertDateTosString(shipping.getFizetve()));
				doc.setNetto(customFormat(pattern, shipping.getDnetto()));
				doc.setBrutto(customFormat(pattern, shipping.getDbrutto()));
				tempNetto = tempNetto.add(shipping.getDnetto());
				tempBrutto = tempBrutto.add(shipping.getDbrutto());
				docList.add(doc);
			}

			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

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

	@Override
	public byte[] handleGenerateOpenItemList(List<InvoiceVO> invoiceList) {
		byte[] resultInBytes = null;
		String xsl = "openitemlist";
		List<InvoiceDocVO> docList = new ArrayList<InvoiceDocVO>();

		try {
			String pattern = "#,##0";
			BigDecimal tempNetto = new BigDecimal(0);
			BigDecimal tempBrutto = new BigDecimal(0);

			for (InvoiceVO invoiceVO : invoiceList) {

				InvoiceDocVO doc = new InvoiceDocVO();
				doc.setCegnev(invoiceVO.getCegnev());
				doc.setSorszam(invoiceVO.getSorszam());
				doc.setTelj(convertDateTosString(invoiceVO.getTelj()));
				doc.setFizetve(convertDateTosString(invoiceVO.getFizetve()));
				doc.setEsedkelt(convertDateTosString(invoiceVO.getEsedkelt()));
				doc.setReleased(convertDateTosString(invoiceVO.getRealesed()));
				doc.setNetto(customFormat(pattern, invoiceVO.getNetto()));
				doc.setBrutto(customFormat(pattern, invoiceVO.getBrutto()));
				doc.setTelephely(invoiceVO.getTelephely().getNev());
				tempNetto = tempNetto.add(invoiceVO.getNetto());
				tempBrutto = tempBrutto.add(invoiceVO.getBrutto());
				docList.add(doc);
			}

			String xmlContent = generateXMLFromObject("main", docList, null, null, "allNetto", (customFormat(pattern, tempNetto)), "allBrutto",
					(customFormat(pattern, tempBrutto)), null, null);

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
}