package com.gratex.tools.pp.model.ppe;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.model.PPFile;

/**
 *
 * @author mkrajcovicux
 */
public final class PPEFile extends PPFile { // TODO: review hierarchy

	private PPEHeader header;
	private List<PPERecord> body;
	private PPEFooter footer;

	public PPEFile(PPEHeader header, List<PPERecord> body, PPEFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public PPEHeader getHeader() {
		return header;
	}

	public List<PPERecord> getBody() {
		return nonNull(body) && !body.isEmpty() 
				? new ArrayList<>(body)
				: emptyList();
	}

	public PPEFooter getFooter() {
		return footer;
	}

}
