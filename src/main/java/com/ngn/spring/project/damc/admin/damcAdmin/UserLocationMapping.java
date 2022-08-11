package com.ngn.spring.project.damc.admin.damcAdmin;

import javax.persistence.*;

/**
 * Created by Pema Drakpa on 11-Jan-22.
 */
@Entity
@Table(name="t_user_location_mapping")
public class UserLocationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "User_Id")
    private String user_Id;
    @Column(name = "Dzongkhag_Id")
    private String dzongkhag_Id;
    @Column(name = "Gewog_Id")
    private int gewog_Id;

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

    public String getDzongkhag_Id() {
        return dzongkhag_Id;
    }

    public void setDzongkhag_Id(String dzongkhag_Id) {
        this.dzongkhag_Id = dzongkhag_Id;
    }

    public int getGewog_Id() {
        return gewog_Id;
    }

    public void setGewog_Id(int gewog_Id) {
        this.gewog_Id = gewog_Id;
    }
}
