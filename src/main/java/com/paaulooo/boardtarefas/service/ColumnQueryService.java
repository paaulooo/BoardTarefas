package com.paaulooo.boardtarefas.service;

import com.paaulooo.boardtarefas.persistence.dao.ColumnDAO;
import com.paaulooo.boardtarefas.persistence.entity.ColumnEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class ColumnQueryService {

    private final Connection connection;

    public Optional<ColumnEntity> findById(final Long id) throws SQLException {
        var dao = new ColumnDAO(connection);
        return dao.findById(id);
    }

}
