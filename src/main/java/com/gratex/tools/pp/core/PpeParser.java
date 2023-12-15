package com.gratex.tools.pp.core;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PPEFooter;
import com.gratex.tools.pp.io.ppe.PPEHeader;
import com.gratex.tools.pp.io.ppe.PPERecord;

/**
 *
 * @author mkrajcovicux
 */
public class PpeParser implements FileParser<PPEFile> {

	/**
	 * TODO: leave only this method with public modifier, others must be package private, but test cases must be moved under this package
	 * @param source
	 * @return
	 * @throws IOException
	 */
	@Override
	public PPEFile parse(Path source, Charset encoding) throws IOException {
		List<String> lines = Files.readAllLines(source, encoding);
		PPEHeader header = parseHeader(lines.remove(0));
		PPEFooter footer = parseFooter(lines.remove(lines.size() - 1));
		List<PPERecord> body = parseBody(lines);
		return new PPEFile(header, body, footer);
	}

	/**
	 *
	 * @param line
	 * @return
	 */
	PPEHeader parseHeader(String line) {
		// TODO: convert this to meta data objects and document it
		int start = 0;
		int end = 1;

		PPEHeader header = new PPEHeader();
		header.setCode(line.substring(start, end));
		header.setIban1(line.substring((start = end), (end += 34)));
		header.setIban2(line.substring((start = end), (end += 34)));
		header.setFileCreated(line.substring((start = end), (end += 8)));
		header.setSerialNumberIn12M(line.substring((start = end), (end += 4)));
		header.setVoucherValidity(line.substring((start = end), (end += 2)));
		header.setDiacriticsCode(line.substring((start = end), (end += 3)));
		header.setTestLetterForDiacriticsCode(line.substring((start = end), (end += 1)));
		header.setPayOutDate(line.substring((start = end), (end += 8)));
		return header;
	}

	/**
	 *
	 * @param line
	 * @return
	 */
	PPEFooter parseFooter(String line) {
		int start = 0;
		int end = 1;

		PPEFooter footer = new PPEFooter();
		footer.setCode(line.substring(start, end));
		footer.setVoucherCount(line.substring((start = end), (end += 5)));
		footer.setTotalAmount(line.substring((start = end), (end += 13)));
		footer.setTotalPrice(line.substring((start = end), (end += 10)));
		footer.setSum(line.substring((start = end), (end += 13)));
		return footer;
	}

	/**
	 *
	 * @param lines
	 * @return
	 */
	List<PPERecord> parseBody(List<String> lines) {
		return lines.stream()
			.map(this::parseRecord)
			.collect(toList());
	}

	private PPERecord parseRecord(String line) {
		int start = 0;
		int end = 1;

		PPERecord record = new PPERecord();
		record.setCode(line.substring(start, end));
		record.setRecipientFullName(line.substring((start = end), (end += 30)));
		record.setRecipientOtherIdentifier(line.substring((start = end), (end += 30)));
		record.setStreet(line.substring((start = end), (end += 28)));
		record.setBuildingNumber(line.substring((start = end), (end += 10)));
		record.setMunicipality(line.substring((start = end), (end += 30)));
		record.setPostalCode(line.substring((start = end), (end += 5)));
		record.setAddressNote(line.substring((start = end), (end += 30)));
		record.setAmount(line.substring((start = end), (end += 10)));
		record.setPrice(line.substring((start = end), (end += 7)));
		record.setServiceCode(line.substring((start = end), (end += 3)));
		record.setRecipientCode(line.substring((start = end), (end += 30)));
		record.setPurpose(line.substring((start = end), (end += 30)));
		record.setEmail(line.substring((start = end), (end += 50)));
		record.setTelephone(line.substring((start = end), (end += 20)));
		return record;
	}

}
