package com.paaulooo.boardtarefas.dto;

import java.time.OffsetDateTime;

public record TaskDetailsDTO(Long id,
                             String title,
                             String description,
                             boolean blocked,
                             OffsetDateTime blockedAt,
                             String blockReason,
                             int blocksAmount,
                             Long columnId,
                             String columnName
) {
}