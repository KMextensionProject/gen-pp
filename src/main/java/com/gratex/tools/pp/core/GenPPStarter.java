package com.gratex.tools.pp.core;

import static com.gratex.tools.pp.core.FileType.PPE;
import static java.util.Arrays.asList;
import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.gratex.tools.pp.io.ppe.PPEFile;
import com.gratex.tools.pp.io.ppe.PpeParser;

/**
 *
 * @author mkrajcovicux
 */
public class GenPPStarter {

	private static final Logger LOGGER;
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %5$s%n");
		LOGGER = getLogger("GenPPStarter");
	}

	public static void main(String[] args) throws Exception {
		printHelpIfAsked(args);

		Path source = parseSourceLocation(args);
		Path target = parseTargetLocation(args);
		try {
			PPEFile ppeFile = new PpeParser().parse(source);
			writeFile(new PpeToPodConverter().convert(ppeFile), target);
			writeFile(new PpeToVrtConverter().convert(ppeFile), target);

		} catch (NoSuchFileException nsf) {
			LOGGER.severe("No such file: " + nsf.getFile());
		} catch (IOException ioex) {
			LOGGER.severe(() -> ioex.getMessage() + ": " + ioex.getCause().getStackTrace()[1]);
		}
	}

	private static void printHelpIfAsked(String[] args) {
		boolean hasHelpOption = asList(args).stream()
			.anyMatch(arg -> arg.contains("-help")); // [-/--help]

		if (hasHelpOption) {
			LOGGER.info("Proper usage: ./gen-pp <.ppe_file_location> [<output_directory>]");
			LOGGER.info("Note: expected charset of input file is Cp1250" + System.lineSeparator() 
				+ "       You can check 'guessed' encoding by running: file -bi <file>");
			System.exit(0);
		}
	}

	private static Path parseSourceLocation(String[] args) {
		if (args.length == 0) {
			LOGGER.severe(".ppe file location is a mandatory program argument");
			System.exit(1);
		}
		Path source = Paths.get(args[0]);
		if (!PPE.isExtensionOn(source)) {
			LOGGER.severe("source file must have .ppe extension");
			System.exit(1);
		}
		return source;
	}

	// we inherit the source file name here
	private static Path parseTargetLocation(String[] args) {
		String sourceFileName = getFileNameWithoutExtension(args[0]);
		if (args.length == 2) {
			Path target = Paths.get(args[1]);
			if (!target.toFile().isDirectory()) {
				LOGGER.severe(() -> target + " is not a directory");
				System.exit(1);
			}
			return Paths.get(target.toString(), sourceFileName);
		} else {
			return Paths.get(System.getProperty("user.dir"), sourceFileName);
		}
	}

	private static String getFileNameWithoutExtension(String filePath) {
		String fileName = new File(filePath).getName();
		int index = fileName.indexOf('.');
		return (index >= 0)
			? fileName.substring(0, index)
			: fileName;
	}

	private static void writeFile(PPFile file, Path target) throws IOException {
		String fileExtension = file.getFileType().getFileExtension();
		Path targetFile = Paths.get(target + fileExtension);
		file.write(targetFile);
		LOGGER.info(() -> fileExtension + " file created: " + targetFile.toAbsolutePath().normalize());
	}
}
