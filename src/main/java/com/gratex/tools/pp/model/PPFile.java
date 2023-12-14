package com.gratex.tools.pp.model;

import java.io.IOException;
import java.nio.file.Path;

// Factory?
public abstract class PPFile {

	/**
	 * => used encoding is Cp1250 .. should be possible to define clients own????
	 * @param targetFile
	 * @throws IOException
	 */
	public abstract void write(Path targetFile) throws IOException;

}
