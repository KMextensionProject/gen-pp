package com.gratex.tools.pp.model.pod;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.model.PPFile;

/**
 *
 * @author mkrajcovicux
 */
public final class PODFile extends PPFile {

	private PODHeader header;
	private List<PODRecord> body;
	private PODFooter footer;

	public PODFile(PODHeader header, List<PODRecord> body, PODFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public PODHeader getHeader() {
		return header;
	}

	public List<PODRecord> getBody() {
		return nonNull(body) && !body.isEmpty() 
				? new ArrayList<>(body)
				: emptyList();
	}

	public PODFooter getFooter() {
		return footer;
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
				   .append(header.getIban1())
				   .append(header.getSerialNumberIn12M())
				   .append(header.getStampNumber())
				   .append(header.getDayReceived())
				   .append(header.getMonthReceived())
				   .append(header.getYearRecieved())
				   .append(System.lineSeparator());
	}

	private void appendBody(StringBuilder content) {
		for (PODRecord record : body) {
			content.append(record.getCode())
				   .append(record.getFileNumber())
				   .append(record.getRecipientCode())
				   .append(record.getAmount())
				   .append(record.getPrice())
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
