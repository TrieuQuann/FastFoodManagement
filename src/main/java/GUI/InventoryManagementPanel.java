package GUI;

import BUS.InventoryBUS;
import BUS.InvoiceInventBUS;
import BUS.InvenDetailBUS;
import DTO.Inventory;
import DTO.InvoiceInvent;
import DTO.InvenDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class InventoryManagementPanel extends JPanel {
    private InventoryBUS inventoryBUS = new InventoryBUS();
    private InvoiceInventBUS invoiceInventBUS = new InvoiceInventBUS();
    private InvenDetailBUS invendetailBUS = new InvenDetailBUS();

    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public InventoryManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ========== HEADER PANEL ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("QUẢN LÝ KHO HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ========== CONTROL PANEL ==========
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Add Invoice Button
        JButton btnAddInvoice = new JButton("Thêm hóa đơn nhập kho");
        btnAddInvoice.addActionListener(this::handleAddInvoiceInvent);
        controlPanel.add(btnAddInvoice);

        // Edit Inventory Button
        JButton btnEdit = new JButton("Sửa hàng hóa");
        btnEdit.addActionListener(this::handleEditInventory);
        controlPanel.add(btnEdit);

        headerPanel.add(controlPanel);

        // ========== SEARCH PANEL ==========
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã hàng", "Tên hàng", "Đơn vị"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchInventory());

        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        headerPanel.add(searchPanel);

        add(headerPanel, BorderLayout.NORTH);

        // ========== TABLE ==========
        model = new DefaultTableModel(
            new String[]{"Mã hàng", "Tên hàng", "Số lượng", "Đơn vị"},
            0
        );
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void handleAddInvoiceInvent(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm hóa đơn nhập kho");
        dialog.setLayout(new BorderLayout());

        // Input Form
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));

        JTextField txtSupplierId = new JTextField();
        JTextField txtQuantityAdded = new JTextField();
        JDateChooser dateChooser = new JDateChooser();
        JComboBox<Inventory> cboInventory = new JComboBox<>();

        // Load inventory items into combo box
        List<Inventory> inventoryList = inventoryBUS.getAllInventory();
        for (Inventory inventory : inventoryList) {
            cboInventory.addItem(inventory);
        }

        inputPanel.add(new JLabel("Mã NCC (*):"));
        inputPanel.add(txtSupplierId);
        inputPanel.add(new JLabel("Số lượng nhập (*):"));
        inputPanel.add(txtQuantityAdded);
        inputPanel.add(new JLabel("Ngày nhập (*):"));
        inputPanel.add(dateChooser);
        inputPanel.add(new JLabel("Chọn hàng hóa (*):"));
        inputPanel.add(cboInventory);

        // Save Button
        JButton btnSave = new JButton("Lưu hóa đơn");
        btnSave.addActionListener(evt -> {
            try {
                if (txtSupplierId.getText().isEmpty() || txtQuantityAdded.getText().isEmpty()
                        || dateChooser.getDate() == null || cboInventory.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                // Create InvoiceInvent object
                InvoiceInvent invoice = new InvoiceInvent(
                    0,
                    Integer.parseInt(txtSupplierId.getText()),
                    Integer.parseInt(txtQuantityAdded.getText()),
                    dateChooser.getDate()
                );

                // Add invoice to database
                if (invoiceInventBUS.addInvoice(invoice)) {
                    // Get selected inventory item
                    Inventory selectedInventory = (Inventory) cboInventory.getSelectedItem();

                    // Create InvenDetail object
                    InvenDetail detail = new InvenDetail(
                        selectedInventory.getInvenId(),
                        invoice.getInvoiceId(),
                        Integer.parseInt(txtQuantityAdded.getText())
                    );

                    // Add detail to database
                    if (invendetailBUS.addInvendetail(detail)) {
                        // Update inventory quantity
                        if (inventoryBUS.updateInventoryQuantity(selectedInventory.getInvenId(),
                                Integer.parseInt(txtQuantityAdded.getText()))) {
                            JOptionPane.showMessageDialog(dialog, "Thêm hóa đơn thành công!");
                            loadData();
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Cập nhật số lượng tồn thất bại!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Thêm chi tiết hóa đơn thất bại!");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm hóa đơn thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Dữ liệu số không hợp lệ!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!");
                ex.printStackTrace();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void handleEditInventory(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hóa để sửa!");
            return;
        }

        int inventoryId = (int) model.getValueAt(selectedRow, 0);
        String currentName = (String) model.getValueAt(selectedRow, 1);
        int currentQuantity = (int) model.getValueAt(selectedRow, 2);
        String currentUnit = (String) model.getValueAt(selectedRow, 3);

        JDialog dialog = new JDialog();
        dialog.setTitle("Sửa hàng hóa");
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));

        JTextField txtName = new JTextField(currentName);
        JTextField txtQuantity = new JTextField(String.valueOf(currentQuantity));
        JTextField txtUnit = new JTextField(currentUnit);

        inputPanel.add(new JLabel("Tên hàng (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Số lượng (*):"));
        inputPanel.add(txtQuantity);
        inputPanel.add(new JLabel("Đơn vị (*):"));
        inputPanel.add(txtUnit);

        JButton btnSave = new JButton("Lưu thay đổi");
        btnSave.addActionListener(evt -> {
            try {
                if (txtName.getText().isEmpty() || txtQuantity.getText().isEmpty()
                        || txtUnit.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                Inventory updatedInventory = new Inventory(
                    inventoryId,
                    txtName.getText(),
                    Integer.parseInt(txtQuantity.getText()),
                    txtUnit.getText()
                );

                if (inventoryBUS.updateInventory(updatedInventory)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thành công!");
                    loadData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Dữ liệu số không hợp lệ!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!");
                ex.printStackTrace();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Inventory> inventoryList = inventoryBUS.getAllInventory();

        for (Inventory inventory : inventoryList) {
            model.addRow(new Object[]{
                inventory.getInvenId(),
                inventory.getName(),
                inventory.getQuantity(),
                inventory.getUnit()
            });
        }
    }

    private void searchInventory() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();

        String column = switch (searchType) {
            case "Mã hàng" -> "inven_id";
            case "Tên hàng" -> "name";
            case "Đơn vị" -> "unit";
            default -> "";
        };

        model.setRowCount(0);
        List<Inventory> result = inventoryBUS.searchInventory(column, keyword);

        for (Inventory inventory : result) {
            model.addRow(new Object[]{
                inventory.getInvenId(),
                inventory.getName(),
                inventory.getQuantity(),
                inventory.getUnit()
            });
        }
    }
}
