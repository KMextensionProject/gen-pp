package com.gratex.tools.pp.core;

import java.io.IOException;

/**
 *
 * @author mkrajcovicux
 */
public interface PPFileConverter<IN extends PPFile, OUT extends PPFile> {

	/**
	 *
	 * @param ppe
	 * @return
	 * @throws IOException
	 */
	public OUT convert(IN source) throws IOException;

}
