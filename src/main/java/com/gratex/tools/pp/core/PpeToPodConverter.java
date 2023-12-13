package com.gratex.tools.pp.core;

import java.io.IOException;

import com.gratex.tools.pp.model.pod.PODFile;
import com.gratex.tools.pp.model.ppe.PPEFile;

/**
 *
 * @author mkrajcovicux
 */
public final class PpeToPodConverter implements PPFileConverter<PPEFile, PODFile> {

	@Override
	public PODFile convert(PPEFile file) throws IOException {
		
		return null;
	}

//	convertAndWrite() ? or do it directly on PDFile objects?

}
