package com.paaulooo.boardtarefas.dto;

import com.paaulooo.boardtarefas.persistence.entity.ColumnEnum;

public record ColumnInfoDTO(Long id, int order, ColumnEnum kind) {
}
