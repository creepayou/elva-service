package com.rsmurniteguh.bpjs.bpjsservice.base.constant;

public final class Constant {

	private Constant(){
		throw new IllegalStateException("Constant class");
	}
	
	public static final String IND_YES = "Y";
	public static final String IND_NO = "N";

	public static final String MT_ENTITY_CODE = "mt-entity-code";

	public static final String TIMEZONE_UTC = "UTC";

	public static final String VCLAIM_VERSION = "VCLAIM_VERSION";

	public static final String METADATA_OK_200 = "200";
	public static final String METADATA_OK_1 = "1";
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String VCLAIM_FEIGN_NAME = "vclaim";
	public static final String APLICARES_FEIGN_NAME = "aplicares";
	public static final String ANTREAN_FEIGN_NAME = "antreanrs";

	public static final String X_CONS_ID = "X-cons-id";
	public static final String X_TIMESTAMP = "X-timestamp";
	public static final String X_SIGNATURE = "X-signature";

}
