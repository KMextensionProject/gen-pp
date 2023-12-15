package com.gratex.tools.pp.io.vrt;

import static com.gratex.tools.pp.utils.DataGenerator.appendSpacesToEnsureSize;

public class EnumReasonUnpaid {

	public static final String RECIPIENT_DIED = appendSpacesToEnsureSize("adresát zomrel", 21);
	public static final String RECIPIENT_MOVED_OUT = appendSpacesToEnsureSize("adresát odsťahovaný", 21);
	public static final String RECIPIENT_UNKNOWN = appendSpacesToEnsureSize("adresát neznámy", 21);
	public static final String SUSPENDED = appendSpacesToEnsureSize("pozastavil", 21);
	public static final String RECIPIENT_REFUSED = appendSpacesToEnsureSize("adresát odmietol", 21);
	public static final String INCONSISTENCY = appendSpacesToEnsureSize("nesúlad", 21);
	public static final String OVER_DEADLINE = appendSpacesToEnsureSize("uplynula lehota platn", 21);
}
