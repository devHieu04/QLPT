package View;

import Controller.HouseController;
import Controller.RentalController;
import Controller.TenantController;
import Models.House;
import Models.Rental;
import Models.Tenant;
import TryCatch.DateCheck;
import TryCatch.EmailCheck;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminView extends JFrame implements ActionListener {
    JPanel pnHouse, pnRental, pnTenant,
           pnLeft, pnRight, pnTop, pnBottom, pnMain, pnLogout,
            pnInput1, pnInput2, pnArea, pnRoomCost, pnFurniture, pnStatus, pnBtn,
            pnCCCD, pnName, pnDateOfBirth, pnEmail, pnStartDate, pnElectricityUsage, pnWaterUsage, pnHouse_id;
    JLabel lbArea, lbRoomCost, lbFurniture, lbStatus,
            lbID,
           lbCCCD, lbName, lbDateOfBirth, lbEmail, lbStartDate, lbElectricityUsage, lbWaterUsage, lbHouse_id;
    JTextField txtArea, txtRoomCost, txtFurniture,
               txtID,
               txtCCCD, txtName, txtDateOfBirth, txtEmail, txtStartDate, txtElectricityUsage, txtWaterUsage;
    JButton btnTenant, btnHouse, btnRental, btnLogout,
            btnAddTen, btnUpdateTen, btnDeleteTen, btnSearchTen,
            btnAddHouse, btnUpdateHouse, btnDeleteHouse, btnSearchHouse, btnArrAreaHouse,
            btnDeleteRental, btnSearchRental;
    JComboBox<Integer> cbHouseStatus, cbHouseID;
    JTable tbHouse, tbTenant, tbRental;
    JScrollPane listScroll;
    DefaultTableModel tblHouse = new DefaultTableModel();
    DefaultTableModel tblRental = new DefaultTableModel();
    DefaultTableModel tblTenant = new DefaultTableModel();
    HouseController houseC = new HouseController();
    RentalController renC = new RentalController();
    TenantController tenC = new TenantController();
    Color myColor = new Color(93, 185, 187);
    CardLayout cardLayout;
    String[] colNames = {"Số phòng","Diện tích","Tiền điện", "Tiền nước","Giá phòng","Nội thất","Trạng thái"};
    ImageIcon imgAdd = new ImageIcon(ClassLoader.getSystemResource("Icon/addhouse.png"));
    ImageIcon imgUpdata = new ImageIcon(ClassLoader.getSystemResource("Icon/updatehouse.png"));
    ImageIcon imgDelete = new ImageIcon(ClassLoader.getSystemResource("Icon/deletehouse.png"));
    ImageIcon imgSearch = new ImageIcon(ClassLoader.getSystemResource("Icon/searchhouse.png"));
    ImageIcon imgLogin = new ImageIcon(ClassLoader.getSystemResource("Icon/login.png"));
    ImageIcon imgTenant = new ImageIcon(ClassLoader.getSystemResource("Icon/tent.png"));
    ImageIcon imgHouse = new ImageIcon(ClassLoader.getSystemResource("Icon/house.png"));
    ImageIcon imgRental = new ImageIcon(ClassLoader.getSystemResource("Icon/rent.png"));
    ImageIcon imgLogout = new ImageIcon(ClassLoader.getSystemResource("Icon/signout.png"));

    public AdminView(String s) {
        super(s);
        addView();
    }
    public void showView() {
        this.setSize(1300,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(imgLogin.getImage());
        this.setVisible(true);
    }
    public void addView() {
        Container con = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        pnLeft = new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

        pnRight = new JPanel(new CardLayout());
        cardLayout = (CardLayout) pnRight.getLayout();

        JPanel panelTenant = createTenantPanel();
        JPanel panelHouse = createHousePanel();
        JPanel panelRental = createRentalPanel();

        pnRight.add(panelTenant, "Tenant");
        pnRight.add(panelHouse, "House");
        pnRight.add(panelRental, "Rental");

        pnTenant = new JPanel();
        pnTenant.setLayout(new BorderLayout());
        btnTenant = new JButton("Người thuê nhà");
        btnTenant.setBackground(Color.WHITE);
        btnTenant.setForeground(myColor);
        btnTenant.setFont(btnTenant.getFont().deriveFont(Font.BOLD, 20));
        btnTenant.setIcon(imgTenant);
        btnTenant.setFocusPainted(false);
        btnTenant.addActionListener(this);

        pnHouse = new JPanel();
        pnHouse.setLayout(new BorderLayout());
        btnHouse = new JButton("Phòng thuê");
        btnHouse.setBackground(Color.WHITE);
        btnHouse.setForeground(myColor);
        btnHouse.setFont(btnHouse.getFont().deriveFont(Font.BOLD, 20));
        btnHouse.setIcon(imgHouse);
        btnHouse.setFocusPainted(false);
        btnHouse.addActionListener(this);

        pnRental = new JPanel();
        pnRental.setLayout(new BorderLayout());
        btnRental = new JButton("Cho thuê");
        btnRental.setBackground(Color.WHITE);
        btnRental.setForeground(myColor);
        btnRental.setFont(btnRental.getFont().deriveFont(Font.BOLD, 20));
        btnRental.setIcon(imgRental);
        btnRental.setFocusPainted(false);
        btnRental.addActionListener(this);

        pnLogout = new JPanel();
        pnLogout.setLayout(new BorderLayout());
        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setForeground(myColor);
        btnLogout.setFont(btnLogout.getFont().deriveFont(Font.BOLD, 20));
        btnLogout.setIcon(imgLogout);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this);

        pnTenant.add(btnTenant, BorderLayout.CENTER);
        pnHouse.add(btnHouse, BorderLayout.CENTER);
        pnRental.add(btnRental, BorderLayout.CENTER);
        pnLogout.add(btnLogout, BorderLayout.CENTER);

        pnLeft.add(pnTenant, "Tenant");
        pnLeft.add(pnHouse, "House");
        pnLeft.add(pnRental, "Rental");
        pnLeft.add(pnLogout);

        pnMain.add(pnLeft, BorderLayout.WEST);
        pnMain.add(pnRight, BorderLayout.CENTER);

        con.add(pnMain);
    }
    private JPanel createRentalPanel() {
        pnRental = new JPanel();
        pnRental.setLayout(new BorderLayout());
        pnRental.setBackground(Color.WHITE);
        pnRental.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());

        tbRental = new JTable();
        tbRental.setModel(tblRental);
        listScroll = new JScrollPane();

        for (String i : colNames)
            tblRental.addColumn(i);

        listScroll.setViewportView(tbRental);
        showAllRentals();
        selectIndexRentalTable();

        pnTop.add(listScroll);

        pnBottom = new JPanel();
        pnBottom.setLayout(new FlowLayout());
        pnBottom.setBackground(Color.WHITE);

        lbID = new JLabel("CCCD người thuê: ");
        lbID.setFont(lbID.getFont().deriveFont(Font.PLAIN, 20));
        lbID.setForeground(Color.BLACK);

        txtID = new JTextField();
        txtID.setPreferredSize(new Dimension(300, 35));
        txtID.setFont(txtID.getFont().deriveFont(Font.PLAIN, 20));

        btnSearchRental = new JButton("Tìm theo CCCD");
        btnSearchRental.setBackground(Color.WHITE);
        btnSearchRental.setForeground(Color.BLACK);
        btnSearchRental.setFont(btnSearchRental.getFont().deriveFont(Font.PLAIN, 17));
        btnSearchRental.setIcon(imgSearch);
        btnSearchRental.setFocusPainted(false);
        btnSearchRental.addActionListener(this);

        btnDeleteRental = new JButton("Xóa hóa đơn");
        btnDeleteRental.setBackground(Color.WHITE);
        btnDeleteRental.setForeground(Color.BLACK);
        btnDeleteRental.setFont(btnDeleteRental.getFont().deriveFont(Font.PLAIN, 17));
        btnDeleteRental.setIcon(imgDelete);
        btnDeleteRental.setFocusPainted(false);
        btnDeleteRental.addActionListener(this);

        pnBottom.add(lbID);
        pnBottom.add(txtID);
        pnBottom.add(btnSearchRental);
        pnBottom.add(btnDeleteRental);

        pnRental.add(pnTop, BorderLayout.CENTER);
        pnRental.add(pnBottom, BorderLayout.SOUTH);

        return pnRental;
    }
    private JPanel createHousePanel() {
        pnHouse = new JPanel();
        pnHouse.setLayout(new BorderLayout());
        pnHouse.setBackground(Color.WHITE);
        pnHouse.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());

        tbHouse = new JTable();
        tbHouse.setModel(tblHouse);
        listScroll = new JScrollPane();

        for (String i : colNames)
            tblHouse.addColumn(i);

        listScroll.setViewportView(tbHouse);
        showAllHouses();
        selectIndexHouseTable();

        pnTop.add(listScroll);

        pnBottom = new JPanel();
        pnBottom.setLayout(new BoxLayout(pnBottom,BoxLayout.Y_AXIS));

        pnInput1 = new JPanel();
        pnInput1.setLayout(new BoxLayout(pnInput1,BoxLayout.X_AXIS));
        pnInput1.setBackground(Color.WHITE);

        pnArea = new JPanel();
        pnArea.setLayout(new FlowLayout());
        lbArea = new JLabel("Diện tích: ");
        lbArea.setFont(lbArea.getFont().deriveFont(Font.PLAIN, 20));
        lbArea.setForeground(Color.BLACK);
        txtArea = new JTextField();
        txtArea.setPreferredSize(new Dimension(300, 35));
        txtArea.setFont(txtArea.getFont().deriveFont(Font.PLAIN, 20));
        pnArea.add(lbArea);
        pnArea.add(txtArea);

        pnFurniture = new JPanel();
        pnFurniture.setLayout(new FlowLayout());
        lbFurniture = new JLabel("Nội thất: ");
        lbFurniture.setFont(lbFurniture.getFont().deriveFont(Font.PLAIN, 20));
        lbFurniture.setForeground(Color.BLACK);
        txtFurniture = new JTextField();
        txtFurniture.setPreferredSize(new Dimension(300, 35));
        txtFurniture.setFont(txtFurniture.getFont().deriveFont(Font.PLAIN, 20));
        pnFurniture.add(lbFurniture);
        pnFurniture.add(txtFurniture);

        pnInput1.add(pnArea);
        pnInput1.add(pnFurniture);

        pnInput2 = new JPanel();
        pnInput2.setLayout(new BoxLayout(pnInput2,BoxLayout.X_AXIS));
        pnInput2.setBackground(Color.WHITE);

        pnRoomCost = new JPanel();
        pnRoomCost.setLayout(new FlowLayout());
        lbRoomCost = new JLabel("Giá phòng: ");
        lbRoomCost.setFont(lbRoomCost.getFont().deriveFont(Font.PLAIN, 20));
        lbRoomCost.setForeground(Color.BLACK);
        txtRoomCost = new JTextField();
        txtRoomCost.setPreferredSize(new Dimension(300, 35));
        txtRoomCost.setFont(txtRoomCost.getFont().deriveFont(Font.PLAIN, 20));
        pnRoomCost.add(lbRoomCost);
        pnRoomCost.add(txtRoomCost);

        pnStatus = new JPanel();
        pnStatus.setLayout(new FlowLayout());
        lbStatus = new JLabel("Trạng thái: ");
        lbStatus.setFont(lbStatus.getFont().deriveFont(Font.PLAIN, 20));
        lbStatus.setForeground(Color.BLACK);
        cbHouseStatus = new JComboBox<>();
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        model.addElement(0);
        model.addElement(1);
        cbHouseStatus.setModel(model);
        cbHouseStatus.setPreferredSize(new Dimension(300, 35));
        cbHouseStatus.setFont(cbHouseStatus.getFont().deriveFont(Font.PLAIN, 20));
        pnStatus.add(lbStatus);
        pnStatus.add(cbHouseStatus);

        pnInput2.add(pnRoomCost);
        pnInput2.add(pnStatus);

        pnBtn = new JPanel();
        pnBtn.setLayout(new FlowLayout());
        pnBtn.setBackground(Color.WHITE);

        JButton btnAdd = new JButton("Thêm phòng");
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setFont(btnAdd.getFont().deriveFont(Font.PLAIN, 17));
        btnAdd.setIcon(imgAdd);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);

        JButton btnUpdate = new JButton("Cập nhật phòng");
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setFont(btnUpdate.getFont().deriveFont(Font.PLAIN, 17));
        btnUpdate.setIcon(imgUpdata);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(this);

        JButton btnDelete = new JButton("Xóa phòng");
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFont(btnDelete.getFont().deriveFont(Font.PLAIN, 17));
        btnDelete.setIcon(imgDelete);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(this);

        JButton btnSearch = new JButton("Tìm theo diện tích");
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFont(btnSearch.getFont().deriveFont(Font.PLAIN, 17));
        btnSearch.setIcon(imgSearch);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);

        JButton btnArrArea = new JButton("Sắp xếp theo diện tích");
        btnArrArea.setBackground(Color.WHITE);
        btnArrArea.setForeground(Color.BLACK);
        btnArrArea.setFont(btnArrArea.getFont().deriveFont(Font.PLAIN, 17));
        btnArrArea.setIcon(imgSearch);
        btnArrArea.setFocusPainted(false);
        btnArrArea.addActionListener(this);

        pnBtn.add(btnAdd);
        pnBtn.add(btnUpdate);
        pnBtn.add(btnDelete);
        pnBtn.add(btnSearch);
        pnBtn.add(btnArrArea);

        pnBottom.add(pnInput1,BorderLayout.WEST);
        pnBottom.add(pnInput2,BorderLayout.CENTER);
        pnBottom.add(pnBtn,BorderLayout.SOUTH);

        pnHouse.add(pnTop, BorderLayout.CENTER);
        pnHouse.add(pnBottom, BorderLayout.SOUTH);

        return pnHouse;
    }
    private JPanel createTenantPanel() {
        pnTenant = new JPanel();
        pnTenant.setLayout(new BorderLayout());
        pnTenant.setBackground(Color.WHITE);
        pnTenant.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());

        tbTenant = new JTable();
        tbTenant.setModel(tblTenant);
        listScroll = new JScrollPane();

        for (String i : colNames)
            tblTenant.addColumn(i);

        listScroll.setViewportView(tbTenant);
        showAllTenants();
        selectIndexTenantTable();

        pnTop.add(listScroll);

        pnBottom = new JPanel();
        pnBottom.setLayout(new BorderLayout());

        pnInput1 = new JPanel();
        pnInput1.setLayout(new BoxLayout(pnInput1, BoxLayout.Y_AXIS));

        pnCCCD = new JPanel();
        pnCCCD.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnCCCD.setBackground(Color.WHITE);
        lbCCCD = new JLabel("CCCD");
        txtCCCD = new JTextField();
        txtCCCD.setPreferredSize(new Dimension(300, 35));
        txtCCCD.setFont(txtCCCD.getFont().deriveFont(Font.PLAIN, 25));
        pnCCCD.add(lbCCCD);
        pnCCCD.add(txtCCCD);

        pnName = new JPanel();
        pnName.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnName.setBackground(Color.WHITE);
        lbName = new JLabel("Tên");
        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(300, 35));
        txtName.setFont(txtName.getFont().deriveFont(Font.PLAIN, 25));
        pnName.add(lbName);
        pnName.add(txtName);

        pnDateOfBirth = new JPanel();
        pnDateOfBirth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnDateOfBirth.setBackground(Color.WHITE);
        lbDateOfBirth = new JLabel("Ngày sinh");
        txtDateOfBirth = new JTextField();
        txtDateOfBirth.setPreferredSize(new Dimension(300, 35));
        txtDateOfBirth.setFont(txtDateOfBirth.getFont().deriveFont(Font.PLAIN, 25));
        pnDateOfBirth.add(lbDateOfBirth);
        pnDateOfBirth.add(txtDateOfBirth);

        pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnEmail.setBackground(Color.WHITE);
        lbEmail = new JLabel("Email");
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(300, 35));
        txtEmail.setFont(txtEmail.getFont().deriveFont(Font.PLAIN, 25));
        pnEmail.add(lbEmail);
        pnEmail.add(txtEmail);

        pnInput1.add(pnCCCD);
        pnInput1.add(pnName);
        pnInput1.add(pnDateOfBirth);
        pnInput1.add(pnEmail);

        pnInput2 = new JPanel();
        pnInput2.setLayout(new BoxLayout(pnInput2, BoxLayout.Y_AXIS));
        pnInput2.setBackground(Color.WHITE);
        pnInput2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));

        pnStartDate = new JPanel();
        pnStartDate.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnStartDate.setBackground(Color.WHITE);
        lbStartDate = new JLabel("Ngày bắt đầu ở");
        txtStartDate = new JTextField();
        txtStartDate.setPreferredSize(new Dimension(300, 35));
        txtStartDate.setFont(txtStartDate.getFont().deriveFont(Font.PLAIN, 25));
        pnStartDate.add(lbStartDate);
        pnStartDate.add(txtStartDate);

        pnElectricityUsage = new JPanel();
        pnElectricityUsage.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnElectricityUsage.setBackground(Color.WHITE);
        lbElectricityUsage = new JLabel("Số điện tiêu thụ");
        txtElectricityUsage = new JTextField();
        txtElectricityUsage.setPreferredSize(new Dimension(300, 35));
        txtElectricityUsage.setFont(txtElectricityUsage.getFont().deriveFont(Font.PLAIN, 25));
        pnElectricityUsage.add(lbElectricityUsage);
        pnElectricityUsage.add(txtElectricityUsage);

        pnWaterUsage = new JPanel();
        pnWaterUsage.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnWaterUsage.setBackground(Color.WHITE);
        lbWaterUsage = new JLabel("Số nước tiêu thụ");
        txtWaterUsage = new JTextField();
        txtWaterUsage.setPreferredSize(new Dimension(300, 35));
        txtWaterUsage.setFont(txtWaterUsage.getFont().deriveFont(Font.PLAIN, 25));
        pnWaterUsage.add(lbWaterUsage);
        pnWaterUsage.add(txtWaterUsage);

        pnHouse_id = new JPanel();
        pnHouse_id.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnHouse_id.setBackground(Color.WHITE);
        lbHouse_id = new JLabel("Số phòng");
        List<Integer> houseIDs = houseC.getUnoccupiedHouseIDs();
        cbHouseID = new JComboBox<>();
        cbHouseID.setPreferredSize(new Dimension(300, 35));
        cbHouseID.setFont(cbHouseID.getFont().deriveFont(Font.PLAIN, 25));
        for (Integer i : houseIDs)
            cbHouseID.addItem(i);
        pnHouse_id.add(lbHouse_id);
        pnHouse_id.add(cbHouseID);

        pnBtn = new JPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.Y_AXIS));
        pnBtn.setBackground(Color.WHITE);

        btnAddTen = new JButton("Thêm");
        btnAddTen.setBackground(Color.WHITE);
        btnAddTen.setForeground(Color.BLACK);
        btnAddTen.setMaximumSize(new Dimension(175, 40));
        btnAddTen.setFont(btnAddTen.getFont().deriveFont(Font.PLAIN, 17));
        btnAddTen.setIcon(imgAdd);
        btnAddTen.setFocusPainted(false);
        btnAddTen.addActionListener(this);

        btnUpdateTen = new JButton("Sửa");
        btnUpdateTen.setBackground(Color.WHITE);
        btnUpdateTen.setForeground(Color.BLACK);
        btnUpdateTen.setMaximumSize(new Dimension(175, 40));
        btnUpdateTen.setFont(btnUpdateTen.getFont().deriveFont(Font.PLAIN, 17));
        btnUpdateTen.setIcon(imgUpdata);
        btnUpdateTen.setFocusPainted(false);
        btnUpdateTen.addActionListener(this);

        btnDeleteTen = new JButton("Xóa");
        btnDeleteTen.setBackground(Color.WHITE);
        btnDeleteTen.setForeground(Color.BLACK);
        btnDeleteTen.setMaximumSize(new Dimension(175, 40));
        btnDeleteTen.setFont(btnDeleteTen.getFont().deriveFont(Font.PLAIN, 17));
        btnDeleteTen.setIcon(imgDelete);
        btnDeleteTen.setFocusPainted(false);
        btnDeleteTen.addActionListener(this);

        btnSearchTen = new JButton("Tìm khách thuê");
        btnSearchTen.setBackground(Color.WHITE);
        btnSearchTen.setForeground(Color.BLACK);
        btnSearchTen.setMaximumSize(new Dimension(175, 40));
        btnSearchTen.setFont(btnSearchTen.getFont().deriveFont(Font.PLAIN, 17));
        btnSearchTen.setIcon(imgSearch);
        btnSearchTen.setFocusPainted(false);
        btnSearchTen.addActionListener(this);

        pnBtn.add(btnAddTen);
        pnBtn.add(btnUpdateTen);
        pnBtn.add(btnDeleteTen);
        pnBtn.add(btnSearchTen);

        pnInput2.add(pnStartDate);
        pnInput2.add(pnElectricityUsage);
        pnInput2.add(pnWaterUsage);
        pnInput2.add(pnHouse_id);

        pnBottom.add(pnInput1, BorderLayout.WEST);
        pnBottom.add(pnInput2, BorderLayout.CENTER);
        pnBottom.add(pnBtn, BorderLayout.EAST);

        pnTenant.add(pnTop, BorderLayout.CENTER);
        pnTenant.add(pnBottom, BorderLayout.SOUTH);

        return pnTenant;
    }
    private void showAllTenants() {
        // Lấy danh sách người thuê nhà từ TenantController
        TenantController tenantController = new TenantController();
        List<Tenant> tenants = tenC.getAllTenants();

        // Xóa dữ liệu hiện tại của tableModel
        tblTenant.setRowCount(0);

        // Thêm dữ liệu mới từ danh sách người thuê nhà
        for (Tenant tenant : tenants) {
            Object[] rowData = {tenant.getTenantId(), tenant.getName(), tenant.getDateOfBirth(), tenant.getEmail(),
                    tenant.getStartDate(), tenant.getElectricityUsage(), tenant.getWaterUsage(), tenant.getHouse_id()};
            tblTenant.addRow(rowData);
        }
    }
    private void showAllHouses() {
        HouseController houseController = new HouseController();
        List<House> houses = houseC.getAllHouses();

        // Xóa dữ liệu hiện tại của tableModel
        tblHouse.setRowCount(0);

        // Thêm dữ liệu mới từ danh sách các phòng
        for (House house : houses) {
            Object[] rowData = {
                    house.getHouseId(),
                    house.getArea(),
                    house.getElectricityCost(),
                    house.getWaterCost(),
                    house.getRoomCost(),
                    house.getFurniture(),
                    house.getHouse_status()
            };
            tblHouse.addRow(rowData);
        }
    }
    private void showAllRentals() {
        // Xóa tất cả các dòng hiện tại trong tableRentalModel
        tblRental.setRowCount(0);

        // Lấy danh sách tất cả các thông tin thuê nhà
        List<Rental> rentals = renC.getAllRentals();

        // Đổ dữ liệu vào tableRentalModel
        for (Rental rental : rentals) {
            Object[] rowData = {
                    rental.getRentalId(),
                    rental.getHouseId(),
                    rental.getTenantId(),
                    rental.getMonthlyPayment()
            };
            tblRental.addRow(rowData);
        }
    }
    private void selectIndexRentalTable() {
        ListSelectionModel selectionModel = tbRental.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbRental.getSelectedRow();
                if (selectedRow >= 0) {

                    String tenantId = tblRental.getValueAt(selectedRow, 2).toString();

                    // Hiển thị giá trị lên các JTextField tương ứng
                    txtID.setText(tenantId);


                    // Thực hiện các hành động khác liên quan đến việc chọn dòng trong bảng Rental ở đây
                    // Ví dụ: Hiển thị thông tin chi tiết, gọi hàm xử lý, v.v.
                }
            }
        });
    }
    private void selectIndexHouseTable() {
        ListSelectionModel selectionModel = tbHouse.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbHouse.getSelectedRow();
                if (selectedRow >= 0) {
                    txtArea.setText(tblHouse.getValueAt(selectedRow, 1).toString());
                    txtRoomCost.setText(tblHouse.getValueAt(selectedRow, 4).toString());
                    txtFurniture.setText(tblHouse.getValueAt(selectedRow, 5).toString());
                    cbHouseStatus.setSelectedItem(tblHouse.getValueAt(selectedRow, 6).toString());
                }
            }
        });
    }
    private int getSelectedRoomNumber() {
        int selectedRow = tbHouse.getSelectedRow();
        if (selectedRow >= 0) {
            Object value = tblHouse.getValueAt(selectedRow, 0);
            if (value instanceof Integer) {
                return (int) value;
            } else if (value instanceof String) {
                try {
                    return Integer.parseInt((String) value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1; // Trả về -1 nếu không có giá trị hợp lệ được chọn
    }
    private void  selectIndexTenantTable() {
        ListSelectionModel selectionModel = tbTenant.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Chỉ cho phép chọn một hàng

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbTenant.getSelectedRow();
                if (selectedRow >= 0) {
                    txtCCCD.setText(tblTenant.getValueAt(selectedRow, 0).toString());
                    txtName.setText(tblTenant.getValueAt(selectedRow, 1).toString());
                    txtDateOfBirth.setText(tblTenant.getValueAt(selectedRow, 2).toString());
                    txtEmail.setText(tblTenant.getValueAt(selectedRow, 3).toString());
                    txtStartDate.setText(tblTenant.getValueAt(selectedRow,4).toString());
                    txtElectricityUsage.setText(tblTenant.getValueAt(selectedRow,5).toString());
                    txtWaterUsage.setText(tblTenant.getValueAt(selectedRow,6).toString());
                }
            }
        });
    }
    public void updateHouseComboBox(JComboBox comboBox) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Integer> houseIDs = houseC.getUnoccupiedHouseIDs(); // Lấy danh sách house ID không được sử dụng

        for (Integer houseID : houseIDs) {
            model.addElement(houseID); // Thêm từng house ID vào model
        }

        comboBox.setModel(model); // Cập nhật lại model cho combox
    }
    private void displayRentalsInTable(List<Rental> rentals) {
        DefaultTableModel tableModel = (DefaultTableModel) tbRental.getModel();
        tableModel.setRowCount(0); // Xóa tất cả các dòng hiện tại trong table

        for (Rental rental : rentals) {
            Object[] rowData = {rental.getRentalId(), rental.getHouseId(), rental.getTenantId(), rental.getMonthlyPayment()};
            tableModel.addRow(rowData);
        }
    }
    public void displayTenantInfo(String keyword) {
        // Tìm kiếm tenant dựa trên keyword
        Tenant foundTenant = tenC.findTenantByIdOrEmail(keyword);

        if (foundTenant != null) {
            Object[] rowData = {
                    foundTenant.getTenantId(),
                    foundTenant.getName(),
                    foundTenant.getDateOfBirth(),
                    foundTenant.getEmail(),
                    foundTenant.getStartDate(),
                    foundTenant.getElectricityUsage(),
                    foundTenant.getWaterUsage(),
                    foundTenant.getHouse_id()
            };

            // Xóa dữ liệu hiện có trong tableTenantModel
            tblTenant.setRowCount(0);

            // Thêm dòng dữ liệu mới vào tableTenantModel
            tblTenant.addRow(rowData);
        } else {
            // Xử lý khi không tìm thấy tenant
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin cho " + keyword);
        }
    }
    public void clearTable(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    public void addTenant() {
        String tenant_id = txtCCCD.getText();
        String tenant_name = txtName.getText();
        String tenant_birthday = txtDateOfBirth.getText();
        String tenant_email = txtEmail.getText();
        String tenant_startDate = txtStartDate.getText();
        float tenant_electricUsage = Float.parseFloat(txtElectricityUsage.getText());
        float tenant_WaterUsage = Float.parseFloat(txtWaterUsage.getText());
        int house_id = (Integer) cbHouseID.getSelectedItem();

        if(tenant_id.isEmpty() || tenant_birthday.isEmpty() ||
                tenant_email.isEmpty() || tenant_startDate.isEmpty() ||
                tenant_name.isEmpty() || txtElectricityUsage.getText().isEmpty() || txtWaterUsage.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Các ô dữ liệu phải được điền hoặc chọn đầy đủ, số điện nước phải là số nguyên hoặc thập phân");
        } else {
            if(!DateCheck.isBirthday(tenant_birthday)||!DateCheck.isValidDate(tenant_startDate)) {
                JOptionPane.showMessageDialog(null,"Ngày sinh không được lớn hơn ngày hiện tại , ngày bắt đầu ở phải đúng định dạng dd/mm/yyyy và không đươc nhỏ hơn ngày hiện tại.Ví dụ: 20/01/2024");
            } else if(tenC.isTenantIdExists(tenant_id) || tenC.isEmailExists(tenant_email)) {
                JOptionPane.showMessageDialog(null,"email hoặc căn cước công dân không được trùng");
            } else if(!EmailCheck.isValidEmail(tenant_email)) {
                JOptionPane.showMessageDialog(null,"email phải đúng định dạng ví dụ tragiang@gmail.com");
            } else if(!EmailCheck.isNumericAndLength12(tenant_id)) {
                JOptionPane.showMessageDialog(null, "căn cước công dân phải là chuỗi 12 số ví dụ 056204011xxx");
            } else {
                Tenant tenantNew = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,house_id);
                boolean addTeant = tenC.addTenant(tenantNew);
                Rental rentalNew = new Rental(house_id,tenant_id);
                boolean rent=renC.rentHouse(rentalNew);
                boolean house= houseC.updateHouseStatus(house_id,1);
                boolean setCost = houseC.updateUtilityCost(house_id,tenant_electricUsage,tenant_WaterUsage);
                boolean rentalCheck = renC.updateMonthlyPaymentByHouseId(house_id);
                if(addTeant && setCost && rentalCheck) {
                    if(rent&&house) {
                        JOptionPane.showMessageDialog(null, "Thêm khách thuê thành công");
                        txtCCCD.setText("");
                        txtName.setText("");
                        txtDateOfBirth.setText("");
                        txtEmail.setText("");
                        txtStartDate.setText("");
                        txtElectricityUsage.setText("");
                        txtWaterUsage.setText("");
                        updateHouseComboBox(cbHouseID);
                        showAllTenants();
                        showAllHouses();
                        showAllRentals();
                    }
                }
            }
        }
    }
    //    Cập nhật nhà
    private void updateHouse() {
        int houseId = getSelectedRoomNumber();
        if(houseId<0) {
            JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng cần sửa từ table");
        } else {
            int houseLastStatus = houseC.getHouseStatusByHouseId(houseId);
            float area = Float.parseFloat(txtArea.getText());
            float roomcost = Float.parseFloat(txtRoomCost.getText());
            String furnished = txtFurniture.getText();
            int house_Status = (Integer) cbHouseStatus.getSelectedItem();
            if(houseLastStatus==house_Status) {
                House houseNew = new House(houseId,area,roomcost,furnished,house_Status);
                boolean updatehouse = houseC.updateHouse(houseNew);
                if(updatehouse) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    txtArea.setText("");
                    txtRoomCost.setText("");
                    txtFurniture.setText("");
                    showAllHouses();
                    updateHouseComboBox(cbHouseID);
                    boolean checkUpdatehouse = renC.updateMonthlyPaymentByHouseId(houseId);
                    if(checkUpdatehouse) {
                        System.out.println("okkkk");
                    }
                }
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn đang thực hiện cập nhật phòng ở trạng thái 0 tức là phòng trống nếu tiếp tục sẽ xoá người hiện tại đang thuê và tổng tiền thuê chọn No sẽ cập nhật thông tin khác trừ trạng thái nhà", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    boolean deleteRental = renC.deleteRentalByHouseId(houseId);
                    boolean deleteTenant = tenC.deleteTenantByHouseId(houseId);
                    if (deleteRental) {
                        if (deleteTenant) {
                            House houseNew = new House(houseId, area, roomcost, furnished, house_Status);
                            boolean updatehouse = houseC.updateHouse(houseNew);
                            if (updatehouse) {
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                txtArea.setText("");
                                txtRoomCost.setText("");
                                txtFurniture.setText("");
                                showAllHouses();
                                showAllTenants();
                                updateHouseComboBox(cbHouseID);
                            }
                        } else {
                            House houseNew = new House(houseId, area, roomcost, furnished, house_Status);
                            boolean updatehouse = houseC.updateHouse(houseNew);
                            if (updatehouse) {
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                txtArea.setText("");
                                txtRoomCost.setText("");
                                txtFurniture.setText("");
                                showAllHouses();
                                showAllTenants();
                                updateHouseComboBox(cbHouseID);
                            }
                        }
                    } else {
                        House houseNew = new House(houseId, area, roomcost, furnished, house_Status);
                        boolean updatehouse = houseC.updateHouse(houseNew);
                        if (updatehouse) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                            txtArea.setText("");
                            txtRoomCost.setText("");
                            txtFurniture.setText("");
                            showAllHouses();
                            showAllTenants();
                            updateHouseComboBox(cbHouseID);
                        }
                    }
                } else {
                    House houseNew = new House(houseId, area, roomcost, furnished, houseLastStatus);
                    boolean updatehouse = houseC.updateHouse(houseNew);
                    if (updatehouse) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                        txtArea.setText("");
                        txtRoomCost.setText("");
                        txtFurniture.setText("");
                        showAllHouses();
                        updateHouseComboBox(cbHouseID);
                    }
                }
            }
        }
    }
    public void updateTenant() {
        clearTable(tblTenant);
        String tenant_id = txtCCCD.getText();
        String tenant_name = txtName.getText();
        String tenant_birthday = txtDateOfBirth.getText();
        String tenant_email = txtEmail.getText();
        String tenant_startDate = txtStartDate.getText();
        float tenant_electricUsage = Float.parseFloat(txtElectricityUsage.getText());
        float tenant_WaterUsage = Float.parseFloat(txtWaterUsage.getText());
        int house_id = (Integer) cbHouseID.getSelectedItem();
        if(tenant_id.equals("")||tenant_birthday.equals("")||tenant_email.equals("")||tenant_startDate.equals("")||tenant_name.equals("")||txtElectricityUsage.getText().equals("")||txtWaterUsage.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Các ô dữ liệu phải được điền hoặc chọn đầy đủ, số điện nước phải là số nguyên hoặc thập phân");
        } else if(!EmailCheck.isValidEmail(tenant_email)) {
            JOptionPane.showMessageDialog(null,"email phải đúng định dạng ví dụ tragiang@gmail.com");
        }  else if(tenC.isTenantIdExists(tenant_id)) {
            if(EmailCheck.isValidEmail(tenant_email)) {
                int houseId = tenC.getHouseIdByTenantId(tenant_id);
                if(houseId!=house_id) {
                    int r = JOptionPane.showConfirmDialog(null, "Bạn có sự thay đổi về phòng? Lưu ý thay đổi này sẽ dẫn thời thay đổi về giá tổng giá trị tiền của khách ở phòng này.Nếu chỉ muốn thay đổi các thông tin khác hãy chọn NO", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if(r==JOptionPane.YES_OPTION) {
                        Tenant tenantNew = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,house_id);
                        boolean update = tenC.updateTenant(tenantNew);
                        boolean check = houseC.updateHouseStatus(houseId,0);
                        boolean setCostLast = houseC.updateUtilityCost(houseId,0,0);
                        boolean updateHouseStatus = houseC.updateHouseStatus(house_id,1);
                        float monthlyPay = houseC.calculateTotalRentCost(house_id);
                        boolean haveBill = renC.isTenantIdExists(tenant_id);
                        if(check && updateHouseStatus) {
                            if(update && setCostLast) {
                                boolean updateRental = renC.updateHouseIdByTenantId(tenant_id,house_id);
                                if(updateRental && haveBill) {
                                    boolean updatePayment = renC.updateMonthlyPaymentByTenantId(tenant_id,monthlyPay);
                                    if(updatePayment)
                                    {
                                        System.out.println("có bill");
                                    }
                                    JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                    txtCCCD.setText("");
                                    txtName.setText("");
                                    txtDateOfBirth.setText("");
                                    txtEmail.setText("");
                                    txtStartDate.setText("");
                                    txtElectricityUsage.setText("");
                                    txtWaterUsage.setText("");
                                    updateHouseComboBox(cbHouseID);
                                    showAllTenants();
                                    showAllHouses();
                                    boolean setCost = houseC.updateUtilityCost(house_id,tenant_electricUsage,tenant_WaterUsage);
                                    if(setCost) {
                                        System.out.println("successful!=}}}");
                                        showAllHouses();
                                    }
                                } else {
                                    Rental rentalNew = new Rental(house_id,tenant_id);
                                    boolean insertBill = renC.rentHouse(rentalNew);
                                    if(insertBill) {
                                        boolean updatePayment = renC.updateMonthlyPaymentByTenantId(tenant_id,monthlyPay);
                                        if(updatePayment) {
                                            System.out.println("thêm bill");
                                        }
                                        JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                        txtCCCD.setText("");
                                        txtName.setText("");
                                        txtDateOfBirth.setText("");
                                        txtEmail.setText("");
                                        txtElectricityUsage.setText("");
                                        txtStartDate.setText("");
                                        txtWaterUsage.setText("");
                                        updateHouseComboBox(cbHouseID);
                                        showAllTenants();
                                        showAllHouses();
                                        boolean setCost = houseC.updateUtilityCost(house_id,tenant_electricUsage,tenant_WaterUsage);
                                        if(setCost) {
                                            System.out.println("successful!");
                                            showAllHouses();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        Tenant tenantUpdate = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,houseId);
                        boolean updateTenant = tenC.updateTenant(tenantUpdate);
                        boolean checkCost = houseC.updateUtilityCost(houseId,tenant_electricUsage,tenant_WaterUsage);
                        float monthlyPayment = houseC.calculateTotalRentCost(houseId);
                        boolean bill = renC.isTenantIdExists(tenant_id);
                        if(bill) {
                            boolean UpdatePayment = renC.updateMonthlyPaymentByTenantId(tenant_id,monthlyPayment);
                            if(UpdatePayment) {
                                if(updateTenant && checkCost) {
                                    JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                    txtCCCD.setText("");
                                    txtName.setText("");
                                    txtDateOfBirth.setText("");
                                    txtEmail.setText("");
                                    txtElectricityUsage.setText("");
                                    txtStartDate.setText("");
                                    txtWaterUsage.setText("");
                                    updateHouseComboBox(cbHouseID);
                                    showAllTenants();
                                    showAllHouses();
                                    showAllRentals();
                                }
                            }
                        } else {
                            Rental createBill = new Rental(houseId,tenant_id);
                            boolean CreateBill = renC.rentHouse(createBill);
                            if(CreateBill) {
                                boolean UpdatePayment = renC.updateMonthlyPaymentByTenantId(tenant_id,monthlyPayment);
                                if(UpdatePayment) {
                                    if(updateTenant && checkCost) {
                                        JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                        txtCCCD.setText("");
                                        txtName.setText("");
                                        txtDateOfBirth.setText("");
                                        txtEmail.setText("");
                                        txtElectricityUsage.setText("");
                                        txtWaterUsage.setText("");
                                        txtStartDate.setText("");
                                        updateHouseComboBox(cbHouseID);
                                        showAllTenants();
                                        showAllHouses();
                                        showAllRentals();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "email sai định dạng vui lòng sửa lại");
                txtEmail.setText("");
            }
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Chưa tồn tại người dùng này. Bạn có muốn thêm mới người dùng?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                addTenant(); // Thêm mới người dùng
            } else {
                txtCCCD.setText("");
                txtName.setText("");
                txtDateOfBirth.setText("");
                txtEmail.setText("");
                txtElectricityUsage.setText("");
                txtWaterUsage.setText("");
                txtStartDate.setText("");
                updateHouseComboBox(cbHouseID);
                showAllTenants();
            }


        }
    }
    public static int[] getNumbersSeparatedByComma(String input) {
        try {
            String[] parts = input.split(",");
            int[] numbers = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                numbers[i] = Integer.parseInt(parts[i].trim());
            }

            return numbers;
        } catch (NumberFormatException | NullPointerException e) {
            // Xử lý nếu có lỗi chuyển đổi hoặc chuỗi là null
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTenant) {
            cardLayout.show(pnRight, "Tenant");
        }
        if (e.getSource() == btnRental) {
            cardLayout.show(pnRight, "Rental");
        }
        if (e.getSource() == btnHouse) {
            cardLayout.show(pnRight, "House");
        }
        if (e.getSource() == btnLogout) {
            this.dispose();
            Login login = new Login("Welcome to lLgin");
            login.showView();
        }

        if (e.getSource() == btnAddTen) {
            clearTable(tblTenant);
            addTenant();
            showAllHouses();
        }
        if (e.getSource() == btnUpdateTen) {
            updateTenant();
        }
        if (e.getSource() == btnDeleteTen) {
            clearTable(tblTenant);
            String tenantId = txtCCCD.getText();
            if (txtCCCD.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 người thuê nhà từ table hoặc nhập từ bàn phím");
            } else if (tenC.isTenantIdExists(txtCCCD.getText())) {
                int houseId = tenC.getHouseIdByTenantId(tenantId);
                boolean setCost = houseC.updateUtilityCost(houseId, 0, 0);
                boolean check = houseC.updateHouseStatus(houseId, 0);
                boolean rent = renC.deleteRentalByTenantId(tenantId);
                boolean delete = tenC.deleteTenant(tenantId);
                if (rent) {
                    if (check && setCost) {
                        if (delete) {
                            JOptionPane.showMessageDialog(null, "Xoá thành công");
                            txtCCCD.setText("");
                            txtName.setText("");
                            txtDateOfBirth.setText("");
                            txtEmail.setText("");
                            txtStartDate.setText("");
                            txtElectricityUsage.setText("");
                            txtWaterUsage.setText("");
                            updateHouseComboBox(cbHouseID);
                            showAllTenants();
                            showAllHouses();
                        }


                    }
                } else {
                    System.out.println("sai gì ở đây");
                }
            } else {
                JOptionPane.showMessageDialog(null, "không tồn tại CCCD:  " + tenantId);
            }
        }
        if (e.getSource() == btnSearchTen) {
            String tenant_id = txtCCCD.getText();
            String tenant_email = txtEmail.getText();
            if(tenant_id.equals("")&&tenant_email.equals("")) {
                JOptionPane.showMessageDialog(null, "Điền ID hoặc email của người thuê nhà để thực hiện tìm kiếm");
            } else {
                if(tenant_id.equals("")) {
                    displayTenantInfo(tenant_email);
                } else {
                    displayTenantInfo(tenant_id);
                }
            }
        }

        if (e.getSource() == btnAddHouse) {
            float  area = Float.parseFloat(txtArea.getText());
            float room_cost = Float.parseFloat(txtRoomCost.getText());
            String Furniture = txtFurniture.getText();
            int house_status = (Integer) cbHouseStatus.getSelectedItem();

            if(txtArea.getText().equals("")||txtRoomCost.getText().equals("")||Furniture.equals("")) {
                JOptionPane.showMessageDialog(null,"điền đầy đủ thông tin về phòng trọ");
            } else {
                House houseNew = new House(area,room_cost,Furniture,house_status);
                boolean addHouse = houseC.addHouse(houseNew);
                if(addHouse) {
                    JOptionPane.showMessageDialog(null,"Thêm phòng thành công");
                    txtArea.setText("");
                    txtRoomCost.setText("");
                    txtFurniture.setText("");
                    showAllHouses();
                    updateHouseComboBox(cbHouseStatus);
                } else {
                    JOptionPane.showMessageDialog(null,"Đã có lỗi xảy ra");
                }
            }
        }
        if (e.getSource() == btnDeleteHouse) {
            int houseId = getSelectedRoomNumber();
            if(houseId<0) {
                JOptionPane.showMessageDialog(null,"Chọn phòng cần xoá từ table");
            } else {
                boolean deleteRental = renC.deleteRentalByHouseId(houseId);
                if(deleteRental) {
                    boolean deleteTenant = tenC.deleteTenantByHouseId(houseId);
                    if(deleteTenant) {
                        boolean deleteRoom = houseC.deleteHouseById(houseId);
                        if(deleteRoom) {
                            JOptionPane.showMessageDialog(null,"xoá thành công");
                            showAllHouses();
                            System.out.println("Đã xoá thành công");
                            showAllTenants();
                            updateHouseComboBox(cbHouseID);
                        }
                    } else {
                        boolean deleteRoom = houseC.deleteHouseById(houseId);
                        if(deleteRoom) {
                            JOptionPane.showMessageDialog(null,"xoá thành công");
                        }
                        showAllHouses();
                        showAllTenants();
                        updateHouseComboBox(cbHouseID);
                    }
                } else {
                    boolean deleteTenant = tenC.deleteTenantByHouseId(houseId);
                    if(deleteTenant) {
                        boolean deleteRoom = houseC.deleteHouseById(houseId);
                        if(deleteRoom) {
                            JOptionPane.showMessageDialog(null,"xoá thành công");
                            showAllHouses();
                            showAllTenants();
                            updateHouseComboBox(cbHouseID);
                        }
                    } else {
                        boolean deleteRoom = houseC.deleteHouseById(houseId);
                        if(deleteRoom) {
                            JOptionPane.showMessageDialog(null,"xoá thành công");
                            showAllHouses();
                            showAllTenants();
                            updateHouseComboBox(cbHouseID);
                        }
                    }
                }
            }
        }
        if (e.getSource() == btnUpdateHouse) {
            updateHouse();
        }
        if (e.getSource() == btnSearchHouse) {
            String area = txtArea.getText();
            if(area.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập khoảng diện tích cần tìm các số cách nhau bởi dấu , ví dụ 20,40");
            } else {
                int[] areaNumbers = getNumbersSeparatedByComma(area);
                if(areaNumbers!=null&& areaNumbers.length==2) {
                    float minArea = areaNumbers[0];
                    float maxArea = areaNumbers[1];
                    List<House> filteredHouses = houseC.findHousesByArea(minArea,maxArea);
                    tblHouse.setRowCount(0);
                    for (House house : filteredHouses) {
                        Object[] rowData = {
                                house.getHouseId(),
                                house.getArea(),
                                house.getElectricityCost(),
                                house.getWaterCost(),
                                house.getRoomCost(),
                                house.getFurniture(),
                                house.getHouse_status()
                        };
                        tblHouse.addRow(rowData);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập khoảng diện tích cần tìm các số cách nhau bởi dấu , ví dụ 20,40");
                }
            }
        }
        if (e.getSource() == btnArrAreaHouse) {
            List<House> sortedHouses = houseC.getSortedHousesByArea();
            // Xóa dữ liệu cũ trong bảng
            tblHouse.setRowCount(0);
            // Thêm dữ liệu mới vào bảng
            for (House house : sortedHouses) {
                Object[] rowData = {
                        house.getHouseId(),
                        house.getArea(),
                        house.getElectricityCost(),
                        house.getWaterCost(),
                        house.getRoomCost(),
                        house.getFurniture(),
                        house.getHouse_status()
                };
                tblHouse.addRow(rowData);
            }
        }

        if (e.getSource() == btnSearchRental) {
            String tenantId = txtID.getText(); // Lấy tenant_id từ JTextField txtID
            // Gọi controller để tìm các rentals theo tenant_id
            RentalController rentalController = new RentalController();
            List<Rental> rentals = renC.findRentalsByTenantId(tenantId);

            // Hiển thị kết quả lên tableRental
            displayRentalsInTable(rentals);
        }
        if (e.getSource() == btnDeleteRental) {
            String tenant_id = txtID.getText();
            if(tenant_id.equals("")) {
                JOptionPane.showMessageDialog(null,"vui lòng điền căn cước của người thuê nhà");
            } else {
                boolean tenantUpdate = tenC.updateElectricWaterUsageToZero(tenant_id);
                int house_id = tenC.getHouseIdByTenantId(tenant_id);
                boolean houseUpdate = houseC.updateUtilityCost(house_id,0,0);
                if(tenantUpdate && houseUpdate) {
                    boolean deleteRental = renC.deleteRentalByTenantId(tenant_id);
                    if(deleteRental) {
                        txtID.setText("");
                        JOptionPane.showMessageDialog(null,"Xoá bill thành công tiền điện nước và số điện nước sử dụng sẽ về 0");
                        showAllHouses();
                        showAllTenants();
                        showAllRentals();
                    }
                }
            }
        }
    }
}
