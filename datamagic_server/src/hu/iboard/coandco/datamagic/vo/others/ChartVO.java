package hu.iboard.coandco.datamagic.vo.others;

import java.math.BigDecimal;

public class ChartVO {
	
	private Integer year;
	private Integer month;
	private BigDecimal value;
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
