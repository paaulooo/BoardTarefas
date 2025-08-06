package com.paaulooo.boardtarefas.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class ColumnEntity {

    private Long id;
    private String name;
    private int order;
    private ColumnEnum kind;
    private BoardEntity board = new BoardEntity();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TaskEntity> cards = new ArrayList<>();

}
