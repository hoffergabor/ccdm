package hu.iboard.coandco.datamagic.presentation.controllers.mainpage;

import hu.iboard.coandco.datamagic.generated.Fuvpartner;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.fuvpartner.FuvpartnerServiceBase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mainpageController")
@RequestScoped
public class MainPageController extends AbstractController {

	public static final String MAINPAGE_ACTION = "mainpage";

	@ManagedProperty(value = "#{mainpageData}")
	private MainPageData data;
	@ManagedProperty(value = "#{fuvpartnerService}")
	private FuvpartnerServiceBase fuvpartnerService;

	@Override
	public void loadData() {
		if (getManagedStore() != null && getManagedFuvDates() != null) {
			getData().setAvaibleDates(getManagedFuvDates());
		}
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(MAINPAGE_CONTROLLER);

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

	private List<Date> loadAvaibleDates() {
		Calendar now = Calendar.getInstance();
		int week = now.get(Calendar.WEEK_OF_YEAR);
		int day = now.get(Calendar.DAY_OF_WEEK);
		boolean isEvenWeek = (week % 2 == 0 ? true : false);
		Integer vevokod = 0;
		if (getManagedPartner() != null)
			vevokod = getManagedPartner().getVevokod();
		if (getManagedStore() != null)
			vevokod = getManagedStore().getVevokod();
		List<Fuvpartner> fuvpartners = getFuvpartnerService().getFuvpartnersByPartnerId(vevokod);
		List<Date> dateList = new ArrayList<Date>();
		if (fuvpartners != null) {
			if (!isEvenWeek) {
				List<Integer> odd = checkOddAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleOddDay : odd) {
					if (avaibleOddDay >= day - 1) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
						dateList.add(cal.getTime());
						cal.add(Calendar.WEEK_OF_YEAR, 2);
						dateList.add(cal.getTime());
					}
				}
				List<Integer> even = checkEvenAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleEvenDay : even) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
					dateList.add(cal.getTime());
				}
			} else {
				List<Integer> even = checkEvenAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleEvenDay : even) {
					if (avaibleEvenDay >= day - 1) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, avaibleEvenDay - (day - 1));
						dateList.add(cal.getTime());
						cal.add(Calendar.WEEK_OF_YEAR, 2);
						dateList.add(cal.getTime());
					}
				}
				List<Integer> odd = checkOddAvaibleDays(fuvpartners.get(0));
				for (Integer avaibleOddDay : odd) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.add(Calendar.DATE, avaibleOddDay - (day - 1));
					dateList.add(cal.getTime());
				}

			}
		}
		Collections.sort(dateList);
		return dateList;
	}

	public MainPageData getData() {
		return data;
	}

	public void setData(MainPageData data) {
		this.data = data;
	}

	public FuvpartnerServiceBase getFuvpartnerService() {
		return fuvpartnerService;
	}

	public void setFuvpartnerService(FuvpartnerServiceBase fuvpartnerService) {
		this.fuvpartnerService = fuvpartnerService;
	}

}
