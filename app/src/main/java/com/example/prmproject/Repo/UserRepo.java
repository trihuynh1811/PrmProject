package com.example.prmproject.Repo;

import com.example.prmproject.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo  extends BaseRepo<User, Integer>{
    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setAccountName(rs.getString("AccountName"));
        user.setEmail(rs.getString("Email"));
        return user;
    }

    @Override
    protected void setInsertStatementParameters(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getAccountName());
        ps.setString(2, entity.getPassword());
        ps.setString(3, entity.getEmail());
    }

    @Override
    protected void setUpdateStatementParameters(PreparedStatement ps, User entity) throws SQLException {

    }

    @Override
    protected Integer getEntityId(User entity) {
        return null;
    }
}
