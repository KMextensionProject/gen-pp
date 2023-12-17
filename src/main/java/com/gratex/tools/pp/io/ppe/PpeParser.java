package com.gratex.tools.pp.io.ppe;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.gratex.tools.pp.core.FileParser;
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

		PPERecord bodyLine = new PPERecord();
		bodyLine.setCode(sequencer.next(1));
		bodyLine.setRecipientFullName(sequencer.next(30));
		bodyLine.setRecipientOtherIdentifier(sequencer.next(30));
		bodyLine.setStreet(sequencer.next(28));
		bodyLine.setBuildingNumber(sequencer.next(10));
		bodyLine.setMunicipality(sequencer.next(30));
		bodyLine.setPostalCode(sequencer.next(5));
		bodyLine.setAddressNote(sequencer.next(30));
		bodyLine.setAmount(sequencer.next(10));
		bodyLine.setPrice(sequencer.next(7));
		bodyLine.setServiceCode(sequencer.next(3));
		bodyLine.setRecipientCode(sequencer.next(30));
		bodyLine.setPurpose(sequencer.next(30));
		bodyLine.setEmail(sequencer.next(50));
		bodyLine.setTelephone(sequencer.next(20));
		return bodyLine;
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

}
