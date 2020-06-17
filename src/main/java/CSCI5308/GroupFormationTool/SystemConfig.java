package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.IUserPasswordHistoryPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicyImplementer;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicyPopulator;
import CSCI5308.GroupFormationTool.PasswordPolicy.UserPasswordHistory;
import CSCI5308.GroupFormationTool.Courses.*;

/*
 * This is a singleton, we will learn about these when we learn design patterns.
 * 
 * The single responsibility of this singleton is to store concrete classes
 * selected by the system for use in the rest of the system. This will allow
 * a form of dependency injection in places where we cannot use normal
 * dependency injection (for example classes that override or extend existing
 * library classes in the framework).
 */
public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IPasswordPolicy passwordPolicy;
	private IPasswordPolicyPersistence iPasswordPolicyPersistance;
	private IUserPasswordHistoryPersistence iUserPasswordHistory;
	
	// This private constructor ensures that no class other than System can allocate
	// the System object. The compiler would prevent it.
	private SystemConfig()
	{
		// The default instantiations are the choices that would be used in the
		// production application. These choices can all be overridden by test
		// setup logic when necessary.
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		passwordPolicy = new PasswordPolicyImplementer();
		iPasswordPolicyPersistance = new PasswordPolicyPopulator();
		iUserPasswordHistory = new UserPasswordHistory();
	}
	
	// This is the way the rest of the application gets access to the System object.
	public static SystemConfig instance()
	{
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}
	
	
	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}
	
	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}
	
	public IUserPersistence getUserDB()
	{
		return userDB;
	}
	
	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}
	
	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
	
	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}
	
	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}
	
	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IPasswordPolicy getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(IPasswordPolicy passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	public IPasswordPolicyPersistence getiPasswordPolicyPersistance() {
		return iPasswordPolicyPersistance;
	}

	public void setiPasswordPolicyPersistance(IPasswordPolicyPersistence iPasswordPolicyPersistance) {
		this.iPasswordPolicyPersistance = iPasswordPolicyPersistance;
	}

	public IUserPasswordHistoryPersistence getiUserPasswordHistory() {
		return iUserPasswordHistory;
	}

	public void setiUserPasswordHistory(IUserPasswordHistoryPersistence iUserPasswordHistory) {
		this.iUserPasswordHistory = iUserPasswordHistory;
	}


}
