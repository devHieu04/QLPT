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

public class AdminView extends JFrame {
    private JPanel leftPanel;
    private JTable tenantTable , houseTable;
    private DefaultTableModel tableTenantModel,tabelHouseModel;

    private JPanel rightPanel;
    JComboBox<Integer> houseIDComboBox;
    JComboBox<Integer> houseStatusComboBox;
    private CardLayout cardLayout;
    private JButton tentButton , rentalButton, houseButton;
    private JTextField txtTenantID, txtTenantName , txtTenantEmail,txtDateofBirth , txtDateStart,txtElectricUsage,txtWaterUsage;
    private JTextField txtArea , txtRoomcost, txtFurniture;

    private JButton btnAddTenant , btnDeleteTenant , btnUpdateTenant,btnSearchTenant;
    private JButton btnAddHouse , btnDeleteHouse , btnUpdateHouse,btnSearchHouse,btnArrAreaHouse;
    TenantController tenantController = new TenantController();
    HouseController houseController = new HouseController();
    EmailCheck emailCheck = new EmailCheck();
    RentalController rentalController = new RentalController();
    public AdminView(String s)
    {
        super(s);
        addView();
        addEvent();

    }

    public void showView()
    {
        this.setSize(1300,700);
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
        JPanel panelMainHouse = new JPanel();
        panelMainHouse.setLayout(new BorderLayout());
        String[] columnNames = {"Số phòng","Diện tích","Tiền điện", "Tiền nước","Giá phòng","Nội thất","Trạng thái"};
        tabelHouseModel = new DefaultTableModel(columnNames,0);
        houseTable=new JTable(tabelHouseModel);
        houseTable.setShowGrid(true);
        houseTable.setShowVerticalLines(true);
        JScrollPane scrollPane = new JScrollPane(houseTable);
        panelMainHouse.add(scrollPane,BorderLayout.CENTER);
        showAllHouses();
        selectIndexHouseTable();

        JPanel panelBottom = new JPanel();
        panelMainHouse.add(panelBottom,BorderLayout.SOUTH);
        panelBottom.setLayout(new BorderLayout());
        JPanel panelBottomCenter = new JPanel();
        panelBottom.add(panelBottomCenter,BorderLayout.CENTER);
        panelBottomCenter.setLayout(new BoxLayout(panelBottomCenter,BoxLayout.X_AXIS));

        JPanel panelInputC1 = new JPanel();
        panelInputC1.setLayout(new BoxLayout(panelInputC1,BoxLayout.Y_AXIS));
        panelBottomCenter.add(panelInputC1);

        JPanel panelArea = new JPanel();
        panelArea.setLayout(new FlowLayout());
        JLabel lbArea = new JLabel("Diện tích");
        txtArea = new JTextField();
        txtArea.setPreferredSize(new Dimension(300,35));
        panelArea.add(lbArea);
        panelArea.add(txtArea);
        panelInputC1.add(panelArea);

        JPanel panelRoomCost = new JPanel();
        panelRoomCost.setLayout(new FlowLayout());
        JLabel lbRoomCost = new JLabel("Giá phòng");
        txtRoomcost = new JTextField();
        txtRoomcost.setPreferredSize(new Dimension(300,35));
        panelRoomCost.add(lbRoomCost);
        panelRoomCost.add(txtRoomcost);
        panelInputC1.add(panelRoomCost);
        lbArea.setPreferredSize(lbRoomCost.getPreferredSize());

        JPanel panelInputC2 = new JPanel();
        panelBottomCenter.add(panelInputC2);
        panelInputC2.setLayout(new BoxLayout(panelInputC2,BoxLayout.Y_AXIS));

        JPanel panelFurniture = new JPanel();
        panelFurniture.setLayout(new FlowLayout());
        JLabel lbFurniture = new JLabel("Nội thất");
        panelFurniture.add(lbFurniture);
        txtFurniture = new JTextField();
        txtFurniture.setPreferredSize(new Dimension(300,35));
        panelFurniture.add(txtFurniture);
        panelInputC2.add(panelFurniture);

        JPanel panelHouseStatus = new JPanel();
        panelHouseStatus.setLayout(new FlowLayout());
        JLabel lbHouseStatus = new JLabel("Trạng thái");
        panelHouseStatus.add(lbHouseStatus);
        houseStatusComboBox = new JComboBox<>();
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        model.addElement(0);
        model.addElement(1);
        houseStatusComboBox.setModel(model);
        houseStatusComboBox.setPreferredSize(new Dimension(300,35));
        panelHouseStatus.add(houseStatusComboBox);
        panelInputC2.add(panelHouseStatus);
        lbFurniture.setPreferredSize(lbHouseStatus.getPreferredSize());

        // Các nút chức năng của House

        JPanel panelBottomSouth = new JPanel();
        panelBottom.add(panelBottomSouth,BorderLayout.SOUTH);
        panelBottomSouth.setLayout(new FlowLayout());
        btnAddHouse = new JButton("Thêm phòng");
        btnAddHouse.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/addhouse.png")));
        btnAddHouse.setPreferredSize(new Dimension(200,40));
        panelBottomSouth.add(btnAddHouse);
        btnUpdateHouse = new JButton("Cập Nhật");
        btnUpdateHouse.setPreferredSize(new Dimension(200,40));
        btnUpdateHouse.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/updatehouse.png")));
        panelBottomSouth.add(btnUpdateHouse);
        btnDeleteHouse = new JButton("Xoá phòng");
        btnDeleteHouse.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/deletehouse.png")));
        btnDeleteHouse.setPreferredSize(new Dimension(200,40));
        panelBottomSouth.add(btnDeleteHouse);
        btnSearchHouse = new JButton("Tìm phòng");
        btnSearchHouse.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/searchhouse.png")));
        btnSearchHouse.setPreferredSize(new Dimension(200,40));
        panelBottomSouth.add(btnSearchHouse);
        btnArrAreaHouse = new JButton("Sắp xếp theo diện tích");
        btnArrAreaHouse.setPreferredSize(new Dimension(200,40));
        btnArrAreaHouse.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/arrhouse.png")));
        panelBottomSouth.add(btnArrAreaHouse);






        return panelMainHouse;
    }


