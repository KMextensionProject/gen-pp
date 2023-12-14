package com.gratex.tools.pp.core;

import static com.gratex.tools.main.DummyDataGenerator.getRandomNumericString;
import static com.gratex.tools.main.DummyDataGenerator.appendSpacesToMatchSize;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.gratex.tools.pp.model.ppe.PPEFile;
import com.gratex.tools.pp.model.ppe.PPEFooter;
import com.gratex.tools.pp.model.ppe.PPEHeader;
import com.gratex.tools.pp.model.ppe.PPERecord;
import com.gratex.tools.pp.model.vrt.VRTFile;
import com.gratex.tools.pp.model.vrt.VRTFooter;
import com.gratex.tools.pp.model.vrt.VRTHeader;
import com.gratex.tools.pp.model.vrt.VRTRecord;

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
				convertFooter(file.getFooter()));
	}

	private VRTHeader convertHeader(PPEHeader ppeHeader) {
		VRTHeader vrtHeader = new VRTHeader();
		vrtHeader.setCode(ppeHeader.getCode());
		vrtHeader.setSerialNumberIn12M(ppeHeader.getSerialNumberIn12M());
		vrtHeader.setIban(ppeHeader.getIban1());
		vrtHeader.setDateReceived(ppeHeader.getFileCreated());

		// these fields must be generated
		vrtHeader.setBuildingNumber(appendSpacesToMatchSize("17/C", 7));
		vrtHeader.setMunicipality(appendSpacesToMatchSize("Bratislava", 20));
		vrtHeader.setPostOfficePostalCode("82104");
		vrtHeader.setE2EReference(appendSpacesToMatchSize("test referencie", 35));
		vrtHeader.setReceivedVoucherCount("123456");
		vrtHeader.setRecipientCode("1234");
		vrtHeader.setReturnedPrice(getRandomNumericString(10) + ".99");
		vrtHeader.setSender(appendSpacesToMatchSize("Martin", 28));
		vrtHeader.setStampNumber("ABC123");
		vrtHeader.setStreet(appendSpacesToMatchSize("Galvaniho", 20));
		vrtHeader.setTotalAmountRemitted(getRandomNumericString(10) + ".99");
		vrtHeader.setTotalPriceRemitted(getRandomNumericString(7) + ".29");
		vrtHeader.setUnpaidAmountAttributionDate("14.12.2023");
		vrtHeader.setUnpaidVouchersCount(appendSpacesToMatchSize("66", 6));
		vrtHeader.setVoucherIssueDate("13.12.2023");
		vrtHeader.setVoucherReceiveDate("13.12.2023");
		vrtHeader.setVoucherValidityDate("20.10.2023");

		return vrtHeader;
	}

	private List<VRTRecord> convertBody(List<PPERecord> ppeBody) {
		return ppeBody.stream()
			.map(this::convertRecord)
			.collect(Collectors.toList());
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
		vrtRecord.setReasonUnpaid("DOVOD:" + getRandomNumericString(15));
		vrtRecord.setFileNumber(getRandomNumericString(5));
		return vrtRecord;
	}

	private VRTFooter convertFooter(PPEFooter ppeFooter) {
		VRTFooter vrtFooter = new VRTFooter();
		vrtFooter.setCode(ppeFooter.getCode());

		// these two fields must be generated, because they cannot be mapped
		vrtFooter.setDataSentencesAmount(getRandomNumericString(6));
		vrtFooter.setDataSentencesPrice(getRandomNumericString(10) + ".99");
		return vrtFooter;
	}
}
