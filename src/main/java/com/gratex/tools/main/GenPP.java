package com.gratex.tools.main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.gratex.tools.pp.core.PpeParser;
import com.gratex.tools.pp.core.PpeToPodConverter;
import com.gratex.tools.pp.model.pod.PODFile;
import com.gratex.tools.pp.model.ppe.PPEFile;

/**
 *
 * @author mkrajcovicux
 */
public class GenPP {

	public static void main(String[] args) {
		Path ppePath = Paths.get(System.getProperty("user.dir"), "src/test/resources/b2258611_iban.ppe");
		System.out.println(ppePath);
		try {
			List<String> ppeLines = Files.readAllLines(ppePath, Charset.forName("Cp1250"));
			ppeLines.forEach(System.out::println);

			PPEFile ppeFile = new PpeParser().parse(ppePath);
			PODFile podFile = new PpeToPodConverter().convert(ppeFile);
			podFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/ppeFileTest.ppe"));
			
			
			
			
//			VRTFile vrtFile = new PpeToVrtConverter().convert(ppeFile);

		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}
