package GUI;

import BUS.InventoryBUS;
import BUS.InvoiceInventBUS;
import BUS.InvenDetailBUS;
import DTO.Inventory;
import DTO.InvoiceInvent;
import DTO.InvenDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceInventManagementPanel extends JPanel {
    private InventoryBUS inventoryBUS = new InventoryBUS();
    private InvoiceInventBUS invoiceInventBUS = new InvoiceInventBUS();
    private InvenDetailBUS invendetailBUS = new InvenDetailBUS();

    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public InvoiceInventManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ========== HEADER PANEL ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("QUẢN LÝ HÓA ĐƠN NHẬP KHO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ========== CONTROL PANEL ==========
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Add Button
        JButton btnAdd = new JButton("Thêm hóa đơn");
        btnAdd.addActionListener(this::handleAddInvoiceInvent);
        controlPanel.add(btnAdd);

        headerPanel.add(controlPanel);

        // ========== SEARCH PANEL ==========
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[] { "Mã hóa đơn", "Mã nhà cung cấp", "Ngày nhập"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchInventory());

        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        headerPanel.add(searchPanel);

        add(headerPanel, BorderLayout.NORTH);

        // ========== TABLE ==========
        model = new DefaultTableModel(
            new String[]{"Mã hóa đơn", "Mã NCC", "Số lượng nhập", "Ngày nhập"},
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

        // ========== INPUT FORM ==========
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

        // ========== SAVE BUTTON ==========
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

    private void loadData() {
        model.setRowCount(0);
        List<InvoiceInvent> invoices = invoiceInventBUS.getAllInvoice();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (InvoiceInvent invoice : invoices) {
            model.addRow(new Object[]{
                invoice.getInvoiceId(),
                invoice.getSupplierId(),
                invoice.getQuantityAdded(),
                sdf.format(invoice.getDate())
            });
        }
    }
    private void searchInventory(){
        String searchType = (String) cboSearchType.getSelectedItem();
        String keyword = txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }

        List<InvoiceInvent> invoices = null;
        switch (searchType) {
            case "Mã hóa đơn":
                invoices = invoiceInventBUS.searchInvoices("invoice_id", keyword);
                break;
            case "Mã nhà cung cấp":
                invoices = invoiceInventBUS.searchInvoices("supplier_id", keyword);
                break;
            case "Ngày nhập":
                invoices = invoiceInventBUS.searchInvoices("date", keyword);
                break;
        }

        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (InvoiceInvent invoice : invoices) {
            model.addRow(new Object[]{
                invoice.getInvoiceId(),
                invoice.getSupplierId(),
                invoice.getQuantityAdded(),
                sdf.format(invoice.getDate())
            });
        }
    }
    
}