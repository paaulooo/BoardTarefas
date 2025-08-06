package com.paaulooo.boardtarefas.persistence.entity;

import java.util.stream.Stream;

public enum ColumnEnum {

    INITIAL,
    FINAL,
    CANCEL,
    PENDING;

    public static ColumnEnum findByName(final String name){
        return Stream.of(ColumnEnum.values())
                .filter(b -> b.name().equals(name))
                .findFirst().orElseThrow();
    }

}