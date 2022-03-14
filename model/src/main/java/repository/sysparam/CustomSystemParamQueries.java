package repository.sysparam;

import model.sysparams.SystemParameter;
import utils.ListResult;

public interface CustomSystemParamQueries {

	ListResult<SystemParameter> filterByAttributes(String code, String description, String value, int limit, int page);
}
