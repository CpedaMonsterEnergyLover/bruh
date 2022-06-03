package ru.vovac.forms;

import ru.vovac.entity.ProductEntity;
import ru.vovac.manager.ProductEntityManager;
import ru.vovac.util.BaseForm;
import ru.vovac.util.ObjectTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton testButton;

    private ObjectTableModel<ProductEntity> tableModel;


    public ProductsTableForm() {
        super(1600, 800);
        setContentPane(mainPanel);

        initTable();
        mainTable.getTableHeader().setReorderingAllowed(false);

        setVisible(true);
    }



    private void initTable(){
        try {
            mainTable.setRowHeight(50);
            tableModel = new ObjectTableModel<>(
                    ProductEntityManager.selectAll(),
                    ProductEntity.class,
                    new String[]{"ID", "Наименование", "Тип", "Артикль", "Описание", "Путь до изображения", "Кол-во работников", "Номер мастерской", "Мин. стоимость", "Изображение"}
            );
            mainTable.setModel(tableModel);
            mainTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2){
                        int row = mainTable.rowAtPoint(e.getPoint());
                        if(row != -1){
                            new UpdateForm(tableModel.getObjects().get(row));
                            dispose();
                        }
                    }
                }
            });
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
