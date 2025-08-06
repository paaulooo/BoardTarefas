package com.paaulooo.boardtarefas.persistence.dao;

import com.paaulooo.boardtarefas.dto.TaskDetailsDTO;
import com.paaulooo.boardtarefas.persistence.entity.TaskEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.paaulooo.boardtarefas.persistence.converter.OffSetDateTimeConverter.toOffsetDateTime;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class TaskDAO {

    private Connection connection;

    public TaskEntity insert(final TaskEntity entity) throws SQLException {
        var sql = "INSERT INTO CARDS (title, description, board_column_id) values (?, ?, ?);";
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setString(i ++, entity.getTitle());
            statement.setString(i ++, entity.getDescription());
            statement.setLong(i, entity.getBoardColumn().getId());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1)); // Primeira coluna do resultSet = ID gerado
                }
            }
        }
        return entity;
    }

    public void moveToColumn(final Long columnId, final Long cardId) throws SQLException{
        var sql = "UPDATE CARDS SET board_column_id = ? WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setLong(i ++, columnId);
            statement.setLong(i, cardId);
            statement.executeUpdate();
        }
    }

    public Optional<TaskDetailsDTO> findById(final Long id) throws SQLException {
        var sql =
                """
                SELECT c.id,
                       c.title,
                       c.description,
                       b.blocked_at,
                       b.block_reason,
                       c.board_column_id,
                       bc.name,
                       (SELECT COUNT(sub_b.id)
                               FROM BLOCKS sub_b
                              WHERE sub_b.card_id = c.id) blocks_amount
                  FROM CARDS c
                  LEFT JOIN BLOCKS b
                    ON c.id = b.card_id
                   AND b.unblocked_at IS NULL
                 INNER JOIN BOARDS_COLUMNS bc
                    ON bc.id = c.board_column_id
                  WHERE c.id = ?;
                """;
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                var dto = new TaskDetailsDTO(
                        resultSet.getLong("c.id"),
                        resultSet.getString("c.title"),
                        resultSet.getString("c.description"),
                        nonNull(resultSet.getString("b.block_reason")),
                        toOffsetDateTime(resultSet.getTimestamp("b.blocked_at")),
                        resultSet.getString("b.block_reason"),
                        resultSet.getInt("blocks_amount"),
                        resultSet.getLong("c.board_column_id"),
                        resultSet.getString("bc.name")
                );
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }

}