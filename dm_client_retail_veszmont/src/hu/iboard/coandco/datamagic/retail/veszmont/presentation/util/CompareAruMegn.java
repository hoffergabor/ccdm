package hu.iboard.coandco.datamagic.retail.veszmont.presentation.util;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.Comparator;

public class CompareAruMegn implements Comparator<ProductVO> {

	@Override
	public int compare(ProductVO aru1, ProductVO aru2) {
		String megn1 = aru1.getMegn();
		String megn2 = aru2.getMegn();
		return stringKonverter(megn1).compareToIgnoreCase(megn2);
	}

	protected String stringKonverter(String s) {
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++)
			switch (c[i]) {
			case 'á':
				sb.append("a");
				break;
			case 'Á':
				sb.append("A");
				break;
			case 'é':
				sb.append("e");
				break;
			case 'É':
				sb.append("E");
				break;
			case 'í':
				sb.append("i");
				break;
			case 'Í':
				sb.append("I");
				break;
			case 'ó':
				sb.append("o");
				break;
			case 'Ó':
				sb.append("O");
				break;
			case 'ö':
				sb.append("o");
				break;
			case 'Ö':
				sb.append("O");
				break;
			case 'ő':
				sb.append("o");
				break;
			case 'Ő':
				sb.append("O");
				break;
			case 'ú':
				sb.append("u");
				break;
			case 'Ú':
				sb.append("u");
				break;
			case 'ű':
				sb.append("u");
				break;
			case 'Ű':
				sb.append("u");
				break;
			default:
				sb.append(c[i]);
			}
		return sb.toString();
	}
}
