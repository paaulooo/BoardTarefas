package com.paaulooo.boardtarefas.persistence.entity;

import lombok.Data;

@Data
public class TaskEntity {

    private Long id;
    private String title;
    private String description;
    private ColumnEntity boardColumn = new ColumnEntity();

}
