package com.gptool.chartgpt.repository;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.gptool.chartgpt.pojo.Table;

@Repository
@Primary
public class TableRepoImpl implements TableRepository {

    @Override
    public Table getTable(String id) {
        return new Table();
    }
    
    @Override
    public void saveTable(String table) {
        try (FileWriter fileWriter = new FileWriter("chartgpt/src/main/resources/static/json")) {
            fileWriter.write(table);
        } catch (IOException e) {
        }
    }
}

