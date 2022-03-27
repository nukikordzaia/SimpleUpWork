package com.simpleupwork.repository.sysparam;

import com.simpleupwork.model.sysparams.SystemParameter;
import com.simpleupwork.utils.ListResult;

public interface CustomSystemParamQueries {

	ListResult<SystemParameter> filterByAttributes(String code, String description, String value, int limit, int page);
}
