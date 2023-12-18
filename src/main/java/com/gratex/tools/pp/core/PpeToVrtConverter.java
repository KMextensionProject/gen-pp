package com.gratex.tools.pp.core;

import static com.gratex.tools.pp.utils.DataGenerator.appendSpacesToEnsureSize;
import static com.gratex.tools.pp.utils.DataGenerator.getRandomNumericString;
import static com.gratex.tools.pp.utils.FormattingUtility.fromDDMMYYYY;
import static com.gratex.tools.pp.utils.FormattingUtility.toSlovakDateFormatString;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PPEFooter;
import com.gratex.tools.pp.io.ppe.PPEHeader;
import com.gratex.tools.pp.io.ppe.PPERecord;
import com.gratex.tools.pp.io.vrt.EnumReasonUnpaid;
import com.gratex.tools.pp.io.vrt.VRTFile;
import com.gratex.tools.pp.io.vrt.VRTFooter;
import com.gratex.tools.pp.io.vrt.VRTHeader;
import com.gratex.tools.pp.io.vrt.VRTRecord;
import com.gratex.tools.pp.utils.FormattingUtility;

/**
 *
 * @author mkrajcovicux
 */
public class PpeToVrtConverter implements PPFileConverter<PPEFile, VRTFile> {

	@Override
	public VRTFile convert(PPEFile file) throws IOException {
		return new VRTFile(
				convertHeader(file.getHeader()),
				convertBody(file.getBody()),
				convertFooter(file.getFooter(), file.getBody().size()));
	}

	private VRTHeader convertHeader(PPEHeader ppeHeader) {
		VRTHeader vrtHeader = new VRTHeader();
		vrtHeader.setCode(ppeHeader.getCode());
		vrtHeader.setSerialNumberIn12M(ppeHeader.getSerialNumberIn12M());
		vrtHeader.setIban(ppeHeader.getIban1());

		LocalDate ppeReceived = fromDDMMYYYY(ppeHeader.getFileCreated());
		vrtHeader.setDateReceived(toSlovakDateFormatString(ppeReceived));

		// these fields must be generated
		vrtHeader.setBuildingNumber(appendSpacesToEnsureSize("17/C", 7));
		vrtHeader.setMunicipality(appendSpacesToEnsureSize("Bratislava", 20));
		vrtHeader.setPostOfficePostalCode("82104");
		vrtHeader.setE2EReference(appendSpacesToEnsureSize("/VS1234567890/SS1234567890/KS1234", 35));
		vrtHeader.setReceivedVoucherCount("123456");
		vrtHeader.setSenderCode("1234");
		vrtHeader.setReturnedPrice(getRandomNumericString(10) + ".99");
		vrtHeader.setSender(appendSpacesToEnsureSize("Martin Krajcovic", 28));
		vrtHeader.setStampNumber("ABC123");
		vrtHeader.setStreet(appendSpacesToEnsureSize("Galvaniho", 20));
		vrtHeader.setTotalAmountRemitted(getRandomNumericString(10) + ".99");
		vrtHeader.setTotalPriceRemitted(getRandomNumericString(7) + ".29");
		vrtHeader.setUnpaidAmountAttributionDate("14.12.2023");
		vrtHeader.setUnpaidVouchersCount(appendSpacesToEnsureSize("66", 6));
		vrtHeader.setVoucherIssueDate("13.12.2023");
		vrtHeader.setVoucherReceiveDate("13.12.2023");
		vrtHeader.setVoucherValidityDate("20.10.2023");

		return vrtHeader;
	}

	private List<VRTRecord> convertBody(List<PPERecord> ppeBody) {
		return ppeBody.stream()
			.map(this::convertRecord)
			.collect(toList());
	}

	private VRTRecord convertRecord(PPERecord ppeRecord) {
		VRTRecord vrtRecord = new VRTRecord();
		vrtRecord.setCode(ppeRecord.getCode());
		vrtRecord.setRecipientCode(ppeRecord.getRecipientCode());
		vrtRecord.setRecipientFullName(ppeRecord.getRecipientFullName());
		vrtRecord.setRecipientOtherIdentifier(ppeRecord.getRecipientOtherIdentifier());
		vrtRecord.setAddressNote(ppeRecord.getAddressNote());
		vrtRecord.setMunicipality(ppeRecord.getMunicipality());
		vrtRecord.setStreet(ppeRecord.getStreet());
		vrtRecord.setBuildingNumber(ppeRecord.getBuildingNumber());
		vrtRecord.setAmount(ppeRecord.getAmount());
		vrtRecord.setPostOfficePostalCode(ppeRecord.getPostalCode());

		// these two fields must be generated, because they cannot be mapped
		vrtRecord.setFileNumber(getRandomNumericString(5));
		vrtRecord.setReasonUnpaid(EnumReasonUnpaid.RECIPIENT_UNKNOWN);
		return vrtRecord;
	}

	private VRTFooter convertFooter(PPEFooter ppeFooter, int bodyLines) {
		VRTFooter vrtFooter = new VRTFooter();
		vrtFooter.setCode(ppeFooter.getCode());

		// these two fields must be generated, because they cannot be mapped
		vrtFooter.setDataSentencesCount(FormattingUtility.formatNumber(bodyLines, 6));
		vrtFooter.setDataSentencesAmount(getRandomNumericString(10) + ".99");
		return vrtFooter;
	}
}
