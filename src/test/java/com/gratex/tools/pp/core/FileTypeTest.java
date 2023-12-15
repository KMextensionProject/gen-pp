package com.gratex.tools.pp.core;

import static com.gratex.tools.pp.core.FileType.POD;
import static com.gratex.tools.pp.core.FileType.PPE;
import static com.gratex.tools.pp.core.FileType.VRT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class FileTypeTest {

	@Test
	@DisplayName("File extension detection")
	void fileExtensionTest() {
		Path ppeFile = Paths.get("/home/UX/mkrajcovicux/Desktop/myFile.ppe");

		assertTrue (PPE.isExtensionOn(ppeFile));
		assertFalse(POD.isExtensionOn(ppeFile));
		assertFalse(VRT.isExtensionOn(null));
	}

}
