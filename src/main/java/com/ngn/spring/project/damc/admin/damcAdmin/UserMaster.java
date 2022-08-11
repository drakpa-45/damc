package com.ngn.spring.project.damc.admin.damcAdmin;

import javax.persistence.*;

/**
 * Created by Pema Drakpa on 11-Jan-22.
 * userMaster --- t_user_master table
 */
@Entity
@Table(name="t_user_master")
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private int user_Id;
    @Column(name = "Login_Id")
    private String login_Id;
    @Column(name = "First_Name")
    private String first_Name;
    @Column(name = "Email_Id")
    private String email_Id;
    @Column(name = "Password")
    private String password;
    @Column(name = "DesignationId")
    private String designationId;
    @Column(name = "RegionId")
    private String regionId;
    @Column(name = "SectorId")
    private String sectorId;
    @Column(name = "Is_Approved")
    private String is_Approved;
    @Column(name = "user_Status")
    private String User_Status;

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getLogin_Id() {
        return login_Id;
    }

    public void setLogin_Id(String login_Id) {
        this.login_Id = login_Id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public String getIs_Approved() {
        return is_Approved;
    }

    public void setIs_Approved(String is_Approved) {
        this.is_Approved = is_Approved;
    }

    public String getUser_Status() {
        return User_Status;
    }

    public void setUser_Status(String user_Status) {
        User_Status = user_Status;
    }

}
