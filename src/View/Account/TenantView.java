package View.Account;

import Controller.TenantController;
import Models.Tenant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TenantView extends JPanel implements ActionListener {
    JPanel pnTop, pnBottom, pnInfoBottom, pnInfoLeft, pnInfoRight, pnUpdateBottom, pnUpdateLeft, pnUpdateRight,
            pnName, pnId, pnBrith, pnEmail, pnStartDate, pnElectricNumber, pnWaterNumber, pnRoomNumber;
    JLabel lbTitle, lbId, lbName, LbBrith, lbEmail, LbStartDate, lbElectricNumber, lbWaterNumber, lbRoomNumber,
            lbTenantName, lbTenantID, lbTenantBrith, lbTenantEmail, lbTenantStartDate, lbTenantElectricNumber, lbTenantWaterNumber, lbTenantRoomNumber;
    JTextField txtTenantName, txtTenantID, txtTenantBrith, txtTenantEmail;
    JButton btnUpdate;
    String tenantId, name, dateOfBirth, email;
    Font font = new Font("SansSerif", Font.PLAIN, 18);
    Color color = new Color(93, 185, 187);
    TenantController tenC = new TenantController();
    Tenant tenant = new Tenant();
    public TenantView(String checkEmail) {
        super();
        getDB(checkEmail);
        addView();
    }

    private void addView() {
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));

        pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(Color.WHITE);
        pnTop.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        lbTitle = new JLabel("Thông tin người thuê nhà");
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
        lbTitle.setForeground(color);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
//===================================================================================
        pnInfoBottom = new JPanel();
        pnInfoBottom.setLayout(new GridLayout(1, 2));
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnInfoLeft = new JPanel();
        pnInfoLeft.setLayout(new BoxLayout(pnInfoLeft, BoxLayout.Y_AXIS));
        pnInfoLeft.setBackground(Color.WHITE);

        pnId = new JPanel();
        pnId.setLayout(new FlowLayout());
        pnId.setBackground(null);
        lbId = new JLabel("CCCD/CMND ");
        lbId.setFont(font);
        lbId.setPreferredSize(new Dimension(150, 20));
        lbTenantID = new JLabel(tenant.getTenantId());
        lbTenantID.setFont(font);
        lbTenantID.setPreferredSize(lbId.getPreferredSize());
        pnId.add(lbId);
        pnId.add(lbTenantID);

        pnName = new JPanel();
        pnName.setLayout(new FlowLayout());
        pnName.setBackground(null);
        lbName = new JLabel("Họ và tên ");
        lbName.setFont(font);
        lbName.setPreferredSize(new Dimension(150, 20));
        lbTenantName = new JLabel(tenant.getName());
        lbTenantName.setFont(font);
        lbTenantName.setPreferredSize(lbName.getPreferredSize());
        pnName.add(lbName);
        pnName.add(lbTenantName);

        pnBrith = new JPanel();
        pnBrith.setLayout(new FlowLayout());
        pnBrith.setBackground(null);
        LbBrith = new JLabel("Ngày sinh ");
        LbBrith.setFont(font);
        LbBrith.setPreferredSize(new Dimension(150, 20));
        lbTenantBrith = new JLabel(tenant.getDateOfBirth());
        lbTenantBrith.setFont(font);
        lbTenantBrith.setPreferredSize(LbBrith.getPreferredSize());
        pnBrith.add(LbBrith);
        pnBrith.add(lbTenantBrith);

        pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout());
        pnEmail.setBackground(null);
        lbEmail = new JLabel("Email ");
        lbEmail.setFont(font);
        lbEmail.setPreferredSize(new Dimension(150, 20));
        lbTenantEmail = new JLabel(tenant.getEmail());
        lbTenantEmail.setFont(font);
        lbTenantEmail.setPreferredSize(lbEmail.getPreferredSize());
        pnEmail.add(lbEmail);
        pnEmail.add(lbTenantEmail);

        pnInfoLeft.add(pnId);
        pnInfoLeft.add(pnName);
        pnInfoLeft.add(pnBrith);
        pnInfoLeft.add(pnEmail);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnInfoRight = new JPanel();
        pnInfoRight.setLayout(new BoxLayout(pnInfoRight, BoxLayout.Y_AXIS));
        pnInfoRight.setBackground(Color.WHITE);

        pnStartDate = new JPanel();
        pnStartDate.setLayout(new FlowLayout());
        pnStartDate.setBackground(null);
        LbStartDate = new JLabel("Ngày bắt đầu thuê ");
        LbStartDate.setFont(font);
        LbStartDate.setPreferredSize(new Dimension(150, 20));
        lbTenantStartDate = new JLabel(tenant.getStartDate());
        lbTenantStartDate.setFont(font);
        lbTenantStartDate.setPreferredSize(LbStartDate.getPreferredSize());
        pnStartDate.add(LbStartDate);
        pnStartDate.add(lbTenantStartDate);

        pnElectricNumber = new JPanel();
        pnElectricNumber.setLayout(new FlowLayout());
        pnElectricNumber.setBackground(null);
        lbElectricNumber = new JLabel("Số điện ");
        lbElectricNumber.setFont(font);
        lbElectricNumber.setPreferredSize(new Dimension(150, 20));
        lbTenantElectricNumber = new JLabel(tenant.getElectricityUsage() + "");
        lbTenantElectricNumber.setFont(font);
        lbTenantElectricNumber.setPreferredSize(lbElectricNumber.getPreferredSize());
        pnElectricNumber.add(lbElectricNumber);
        pnElectricNumber.add(lbTenantElectricNumber);

        pnWaterNumber = new JPanel();
        pnWaterNumber.setLayout(new FlowLayout());
        pnWaterNumber.setBackground(null);
        lbWaterNumber = new JLabel("Số nước ");
        lbWaterNumber.setFont(font);
        lbWaterNumber.setPreferredSize(new Dimension(150, 20));
        lbTenantWaterNumber = new JLabel(tenant.getWaterUsage() + "");
        lbTenantWaterNumber.setFont(font);
        lbTenantWaterNumber.setPreferredSize(lbWaterNumber.getPreferredSize());
        pnWaterNumber.add(lbWaterNumber);
        pnWaterNumber.add(lbTenantWaterNumber);

        pnRoomNumber = new JPanel();
        pnRoomNumber.setLayout(new FlowLayout());
        pnRoomNumber.setBackground(null);
        lbRoomNumber = new JLabel("Số phòng ");
        lbRoomNumber.setFont(font);
        lbRoomNumber.setPreferredSize(new Dimension(150, 20));
        lbTenantRoomNumber = new JLabel(tenant.getHouse_id() + "");
        lbTenantRoomNumber.setFont(font);
        lbTenantRoomNumber.setPreferredSize(lbRoomNumber.getPreferredSize());
        pnRoomNumber.add(lbRoomNumber);
        pnRoomNumber.add(lbTenantRoomNumber);

        pnInfoRight.add(pnStartDate);
        pnInfoRight.add(pnElectricNumber);
        pnInfoRight.add(pnWaterNumber);
        pnInfoRight.add(pnRoomNumber);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnInfoBottom.add(pnInfoLeft);
        pnInfoBottom.add(pnInfoRight);

        pnTop.add(lbTitle, BorderLayout.NORTH);
        pnTop.add(pnInfoBottom, BorderLayout.CENTER);
