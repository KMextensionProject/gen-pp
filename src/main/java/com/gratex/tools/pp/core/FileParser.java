package com.gratex.tools.pp.core;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import com.gratex.tools.pp.model.PPFile;

/**
 *
 * @author mkrajcovicux
 */
public interface FileParser<R extends PPFile> {

	/**
	 *
	 * @param source
	 * @return
	 */
	public default R parse(Path source) throws IOException {
		return parse(source, Charset.forName("Cp1250"));
	}

	/**
	 *
	 * @param source
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public R parse(Path source, Charset encoding) throws IOException;

}
