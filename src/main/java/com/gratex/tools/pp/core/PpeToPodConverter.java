package com.gratex.tools.pp.core;

import static com.gratex.tools.pp.core.BankAccountPop.DAVKY_A_REGRESY;
import static com.gratex.tools.pp.utils.DataGenerator.getRandomNumericString;
import static com.gratex.tools.pp.utils.FormattingUtility.DAY_MONTH_FORMAT_SCALE;
import static com.gratex.tools.pp.utils.FormattingUtility.formatNumber;
import static com.gratex.tools.pp.utils.FormattingUtility.fromDDMMYYYY;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.gratex.tools.pp.io.pod.PODFile;
import com.gratex.tools.pp.io.pod.PODFooter;
import com.gratex.tools.pp.io.pod.PODHeader;
import com.gratex.tools.pp.io.pod.PODRecord;
import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PPEFooter;
import com.gratex.tools.pp.io.ppe.PPEHeader;
import com.gratex.tools.pp.io.ppe.PPERecord;
import com.gratex.tools.pp.utils.DataGenerator;

/**
 *
 * @author mkrajcovicux
 */
public final class PpeToPodConverter implements PPFileConverter<PPEFile, PODFile> {

	/**
	 * Header has fixed iban1 field set to {@link BankAccountPop#DAVKY_A_REGRESY}, stampNumber is randomly generated,<br>
	 * Body has random fileNumber<br>
	 */
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
		// it is not correct to map PPE IBAN to POD IBAN -> expected IBAN is other one from the POP.C_BANKACCOUNT
		podHeader.setIban1(DataGenerator.appendSpacesToEnsureSize(DAVKY_A_REGRESY.getIban(), 34));
		podHeader.setSerialNumberIn12M(ppeHeader.getSerialNumberIn12M());
		podHeader.setStampNumber(getRandomNumericString(6));

		LocalDate ppeCreated = fromDDMMYYYY(ppeHeader.getFileCreated());
		podHeader.setDayReceived(formatNumber(ppeCreated.getDayOfMonth(), DAY_MONTH_FORMAT_SCALE));
		podHeader.setMonthReceived(formatNumber(ppeCreated.getMonthValue(), DAY_MONTH_FORMAT_SCALE));
		podHeader.setYearReceived(String.valueOf(ppeCreated.getYear()));
		return podHeader;
	}

	private List<PODRecord> convertBody(List<PPERecord> ppeBody) {
		return ppeBody.stream()
			.map(this::convertRecord)
			.collect(toList());
	}

	private PODRecord convertRecord(PPERecord ppeRecord) {
		PODRecord podRecord = new PODRecord();
		podRecord.setCode(ppeRecord.getCode());
		podRecord.setFileNumber(DataGenerator.getRandomNumericString(5));
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

}
