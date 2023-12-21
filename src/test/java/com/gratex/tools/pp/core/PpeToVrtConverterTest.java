package com.gratex.tools.pp.core;

import static com.gratex.tools.pp.core.BankAccountPop.OSOBITNY_FOND_SOCIALNA_POISTOVNA;
import static com.gratex.tools.pp.utils.DataGenerator.appendSpacesToEnsureSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PPEFooter;
import com.gratex.tools.pp.io.ppe.PPEHeader;
import com.gratex.tools.pp.io.ppe.PPEParser;
import com.gratex.tools.pp.io.ppe.PPERecord;
import com.gratex.tools.pp.io.vrt.VRTFile;
import com.gratex.tools.pp.io.vrt.VRTFooter;
import com.gratex.tools.pp.io.vrt.VRTHeader;
import com.gratex.tools.pp.io.vrt.VRTRecord;

@TestInstance(Lifecycle.PER_CLASS)
class PpeToVrtConverterTest {

	private PPEFile ppeFile;
	private VRTFile vrtFile;

	@BeforeAll
	void init() throws IOException {
		ppeFile = new PPEParser().parse(Paths.get("src/test/resources/b2258611_iban.ppe"));
		vrtFile = new PpeToVrtConverter().convert(ppeFile);
	}

	@Test
	@DisplayName("Conversion test for VRT object obtained from PPE")
	void conversionOfMappableFields() throws IOException {
		PPEHeader ppeHeader = ppeFile.getHeader();
		VRTHeader vrtHeader = vrtFile.getHeader();
		assertEquals(ppeHeader.getCode(), vrtHeader.getCode());
		assertEquals(appendSpacesToEnsureSize(OSOBITNY_FOND_SOCIALNA_POISTOVNA.getIban(), 34), vrtHeader.getIban());
		assertEquals(ppeHeader.getSerialNumberIn12M(), vrtHeader.getSerialNumberIn12M());

		// order in this case should be maintained
		List<PPERecord> ppeBody = ppeFile.getBody();
		List<VRTRecord> vrtBody = vrtFile.getBody();
		assertEquals(ppeBody.size(), vrtBody.size());
		for (int i = 0; i < ppeBody.size(); i++) {
			PPERecord ppeRecord = ppeBody.get(i);
			VRTRecord vrtRecord = vrtBody.get(i);
			assertEquals(ppeRecord.getCode(), vrtRecord.getCode());
			assertEquals(ppeRecord.getRecipientCode(), vrtRecord.getRecipientCode());
			assertEquals(ppeRecord.getRecipientFullName(), vrtRecord.getRecipientFullName());
			assertEquals(ppeRecord.getRecipientOtherIdentifier(), vrtRecord.getRecipientOtherIdentifier());
			assertEquals(ppeRecord.getAddressNote(), vrtRecord.getAddressNote());
			assertEquals(ppeRecord.getMunicipality(), vrtRecord.getMunicipality());
			assertEquals(ppeRecord.getStreet(), vrtRecord.getStreet());
			assertEquals(ppeRecord.getBuildingNumber(), vrtRecord.getBuildingNumber());
			assertEquals(ppeRecord.getAmount(), vrtRecord.getAmount());
			assertEquals(ppeRecord.getPostOfficePostalCode(), vrtRecord.getPostOfficePostalCode());
		}

		PPEFooter ppeFooter = ppeFile.getFooter();
		VRTFooter vrtFooter = vrtFile.getFooter();
		assertEquals(ppeFooter.getCode(), vrtFooter.getCode());
	}

	@Test
	@DisplayName("Integrity of generated fields")
	void validityOfGeneratedFields() {
		VRTHeader vrtHeader = vrtFile.getHeader();
		assertEquals(07, vrtHeader.getBuildingNumber().length());
		assertEquals(20, vrtHeader.getMunicipality().length());
		assertEquals(05, vrtHeader.getPostOfficePostalCode().length());
		assertEquals(35, vrtHeader.getE2eReference().length());
		assertEquals(06, vrtHeader.getReceivedVoucherCount().length());
		assertEquals(04, vrtHeader.getSenderCode().length());
		assertEquals(13, vrtHeader.getReturnedPrice().length());
		assertEquals(28, vrtHeader.getSender().length());
		assertEquals(06, vrtHeader.getStampNumber().length());
		assertEquals(20, vrtHeader.getStreet().length());
		assertEquals(13, vrtHeader.getTotalAmountRemitted().length());
		assertEquals(10, vrtHeader.getTotalPriceRemitted().length());
		assertEquals(10, vrtHeader.getUnpaidAmountAttributionDate().length());
		assertEquals(06, vrtHeader.getUnpaidVouchersCount().length());
		assertEquals(10, vrtHeader.getVoucherIssueDate().length());
		assertEquals(10, vrtHeader.getVoucherReceiveDate().length());
		assertEquals(10, vrtHeader.getVoucherValidityDate().length());

		List<VRTRecord> vrtBody = vrtFile.getBody();
		for (int i = 0; i < vrtBody.size(); i++) {
			VRTRecord vrtRecord = vrtBody.get(i);
			assertEquals(21, vrtRecord.getReasonUnpaid().length());
			assertEquals(05, vrtRecord.getFileNumber().length());
		}

		VRTFooter vrtFooter = vrtFile.getFooter();
		assertEquals(06, vrtFooter.getDataSentencesCount().length());
		assertEquals(13, vrtFooter.getDataSentencesAmount().length());
	}

}
