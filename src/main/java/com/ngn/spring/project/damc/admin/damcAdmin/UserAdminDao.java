package com.ngn.spring.project.damc.admin.damcAdmin;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.lib.LoggedInUser;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class UserAdminDao extends BaseDao {
    @Transactional(readOnly = true)
    public List<userDTO> getPendingList() {
        sqlQuery = properties.getProperty("userAdmin.getPendingList");
        return hibernateQuery(sqlQuery, userDTO.class).list();
    }

    public void approve(String cidNo, String email, LoggedInUser loggedInUser, String hashed_password) {
        sqlQuery = properties.getProperty("userAdmin.approve");
        hibernateQuery(sqlQuery).setParameter(1,hashed_password).setParameter(2, cidNo).setParameter(3, email).executeUpdate();
    }

    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    public void deleteThisFM(String cidNo, String email) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM t_user_master WHERE Login_Id=? AND Email_Id=?");
            query1.setParameter(1, cidNo).setParameter(2,email);
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in UserAdminDao # deleteThisFM: " + e);
            e.printStackTrace();
        }
    }

    public Boolean isEmployeeIdUnique(String employeeId) {
        sqlQuery = properties.getProperty("userAdmin.isEmployeeIdUnique");
        return hibernateQuery(sqlQuery).setParameter("employeeId",employeeId).list().isEmpty();
    }
}
