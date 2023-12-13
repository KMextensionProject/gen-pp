package com.gratex.tools.pp.model.ppe;

public class PPEHeader {

	private String code; // 1
	private String iban1; // 34
	private String iban2; // 34
	private String fileCreated; // 8 (ddmmrrrr)
	private String serialNumberIn12M; // 4
	private String voucherValidity; // 2
	private String diacriticsCode; // 3
	private String testLetterForDiacriticsCode; // 1 (char?)
	private String payOutDate; // 8

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getSerialNumberIn12M() {
		return serialNumberIn12M;
	}

	public void setSerialNumberIn12M(String serialNumberIn12M) {
		this.serialNumberIn12M = serialNumberIn12M;
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
