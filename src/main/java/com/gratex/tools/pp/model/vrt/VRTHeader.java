package com.gratex.tools.pp.model.vrt;

import com.gratex.tools.pp.model.PPHeader;

public class VRTHeader extends PPHeader {

	private String senderCode; // 4
	private String iban; // 34
	private String sender; // 28
	private String street; // 20
	private String buildingNumber; // 7
	private String municipality; // 20
	private String postOfficePostalCode; // 5
	private String stampNumber; // 6
	private String dateReceived; // 10 (dd.mm.rrrr)
	private String receivedVoucherCount; // 6
	private String totalAmountRemitted; // 13
	private String totalPriceRemitted; // 10
	private String voucherReceiveDate; // 10 (dd.mm.rrrr)
	private String voucherValidityDate; // 10 (dd.mm.rrrr)
	private String voucherIssueDate; // 10 (dd.mm.rrrr)
	private String unpaidAmountAttributionDate; // 10 (dd.mm.rrrr)
	private String unpaidVouchersCount; // 6
	private String returnedPrice; // 13 (xxxxxxxxxx.xx)
	private String E2EReference; // 35 -> there is an enumeration of permitted values

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public String getStampNumber() {
		return stampNumber;
	}

	public void setStampNumber(String stampNumber) {
		this.stampNumber = stampNumber;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getReceivedVoucherCount() {
		return receivedVoucherCount;
	}

	public void setReceivedVoucherCount(String receivedVoucherCount) {
		this.receivedVoucherCount = receivedVoucherCount;
	}

	public String getTotalAmountRemitted() {
		return totalAmountRemitted;
	}

	public void setTotalAmountRemitted(String totalAmountRemitted) {
		this.totalAmountRemitted = totalAmountRemitted;
	}

	public String getTotalPriceRemitted() {
		return totalPriceRemitted;
	}

	public void setTotalPriceRemitted(String totalPriceRemitted) {
		this.totalPriceRemitted = totalPriceRemitted;
	}

	public String getVoucherReceiveDate() {
		return voucherReceiveDate;
	}

	public void setVoucherReceiveDate(String voucherReceiveDate) {
		this.voucherReceiveDate = voucherReceiveDate;
	}

	public String getVoucherValidityDate() {
		return voucherValidityDate;
	}

	public void setVoucherValidityDate(String voucherValidityDate) {
		this.voucherValidityDate = voucherValidityDate;
	}

	public String getVoucherIssueDate() {
		return voucherIssueDate;
	}

	public void setVoucherIssueDate(String voucherIssueDate) {
		this.voucherIssueDate = voucherIssueDate;
	}

	public String getUnpaidAmountAttributionDate() {
		return unpaidAmountAttributionDate;
	}

	public void setUnpaidAmountAttributionDate(String unpaidAmountAttributionDate) {
		this.unpaidAmountAttributionDate = unpaidAmountAttributionDate;
	}

	public String getUnpaidVouchersCount() {
		return unpaidVouchersCount;
	}

	public void setUnpaidVouchersCount(String unpaidVouchersCount) {
		this.unpaidVouchersCount = unpaidVouchersCount;
	}

	public String getReturnedPrice() {
		return returnedPrice;
	}

	public void setReturnedPrice(String returnedPrice) {
		this.returnedPrice = returnedPrice;
	}

	public String getE2EReference() {
		return E2EReference;
	}

	public void setE2EReference(String e2eReference) {
		E2EReference = e2eReference;
	}

}
