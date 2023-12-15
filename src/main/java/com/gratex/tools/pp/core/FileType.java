package com.gratex.tools.pp.core;

import java.nio.file.Path;
import static java.util.Objects.nonNull;

public enum FileType {

	PPE(".ppe"),
	POD(".pod"),
	VRT(".vrt");

	private final String extension;

	private FileType(String extension) {
		this.extension = extension;
	}

	public String getFileExtension() {
		return this.extension;
	}

	/**
	 *
	 * @param path
	 * @return
	 */
	public boolean isExtensionOn(Path path) {
		if (nonNull(path)) {
			String file = String.valueOf(path.getFileName());
			return file.endsWith(extension);
		}
		return false;
	}

}
