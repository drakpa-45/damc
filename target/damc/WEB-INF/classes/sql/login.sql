LoginDao.login = SELECT u.Login_Id AS username,u.password password,u.First_Name firstName,u.Middle_Name middleName,u.Last_Name lastName,r.Role_Id roleId,u.User_Status status FROM t_user_master u LEFT JOIN t_user_role_mapping r ON r.User_Id = u.Login_Id WHERE u.Login_Id =:username
LoginDao.getUserRoleList = SELECT b.User_Id userId,b.Role_Id roleId FROM t_user_role_mapping b INNER JOIN t_user_master a ON a.Login_Id=b.User_Id WHERE a.Login_Id =:userId
LoginDao.checkForUserName=SELECT username,PASSWORD,FROM sysuser s WHERE s.username=:username