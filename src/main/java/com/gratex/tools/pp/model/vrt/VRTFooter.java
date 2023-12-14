package com.gratex.tools.pp.model.vrt;

import com.gratex.tools.pp.model.PPPart;

public class VRTFooter extends PPPart {

	private String dataSentencesAmount; // 6
	private String dataSentencesPrice; // 13 (xxxxxxxxxx.xx)

	public String getDataSentencesAmount() {
		return dataSentencesAmount;
	}

	public void setDataSentencesAmount(String dataSentencesAmount) {
		this.dataSentencesAmount = dataSentencesAmount;
	}

	public String getDataSentencesPrice() {
		return dataSentencesPrice;
	}

	public void setDataSentencesPrice(String dataSentencesPrice) {
		this.dataSentencesPrice = dataSentencesPrice;
	}

}
