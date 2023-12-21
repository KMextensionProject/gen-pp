package com.gratex.tools.pp.core;

/**
 * This mirrors that every 'sentence' that PPFile consists of has
 * these common properties.
 */
public abstract class PPPart {

	protected String code; // 1

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
