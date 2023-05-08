package com.mycompany.elecciones2023datos;

import javax.swing.table.AbstractTableModel;

public class ArrayTableModel extends AbstractTableModel {
    private final Object[][] data;
    private final String[] columnNames;

    public ArrayTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }



    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
