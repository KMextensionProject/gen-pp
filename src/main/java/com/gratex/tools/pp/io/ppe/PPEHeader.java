package com.gratex.tools.pp.io.ppe;

import com.gratex.tools.pp.core.PPHeader;

public class PPEHeader extends PPHeader {

	private String iban1; // 34
	private String iban2; // 34
	private String fileCreated; // 8 (ddmmrrrr)
	private String voucherValidity; // 2
	private String diacriticsCode; // 3
	private String testLetterForDiacriticsCode; // 1 (char?)
	private String payOutDate; // 8

	public String getIban1() {
		return iban1;
	}

	public void setIban1(String iban1) {
		this.iban1 = iban1;
	}

	public String getIban2() {
		return iban2;
	}

	public void setIban2(String iban2) {
		this.iban2 = iban2;
	}

	public String getFileCreated() {
		return fileCreated;
	}

	public void setFileCreated(String fileCreated) {
		this.fileCreated = fileCreated;
	}

	public String getVoucherValidity() {
		return voucherValidity;
	}

	public void setVoucherValidity(String voucherValidity) {
		this.voucherValidity = voucherValidity;
	}

	public String getDiacriticsCode() {
		return diacriticsCode;
	}

	public void setDiacriticsCode(String diacriticsCode) {
		this.diacriticsCode = diacriticsCode;
	}

	public String getTestLetterForDiacriticsCode() {
		return testLetterForDiacriticsCode;
	}

	public void setTestLetterForDiacriticsCode(String testLetterForDiacriticsCode) {
		this.testLetterForDiacriticsCode = testLetterForDiacriticsCode;
	}

	public String getPayOutDate() {
		return payOutDate;
	}

	public void setPayOutDate(String payOutDate) {
		this.payOutDate = payOutDate;
	}

}
