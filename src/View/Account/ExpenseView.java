package View.Account;

import Controller.HouseController;
import Controller.TenantController;
import Models.House;

import javax.swing.*;
import java.awt.*;

public class ExpenseView extends JPanel {
    JPanel pnTop, pnBottom, pnRight, pnLeft, pnTitle, pnContent, pnMain,
            pnNumHouse, pnArea, pnElectric, pnWater, pnFurniture, pnRoom, pnTotal,
            pnName, pnBank, pnNumBank;
    JLabel lbTitle, lbNumHouse, lbArea, lbElecttric, lbWater, lbFurniture, lbRoom, lbTotal,
            lbResultNumHouse, lbResultArea, lbResultElectric, lbResultWater, lbResultFurniture, lbResultRoom, lbResultTotal,
            lbName, lbBank, lbNumBank, lbQRCode, lbQRCodeImg;
    Color color = new Color(93, 185, 187);
    Font fontB = new Font("SansSerif", Font.BOLD, 20);
    Font fontP = new Font("SansSerif", Font.PLAIN, 20);
    ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("Icon/qr.png"));
    Image img = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    ImageIcon qrCode = new ImageIcon(img);
    House house;
    public ExpenseView(String email) {
        super();
        getDB(email);
        addView();
    }

    private void addView() {
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnTop = new JPanel();
        pnTop.setBackground(null);
        pnTop.setLayout(new BorderLayout());
        pnTop.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        pnTitle.setBackground(null);

        lbTitle = new JLabel("TIỀN NHÀ");
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
        lbTitle.setForeground(color);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);

        pnTitle.add(lbTitle);

        pnContent = new JPanel();
        pnContent.setBackground(null);
        pnContent.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnMain = new JPanel();
        pnMain.setBackground(null);
        pnMain.setLayout(new GridLayout(6, 1));

        pnNumHouse = new JPanel();
        pnNumHouse.setBackground(null);
        pnNumHouse.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbNumHouse = new JLabel("Phòng ");
        lbNumHouse.setFont(fontB);
        lbNumHouse.setForeground(color);
        lbNumHouse.setPreferredSize(new Dimension(175, 25));
        lbNumHouse.setHorizontalAlignment(JLabel.LEFT);
        lbResultNumHouse = new JLabel(house.getHouseId() + "");
        lbResultNumHouse.setFont(fontP);
        lbResultNumHouse.setForeground(Color.BLACK);
        lbResultNumHouse.setPreferredSize(lbNumHouse.getPreferredSize());
        lbResultNumHouse.setHorizontalAlignment(JLabel.RIGHT);
        pnNumHouse.add(lbNumHouse);
        pnNumHouse.add(lbResultNumHouse);

        pnArea = new JPanel();
        pnArea.setBackground(null);
        pnArea.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbArea = new JLabel("Diện tích ");
        lbArea.setFont(fontB);
        lbArea.setForeground(color);
        lbArea.setPreferredSize(new Dimension(175, 25));
        lbArea.setHorizontalAlignment(JLabel.LEFT);
        lbResultArea = new JLabel(house.getArea() + "");
        lbResultArea.setFont(fontP);
        lbResultArea.setForeground(Color.BLACK);
        lbResultArea.setPreferredSize(lbArea.getPreferredSize());
        lbResultArea.setHorizontalAlignment(JLabel.RIGHT);
        pnArea.add(lbArea);
        pnArea.add(lbResultArea);

        pnElectric = new JPanel();
        pnElectric.setBackground(null);
        pnElectric.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbElecttric = new JLabel("Tiền điện ");
        lbElecttric.setFont(fontB);
        lbElecttric.setForeground(color);
        lbElecttric.setPreferredSize(new Dimension(175, 25));
        lbElecttric.setHorizontalAlignment(JLabel.LEFT);
        lbResultElectric = new JLabel(house.getElectricityCost() + "");
        lbResultElectric.setFont(fontP);
        lbResultElectric.setForeground(Color.BLACK);
        lbResultElectric.setPreferredSize(lbElecttric.getPreferredSize());
        lbResultElectric.setHorizontalAlignment(JLabel.RIGHT);
        pnElectric.add(lbElecttric);
        pnElectric.add(lbResultElectric);

        pnWater = new JPanel();
        pnWater.setBackground(null);
        pnWater.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbWater = new JLabel("Tiền nước ");
        lbWater.setFont(fontB);
        lbWater.setForeground(color);
        lbWater.setPreferredSize(new Dimension(175, 25));
        lbWater.setHorizontalAlignment(JLabel.LEFT);
        lbResultWater = new JLabel(house.getWaterCost() + "");
        lbResultWater.setFont(fontP);
        lbResultWater.setForeground(Color.BLACK);
        lbResultWater.setPreferredSize(lbWater.getPreferredSize());
        lbResultWater.setHorizontalAlignment(JLabel.RIGHT);
        pnWater.add(lbWater);
        pnWater.add(lbResultWater);

        pnRoom = new JPanel();
        pnRoom.setBackground(null);
        pnRoom.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbRoom = new JLabel("Tiền nước ");
        lbRoom.setFont(fontB);
        lbRoom.setForeground(color);
        lbRoom.setPreferredSize(new Dimension(175, 25));
        lbRoom.setHorizontalAlignment(JLabel.LEFT);
        lbResultRoom = new JLabel(house.getWaterCost() + "");
        lbResultRoom.setFont(fontP);
        lbResultRoom.setForeground(Color.BLACK);
        lbResultRoom.setPreferredSize(lbWater.getPreferredSize());
        lbResultRoom.setHorizontalAlignment(JLabel.RIGHT);
        pnRoom.add(lbRoom);
        pnRoom.add(lbResultRoom);

        pnFurniture = new JPanel();
        pnFurniture.setBackground(null);
        pnFurniture.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbFurniture = new JLabel("Nội thất");
        lbFurniture.setFont(fontB);
        lbFurniture.setForeground(color);
        lbFurniture.setPreferredSize(new Dimension(175, 25));
        lbFurniture.setHorizontalAlignment(JLabel.LEFT);
        lbResultFurniture = new JLabel(house.getFurniture());
        lbResultFurniture.setFont(fontP);
        lbResultFurniture.setForeground(Color.BLACK);
        lbResultFurniture.setPreferredSize(lbFurniture.getPreferredSize());
        lbResultFurniture.setHorizontalAlignment(JLabel.RIGHT);
        pnFurniture.add(lbFurniture);
        pnFurniture.add(lbResultFurniture);

        pnTotal = new JPanel();
        pnTotal.setBackground(null);
        pnTotal.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lbTotal = new JLabel("Tổng tiền");
        lbTotal.setFont(new Font("SansSerif", Font.BOLD, 35));
        lbTotal.setForeground(color);
        lbTotal.setPreferredSize(new Dimension(175, 50));
        lbTotal.setHorizontalAlignment(JLabel.LEFT);
        lbResultTotal = new JLabel(String.valueOf(house.getWaterCost() == 0 || house.getElectricityCost() == 0 ? 0.0 : house.getWaterCost() + house.getElectricityCost() + house.getRoomCost() + ""));
        lbResultTotal.setFont(new Font("SansSerif", Font.PLAIN, 35));
        lbResultTotal.setForeground(Color.BLACK);
        lbResultTotal.setPreferredSize(lbTotal.getPreferredSize());
        lbResultTotal.setHorizontalAlignment(JLabel.RIGHT);
        pnTotal.add(lbTotal);
        pnTotal.add(lbResultTotal);

        pnMain.add(pnNumHouse);
        pnMain.add(pnArea);
        pnMain.add(pnElectric);
        pnMain.add(pnWater);
        pnMain.add(pnRoom);
        pnMain.add(pnFurniture);
        pnMain.add(pnTotal);

        pnContent.add(pnMain);

        pnTop.add(pnTitle, BorderLayout.NORTH);
        pnTop.add(pnContent, BorderLayout.CENTER);

        pnBottom = new JPanel();
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new GridLayout(1, 2));
        pnBottom.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnLeft = new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
        pnLeft.setBackground(Color.WHITE);

        pnName = new JPanel();
        pnName.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnName.setBackground(null);
        lbName = new JLabel("HOÀNG TRÀ GIANG");
        lbName.setFont(fontP);
        lbName.setForeground(Color.BLACK);
        lbName.setPreferredSize(lbName.getPreferredSize());
        lbName.setHorizontalAlignment(JLabel.RIGHT);
        pnName.add(lbName);
        pnName.add(lbName);

        pnBank = new JPanel();
        pnBank.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnBank.setBackground(null);
        lbBank = new JLabel("Ngân hàng Quân Đội MBBank");
        lbBank.setFont(fontP);
        lbBank.setForeground(Color.BLACK);
        lbBank.setPreferredSize(lbBank.getPreferredSize());
        lbBank.setHorizontalAlignment(JLabel.RIGHT);
        pnBank.add(lbBank);
        pnBank.add(lbBank);

        pnNumBank = new JPanel();
        pnNumBank.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnNumBank.setBackground(null);
        lbNumBank = new JLabel("0799102910");
        lbNumBank.setFont(fontP);
        lbNumBank.setForeground(Color.BLACK);
        lbNumBank.setPreferredSize(lbNumBank.getPreferredSize());
        lbNumBank.setHorizontalAlignment(JLabel.RIGHT);
        pnNumBank.add(lbNumBank);
        pnNumBank.add(lbNumBank);

        pnLeft.add(pnName);
        pnLeft.add(pnBank);
        pnLeft.add(pnNumBank);

        pnRight = new JPanel();
        pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
        pnRight.setBackground(Color.WHITE);

        lbQRCode = new JLabel("QR Code");
        lbQRCode.setFont(fontB);
        lbQRCode.setForeground(color);
        lbQRCode.setPreferredSize(new Dimension(175, 25));
        lbQRCode.setHorizontalAlignment(JLabel.CENTER);

        lbQRCodeImg = new JLabel(qrCode);
        lbQRCodeImg.setPreferredSize(new Dimension(100, 100));

        pnRight.add(lbQRCode);
        pnRight.add(lbQRCodeImg);

        pnBottom.add(pnLeft, BorderLayout.WEST);
        pnBottom.add(pnRight, BorderLayout.EAST);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnBottom, BorderLayout.CENTER);
    }

    public void getDB(String email) {
        TenantController tenC = new TenantController();
        HouseController houseC = new HouseController();
        String tenant_id = tenC.getTenant(email).getTenantId();
        int house_id = tenC.getHouseIdByTenantId(tenant_id);
        house = houseC.getHouse(house_id);
    }
}
