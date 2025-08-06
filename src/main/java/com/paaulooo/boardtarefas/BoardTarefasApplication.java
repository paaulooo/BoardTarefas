package com.paaulooo.boardtarefas;

import com.paaulooo.boardtarefas.persistence.migration.Strategy;
import com.paaulooo.boardtarefas.ui.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import static com.paaulooo.boardtarefas.persistence.config.ConnectionConfig.getConnection;

@SpringBootApplication
public class BoardTarefasApplication {

    public static void main(String[] args) throws SQLException {
        try(var connection = getConnection()){
            new Strategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }

}
