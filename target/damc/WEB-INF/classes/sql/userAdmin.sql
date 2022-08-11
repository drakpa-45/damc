userAdmin.getPendingList=SELECT u.Login_Id cidNo,CONCAT(u.First_Name,' ', IFNULL(u.Middle_Name,''), ' ',IFNULL(u.Last_Name,'')) name,u.Email_Id email,m.Role_Name role,g.Gewog_Name gewogName,d.Dzongkhag_Name dzongkhagName FROM t_user_master u LEFT JOIN t_user_role_mapping r ON r.User_Id = u.Login_Id LEFT JOIN t_role_master m ON m.Role_Id = r.Role_Id LEFT JOIN t_user_location_mapping l ON l.User_Id = u.Login_Id LEFT JOIN t_dzongkhag_lookup d ON d.Dzongkhag_Id = l.Dzongkhag_Id LEFT JOIN t_gewog_lookup g ON g.Gewog_Id = l.Gewog_Id WHERE u.Is_Approved='0' AND r.Role_Id=1;

userAdmin.approve = UPDATE t_user_master SET Is_Approved = '1',Password=? WHERE Login_Id = ? AND Email_Id=?;

userAdmin.isEmployeeIdUnique=SELECT a.Login_Id FROM t_user_master a WHERE a.Login_Id =:employeeId