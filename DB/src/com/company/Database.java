package com.company;

import com.company.DBExceptions.EmptyData;

import java.util.ArrayList;

public class Database {

    private String databaseName;
    private ArrayList<Table> tables;
    private int numberOfTables = 0;

    public Database(String databaseName){
        this.databaseName = databaseName;
        tables = new ArrayList<Table>();
    }

    public void addTable(Table newTable)
        throws EmptyData{
        if(newTable!=null) {
            tables.add(newTable);
            numberOfTables++;
        }
        else{
            throw new EmptyData(databaseName);
        }
    }

    public int getNumberOfTables(){
        return numberOfTables;
    }

    public void printDatabase(){
        for(int i = 0; i < tables.size(); i++){
            tables.get(i).printTable();
        }
    }
}
