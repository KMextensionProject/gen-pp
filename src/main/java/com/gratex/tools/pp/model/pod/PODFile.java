package com.gratex.tools.pp.model.pod;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.model.PPFile;

public final class PODFile extends PPFile {

	private PODHeader header;
	private List<PODRecord> body;
	private PODFooter footer;

	public PODFile(PODHeader header, List<PODRecord> body, PODFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public PODHeader getHeader() {
		return header;
	}

	public List<PODRecord> getBody() {
		return nonNull(body) && !body.isEmpty() 
				? new ArrayList<>(body)
				: emptyList();
	}

	public PODFooter getFooter() {
		return footer;
	}

	/**
	 *
	 * @param targetFile
	 * @throws IOException
	 */
	public void write(Path targetFile) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(targetFile, Charset.forName("Cp1250"))) {
//			writer.write(buildFileString());
			// log
		}
	}

//	private String buildFileString() {
//		StringBuilder fileContent = new StringBuilder();
//		appendHeader(fileContent);
//		appendBody(fileContent);
//		appendFooter(fileContent);
//		return fileContent.toString();
//	}

}
