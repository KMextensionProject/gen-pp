package com.gratex.tools.pp.model;

public abstract class PPHeader extends PPPart {

	protected String serialNumberIn12M; // 4

	public String getSerialNumberIn12M() {
		return serialNumberIn12M;
	}

	public void setSerialNumberIn12M(String serialNumberIn12M) {
		this.serialNumberIn12M = serialNumberIn12M;
	}

}
