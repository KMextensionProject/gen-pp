package com.gratex.tools.pp.io.vrt;

import static com.gratex.tools.pp.core.FileType.VRT;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.core.FileType;
import com.gratex.tools.pp.core.PPFile;

public class VRTFile extends PPFile {

	private VRTHeader header;
	private List<VRTRecord> body;
	private VRTFooter footer;

	public VRTFile(VRTHeader header, List<VRTRecord> body, VRTFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public VRTHeader getHeader() {
		return header;
	}

	public List<VRTRecord> getBody() {
		return nonNull(body) && !body.isEmpty() 
			? new ArrayList<>(body) 
			: emptyList();
	}

	public VRTFooter getFooter() {
		return footer;
	}

	@Override
	public FileType getFileType() {
		return VRT;
	}

	@Override
	protected final String buildFileString() {
		StringBuilder fileContent = new StringBuilder();
		appendHeader(fileContent);
		appendBody(fileContent);
		appendFooter(fileContent);
		return fileContent.toString();
	}

	private void appendHeader(StringBuilder fileContent) {
		fileContent.append(header.getCode())
				   .append(header.getSenderCode())
				   .append(header.getIban())
				   .append(header.getSender())
				   .append(header.getStreet())
				   .append(header.getBuildingNumber())
				   .append(header.getMunicipality())
				   .append(header.getPostOfficePostalCode())
				   .append(header.getSerialNumberIn12M())
				   .append(header.getStampNumber())
				   .append(header.getDateReceived())
				   .append(header.getReceivedVoucherCount())
				   .append(header.getTotalAmountRemitted())
				   .append(header.getTotalPriceRemitted())
				   .append(header.getVoucherReceiveDate())
				   .append(header.getVoucherValidityDate())
				   .append(header.getVoucherIssueDate())
				   .append(header.getUnpaidAmountAttributionDate())
				   .append(header.getUnpaidVouchersCount())
				   .append(header.getReturnedPrice())
				   .append(header.getE2EReference())
				   .append("\r\n");
	}

	private void appendBody(StringBuilder content) {
		for (VRTRecord bodyLine : body) {
			content.append(bodyLine.getCode())
				   .append(bodyLine.getRecipientFullName())
				   .append(bodyLine.getRecipientOtherIdentifier())
				   .append(bodyLine.getStreet())
				   .append(bodyLine.getBuildingNumber())
				   .append(bodyLine.getMunicipality())
				   .append(bodyLine.getPostOfficePostalCode())
				   .append(bodyLine.getAddressNote())
				   .append(bodyLine.getRecipientCode())
				   .append(bodyLine.getAmount())
				   .append(bodyLine.getFileNumber())
				   .append(bodyLine.getReasonUnpaid())
				   .append("\r\n");
		}
	}

	private void appendFooter(StringBuilder fileContent) {
		fileContent.append(footer.getCode())
				   .append(footer.getDataSentencesCount())
				   .append(footer.getDataSentencesAmount());
	}
}
