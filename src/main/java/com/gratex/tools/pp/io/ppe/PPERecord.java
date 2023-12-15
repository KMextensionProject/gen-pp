package com.gratex.tools.pp.io.ppe;

import com.gratex.tools.pp.core.PPPart;

public class PPERecord extends PPPart {

	private String recipientFullName; // 30
	private String recipientOtherIdentifier; // 30
	private String street; // 28
	private String buildingNumber; // 10
	private String municipality; // 30
	private String postalCode; // 5
	private String addressNote; // 30
	private String amount; // 10 (including delimiter) (7777777.22)
	private String price; // 7 (including delimiter) (666666.22)
	private String serviceCode; // 3
	private String recipientCode; // 30
	private String purpose; // 30
	private String email; // 50
	private String telephone; // 20

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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressNote() {
		return addressNote;
	}

	public void setAddressNote(String addressNote) {
		this.addressNote = addressNote;
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

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getRecipientCode() {
		return recipientCode;
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
