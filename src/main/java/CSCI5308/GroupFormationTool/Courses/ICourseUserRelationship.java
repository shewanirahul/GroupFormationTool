package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface ICourseUserRelationship
{
	public boolean userHasRoleInCourse(IUser user, Role role, ICourse course) throws Exception;
	public List<Role> loadAllRoluesForUserInCourse(IUser user, ICourse course) throws Exception;
	public void enrollUserInCourse(IUser user, ICourse course, Role role) throws Exception;
}
