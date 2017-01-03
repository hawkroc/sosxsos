package com.sosxsos.ssm.dto;
import com.sosxsos.ssm.util.Const;

public interface ResBase {
public static	final Integer version_code =Const.VERSION;

default Integer getVersion_code() {
	return version_code;
}



}
