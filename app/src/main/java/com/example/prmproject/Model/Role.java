package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Roles")
public class Role {
    @PrimaryKey(autoGenerate = true)
    private int roleId;
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}


