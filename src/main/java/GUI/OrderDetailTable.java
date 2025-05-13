/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.Orders;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author LENOVO
 */
public class OrderDetailTable extends JPanel {
    private JTextField jtfCusName;
    private JTextField jtfCusId;
    private JTextField jtfEmpId;
    private JTextField jtfTime;
    private JTextField jtfTotalPrice;
    private Double TotalPrice;
    private JButton btSave;
    private JButton btDel;
    private JTable tableProducts;
    private DefaultTableModel tableModel;

    public JTextField getJtfCusName() {
        return jtfCusName;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public JTextField getJtfCusId() {
        return jtfCusId;
    }

    public JTextField getJtfEmpId() {
        return jtfEmpId;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public OrderDetailTable() {
        this.TotalPrice = 0.0;
        initOrderDetailTable();
    }

    public void initOrderDetailTable() {
        setPreferredSize(new Dimension(580, 690));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));

        // Panel Title
        JPanel pnTitle = new JPanel();
        pnTitle.setPreferredSize(new Dimension(500, 40));
        JLabel jlbTitle = new JLabel("Hóa đơn mua hàng");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        jlbTitle.setOpaque(false);
        pnTitle.add(jlbTitle);
        add(pnTitle);

        // Label and TextFields
        JLabel jlbCusName = new JLabel("Người mua hàng:");
        jlbCusName.setPreferredSize(new Dimension(110, 40));
        jlbCusName.setOpaque(false);
        jtfCusName = new JTextField();
        jtfCusName.setPreferredSize(new Dimension(200, 40));
        jtfCusName.setEditable(false);

        JLabel jlbTime = new JLabel("Thời gian:");
        jlbTime.setPreferredSize(new Dimension(110, 40));
        jlbTime.setOpaque(false);
        jtfTime = new JTextField();
        jtfTime.setPreferredSize(new Dimension(200, 40));
        jtfTime.setEditable(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDate.now().format(formatter);
        jtfTime.setText(currentDate);

        JLabel jlbTotalPrice = new JLabel("Tổng tiền:");
        jlbTotalPrice.setPreferredSize(new Dimension(110, 40));
        jlbTotalPrice.setOpaque(false);
        jtfTotalPrice = new JTextField();
        jtfTotalPrice.setPreferredSize(new Dimension(200, 40));
        jtfTotalPrice.setEditable(false);

        JPanel newLine = new JPanel();
        newLine.setPreferredSize(new Dimension(120, 40));
        JPanel newLine1 = new JPanel();
        newLine1.setPreferredSize(new Dimension(120, 40));
        JPanel newLine2 = new JPanel();
        newLine2.setPreferredSize(new Dimension(120, 40));

        jtfEmpId = new JTextField();
        jtfEmpId.setVisible(false);
        jtfCusId = new JTextField();
        jtfCusId.setVisible(false);

        add(jlbCusName);
        add(jtfCusName);
        add(newLine);
        add(jlbTime);
        add(jtfTime);
        add(newLine1);
        add(jlbTotalPrice);
        add(jtfTotalPrice);
        add(newLine2);
        add(jtfEmpId);
        add(jtfCusId);

        // Table
        JPanel pnTableContainer = new JPanel();
        pnTableContainer.setPreferredSize(new Dimension(520, 370));
        pnTableContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel pnTableTitle = new JPanel();
        pnTableTitle.setPreferredSize(new Dimension(500, 40));
        JLabel jlbTableTitle = new JLabel("Danh sách sản phẩm");
        jlbTableTitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jlbTableTitle.setOpaque(false);
        pnTableTitle.add(jlbTableTitle);
        pnTableContainer.add(pnTableTitle);

        JPanel pnTableContent = new JPanel();
        pnTableContent.setPreferredSize(new Dimension(520, 320));
        pnTableContent.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Column names for table
        String[] columnNames = {"Tên sản phẩm", "Số lượng", "Giá", "Tổng giá"};
        String[][] data = new String[0][4];
        tableModel = new DefaultTableModel(data, columnNames);
        tableProducts = new JTable(tableModel);
        tableProducts.setPreferredScrollableViewportSize(new Dimension(520, 320));
        tableProducts.setFillsViewportHeight(true);
        tableProducts.setRowHeight(22);
        JTableHeader header = tableProducts.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(204, 229, 255));
        header.setForeground(Color.BLACK);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < tableProducts.getColumnCount(); i++) {
            tableProducts.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        scrollPane.setPreferredSize(new Dimension(520, 320));
        pnTableContent.add(scrollPane);
        pnTableContainer.add(pnTableContent);

        add(pnTableContainer);

        // Bottom Buttons
        JPanel pnBottomTable = new JPanel();
        pnBottomTable.setPreferredSize(new Dimension(520, 50));
        pnBottomTable.setLayout(new FlowLayout(FlowLayout.CENTER));

        btSave = new JButton("Lưu hóa đơn");
        btSave.setPreferredSize(new Dimension(110, 40));
        btSave.setBackground(new Color(30, 144, 255));
        btSave.setForeground(Color.WHITE);
        btSave.setFocusPainted(false);
        btSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btSave.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btSave.addActionListener(e -> handleSaveOrder());

        btDel = new JButton("Xóa sản phẩm");
        btDel.setVisible(false);
        btDel.setPreferredSize(new Dimension(110, 40));
        btDel.setBackground(new Color(30, 144, 255));
        btDel.setForeground(Color.WHITE);
        btDel.setFocusPainted(false);
        btDel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btDel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btDel.addActionListener(e -> handleDeleteRowProduct());

        pnBottomTable.add(btDel);
        pnBottomTable.add(btSave);
        add(pnBottomTable);

        initTableSelectionListener();
    }

