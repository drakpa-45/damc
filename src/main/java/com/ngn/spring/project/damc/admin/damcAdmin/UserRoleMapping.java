package com.ngn.spring.project.damc.admin.damcAdmin;


import javax.persistence.*;

/**
 * Created by Pema Drakpa on 11-Jan-22.
 */
@Entity
@Table(name="t_user_role_mapping")
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "User_Id")
    private String user_Id;
    @Column(name = "Role_Id")
    private Integer role_Id;
    @Column(name = "Agency_Id")
    private String agency_Id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public Integer getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Integer role_Id) {
        this.role_Id = role_Id;
    }

    public String getAgency_Id() {
        return agency_Id;
    }

    public void setAgency_Id(String agency_Id) {
        this.agency_Id = agency_Id;
    }
}
