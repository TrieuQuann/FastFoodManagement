package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DAO.ConnectionDB; // Import lớp ConnectionDB
import DTO.Feature; // Import lớp Feature

public class MainFrame extends JFrame {

    private JPanel sidebar, mainPanel;
    private int currentrole;
    private String currname;

    public MainFrame(int currentroleid,String name) {
        // Thiết lập tiêu đề và kích thước cho JFrame
        currentrole = currentroleid;
        currname = name;
        System.out.println(currentrole);
        setTitle("Admin Interface");
        setSize(1728, 1040); // Kích thước giao diện
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình

        // Sử dụng BorderLayout cho JFrame
        setLayout(new BorderLayout());

        // Sidebar (1/4 chiều ngang giao diện)
        sidebar = new JPanel();
        sidebar.setBackground(new Color(255,255,255));
        sidebar.setPreferredSize(new Dimension(200, getHeight())); // 1/4 của 800px
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Thêm khoảng cách 10px từ cạnh trên
        sidebar.add(Box.createVerticalStrut(10)); // Khoảng cách từ cạnh trên
        JLabel username = new JLabel("Hello, "+currname);
        username.setOpaque(true);
        username.setPreferredSize(new Dimension(200, 50));
        username.setMaximumSize(new Dimension(200, 50));
        username.setMinimumSize(new Dimension(200, 50));
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setBackground(new Color(61,205,128));
        sidebar.add(username);
        sidebar.add(Box.createVerticalStrut(20));

        // Lấy dữ liệu từ bảng feature trong cơ sở dữ liệu
        List<Feature> sidebarData = fetchSidebarDataFromDatabase();

        // Thêm các JLabel vào sidebar và gắn sự kiện
        for (Feature feature : sidebarData) {
            JLabel label = new JLabel(feature.getFeature_name());
            label.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
            label.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ
            label.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Thay đổi con trỏ chuột
            label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Cách trái 20px
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(200, 50));
            label.setMaximumSize(new Dimension(200, 50));
            label.setMinimumSize(new Dimension(200, 50));
            label.setBackground(Color.white);

            // Thêm icon nếu có URL
            if (feature.getIcon_url() != null) {
                try {
                    ImageIcon icon = new ImageIcon(feature.getIcon_url());
                    label.setIcon(icon);
                } catch (Exception e) {
                    System.err.println("Error loading icon for feature: " + feature.getFeature_name());
                    e.printStackTrace();
                }
            }

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateMainPanel(feature.getFeature_name()); // Cập nhật mainPanel khi nhấn vào JLabel
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    label.setBackground(new Color(61,205,128));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    label.setBackground(Color.white);
                }
            });

            sidebar.add(label);
            sidebar.add(Box.createVerticalStrut(30)); // Giảm khoảng cách giữa các JLabel (40 pixel)
        }

        add(sidebar, BorderLayout.WEST);

        // Main panel (bên phải)
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Thêm nội dung mặc định cho mainPanel khi khởi động
        // mainPanel.add(new JLabel("Chào mừng bạn đến với hệ thống quản lý!",
        // SwingConstants.CENTER), BorderLayout.CENTER);

        // Hiển thị JFrame
        setVisible(true);
    }

    // Phương thức lấy dữ liệu từ bảng feature trong cơ sở dữ liệu
    private List<Feature> fetchSidebarDataFromDatabase() {
        List<Feature> features = new ArrayList<>();
        String query = "SELECT feature_name, icon_url FROM feature";
        try (Connection conn = ConnectionDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String iconUrl = rs.getString("icon_url");
                String name = rs.getString("feature_name"); // Sửa lại tên cột
                features.add(new Feature(iconUrl, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return features;
    }

    // Phương thức cập nhật nội dung của mainPanel
    private void updateMainPanel(String selectedItem) {
        mainPanel.removeAll(); // Xóa tất cả các thành phần cũ
        switch (selectedItem.toLowerCase()) {
            case "nhân viên":
                mainPanel.add(new EmployeeManagementPanel(), BorderLayout.CENTER);
                break;
            case "khách hàng":
                mainPanel.add(new CustomersManagementPanel(), BorderLayout.CENTER);
                break;
            case "hóa đơn":
                mainPanel.add(new OrdersManagementPanel(), BorderLayout.CENTER);
                break;
            case "phân quyền":
                mainPanel.add(new DecentralizedManagementPanel(), BorderLayout.CENTER);
                break;
            case "chức vụ":
                mainPanel.add(new PositionManagementPanel(), BorderLayout.CENTER);
                break;
            case "tài khoản":
                mainPanel.add(new UserManagementPanel(currentrole), BorderLayout.CENTER);
                break;
            case "sản phẩm":
                mainPanel.add(new InformationProductPanel(), BorderLayout.CENTER);
                break;
            case "thống kê":
                mainPanel.add(new StatisticsPanel(), BorderLayout.CENTER);
                break;
            case "danh mục":
                mainPanel.add(new InformationCategoryPanel(), BorderLayout.CENTER);
                break;
            case "công thức":
                mainPanel.add(new RecipeManagementPanel(), BorderLayout.CENTER);
                break;
            case "nhà cung cấp":
                mainPanel.add(new SupplierManagementPanel(),BorderLayout.CENTER);
                break;
            default:
                JLabel content = new JLabel("You selected: " + selectedItem, SwingConstants.CENTER);
                content.setFont(new Font("Arial", Font.BOLD, 24));
                mainPanel.add(content, BorderLayout.CENTER);
        }
        mainPanel.revalidate(); // Làm mới mainPanel
        mainPanel.repaint(); // Vẽ lại mainPanel
    }

    public static void main(String[] args) {
        new MainFrame(1,"admin");
    }
}
