package com.example.kingcoffee.model;

import java.io.Serializable;

/**
 * Created by Victor on 09/07/2017.
 */

public class Table implements Serializable {

    private String tableId;

    public Table() {
    }

    public Table(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
