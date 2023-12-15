package com.gratex.tools.pp.io.ppe;

import com.gratex.tools.pp.core.PPPart;

public class PPEFooter extends PPPart {

	private String voucherCount; // 5
	private String totalAmount; // 13 (xxxxxxxxxx.xx)
	private String totalPrice; // 10 (xxxxxxx.xx)
	private String sum; // 13 (xxxxxxxxxx.xx)

	public String getVoucherCount() {
		return voucherCount;
	}

	public void setVoucherCount(String voucherCount) {
		this.voucherCount = voucherCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

}
