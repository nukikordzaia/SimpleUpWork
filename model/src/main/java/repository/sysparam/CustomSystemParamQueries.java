package repository.sysparam;

import model.sysparams.SystemParameter;

import java.util.List;

public interface CustomSystemParamQueries {

	List<SystemParameter> filterByAttributes(String code, String description, String value);
}
