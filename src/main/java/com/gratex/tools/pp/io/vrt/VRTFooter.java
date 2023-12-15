package com.gratex.tools.pp.io.vrt;

import com.gratex.tools.pp.core.PPPart;

public class VRTFooter extends PPPart {

	private String dataSentencesCount; // 6
	private String dataSentencesAmount; // 13 (xxxxxxxxxx.xx)

	public String getDataSentencesCount() {
		return dataSentencesCount;
	}

	public void setDataSentencesCount(String dataSentencesCount) {
		this.dataSentencesCount = dataSentencesCount;
	}

	public String getDataSentencesAmount() {
		return dataSentencesAmount;
	}

	public void setDataSentencesAmount(String dataSentencesAmount) {
		this.dataSentencesAmount = dataSentencesAmount;
	}

}
