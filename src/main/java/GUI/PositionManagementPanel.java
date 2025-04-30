package GUI;

import BUS.PositionBUS;
import DTO.Position;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PositionManagementPanel extends JPanel {
    private PositionBUS bus = new PositionBUS();
    private JTable table;
    private DefaultTableModel model;

    public PositionManagementPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"Mã chức vụ", "Tên chức vụ"}, 0);
        table = new JTable(model);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        JLabel title = new JLabel("Quản lý chức vụ", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Position> list = bus.getAllPositions();
        for (Position p : list) {
            model.addRow(new Object[]{
                p.getPosition_id(), p.getName()
            });
        }
    }
}