package com.gratex.tools.pp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gratex.tools.pp.io.pod.PODFile;
import com.gratex.tools.pp.io.pod.PODFooter;
import com.gratex.tools.pp.io.pod.PODHeader;
import com.gratex.tools.pp.io.pod.PODRecord;
import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PPEFooter;
import com.gratex.tools.pp.io.ppe.PPEHeader;
import com.gratex.tools.pp.io.ppe.PPERecord;
import com.gratex.tools.pp.io.ppe.PpeParser;

class PpeToPodConverterTest {

	@Test
	@DisplayName("Conversion test for POD object obtained from PPE")
	void conversionTest() throws IOException {
		PpeParser parser = new PpeParser();
		PpeToPodConverter converter = new PpeToPodConverter();

		Path ppeSample = Paths.get("src/test/resources/b2258611_iban.ppe");
		PPEFile ppeFile = parser.parse(ppeSample);
		PODFile podFile = converter.convert(ppeFile);

		PPEHeader ppeHeader = ppeFile.getHeader();
		PODHeader podHeader = podFile.getHeader();
		assertEquals(ppeHeader.getCode(), podHeader.getCode());
		assertEquals(ppeHeader.getIban1(), podHeader.getIban1());
		assertEquals(ppeHeader.getSerialNumberIn12M(), podHeader.getSerialNumberIn12M());
		assertEquals(ppeHeader.getFileCreated().substring(4), podHeader.getYearRecieved());
		assertEquals(ppeHeader.getFileCreated().substring(2, 4), podHeader.getMonthReceived());
		assertEquals(ppeHeader.getFileCreated().substring(0, 2), podHeader.getDayReceived());
		assertEquals(6, podHeader.getStampNumber().length());

		// order in this case should be maintained
		List<PPERecord> ppeBody = ppeFile.getBody();
		List<PODRecord> podBody = podFile.getBody();
		assertEquals(ppeBody.size(), podBody.size());
		for (int i = 0; i < ppeBody.size(); i++) {
			PPERecord ppeRecord = ppeBody.get(i);
			PODRecord podRecord = podBody.get(i);
			assertEquals(ppeRecord.getCode(), podRecord.getCode());
			assertEquals(5, podRecord.getFileNumber().length());
			assertEquals(ppeRecord.getRecipientCode(), podRecord.getRecipientCode());
			assertEquals(ppeRecord.getAmount(), podRecord.getAmount());
			assertEquals(ppeRecord.getPrice(), podRecord.getPrice());
		}

		PPEFooter ppeFooter = ppeFile.getFooter();
		PODFooter podFooter = podFile.getFooter();
		assertEquals(ppeFooter.getCode(), podFooter.getCode());
		assertEquals(ppeFooter.getVoucherCount(), podFooter.getVoucherCount());
		assertEquals(ppeFooter.getTotalAmount(), podFooter.getTotalAmount());
		assertEquals(ppeFooter.getTotalPrice(), podFooter.getTotalPrice());
		assertEquals(ppeFooter.getSum(), podFooter.getSum());
	}

}
