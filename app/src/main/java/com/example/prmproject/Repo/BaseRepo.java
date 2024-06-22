package com.example.prmproject.Repo;

import com.example.prmproject.Database.SqlServer.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepo<T, ID> implements IBaseRepo<T, ID> {

    protected abstract String getTableName();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    protected abstract void setInsertStatementParameters(PreparedStatement ps, T entity) throws SQLException;
    protected abstract void setUpdateStatementParameters(PreparedStatement ps, T entity) throws SQLException;
    protected abstract ID getEntityId(T entity);

    SqlConnection sqlConnection = new SqlConnection();

    @Override
    public T findById(ID id) throws SQLException {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (Connection conn = sqlConnection.CONN();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
            return null;
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        String query = "SELECT * FROM " + getTableName();
        try (Connection conn = sqlConnection.CONN();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
            return entities;
        }
    }

    @Override
    public void save(T entity) throws SQLException {
        String query = "INSERT INTO " + getTableName() + " VALUES (?, ?)";
        try (Connection conn = sqlConnection.CONN();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setInsertStatementParameters(ps, entity);
            ps.executeUpdate();
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        String query = "UPDATE " + getTableName() + " SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = sqlConnection.CONN();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setUpdateStatementParameters(ps, entity);
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteById(ID id) throws SQLException {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (Connection conn = sqlConnection.CONN();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        }
    }
}