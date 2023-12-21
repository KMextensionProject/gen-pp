package com.gratex.tools.pp.io.pod;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.gratex.tools.pp.utils.CharSequenceIterator;
import com.gratex.tools.pp.core.DataIntegrityViolation;
import com.gratex.tools.pp.core.FileParser;

/**
 * Caution, this class was generated by pp-codegen.jar, thus it shouldn't be altered manually.
 *
 * @source: pp_structure.xlsx
 * @time: 2023-12-21T10:55:04.089+01:00[Europe/Bratislava]
 * @author mkrajcovicux
 */
public class PODParser implements FileParser<PODFile> {

	@Override
	public PODFile parse(Path source, Charset encoding) throws IOException {
		List<String> lines = Files.readAllLines(source, encoding);
		lines.removeIf(e -> e.trim().isEmpty());

		try {
			PODHeader header = parseHeader(lines.remove(0));
			PODFooter footer = parseFooter(lines.remove(lines.size() - 1));
			List<PODRecord> body = parseBody(lines);
			return new PODFile(header, body, footer);

		} catch (DataIntegrityViolation div) {
			throw new IOException("data integrity violation", div);
		}
	}

	PODHeader parseHeader(String line) {
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PODHeader header = new PODHeader();
		header.setCode(sequencer.next(1));
		header.setIban1(sequencer.next(34));
		header.setSerialNumberIn12M(sequencer.next(4));
		header.setStampNumber(sequencer.next(6));
		header.setDayReceived(sequencer.next(2));
		header.setMonthReceived(sequencer.next(2));
		header.setYearReceived(sequencer.next(4));

		validateRemainingContent(sequencer);
		return header;
	}

	List<PODRecord> parseBody(List<String> lines) {
		return lines.stream()
		.map(this::parseRecord)
		.collect(toList());
	}

	private PODRecord parseRecord(String line) {
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PODRecord bodyLine = new PODRecord();
		bodyLine.setCode(sequencer.next(1));
		bodyLine.setFileNumber(sequencer.next(5));
		bodyLine.setRecipientCode(sequencer.next(30));
		bodyLine.setAmount(sequencer.next(10));
		bodyLine.setPrice(sequencer.next(7));

		validateRemainingContent(sequencer);
		return bodyLine;
	}

	PODFooter parseFooter(String line) {
		CharSequenceIterator sequencer = new CharSequenceIterator(line);

		PODFooter footer = new PODFooter();
		footer.setCode(sequencer.next(1));
		footer.setVoucherCount(sequencer.next(5));
		footer.setTotalAmount(sequencer.next(13));
		footer.setTotalPrice(sequencer.next(10));
		footer.setSum(sequencer.next(13));

		validateRemainingContent(sequencer);
		return footer;
	}

	private void validateRemainingContent(CharSequenceIterator sequencer) {
		if (sequencer.hasMore()) {
			throw new DataIntegrityViolation("Unexpected unparsable content");
		}
	}

}