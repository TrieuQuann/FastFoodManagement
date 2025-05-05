/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import DTO.Category;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

public class CategoryTable extends JPanel {
    private JPanel jpnInfo;
    private CategoryTableModel model;
    private JTable jTable;

    public CategoryTableModel getModel() {
        return model;
    }

    public CategoryTable(JPanel jpnInfo) {
        this.jpnInfo = jpnInfo;
        this.model = new CategoryTableModel();
        initCategoryTable();
    }

    public void reloadData(String search) {
        this.model = new CategoryTableModel(search);
        jTable.setModel((TableModel) this.model);
        revalidate();
        repaint();
    }

    private void initCategoryTable() {
        setPreferredSize(new Dimension(450, 500));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20,20,20,20));
        jTable = new JTable((TableModel) this.model);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane, BorderLayout.CENTER);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    int id = (int) jTable.getValueAt(row, 0);
                    showInfo(id);
                }
            }
        });
    }

    private void showInfo(int id) {
        if (jpnInfo instanceof CategoryInfo) {
            CategoryInfo infoPanel = (CategoryInfo) jpnInfo;
            Category category = (Category) getModel().getCategoryById(id);
            infoPanel.getJtfId().setText(String.valueOf(category.getCategoryId()));
            infoPanel.getJtfCategoryName().setText(category.getName());
            infoPanel.setEditableAllField();
        }
    }
}
