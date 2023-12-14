package com.gratex.tools.pp.core;

import static com.gratex.tools.main.DummyDataGenerator.getRandomNumericString;
import static com.gratex.tools.main.PPFormatUtil.DAY_MONTH_FORMAT_SCALE;
import static com.gratex.tools.main.PPFormatUtil.formatNumber;
import static com.gratex.tools.main.PPFormatUtil.fromDDMMYYYY;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.gratex.tools.main.DummyDataGenerator;
import com.gratex.tools.pp.model.pod.PODFile;
import com.gratex.tools.pp.model.pod.PODFooter;
import com.gratex.tools.pp.model.pod.PODHeader;
import com.gratex.tools.pp.model.pod.PODRecord;
import com.gratex.tools.pp.model.ppe.PPEFile;
import com.gratex.tools.pp.model.ppe.PPEFooter;
import com.gratex.tools.pp.model.ppe.PPEHeader;
import com.gratex.tools.pp.model.ppe.PPERecord;

/**
 *
 * @author mkrajcovicux
 */
public final class PpeToPodConverter implements PPFileConverter<PPEFile, PODFile> {

	@Override
	public PODFile convert(PPEFile file) throws IOException {
		return new PODFile(
				convertHeader(file.getHeader()),
				convertBody(file.getBody()),
				convertFooter(file.getFooter()));
	}

	private PODHeader convertHeader(PPEHeader ppeHeader) {
		PODHeader podHeader = new PODHeader();
		podHeader.setCode(ppeHeader.getCode());
		podHeader.setIban1(ppeHeader.getIban1());
		podHeader.setSerialNumberIn12M(ppeHeader.getSerialNumberIn12M());
		podHeader.setStampNumber(getRandomNumericString(6));

		LocalDate ppeCreated = fromDDMMYYYY(ppeHeader.getFileCreated());
		podHeader.setDayReceived(formatNumber(ppeCreated.getDayOfMonth(), DAY_MONTH_FORMAT_SCALE));
		podHeader.setMonthReceived(formatNumber(ppeCreated.getMonthValue(), DAY_MONTH_FORMAT_SCALE));
		podHeader.setYearRecieved(String.valueOf(ppeCreated.getYear()));
		return podHeader;
	}

	private List<PODRecord> convertBody(List<PPERecord> ppeBody) {
		return ppeBody.stream()
			.map(this::convertRecord)
			.collect(Collectors.toList());
	}

	private PODRecord convertRecord(PPERecord ppeRecord) {
		PODRecord podRecord = new PODRecord();
		podRecord.setCode(ppeRecord.getCode());
		podRecord.setFileNumber(DummyDataGenerator.getRandomNumericString(5));
		podRecord.setRecipientCode(ppeRecord.getRecipientCode());
		podRecord.setAmount(ppeRecord.getAmount());
		podRecord.setPrice(ppeRecord.getPrice());
		return podRecord;
	}

	private PODFooter convertFooter(PPEFooter ppeFooter) {
		PODFooter podFooter = new PODFooter();
		podFooter.setCode(ppeFooter.getCode());
		podFooter.setVoucherCount(ppeFooter.getVoucherCount());
		podFooter.setTotalAmount(ppeFooter.getTotalAmount());
		podFooter.setTotalPrice(ppeFooter.getTotalPrice());
		podFooter.setSum(ppeFooter.getSum());
		return podFooter;
	}

//	convertAndWrite() ? or do it directly on PDFile objects?

}
