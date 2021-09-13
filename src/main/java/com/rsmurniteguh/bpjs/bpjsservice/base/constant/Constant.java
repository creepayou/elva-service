package com.rsmurniteguh.bpjs.bpjsservice.base.constant;

public final class Constant {

	private Constant(){
		throw new IllegalStateException("Constant class");
	}
	
	public static final String IND_YES = "Y";
	public static final String IND_NO = "N";

	public static final String MT_ENTITY_CODE = "mt-entity-code";

	public static final String TIMEZONE_JKT = "Asia/Jakarta";
	public static final String TIMEZONE_UTC = "UTC";

	public enum EntityCode{
		MTMH,
		RSIAR,
		MTSJ,
		MTSW,
		RSAMINAH,
		MTTB
	}
}
