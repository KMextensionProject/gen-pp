package com.gratex.tools.pp.core;

import java.io.IOException;

/**
 * Contract for conversions between PPFile types
 */
public interface PPFileConverter<T extends PPFile, R extends PPFile> {

	/**
	 *
	 * @param source - source PPFile
	 * @return other PPFile
	 * @throws IOException if conversion somehow fails
	 */
	public R convert(T source) throws IOException;

}
