package com.gratex.tools.pp.core;

import java.io.IOException;
import java.nio.file.Path;

import com.gratex.tools.pp.model.PPFile;

/**
 *
 * @author mkrajcovicux
 */
public interface FileParser {

	/**
	 *
	 * @param source
	 * @return
	 */
	public PPFile parse(Path source) throws IOException;

}
