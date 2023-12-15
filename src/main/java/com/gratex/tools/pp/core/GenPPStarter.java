package com.gratex.tools.pp.core;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gratex.tools.pp.io.pod.PODFile;
import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.vrt.VRTFile;

/**
 *
 * @author mkrajcovicux
 */
public class GenPPStarter {

	public static void main(String[] args) throws Exception {
		Path target = Paths.get(System.getProperty("user.dir"));
		if (args.length == 0) {
			System.out.println("[ERROR] expected mandatory .ppe file location");
			return;
		} else if (args.length == 2) {
			target = Paths.get(args[1]);
			if (!target.toFile().isDirectory()) {
				System.out.println("[ERROR] " + target + " is not a directory");
				return;
			}
		}
		Path source = Paths.get(args[0]);
		if (!FileType.PPE.isExtensionOn(source)) {
			System.out.println("[ERROR] source file must have .ppe extension");
			return;
		}
		try {
			PPEFile ppeFile = new PpeParser().parse(source);
			PODFile podFile = new PpeToPodConverter().convert(ppeFile);
			VRTFile vrtFile = new PpeToVrtConverter().convert(ppeFile);

			podFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/podFileTest.pod"));
			System.out.println("[INFO] .pod file created: " + target);

			vrtFile.write(Paths.get("/home/UX/mkrajcovicux/Desktop/vrtFileTest.vrt"));
			System.out.println();

		} catch (NoSuchFileException nsf) {
			System.out.println("[ERROR] No such file: " + nsf.getFile());
		} catch (IOException ioex) {
			System.out.println("[ERROR] Something went wrong " + ioex.getMessage());
		}
	}
}
