package com.gratex.tools.pp.io.ppe;

import static com.gratex.tools.pp.core.FileType.PPE;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.core.FileType;
import com.gratex.tools.pp.core.PPFile;

/**
 *
 * @author mkrajcovicux
 */
public final class PPEFile extends PPFile { // TODO: review hierarchy

	private PPEHeader header;
	private List<PPERecord> body;
	private PPEFooter footer;

	public PPEFile(PPEHeader header, List<PPERecord> body, PPEFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public PPEHeader getHeader() {
		return header;
	}

	public List<PPERecord> getBody() {
		return nonNull(body) && !body.isEmpty() 
				? new ArrayList<>(body)
				: emptyList();
	}

	public PPEFooter getFooter() {
		return footer;
	}

	@Override
	public FileType getFileType() {
		return PPE;
	}

	@Override
	protected String buildFileString() {
		StringBuilder fileContent = new StringBuilder();
		appendHeader(fileContent);
		appendBody(fileContent);
		appendFooter(fileContent);
		return fileContent.toString();
	}

	private void appendHeader(StringBuilder fileContent) {
		fileContent.append(header.getCode())
				   .append(header.getIban1())
				   .append(header.getIban2())
				   .append(header.getFileCreated())
				   .append(header.getSerialNumberIn12M())
				   .append(header.getVoucherValidity())
				   .append(header.getDiacriticsCode())
				   .append(header.getTestLetterForDiacriticsCode())
				   .append(header.getPayOutDate())
				   .append(System.lineSeparator());
	}

	private void appendBody(StringBuilder content) {
		for (PPERecord bodyLine : body) {
			content.append(bodyLine.getCode())
				   .append(bodyLine.getRecipientFullName())
				   .append(bodyLine.getRecipientOtherIdentifier())
				   .append(bodyLine.getStreet())
				   .append(bodyLine.getBuildingNumber())
				   .append(bodyLine.getMunicipality())
				   .append(bodyLine.getPostalCode())
				   .append(bodyLine.getAddressNote())
				   .append(bodyLine.getAmount())
				   .append(bodyLine.getPrice())
				   .append(bodyLine.getServiceCode())
				   .append(bodyLine.getRecipientCode())
				   .append(bodyLine.getPurpose())
				   .append(bodyLine.getEmail())
				   .append(bodyLine.getTelephone())
				   .append(System.lineSeparator());
		}
	}

	private void appendFooter(StringBuilder fileContent) {
		fileContent.append(footer.getCode())
				   .append(footer.getVoucherCount())
				   .append(footer.getTotalAmount())
				   .append(footer.getTotalPrice())
				   .append(footer.getSum());
	}

}
