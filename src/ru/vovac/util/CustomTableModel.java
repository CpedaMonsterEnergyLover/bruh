package ru.vovac.util;

import ru.vova.c.NewCalculation;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel {

    private Class<T> tClass;
    private List<T> objects;
    private String[] columnNames;

    public CustomTableModel(Class<T> tClass, List<T> objects, String[] columnNames) {
        this.tClass = tClass;
        this.objects = objects;
        this.columnNames = columnNames;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tClass.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return objects.size();
    }

    @Override
    public int getColumnCount() {
        return tClass.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Field field = tClass.getDeclaredFields()[columnIndex];
            field.setAccessible(true);
            return field.get(objects.get(columnIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
