package com.gratex.tools.pp.model.vrt;

import com.gratex.tools.pp.model.PPPart;

public class VRTRecord extends PPPart {

	private String recipientFullName; // 30
	private String recipientOtherIdentifier; // 30
	private String street; // 28
	private String buildingNumber; // 10
	private String municipality; // 30
	private String postOfficePostalCode; // 5
	private String addressNote; // 30
	private String recipientCode; // 30
	private String amount; // 10
	private String fileNumber; // 5
	private String reasonUnpaid; // 21

	public String getRecipientFullName() {
		return recipientFullName;
	}

	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
	}

	public String getRecipientOtherIdentifier() {
		return recipientOtherIdentifier;
	}

	public void setRecipientOtherIdentifier(String recipientOtherIdentifier) {
		this.recipientOtherIdentifier = recipientOtherIdentifier;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getPostOfficePostalCode() {
		return postOfficePostalCode;
	}

	public void setPostOfficePostalCode(String postOfficePostalCode) {
		this.postOfficePostalCode = postOfficePostalCode;
	}

	public String getAddressNote() {
		return addressNote;
	}

	public void setAddressNote(String addressNote) {
		this.addressNote = addressNote;
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

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getReasonUnpaid() {
		return reasonUnpaid;
	}

	public void setReasonUnpaid(String reasonUnpaid) {
		this.reasonUnpaid = reasonUnpaid;
	}

}
