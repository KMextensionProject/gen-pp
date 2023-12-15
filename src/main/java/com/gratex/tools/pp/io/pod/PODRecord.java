package com.gratex.tools.pp.io.pod;

import com.gratex.tools.pp.core.PPPart;

public class PODRecord extends PPPart {

	private String fileNumber; // 5
	private String recipientCode; // 30
	private String amount; // 10
	private String price; // 7

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getRecipientCode() {
		return recipientCode;
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
