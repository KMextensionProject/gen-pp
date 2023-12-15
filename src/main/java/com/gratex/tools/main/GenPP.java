package com.gratex.tools.main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gratex.tools.pp.core.PpeParser;
import com.gratex.tools.pp.core.PpeToPodConverter;
import com.gratex.tools.pp.core.PpeToVrtConverter;
import com.gratex.tools.pp.model.pod.PODFile;
import com.gratex.tools.pp.model.ppe.PPEFile;
import com.gratex.tools.pp.model.vrt.VRTFile;

/**
 *
 * @author mkrajcovicux
 */
public class GenPP {

	public static void main(String[] args) throws Exception {
		Path ppePath = Paths.get(System.getProperty("user.dir"), "src/test/resources/b2258611_iban.ppe");
//		System.out.println(ppePath);

		try {
			PPEFile ppeFile = new PpeParser().parse(ppePath);
			PODFile podFile = new PpeToPodConverter().convert(ppeFile);
			VRTFile vrtFile = new PpeToVrtConverter().convert(ppeFile);

			ppeFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/ppeFileTest.ppe"));
			podFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/podFileTest.pod"));
			vrtFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/vrtFileTest.vrt"));
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}
