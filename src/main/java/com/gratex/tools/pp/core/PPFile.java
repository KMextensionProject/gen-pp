package com.gratex.tools.pp.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class PPFile {
	// not all PPFiles have header, body, footer

	/**
	 * Default encoding is Cp1250
	 * @param targetFile
	 * @throws IOException
	 */
	public void write(Path targetFile) throws IOException {
		write(targetFile, Charset.forName("Cp1250"));
	}

	/**
	 * @param targetFile
	 * @throws IOException
	 */
	public void write(Path targetFile, Charset encoding) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(targetFile, encoding)) {
			writer.write(buildFileString());
		}
	}

	/**
	 *
	 * @return
	 */
	protected abstract String buildFileString();

}
