userAdmin.getPendingList=SELECT u.Login_Id cidNo, CONCAT( u.First_Name, ' ' ) name, u.Email_Id email, m.Role_Name role, g.Gewog_Name gewogName, d.Dzongkhag_Name dzongkhagName FROM t_user_master u LEFT JOIN t_user_role_mapping r ON u.Login_Id = r.User_Id LEFT JOIN t_role_master m ON r.Role_Id=m.Role_Id LEFT JOIN t_dzongkhag_lookup d ON u.DzongkhagId=d.Dzongkhag_Id LEFT JOIN t_gewog_lookup g ON u.GewogId = g.Gewog_Id WHERE u.Is_Approved = "0" AND r.Role_Id = 1;

userAdmin.approve = UPDATE t_user_master SET Is_Approved = '1',Password=? WHERE Login_Id = ? AND Email_Id=?;

userAdmin.isEmployeeIdUnique=SELECT a.Login_Id FROM t_user_master a WHERE a.Login_Id =:employeeId