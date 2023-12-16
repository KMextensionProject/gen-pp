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
import com.gratex.tools.pp.utils.CharSequenceIterator;

/**
 *
 * @author mkrajcovicux
 */
public class PpeParser implements FileParser<PPEFile> {

	/**
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
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PPEHeader header = new PPEHeader();
		header.setCode(sequencer.next(1));
		header.setIban1(sequencer.next(34));
		header.setIban2(sequencer.next(34));
		header.setFileCreated(sequencer.next(8));
		header.setSerialNumberIn12M(sequencer.next(4));
		header.setVoucherValidity(sequencer.next(2));
		header.setDiacriticsCode(sequencer.next(3));
		header.setTestLetterForDiacriticsCode(sequencer.next(1));
		header.setPayOutDate(sequencer.next(8));
		return header;
	}

	/**
	 *
	 * @param line
	 * @return
	 */
	PPEFooter parseFooter(String line) {
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PPEFooter footer = new PPEFooter();
		footer.setCode(sequencer.next(1));
		footer.setVoucherCount(sequencer.next(5));
		footer.setTotalAmount(sequencer.next(13));
		footer.setTotalPrice(sequencer.next(10));
		footer.setSum(sequencer.next(13));
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
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PPERecord record = new PPERecord();
		record.setCode(sequencer.next(1));
		record.setRecipientFullName(sequencer.next(30));
		record.setRecipientOtherIdentifier(sequencer.next(30));
		record.setStreet(sequencer.next(28));
		record.setBuildingNumber(sequencer.next(10));
		record.setMunicipality(sequencer.next(30));
		record.setPostalCode(sequencer.next(5));
		record.setAddressNote(sequencer.next(30));
		record.setAmount(sequencer.next(10));
		record.setPrice(sequencer.next(7));
		record.setServiceCode(sequencer.next(3));
		record.setRecipientCode(sequencer.next(30));
		record.setPurpose(sequencer.next(30));
		record.setEmail(sequencer.next(50));
		record.setTelephone(sequencer.next(20));
		return record;
	}

}
