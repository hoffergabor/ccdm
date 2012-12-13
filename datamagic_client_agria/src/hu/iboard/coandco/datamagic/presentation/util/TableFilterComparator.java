package hu.iboard.coandco.datamagic.presentation.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TableFilterComparator implements Comparator<Object> {

	private List<String> getters;
	private boolean ascending;

	// Constructor
	// -------------------------------------------------------------------------------

	/**
	 * @param getter
	 *            The name of the getter of the field to sort on.
	 * @param ascending
	 *            The sort order: true = ascending, false = descending.
	 */
	public TableFilterComparator(String getter, boolean ascending) {
		this.getters = new ArrayList<String>();
		for (String name : getter.split("\\.")) {
			if (!name.startsWith("get")) {
				name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			}
			this.getters.add(name);
		}
		this.ascending = ascending;
	}

	/**
	 * @param getter
	 *            The name of the getter of the field to sort on.
	 */
	public TableFilterComparator(String getter) {
		this(getter, true);
	}

	@Override
	public int compare(Object o1, Object o2) {
		try {
			Iterator<String> iter = getters.iterator();
			while (o1 != null && o2 != null && iter.hasNext()) {
				String getter = iter.next();
				o1 = o1.getClass().getMethod(getter, new Class[0]).invoke(o1, new Object[0]);
				o2 = o2.getClass().getMethod(getter, new Class[0]).invoke(o2, new Object[0]);
			}
		} catch (Exception e) {
			// If this exception occurs, then it is usually a fault of the DTO
			// developer.
			throw new RuntimeException("Cannot compare " + o1 + " with " + o2 + " on " + getters, e);
		}

		if (o1 instanceof String) {
			o1 = stringKonverter((String) o1);
		}
		
		if (o2 instanceof String) {
			o2 = stringKonverter((String) o2);
		}

		if (ascending) {
			return (o1 == null) ? -1 : ((o2 == null) ? 1 : ((Comparable<Object>) o1).compareTo(o2));
		} else {
			return (o2 == null) ? -1 : ((o1 == null) ? 1 : ((Comparable<Object>) o2).compareTo(o1));
		}
	}

	private String stringKonverter(String s) {
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
