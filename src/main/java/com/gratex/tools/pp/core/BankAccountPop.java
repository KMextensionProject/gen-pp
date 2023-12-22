package com.gratex.tools.pp.core;

public enum BankAccountPop {

	OSOBITNY_FOND_SOCIALNA_POISTOVNA(0, "SK8381800000007000640683"),
	DAVKY_A_REGRESY(2, "SK8381800000007000640683"),
	POSTA_POUKAZOVANIE_PPP_E(4, "SK5602000020140008402012"),
	SPRAVNY_FOND(7, "SK4081800000007000164314");

	private int bankAccountTypeId;
	private String iban;

	private BankAccountPop(int bankAccountTypeId, String iban) {
		this.bankAccountTypeId = bankAccountTypeId;
		this.iban = iban;
	}

	public int getBankAccountTypeId() {
		return this.bankAccountTypeId;
	}

	public String getIban() {
		return this.iban;
	}

	public static String getIbanById(int bankTypeId) {
		for (BankAccountPop bankAccount : BankAccountPop.values()) {
			if (bankAccount.bankAccountTypeId == bankTypeId) {
				return bankAccount.iban;
			}
		}
		return DAVKY_A_REGRESY.iban;
	}
}
