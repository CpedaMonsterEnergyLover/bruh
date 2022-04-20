package ru.vovac.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class ObjectTableModel<T> extends AbstractTableModel {

    private List<T> objects;
    private Class<T> tClass;

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    private String[] columnNames;

    public ObjectTableModel(List<T> objects, Class<T> tClass, String[] columnNames) {
        this.objects = objects;
        this.tClass = tClass;
        this.columnNames = columnNames;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tClass.getDeclaredFields()[columnIndex].getType();
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
            return field.get(objects.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public Class<T> gettClass() {
        return tClass;
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }
}
