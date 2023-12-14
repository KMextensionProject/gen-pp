package com.gratex.tools.pp.model.vrt;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.gratex.tools.pp.model.PPFile;

public class VRTFile extends PPFile {

	private VRTHeader header;
	private List<VRTRecord> body;
	private VRTFooter footer;

	public VRTFile(VRTHeader header, List<VRTRecord> body, VRTFooter footer) {
		this.header = header;
		this.body = body;
		this.footer = footer;
	}

	public VRTHeader getHeader() {
		return header;
	}

	public List<VRTRecord> getBody() {
		return nonNull(body) && !body.isEmpty() ? new ArrayList<>(body) : emptyList();
	}

	public VRTFooter getFooter() {
		return footer;
	}

	@Override
	public void write(Path targetFile) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
