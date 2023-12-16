package com.gratex.tools.pp.core;

import java.io.IOException;

/**
 *
 * @author mkrajcovicux
 */
public interface PPFileConverter<T extends PPFile, R extends PPFile> {

	/**
	 *
	 * @param ppe
	 * @return
	 * @throws IOException
	 */
	public R convert(T source) throws IOException;

}