    private JPanel createUserPanel() {
        JPanel panelTenant = new JPanel();
        panelTenant.setLayout(new BorderLayout());
        String[] columnNames = {"CCCD", "Tên", "ngày sinh", "email", "ngày bắt đầu ở", "số điện tiêu tụ", "số nước tiêu thụ", "số phòng"};
        tableTenantModel = new DefaultTableModel(columnNames, 0);
        tenantTable = new JTable(tableTenantModel);
        tenantTable.setShowGrid(true);
        tenantTable.setShowVerticalLines(true);
        JScrollPane scrollPane = new JScrollPane(tenantTable);
        panelTenant.add(scrollPane, BorderLayout.CENTER);
        showAllTenants();
        selectIndexTenantTable();

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new BorderLayout());
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        JPanel panelInputID = new JPanel();
        panelInputID.setLayout(new FlowLayout());
        JLabel lbTenantID = new JLabel("CCCD");
        panelInputID.add(lbTenantID);
        txtTenantID = new JTextField();
        txtTenantID.setPreferredSize(new Dimension(300,35));
        panelInputID.add(txtTenantID);
        panelWest.add(panelInputID);

        JPanel panelTenantName = new JPanel();
        panelTenantName.setLayout(new FlowLayout());
        JLabel lbTenantName = new JLabel("Tên   ");
        panelTenantName.add(lbTenantName);
        txtTenantName  = new JTextField();
        txtTenantName.setPreferredSize(new Dimension(300,35));
        panelTenantName.add(txtTenantName);
        panelWest.add(panelTenantName);

        JPanel panelTenantBirthDay = new JPanel();
        panelTenantBirthDay.setLayout(new FlowLayout());
        JLabel lbBirthDay = new JLabel("Ngày sinh");
        panelTenantBirthDay.add(lbBirthDay);
        txtDateofBirth = new JTextField();
        txtDateofBirth.setPreferredSize(new Dimension(300,35));
        panelTenantBirthDay.add(txtDateofBirth);
        panelWest.add(panelTenantBirthDay);

