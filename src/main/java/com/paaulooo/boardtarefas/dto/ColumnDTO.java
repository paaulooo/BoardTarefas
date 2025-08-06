package com.paaulooo.boardtarefas.dto;

import com.paaulooo.boardtarefas.persistence.entity.ColumnEnum;

public record ColumnDTO(Long id,
                        String name,
                        ColumnEnum kind,
                        int cardsAmount) {
}