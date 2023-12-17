package com.gratex.tools.pp.io.ppe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PpeParserTest {

	@Test
	void headerTest() {
		String line = "1SK9181800000007000155733          SK3881800000007000155717          26092023861130WL2š00000000";
		/*
		 * 1
		 * SK9181800000007000155733          
		 * SK3881800000007000155717          
		 * 26092023
		 * 8611
		 * 30
		 * WL2
		 * š
		 * 00000000
		 */
		PPEHeader header = new PpeParser().parseHeader(line);
		assertEquals("1", header.getCode());
		assertEquals("SK9181800000007000155733          ", header.getIban1());
		assertEquals("SK3881800000007000155717          ", header.getIban2());
		assertEquals("26092023", header.getFileCreated());
		assertEquals("8611", header.getSerialNumberIn12M());
		assertEquals("30", header.getVoucherValidity());
		assertEquals("WL2", header.getDiacriticsCode());
		assertEquals("š", header.getTestLetterForDiacriticsCode());
		assertEquals("00000000", header.getPayOutDate());
	}

	@Test
	void bodyTest() {
		String line = "3000080000006984.800000018.800000007003.60";
		/*
		 * 3
		 * 00008
		 * 0000006984.80
		 * 0000018.80
		 * 0000007003.60
		 */
		PPEFooter footer = new PpeParser().parseFooter(line);
		assertEquals("3", footer.getCode());
		assertEquals("00008", footer.getVoucherCount());
		assertEquals("0000006984.80", footer.getTotalAmount());
		assertEquals("0000018.80", footer.getTotalPrice());
		assertEquals("0000007003.60", footer.getSum());
	}

	@Test
	void footerTest() { // NOSONAR
		List<String> lines = Arrays.asList(
				"2Djemaili Nagian                                             Dolné Lovčice               510       Dolné Lovčice                 91927                              0000110.800001.80001263074C0     AABQGO0                                                                                                                                            ",
				"2Volek Emil                                                  Ulica Andreja Kubinu        3199/12   Trnava                        91701                              0000389.400002.20001210664C0     AABQGO1                                                                                                                                            "
		);
		List<PPERecord> body = new PpeParser().parseBody(lines);
		assertEquals(2, body.size());
		/*
		 * 2
		 * Djemaili Nagian               
		 *                               
		 * Dolné Lovčice               
		 * 510       
		 * Dolné Lovčice                 
		 * 91927
		 *                               
		 * 0000110.80
		 * 0001.80
		 * 001
		 * 263074C0     AABQGO0          
		 *                               
		 *                                                   
		 *                     
		 */
		PPERecord record_1 = body.get(0);
		assertEquals("2", record_1.getCode());
		assertEquals("Djemaili Nagian               ", record_1.getRecipientFullName());
		assertEquals("                              ", record_1.getRecipientOtherIdentifier());
		assertEquals("Dolné Lovčice               ", record_1.getStreet());
		assertEquals("510       ", record_1.getBuildingNumber());
		assertEquals("Dolné Lovčice                 ", record_1.getMunicipality());
		assertEquals("91927", record_1.getPostalCode());
		assertEquals("                              ", record_1.getAddressNote());
		assertEquals("0000110.80", record_1.getAmount());
		assertEquals("0001.80", record_1.getPrice());
		assertEquals("001", record_1.getServiceCode());
		assertEquals("263074C0     AABQGO0          ", record_1.getRecipientCode());
		assertEquals("                              ", record_1.getPurpose());
		assertEquals("                                                  ", record_1.getEmail());
		assertEquals("                    ", record_1.getTelephone());
		/*
		 * 2
		 * Volek Emil                    
		 *                               
		 * Ulica Andreja Kubinu        
		 * 3199/12   
		 * Trnava                        
		 * 91701
		 *                               
		 * 0000389.40
		 * 0002.20
		 * 001
		 * 210664C0     AABQGO1          
		 *                               
		 *                                                   
		 *                     
		 */
		PPERecord record_2 = body.get(1);
		assertEquals("2", record_2.getCode());
		assertEquals("Volek Emil                    ", record_2.getRecipientFullName());
		assertEquals("                              ", record_2.getRecipientOtherIdentifier());
		assertEquals("Ulica Andreja Kubinu        ", record_2.getStreet());
		assertEquals("3199/12   ", record_2.getBuildingNumber());
		assertEquals("Trnava                        ", record_2.getMunicipality());
		assertEquals("91701", record_2.getPostalCode());
		assertEquals("                              ", record_2.getAddressNote());
		assertEquals("0000389.40", record_2.getAmount());
		assertEquals("0002.20", record_2.getPrice());
		assertEquals("001", record_2.getServiceCode());
		assertEquals("210664C0     AABQGO1          ", record_2.getRecipientCode());
		assertEquals("                              ", record_2.getPurpose());
		assertEquals("                                                  ", record_2.getEmail());
		assertEquals("                    ", record_2.getTelephone());
	}

}
