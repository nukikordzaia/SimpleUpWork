package repository.user.usergroup;

import model.sysparams.SystemParameter;
import model.user.UserGroup;

import java.util.List;

public interface CustomUserGroupQueries {

	List<UserGroup> filterByAttributes(String name, String permission, Boolean active);
}
