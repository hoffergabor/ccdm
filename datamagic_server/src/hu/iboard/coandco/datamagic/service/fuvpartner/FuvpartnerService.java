package hu.iboard.coandco.datamagic.service.fuvpartner;

import hu.iboard.coandco.datamagic.generated.Fuvpartner;
import hu.iboard.coandco.datamagic.generated.Fuvtura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FuvpartnerService extends FuvpartnerServiceBase {

	@Override
	public Fuvpartner getFuvpartnerById(Integer id) {
		if (id == null)
			return null;
		return getFuvpartnerDao().getFuvpartnerById(id);
	}

	@Override
	public List<Fuvpartner> getFuvpartnersByPartnerId(Integer id) {
		if (id == null)
			return null;
		return getFuvpartnerDao().getFuvpartnersByPartnerId(id);
	}

	@Override
	public Fuvtura getFuvturaById(Integer id) {
		if (id == null)
			return null;
		return getFuvturaDao().getFuvturaById(id);
	}

	@Override
	public List<Fuvtura> getFuvturaByPartnerUtvonal(Integer id) {
		if (id == null)
			return null;
		return getFuvturaDao().getFuvturaByPartnerUtvonal(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Date> loadAvaibleFuvDates(Integer vevokod, Integer utvonal) {
		Calendar now = Calendar.getInstance();
		int week = now.get(Calendar.WEEK_OF_YEAR);
		int day = now.get(Calendar.DAY_OF_WEEK);
		boolean isEvenWeek = (week % 2 == 0 ? true : false);
		List<Date> dateList = new ArrayList<Date>();

		// FUVPARTNER
		List<Fuvpartner> fuvpartners = getFuvpartnersByPartnerId(vevokod);
		List<Date> fuvPartnerDateList = new ArrayList<Date>();
		if (fuvpartners != null && fuvpartners.size() > 0) {
			// paratlan het
			if (!isEvenWeek) {
				List<Integer> odd = checkOddAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleOddDay : odd) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
					if (avaibleOddDay >= day - 1) {
						fuvPartnerDateList.add(cal.getTime());
					}
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					fuvPartnerDateList.add(cal.getTime());
				}
				List<Integer> even = checkEvenAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleEvenDay : even) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
					fuvPartnerDateList.add(cal.getTime());
				}
				
				//ha nincs
				if(even.isEmpty() && odd.isEmpty()){
					return getFuvturas(utvonal, isEvenWeek, day);
				}
			} else {
				List<Integer> even = checkEvenAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleEvenDay : even) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
					if (avaibleEvenDay >= day - 1) {
						fuvPartnerDateList.add(cal.getTime());
					}
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					fuvPartnerDateList.add(cal.getTime());
				}
				List<Integer> odd = checkOddAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleOddDay : odd) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
					fuvPartnerDateList.add(cal.getTime());
				}
				if(even.isEmpty() && odd.isEmpty()){
					return getFuvturas(utvonal, isEvenWeek, day);
				}
			}
		} 
		normalizeDates(fuvPartnerDateList);
		Collections.sort(fuvPartnerDateList);

		// FUVTURA
		List<Date> fuvTuraDateList = getFuvturas(utvonal, isEvenWeek, day);

		Calendar pCalendar = Calendar.getInstance();
		Calendar tCalendar = Calendar.getInstance();
		for (Date pDate : fuvPartnerDateList) {
			for (Date tDate : fuvTuraDateList) {
				pCalendar.setTime(pDate);
				tCalendar.setTime(tDate);
				tCalendar.set(Calendar.MILLISECOND, 0);
				pCalendar.set(Calendar.MILLISECOND, 0);
				if (pCalendar.getTimeInMillis() == tCalendar.getTimeInMillis()) {
					// if (pDate.getTime() == tDate.getTime()) {
					// if (((pDate.getYear() + 1900) + (pDate.getMonth() + 1) +
					// pDate.getDay()) == ((tDate.getYear() + 1900) +
					// (tDate.getMonth() + 1) + tDate.getDay())) {
					dateList.add(pDate);
					break;
				}
			}
		}
		if (dateList == null || dateList.size() == 0)
			return null;
		return dateList;
	}

	private List<Date> getFuvturas(Integer utvonal, boolean isEvenWeek, int day) {
		List<Fuvtura> fuvturas = getFuvturaByPartnerUtvonal(utvonal);
		List<Date> fuvTuraDateList = new ArrayList<Date>();
		if (fuvturas != null && fuvturas.size() > 0) {
			if (!isEvenWeek) {
				List<Integer> odd = checkOddAvaibleDaysTura(fuvturas.get(0));
				for (Integer avaibleOddDay : odd) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
					if (avaibleOddDay >= day - 1) {
						fuvTuraDateList.add(cal.getTime());
					}
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					fuvTuraDateList.add(cal.getTime());
				}
				List<Integer> even = checkEvenAvaibleDaysTura(fuvturas.get(0));
				for (Integer avaibleEvenDay : even) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
					fuvTuraDateList.add(cal.getTime());
				}
			} else {
				List<Integer> even = checkEvenAvaibleDaysTura(fuvturas.get(0));
				for (Integer avaibleEvenDay : even) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
					if (avaibleEvenDay >= day - 1) {
						fuvTuraDateList.add(cal.getTime());
					}
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					fuvTuraDateList.add(cal.getTime());
				}
				List<Integer> odd = checkOddAvaibleDaysTura(fuvturas.get(0));
				for (Integer avaibleOddDay : odd) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
					fuvTuraDateList.add(cal.getTime());
				}
			}
		}
		normalizeDates(fuvTuraDateList);
		Collections.sort(fuvTuraDateList);
		return fuvTuraDateList;

	}

	@SuppressWarnings("deprecation")
	private List<Date> normalizeDates(List<Date> dates) {
		List<Date> temp = new ArrayList<Date>();
		for (Date date : dates) {
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			Calendar cal = Calendar.getInstance();
			cal.set(date.getYear() + 1900, date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
			cal.set(Calendar.MILLISECOND, 0);
			temp.add(cal.getTime());
		}

		return temp;
	}

	private List<Integer> checkOddAvaibleDays(Fuvpartner fuvpartner) {
		List<Integer> temp = new ArrayList<Integer>();
		if (fuvpartner.getPlhetfo()) {
			temp.add(1);
		}
		if (fuvpartner.getPlkedd()) {
			temp.add(2);
		}
		if (fuvpartner.getPlszerda()) {
			temp.add(3);
		}
		if (fuvpartner.getPlcsutortok()) {
			temp.add(4);
		}
		if (fuvpartner.getPlpentek()) {
			temp.add(5);
		}
		if (fuvpartner.getPlszombat()) {
			temp.add(6);
		}
		if (fuvpartner.getPlvasarnap()) {
			temp.add(7);
		}
		return temp;
	}

	private List<Integer> checkEvenAvaibleDays(Fuvpartner fuvpartner) {
		List<Integer> temp = new ArrayList<Integer>();
		if (fuvpartner.getPshetfo()) {
			temp.add(1);
		}
		if (fuvpartner.getPskedd()) {
			temp.add(2);
		}
		if (fuvpartner.getPsszerda()) {
			temp.add(3);
		}
		if (fuvpartner.getPscsutortok()) {
			temp.add(4);
		}
		if (fuvpartner.getPspentek()) {
			temp.add(5);
		}
		if (fuvpartner.getPsszombat()) {
			temp.add(6);
		}
		if (fuvpartner.getPsvasarnap()) {
			temp.add(7);
		}
		return temp;
	}

	private List<Integer> checkOddAvaibleDaysTura(Fuvtura fuvtura) {
		List<Integer> temp = new ArrayList<Integer>();
		if (fuvtura.getPlhetfo()) {
			temp.add(1);
		}
		if (fuvtura.getPlkedd()) {
			temp.add(2);
		}
		if (fuvtura.getPlszerda()) {
			temp.add(3);
		}
		if (fuvtura.getPlcsutortok()) {
			temp.add(4);
		}
		if (fuvtura.getPlpentek()) {
			temp.add(5);
		}
		if (fuvtura.getPlszombat()) {
			temp.add(6);
		}
		if (fuvtura.getPlvasarnap()) {
			temp.add(7);
		}
		return temp;
	}

	private List<Integer> checkEvenAvaibleDaysTura(Fuvtura fuvtura) {
		List<Integer> temp = new ArrayList<Integer>();
		if (fuvtura.getPshetfo()) {
			temp.add(1);
		}
		if (fuvtura.getPskedd()) {
			temp.add(2);
		}
		if (fuvtura.getPsszerda()) {
			temp.add(3);
		}
		if (fuvtura.getPscsutortok()) {
			temp.add(4);
		}
		if (fuvtura.getPspentek()) {
			temp.add(5);
		}
		if (fuvtura.getPsszombat()) {
			temp.add(6);
		}
		if (fuvtura.getPsvasarnap()) {
			temp.add(7);
		}
		return temp;
	}

}
