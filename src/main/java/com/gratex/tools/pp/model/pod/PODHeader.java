package com.gratex.tools.pp.model.pod;

import com.gratex.tools.pp.model.PPHeader;

public class PODHeader extends PPHeader {

	private String iban1; // 34
	private String stampNumber; // 6
	private String dayReceived; // 2
	private String monthReceived; // 2
	private String yearRecieved; // 4

	public String getIban1() {
		return iban1;
	}

	public void setIban1(String iban1) {
		this.iban1 = iban1;
	}

	public String getStampNumber() {
		return stampNumber;
	}

	public void setStampNumber(String stampNumber) {
		this.stampNumber = stampNumber;
	}

	public String getDayReceived() {
		return dayReceived;
	}

	public void setDayReceived(String dayReceived) {
		this.dayReceived = dayReceived;
	}

	public String getMonthReceived() {
		return monthReceived;
	}

	public void setMonthReceived(String monthReceived) {
		this.monthReceived = monthReceived;
	}

	public String getYearRecieved() {
		return yearRecieved;
	}

	public void setYearRecieved(String yearRecieved) {
		this.yearRecieved = yearRecieved;
	}

}