//===================================================================================
//===================================================================================
//===================================================================================
        pnBottom = new JPanel();
        pnBottom.setLayout(new BorderLayout());
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        lbTitle = new JLabel("Cập nhật thông tin người thuê");
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
        lbTitle.setForeground(color);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnUpdateBottom = new JPanel();
        pnUpdateBottom.setLayout(new GridLayout(1, 2));
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnUpdateLeft = new JPanel();
        pnUpdateLeft.setLayout(new BoxLayout(pnUpdateLeft, BoxLayout.Y_AXIS));
        pnUpdateLeft.setBackground(Color.WHITE);

        pnId = new JPanel();
        pnId.setLayout(new FlowLayout());
        pnId.setBackground(null);
        lbId = new JLabel("CCCD/CMND ");
        lbId.setFont(font);
        lbId.setPreferredSize(new Dimension(150, 25));
        txtTenantID = new JTextField();
        txtTenantID.setFont(font);
        txtTenantID.setPreferredSize(lbId.getPreferredSize());
        txtTenantID.setText(lbTenantID.getText());
        pnId.add(lbId);
        pnId.add(txtTenantID);

        pnName = new JPanel();
        pnName.setLayout(new FlowLayout());
        pnName.setBackground(null);
        lbName = new JLabel("Họ và tên ");
        lbName.setFont(font);
        lbName.setPreferredSize(new Dimension(150, 25));
        txtTenantName = new JTextField();
        txtTenantName.setFont(font);
        txtTenantName.setPreferredSize(lbName.getPreferredSize());
        txtTenantName.setText(lbTenantName.getText());
        pnName.add(lbName);
        pnName.add(txtTenantName);

        pnBrith = new JPanel();
        pnBrith.setLayout(new FlowLayout());
        pnBrith.setBackground(null);
        LbBrith = new JLabel("Ngày sinh ");
        LbBrith.setFont(font);
        LbBrith.setPreferredSize(new Dimension(150, 25));
        txtTenantBrith = new JTextField();
        txtTenantBrith.setFont(font);
        txtTenantBrith.setPreferredSize(LbBrith.getPreferredSize());
        txtTenantBrith.setText(lbTenantBrith.getText());
        pnBrith.add(LbBrith);
        pnBrith.add(txtTenantBrith);

        pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout());
        pnEmail.setBackground(null);
        lbEmail = new JLabel("Email ");
        lbEmail.setFont(font);
        lbEmail.setPreferredSize(new Dimension(150, 25));
        txtTenantEmail = new JTextField();
        txtTenantEmail.setFont(font);
        txtTenantEmail.setPreferredSize(lbEmail.getPreferredSize());
        txtTenantEmail.setText(lbTenantEmail.getText());
        pnEmail.add(lbEmail);
        pnEmail.add(txtTenantEmail);

        pnUpdateLeft.add(pnId);
        pnUpdateLeft.add(pnName);
        pnUpdateLeft.add(pnBrith);
        pnUpdateLeft.add(pnEmail);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pnUpdateRight = new JPanel();
        pnUpdateRight.setLayout(new GridBagLayout());
        pnUpdateRight.setBackground(Color.WHITE);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnUpdate.setBackground(color);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setPreferredSize(new Dimension(150, 150));
        btnUpdate.addActionListener(this);

        pnUpdateRight.add(btnUpdate);

        pnUpdateBottom.add(pnUpdateLeft);
        pnUpdateBottom.add(pnUpdateRight);

        pnBottom.add(lbTitle, BorderLayout.NORTH);
        pnBottom.add(pnUpdateBottom, BorderLayout.CENTER);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnBottom, BorderLayout.CENTER);
    }

    public void getDB(String checkEmail) {
        tenant = tenC.getTenant(checkEmail);
    }

    public void input() {
        tenantId = txtTenantID.getText();
        name = txtTenantName.getText();
        dateOfBirth = txtTenantBrith.getText();
        email = txtTenantEmail.getText();
    }

    public void setValue() {
        lbTenantID.setText(tenantId);
        lbTenantName.setText(name);
        lbTenantBrith.setText(dateOfBirth);
        lbTenantEmail.setText(email);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
