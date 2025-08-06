package com.paaulooo.boardtarefas.service;

import com.paaulooo.boardtarefas.dto.TaskDetailsDTO;
import com.paaulooo.boardtarefas.persistence.dao.TaskDAO;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class TaskQueryService {

    private final Connection connection;

    public Optional<TaskDetailsDTO> findById(final Long id) throws SQLException {
        var dao = new TaskDAO(connection);
        return dao.findById(id);
    }

}