        JPanel panelTenantEmail = new JPanel();
        panelTenantEmail.setLayout(new FlowLayout());
        JLabel lbTenantEmail = new JLabel("Email");
        panelTenantEmail.add(lbTenantEmail);
        txtTenantEmail= new JTextField();
        txtTenantEmail.setPreferredSize(new Dimension(300,35));
        panelTenantEmail.add(txtTenantEmail);
        panelWest.add(panelTenantEmail);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        JPanel panelStartDate = new JPanel();
        panelStartDate.setLayout(new FlowLayout() );
        JLabel lbStartDate = new JLabel("Ngày ở");
        panelStartDate.add(lbStartDate);
        txtDateStart = new JTextField();
        txtDateStart.setPreferredSize(new Dimension(300,35));
        panelStartDate.add(txtDateStart);
        panelCenter.add(panelStartDate);

        JPanel panelElectricityUsage = new JPanel();
        panelElectricityUsage.setLayout(new FlowLayout());
        JLabel lbElectricityUsage = new JLabel("Số điện sử dụng");
        panelElectricityUsage.add(lbElectricityUsage);
        txtElectricUsage=new JTextField();
        txtElectricUsage.setPreferredSize(new Dimension(300,35));
        panelElectricityUsage.add(txtElectricUsage);
        panelCenter.add(panelElectricityUsage);

        JPanel panelWaterUsage = new JPanel();
        panelWaterUsage.setLayout(new FlowLayout());
        JLabel lbWaterUsage = new JLabel("Số nước sử dụng");
        panelWaterUsage.add(lbWaterUsage);
        txtWaterUsage=new JTextField();
        txtWaterUsage.setPreferredSize(new Dimension(300,35));
        panelWaterUsage.add(txtWaterUsage);
        panelCenter.add(panelWaterUsage);

        JPanel panelHouseID= new JPanel();
        panelHouseID.setLayout(new FlowLayout());
        JLabel lbHouseId = new JLabel("Số Phòng");
        panelHouseID.add(lbHouseId);
        HouseController houseController = new HouseController();
        List<Integer> houseIDs = houseController.getUnoccupiedHouseIDs();
        houseIDComboBox = new JComboBox<>();
        houseIDComboBox.setPreferredSize(new Dimension(300,35));
        for (Integer houseID:houseIDs)
        {
            houseIDComboBox.addItem(houseID);
        }
        panelHouseID.add(houseIDComboBox);
        panelCenter.add(panelHouseID);

        JPanel panelEast = new JPanel();
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        btnAddTenant = new JButton("Thêm khách ");
        btnAddTenant.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/addtenant.png")));
        btnAddTenant.setPreferredSize(new Dimension(250,40));
        panelEast.add(btnAddTenant);
        btnUpdateTenant=new JButton("Sửa khách thuê");
        btnUpdateTenant.setPreferredSize(new Dimension(250,40));
        btnUpdateTenant.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/updatetenant.png")));
        panelEast.add(btnUpdateTenant);
        btnDeleteTenant=new JButton("Xoá người thuê");
        btnDeleteTenant.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/deletetenant.png")));
        btnDeleteTenant.setPreferredSize(new Dimension(250,40));
        panelEast.add(btnDeleteTenant);
        btnSearchTenant = new JButton("Tìm khách thuê");
        btnSearchTenant.setIcon(new ImageIcon(ClassLoader.getSystemResource("Icon/searchtenant.png")));
        btnSearchTenant.setPreferredSize(new Dimension(200,40));
        panelEast.add(btnSearchTenant);


        lbTenantName.setPreferredSize(lbBirthDay.getPreferredSize());
        lbTenantID.setPreferredSize(lbBirthDay.getPreferredSize());
        lbTenantEmail.setPreferredSize(lbBirthDay.getPreferredSize());

