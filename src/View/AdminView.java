package View;

import Controller.TenantController;
import Models.Tenant;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminView extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private CardLayout cardLayout;
    private JButton tentButton , rentalButton, houseButton;
    private JTextField txtTenantID, txtTenantName , txtTenantEmail,txtDateofBirth , txtDateStart,txtElectricUsage,txtWaterUsage;
    public AdminView(String s)
    {
        super(s);
        addView();
        addEvent();

    }

    public void showView()
    {
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel,BorderLayout.CENTER);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/login.png"));
        this.setVisible(true);
    }
    private void addView() {
        Color myColor = new Color(93, 185, 187);
        leftPanel = new JPanel();
        rightPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout) rightPanel.getLayout();

        JPanel userPanel = createUserPanel();
        JPanel housePanel = createHousePanel();
        JPanel rentalPanel = createRentalPanel();

        rightPanel.add(userPanel,"Tenant");
        rightPanel.add(housePanel, "House");
        rightPanel.add(rentalPanel, "Rental");

        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        JPanel panelBtUser = new JPanel();
        panelBtUser.setLayout(new BorderLayout());
        tentButton = new JButton("Người thuê nhà");
        tentButton.setBackground(myColor);
        tentButton.setForeground(myColor);
        tentButton.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/tent.png") );
         // Đặt màu văn bản là trắng hoặc màu khác để văn bản hiển thị rõ ràng trên nền

        tentButton.setFont(tentButton.getFont().deriveFont(Font.BOLD, 30));
        panelBtUser.add(tentButton, BorderLayout.CENTER);
        leftPanel.add(panelBtUser);
        JPanel panelBtHouse = new JPanel(new BorderLayout());
        houseButton = new JButton("Phòng thuê");
        houseButton.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/house.png"));
        houseButton.setBackground(myColor);
        houseButton.setForeground(myColor);
        houseButton.setFont(houseButton.getFont().deriveFont(Font.BOLD, 20));
        panelBtHouse.add(houseButton);
        leftPanel.add(panelBtHouse);
        JPanel panelRental = new JPanel(new BorderLayout());
        rentalButton = new JButton("Rental");
        rentalButton.setForeground(myColor);
        rentalButton.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/rent.png"));
        panelRental.add(rentalButton, BorderLayout.CENTER);
        leftPanel.add(panelRental);

    }

    private JPanel createRentalPanel() {
        JPanel MainUser = new JPanel();
        JLabel lbTest = new JLabel("Tst");
        MainUser.add(lbTest);
        return MainUser;
    }

    private JPanel createHousePanel() {
        JPanel MainUser = new JPanel();
        return MainUser;
    }

    private DefaultTableModel tableModel; // Biến để lưu trữ DefaultTableModel

    private JPanel createUserPanel() {
        JPanel panelTenant = new JPanel();
        panelTenant.setLayout(new BorderLayout());
        String[] columnNames = {"CCCD", "Tên", "ngày sinh", "email", "ngày bắt đầu ở", "số điện tiêu tụ", "số nước tiêu thụ", "số phòng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable tenantTable = new JTable(tableModel);
        tenantTable.setShowGrid(true);
        tenantTable.setShowVerticalLines(true);
        JScrollPane scrollPane = new JScrollPane(tenantTable);
        panelTenant.add(scrollPane, BorderLayout.CENTER);
        showAllTenants();

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new BorderLayout());
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        JPanel panelInputID = new JPanel();
        panelInputID.setLayout(new FlowLayout());



        panelTenant.add(panelBottom,BorderLayout.SOUTH);

        return panelTenant;
    }
    private void showAllTenants() {
        // Lấy danh sách người thuê nhà từ TenantController
        TenantController tenantController = new TenantController();
        List<Tenant> tenants = tenantController.getAllTenants();

        // Xóa dữ liệu hiện tại của tableModel
        tableModel.setRowCount(0);

        // Thêm dữ liệu mới từ danh sách người thuê nhà
        for (Tenant tenant : tenants) {
            Object[] rowData = {tenant.getTenantId(), tenant.getName(), tenant.getDateOfBirth(), tenant.getEmail(),
                    tenant.getStartDate(), tenant.getElectricityUsage(), tenant.getWaterUsage(), tenant.getHouse_id()};
            tableModel.addRow(rowData);
        }
    }

    private void addNewTenant() {
        // Thêm dữ liệu người thuê mới vào tableModel
        Object[] rowData = {"NewID", "New User", "2000-01-01", "new@example.com", "2023-01-01", 80.0, 40.0, 1};
        tableModel.addRow(rowData);
        tableModel.fireTableDataChanged();
    }


    private void addEvent() {
        tentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "Tenant");
            }
        });
        rentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel,"Rental");
            }
        });
    }

    public static void main(String[] args) {
        AdminView adminView = new AdminView("Manager");
        adminView.showView();
    }
}
