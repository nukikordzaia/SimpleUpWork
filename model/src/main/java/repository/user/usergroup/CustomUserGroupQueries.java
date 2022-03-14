package repository.user.usergroup;

import model.user.UserGroup;
import utils.ListResult;

public interface CustomUserGroupQueries {

	ListResult<UserGroup> filterByAttributes(String name, String permission, Boolean active, int limit, int page);
}