    private void handleDeleteRowProduct() {
        int selectedRow = tableProducts.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            updateTotalPriceToVND();
        }
    }

    public void updateTotalPriceToVND() {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
                total += Double.parseDouble(tableModel.getValueAt(i, 3).toString());
            } catch (NumberFormatException e) {
                // Handle invalid number format
            }
        }
        String formatted = String.format("%,.0f", total);
        jtfTotalPrice.setText(formatted + " VNĐ");
        this.TotalPrice = total;
    }

    public void addProductRow(String tenSP, int soLuong, double gia, double tongGia) {
        boolean isProductExist = false;
        int existingRow = -1;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(tenSP)) {
                isProductExist = true;
                existingRow = i;
                break;
            }
        }

        if (isProductExist) {
            int existingQuantity = Integer.parseInt(tableModel.getValueAt(existingRow, 1).toString());
            double existingTotalPrice = Double.parseDouble(tableModel.getValueAt(existingRow, 3).toString());

            int newQuantity = existingQuantity + soLuong;
            double newTotalPrice = newQuantity * gia;

            tableModel.setValueAt(String.valueOf(newQuantity), existingRow, 1);
            tableModel.setValueAt(String.valueOf(newTotalPrice), existingRow, 3);
        } else {
            String[] row = new String[4];
            row[0] = tenSP;
            row[1] = String.valueOf(soLuong);
            row[2] = String.valueOf(gia);
            row[3] = String.valueOf(tongGia);
            tableModel.addRow(row);
        }
        updateTotalPriceToVND();
    }


    private void initTableSelectionListener() {
        tableProducts.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableProducts.getSelectedRow() != -1) {
                btDel.setVisible(true);
            } else {
                btDel.setVisible(false);
            }
        });
    }
    
    private void handleSaveOrder() {
        if (tableModel.getRowCount() > 0) {
            // Lấy thông tin từ các trường
            int employeeId = Integer.parseInt(jtfEmpId.getText()) ; // employee_id
            int customerId = Integer.parseInt(jtfCusId.getText()); // customer_id
            String timeString = jtfTime.getText(); // time (dạng String "yyyy-MM-dd")
            Date time = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                time = dateFormat.parse(timeString); // Chuyển thành đối tượng Date
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi định dạng thời gian!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String totalPriceStr = jtfTotalPrice.getText().replace(" VNĐ", ""); // total_price, bỏ VNĐ
            double totalPrice = Double.parseDouble(totalPriceStr.replace(",", "")); // Convert sang Double
        
            DTO.Orders newOrder = new Orders(1, employeeId, customerId, time, totalPrice, 0);
            new BUS.OrdersBUS().addOrder(newOrder);
            // Lưu từng sản phẩm vào bảng `order_detail`
            int oId = new BUS.OrderDetailsBUS().getMaxOrderId();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String tenSP = tableModel.getValueAt(i, 0).toString();
                int pId = new BUS.ProductsBUS().getIdByName(tenSP);
                int soLuong = Integer.parseInt(tableModel.getValueAt(i, 1).toString());
                double gia = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
                double tongGia = Double.parseDouble(tableModel.getValueAt(i, 3).toString());

                new BUS.OrderDetailsBUS().addOrderDetail(oId, pId, soLuong, gia, tongGia);
                
                int pQuantity = new BUS.ProductsBUS().getQuantityById(pId);
                int newSoLuong;
                if (pQuantity < soLuong){
                    newSoLuong=0;
                }
                else{
                    newSoLuong = pQuantity - soLuong;
                }
                new BUS.ProductsBUS().updateQuantityById(pId, newSoLuong);
            }
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetAllFields();

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm vào hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void resetAllFields() {
        jtfCusName.setText("");
        jtfCusId.setText("");
        jtfEmpId.setText("");
        jtfTotalPrice.setText("");
        this.TotalPrice = 0.0;
        tableModel.setRowCount(0);
        btDel.setVisible(false);
    }

}
