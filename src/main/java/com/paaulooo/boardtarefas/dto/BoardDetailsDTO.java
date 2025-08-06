package com.paaulooo.boardtarefas.dto;


import java.util.List;

public record BoardDetailsDTO(Long id,
                              String name,
                              List<ColumnDTO> columns) {
}