        lbStartDate.setPreferredSize(lbWaterUsage.getPreferredSize());
        lbElectricityUsage.setPreferredSize(lbWaterUsage.getPreferredSize());
        lbHouseId.setPreferredSize(lbWaterUsage.getPreferredSize());
        panelBottom.add(panelWest,BorderLayout.WEST);
        panelBottom.add(panelCenter,BorderLayout.CENTER);
        panelBottom.add(panelEast,BorderLayout.EAST);
        panelTenant.add(panelBottom,BorderLayout.SOUTH);


        return panelTenant;
    }
    private void showAllTenants() {
        // Lấy danh sách người thuê nhà từ TenantController
        TenantController tenantController = new TenantController();
        List<Tenant> tenants = tenantController.getAllTenants();

        // Xóa dữ liệu hiện tại của tableModel
        tableTenantModel.setRowCount(0);

        // Thêm dữ liệu mới từ danh sách người thuê nhà
        for (Tenant tenant : tenants) {
            Object[] rowData = {tenant.getTenantId(), tenant.getName(), tenant.getDateOfBirth(), tenant.getEmail(),
                    tenant.getStartDate(), tenant.getElectricityUsage(), tenant.getWaterUsage(), tenant.getHouse_id()};
            tableTenantModel.addRow(rowData);
        }
    }
    // Trong lớp AdminView hoặc lớp tương tự

    public void showAllHouses() {
        HouseController houseController = new HouseController();
        List<House> houses = houseController.getAllHouses();

        // Xóa dữ liệu hiện tại của tableModel
        tabelHouseModel.setRowCount(0);

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
            tabelHouseModel.addRow(rowData);
        }
    }


    private void selectIndexHouseTable() {
        ListSelectionModel selectionModel = houseTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = houseTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String houseNumber = tabelHouseModel.getValueAt(selectedRow, 0).toString();
                    String area = tabelHouseModel.getValueAt(selectedRow, 1).toString();
                    String electricityCost = tabelHouseModel.getValueAt(selectedRow, 2).toString();
                    String waterCost = tabelHouseModel.getValueAt(selectedRow, 3).toString();
                    String rentCost = tabelHouseModel.getValueAt(selectedRow, 4).toString();
                    String furniture = tabelHouseModel.getValueAt(selectedRow, 5).toString();
                    String status = tabelHouseModel.getValueAt(selectedRow, 6).toString();

                    txtArea.setText(area);
                    txtRoomcost.setText(rentCost);
                    txtFurniture.setText(furniture);
                    houseStatusComboBox.setSelectedItem(status);
                }
            }
        });
    }
    private int getSelectedRoomNumber() {
        int selectedRow = houseTable.getSelectedRow();
        if (selectedRow >= 0) {
            Object value = tabelHouseModel.getValueAt(selectedRow, 0);
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


    private void  selectIndexTenantTable()
    {
        ListSelectionModel selectionModel = tenantTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Chỉ cho phép chọn một hàng

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tenantTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String tenantID = tableTenantModel.getValueAt(selectedRow, 0).toString();
                    String tenantName = tableTenantModel.getValueAt(selectedRow, 1).toString();
                    String dateOfBirth = tableTenantModel.getValueAt(selectedRow, 2).toString();
                    String tenantEmail = tableTenantModel.getValueAt(selectedRow, 3).toString();
                    String tenantStartDate = tableTenantModel.getValueAt(selectedRow,4).toString();
                    String tenantElectricUsage = tableTenantModel.getValueAt(selectedRow,5).toString();
                    String tenantWaterUsage = tableTenantModel.getValueAt(selectedRow,6).toString();
                    txtTenantID.setText(tenantID);
                    txtTenantName.setText(tenantName);
                    txtDateofBirth.setText(dateOfBirth);
                    txtTenantEmail.setText(tenantEmail);
                    txtDateStart.setText(tenantStartDate);
                    txtElectricUsage.setText(tenantElectricUsage);
                    txtWaterUsage.setText(tenantWaterUsage);

                }
            }
        });
    }

    public void updateHouseComboBox(JComboBox comboBox) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Integer> houseIDs = houseController.getUnoccupiedHouseIDs(); // Lấy danh sách house ID không được sử dụng

        for (Integer houseID : houseIDs) {
            model.addElement(houseID); // Thêm từng house ID vào model
        }

        comboBox.setModel(model); // Cập nhật lại model cho combox
    }


    private void addEvent() {
        tentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "Tenant");
                showAllTenants();
            }
        });
        rentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel,"Rental");
            }
        });
        houseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel,"House");
            }
        });

        btnAddTenant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(tableTenantModel);
                addTenant();
            }
        });

        btnDeleteTenant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(tableTenantModel);
                String tenantId = txtTenantID.getText();
                if(txtTenantID.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn 1 người thuê nhà từ table hoặc nhập từ bàn phím");
                }
                else if(tenantController.isTenantIdExists(txtTenantID.getText()))
                {
                    int houseId=tenantController.getHouseIdByTenantId(tenantId);
                    boolean check = houseController.updateHouseStatus(houseId,0);
                    boolean rent = rentalController.deleteRentalByTenantId(tenantId);
                    boolean delete = tenantController.deleteTenant(tenantId);

                    if(rent)
                    {
                        if(check)
                        {

                            if(delete){
                                JOptionPane.showMessageDialog(null,"Xoá thành công");
                                txtTenantID.setText("");
                                txtTenantName.setText("");
                                txtDateofBirth.setText("");
                                txtElectricUsage.setText("");
                                txtDateStart.setText("");
                                txtTenantEmail.setText("");
                                txtWaterUsage.setText("");
                                updateHouseComboBox(houseIDComboBox);
                                showAllTenants();
                            }


                        }
                    }else
                    {
                        System.out.println("sai gì ở đây");
                    }
                }
                else
                {
                 JOptionPane.showMessageDialog(null, "không tồn tại CCCD:  "+tenantId);
                }

            }
        });
        btnUpdateTenant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(tableTenantModel);

                String tenant_id = txtTenantID.getText();
                String tenant_name = txtTenantName.getText();
                String tenant_birthday = txtDateofBirth.getText();
                String tenant_email = txtTenantEmail.getText();
                String tenant_startDate = txtDateStart.getText();
                float tenant_electricUsage = Float.parseFloat(txtElectricUsage.getText());
                float tenant_WaterUsage = Float.parseFloat(txtWaterUsage.getText());
                int house_id = (Integer) houseIDComboBox.getSelectedItem();
                if(tenant_id.equals("")||tenant_birthday.equals("")||tenant_email.equals("")||tenant_startDate.equals("")||tenant_name.equals("")||txtElectricUsage.getText().equals("")||txtWaterUsage.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Các ô dữ liệu phải được điền hoặc chọn đầy đủ, số điện nước phải là số nguyên hoặc thập phân");
                }else if(!emailCheck.isValidEmail(tenant_email))
                {
                    JOptionPane.showMessageDialog(null,"email phải đúng định dạng ví dụ tragiang@gmail.com");
                }
                else if(tenantController.isTenantIdExists(tenant_id))
                {
                    if(emailCheck.isValidEmail(tenant_email))
                    {
                        int houseId=tenantController.getHouseIdByTenantId(tenant_id);
                        if(houseId!=house_id)
                        {
                            int r = JOptionPane.showConfirmDialog(null, "Bạn có sự thay đổi về phòng? Lưu ý thay đổi này sẽ dẫn thời thay đổi về giá tổng giá trị tiền của khách ở phòng này.Nếu chỉ muốn thay đổi các thông tin khác hãy chọn NO", "Thông báo", JOptionPane.YES_NO_OPTION);
                            if(r==JOptionPane.YES_OPTION)
                            {
                                Tenant tenantNew = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,house_id);
                                boolean update = tenantController.updateTenant(tenantNew);
                                boolean check = houseController.updateHouseStatus(houseId,0);
                                boolean updateHouseStatus = houseController.updateHouseStatus(house_id,1);
                                if(check && updateHouseStatus)
                                {
                                    if(update)
                                    {
                                        boolean updateRental = rentalController.updateHouseIdByTenantId(tenant_id,house_id);
                                        if(updateRental)
                                        {
                                            JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                            txtTenantID.setText("");
                                            txtTenantName.setText("");
                                            txtDateofBirth.setText("");
                                            txtElectricUsage.setText("");
                                            txtDateStart.setText("");
                                            txtTenantEmail.setText("");
                                            txtWaterUsage.setText("");
                                            updateHouseComboBox(houseIDComboBox);
                                            showAllTenants();
                                        }
                                    }
                                }
                            }
                            else
                            {
                                Tenant tenantUpdate = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,houseId);
                                boolean updateTenant = tenantController.updateTenant(tenantUpdate);
                                if(updateTenant)
                                {
                                    JOptionPane.showMessageDialog(null,"cập nhật  thành công");
                                    txtTenantID.setText("");
                                    txtTenantName.setText("");
                                    txtDateofBirth.setText("");
                                    txtElectricUsage.setText("");
                                    txtDateStart.setText("");
                                    txtTenantEmail.setText("");
                                    txtWaterUsage.setText("");
                                    updateHouseComboBox(houseIDComboBox);
                                    showAllTenants();
                                }
                            }
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "email sai định dạng vui lòng sửa lại");
                        txtTenantEmail.setText("");
                    }
                }
                else
                {
                    int result = JOptionPane.showConfirmDialog(null, "Chưa tồn tại người dùng này. Bạn có muốn thêm mới người dùng?", "Thông báo", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        addTenant(); // Thêm mới người dùng
                    } else {
                        txtTenantID.setText("");
                        txtTenantName.setText("");
                        txtDateofBirth.setText("");
                        txtElectricUsage.setText("");
                        txtDateStart.setText("");
                        txtTenantEmail.setText("");
                        txtWaterUsage.setText("");
                        updateHouseComboBox(houseIDComboBox);
                        showAllTenants();
                    }


                }

            }
        });
        btnSearchTenant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenant_id = txtTenantID.getText();
                String tenant_email = txtTenantEmail.getText();
                if(tenant_id.equals("")&&tenant_email.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Điền ID hoặc email của người thuê nhà để thực hiện tìm kiếm");
                }
                else
                {
                    if(tenant_id.equals(""))
                    {
                        displayTenantInfo(tenant_email);
                    }
                    else
                    {
                        displayTenantInfo(tenant_id);
                    }
                }
            }
        });
        btnAddHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float  area = Float.parseFloat(txtArea.getText());
                float room_cost = Float.parseFloat(txtRoomcost.getText());
                String Furniture = txtFurniture.getText();
                int house_status = (Integer) houseStatusComboBox.getSelectedItem();

                if(txtArea.getText().equals("")||txtRoomcost.getText().equals("")||Furniture.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"điền đầy đủ thông tin về phòng trọ");
                }
                else
                {
                    House houseNew = new House(area,room_cost,Furniture,house_status);
                    boolean addHouse = houseController.addHouse(houseNew);
                    if(addHouse)
                    {
                        JOptionPane.showMessageDialog(null,"Thêm phòng thành công");
                        txtArea.setText("");
                        txtRoomcost.setText("");
                        txtFurniture.setText("");
                        showAllHouses();
                        updateHouseComboBox(houseIDComboBox);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Đã có lỗi xảy ra");
                    }
                }
            }
        });
        btnDeleteHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int houseId = getSelectedRoomNumber();
                if(houseId<0)
                {
                    JOptionPane.showMessageDialog(null,"Chọn phòng cần xoá từ table");
                }
                else
                {
                    boolean deleteRental = rentalController.deleteRentalByHouseId(houseId);
                    if(deleteRental)
                    {
                        boolean deleteTenant = tenantController.deleteTenantByHouseId(houseId);
                        if(deleteTenant)
                        {
                            boolean deleteRoom = houseController.deleteHouseById(houseId);
                            if(deleteRoom)
                            {
                                JOptionPane.showMessageDialog(null,"xoá thành công");
                                showAllHouses();
                                System.out.println("Đã xoá thành công");
                                showAllTenants();
                                updateHouseComboBox(houseIDComboBox);
                            }
                        }
                        else
                        {
                            boolean deleteRoom = houseController.deleteHouseById(houseId);
                            if(deleteRoom)
                            {
                                JOptionPane.showMessageDialog(null,"xoá thành công");
                            }
                            showAllHouses();
                            showAllTenants();
                            updateHouseComboBox(houseIDComboBox);
                        }
                    }
                    else
                    {

                        boolean deleteTenant = tenantController.deleteTenantByHouseId(houseId);
                        if(deleteTenant)
                        {
                            boolean deleteRoom = houseController.deleteHouseById(houseId);
                            if(deleteRoom)
                            {
                                JOptionPane.showMessageDialog(null,"xoá thành công");
                                showAllHouses();
                                showAllTenants();
                                updateHouseComboBox(houseIDComboBox);
                            }
                        }
                        else
                        {
                            boolean deleteRoom = houseController.deleteHouseById(houseId);
                            if(deleteRoom)
                            {
                                JOptionPane.showMessageDialog(null,"xoá thành công");
                                showAllHouses();
                                showAllTenants();
                                updateHouseComboBox(houseIDComboBox);
                            }
                        }
                    }
                }
            }
        });
        btnUpdateHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int houseId = getSelectedRoomNumber();
                if(houseId<0)
                {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng cần sửa từ table");
                }
                else
                {
                    int houseLastStatus = houseController.getHouseStatusByHouseId(houseId);
                    float area = Float.parseFloat(txtArea.getText());
                    float roomcost = Float.parseFloat(txtRoomcost.getText());
                    String furnished = txtFurniture.getText();
                    int house_Status = (Integer) houseStatusComboBox.getSelectedItem();
                    if(houseLastStatus==house_Status)
                    {
                        House houseNew = new House(houseId,area,roomcost,furnished,house_Status);
                        boolean updatehouse = houseController.updateHouse(houseNew);
                        if(updatehouse)
                        {
                            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                            txtArea.setText("");
                            txtRoomcost.setText("");
                            txtFurniture.setText("");
                            showAllHouses();
                            updateHouseComboBox(houseIDComboBox);

                        }
                    }
                    else
                    {
                        int choice = JOptionPane.showConfirmDialog(null, "Bạn đang thực hiện cập nhật phòng ở trạng thái 0 tức là phòng trống nếu tiếp tục sẽ xoá người hiện tại đang thuê và tổng tiền thuê chọn No sẽ cập nhật thông tin khác trừ trạng thái nhà", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            boolean deleteRental = rentalController.deleteRentalByHouseId(houseId);
                            boolean deleteTenant = tenantController.deleteTenantByHouseId(houseId);
                            if(deleteRental)
                            {
                                if(deleteTenant)
                                {
                                    House houseNew = new House(houseId,area,roomcost,furnished,house_Status);
                                    boolean updatehouse = houseController.updateHouse(houseNew);
                                    if(updatehouse)
                                    {
                                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                        txtArea.setText("");
                                        txtRoomcost.setText("");
                                        txtFurniture.setText("");
                                        showAllHouses();
                                        showAllTenants();
                                        updateHouseComboBox(houseIDComboBox);

                                    }
                                }
                                else
                                {
                                    House houseNew = new House(houseId,area,roomcost,furnished,house_Status);
                                    boolean updatehouse = houseController.updateHouse(houseNew);
                                    if(updatehouse)
                                    {
                                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                        txtArea.setText("");
                                        txtRoomcost.setText("");
                                        txtFurniture.setText("");
                                        showAllHouses();
                                        showAllTenants();
                                        updateHouseComboBox(houseIDComboBox);

                                    }
                                }
                            }
                            else
                            {
                                House houseNew = new House(houseId,area,roomcost,furnished,house_Status);
                                boolean updatehouse = houseController.updateHouse(houseNew);
                                if(updatehouse)
                                {
                                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                    txtArea.setText("");
                                    txtRoomcost.setText("");
                                    txtFurniture.setText("");
                                    showAllHouses();
                                    showAllTenants();
                                    updateHouseComboBox(houseIDComboBox);

                                }
                            }

                        } else {
                            House houseNew = new House(houseId,area,roomcost,furnished,houseLastStatus);
                            boolean updatehouse = houseController.updateHouse(houseNew);
                            if(updatehouse)
                            {
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                txtArea.setText("");
                                txtRoomcost.setText("");
                                txtFurniture.setText("");
                                showAllHouses();
                                updateHouseComboBox(houseIDComboBox);

                            }

                        }

                    }


                }
            }
        });

    }
    public void displayTenantInfo(String keyword) {
        // Tìm kiếm tenant dựa trên keyword
        Tenant foundTenant = tenantController.findTenantByIdOrEmail(keyword);

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
            tableTenantModel.setRowCount(0);

            // Thêm dòng dữ liệu mới vào tableTenantModel
            tableTenantModel.addRow(rowData);
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


    public void addTenant()
    {
        String tenant_id = txtTenantID.getText();
        String tenant_name = txtTenantName.getText();
        String tenant_birthday = txtDateofBirth.getText();
        String tenant_email = txtTenantEmail.getText();
        String tenant_startDate = txtDateStart.getText();
        float tenant_electricUsage = Float.parseFloat(txtElectricUsage.getText());
        float tenant_WaterUsage = Float.parseFloat(txtWaterUsage.getText());
        int house_id = (Integer) houseIDComboBox.getSelectedItem();


        if(tenant_id.equals("")||tenant_birthday.equals("")||tenant_email.equals("")||tenant_startDate.equals("")||tenant_name.equals("")||txtElectricUsage.getText().equals("")||txtWaterUsage.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Các ô dữ liệu phải được điền hoặc chọn đầy đủ, số điện nước phải là số nguyên hoặc thập phân");
        }
        else
        {
            DateCheck dateCheck = new DateCheck();
            if(!dateCheck.isBirthday(tenant_birthday)||!dateCheck.isValidDate(tenant_startDate))
            {
                JOptionPane.showMessageDialog(null,"Ngày sinh không được lớn hơn ngày hiện tại , ngày bắt đầu ở phải đúng định dạng dd/mm/yyyy và không đươc nhỏ hơn ngày hiện tại.Ví dụ: 20/01/2024");
            }
            else if(tenantController.isTenantIdExists(tenant_id)||tenantController.isEmailExists(tenant_email))
            {
                JOptionPane.showMessageDialog(null,"email hoặc căn cước công dân không được trùng");
            }
            else if(!emailCheck.isValidEmail(tenant_email))
            {
                JOptionPane.showMessageDialog(null,"email phải đúng định dạng ví dụ tragiang@gmail.com");
            }
            else if(!emailCheck.isNumericAndLength12(tenant_id))
            {
                JOptionPane.showMessageDialog(null, "căn cước công dân phải là chuỗi 12 số ví dụ 056204011xxx");
            }
            else
            {
                Tenant tenantNew = new Tenant(tenant_id,tenant_name,tenant_birthday,tenant_email,tenant_startDate, tenant_electricUsage,tenant_WaterUsage,house_id);
                tenantController.addTenant(tenantNew);
                Rental rentalNew = new Rental(house_id,tenant_id);
                boolean rent=rentalController.rentHouse(rentalNew);
                boolean house= houseController.updateHouseStatus(house_id,1);
                if(rent&&house)
                {
                    JOptionPane.showMessageDialog(null, "Thêm khách thuê thành công");
                    txtTenantID.setText("");
                    txtTenantName.setText("");
                    txtDateofBirth.setText("");
                    txtElectricUsage.setText("");
                    txtDateStart.setText("");
                    txtTenantEmail.setText("");
                    txtWaterUsage.setText("");
                    updateHouseComboBox(houseIDComboBox);
                    showAllTenants();
                }


            }
        }
    }

    public static void main(String[] args) {
        AdminView adminView = new AdminView("Manager");
        adminView.showView();
    }
}
