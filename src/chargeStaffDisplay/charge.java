package chargeStaffDisplay;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import db.insert;
import staff.chargeStaff;


//收费界面
public class charge {
	private static boolean isInsert=false;
	
	public static void display(String houseID,String ownerName,chargeStaff cs) {
		JFrame jf = new JFrame("收费");
        jf.setSize(520, 520);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);
        
        JPanel insidePanel=new JPanel(null);
        insidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        insidePanel.setBackground(Color.WHITE);
        insidePanel.setSize(460, 380);
        insidePanel.setLocation(20, 20);
        
        panel.add(insidePanel);

        JLabel label1=myLabel.normalLabel("房号："+houseID);
        label1.setLocation(0, 0);
        insidePanel.add(label1);
        
        JLabel label2=myLabel.normalLabel("业主："+ownerName);
        label2.setLocation(360, 0);
        insidePanel.add(label2);
        
        long l=System.currentTimeMillis();
		Date date=new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
        JLabel label3=new JLabel("日期："+time,SwingConstants.CENTER);
        label3.setSize(150,50);
        label3.setLocation(0, 324);
        insidePanel.add(label3);
        
        JLabel label4=myLabel.normalLabel("员工："+cs.getID());
        label4.setLocation(360, 324);
        insidePanel.add(label4);
        
        JLabel label5=new JLabel("合计",SwingConstants.CENTER);
        label5.setSize(112, 50);
        label5.setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.GRAY));
        label5.setLocation(5, 274);
        insidePanel.add(label5);
        
        JLabel label6=new JLabel("",SwingConstants.CENTER);
        label6.setSize(224, 50);
        label6.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.GRAY));
        label6.setLocation(117, 274);
        insidePanel.add(label6);
        
        JLabel label7=new JLabel("",SwingConstants.CENTER);
        label7.setSize(112, 50);
        label7.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
        label7.setLocation(341, 274);
        insidePanel.add(label7);
        
        Object[] columnNames = {"序号", "收费类型", "数量", "金额"};

        // 表格所有行数据
        Object[][] rowData = {
                {"1", "物业费", cs.houseInfo(houseID)[0], cs.Charge(houseID)[0]},
                {"2", "卫生费", cs.houseInfo(houseID)[1], cs.Charge(houseID)[1]},
                {"3", "水费", "", ""},
                {"4", "电费", "", ""}
        };
        JTable table=myTable.chargeTable(rowData, columnNames);
        final TableModel tableModel = table.getModel();
		tableModel.addTableModelListener(new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				//获取第一个和最后一个被改变的行（只改变了一行，则两者相同）
				int firstRow=e.getFirstRow();
				int lastRow=e.getLastRow();
				
				//获取被改变的列
				int column=e.getColumn();
				
				//获取事件的类型
				int type=e.getType();
				
				//针对现有数据的更改，更新其他单元格数据
				if(type==TableModelEvent.UPDATE) {
					//只处理数量这行的更改
					if(column<2 || column>2) {
						return;
					}
					//遍历每一个修改的行，数量更改后同时更新金额
					for(int row=firstRow;row<=lastRow;row++) {
						Object numberObj=tableModel.getValueAt(row, 2);
						if((numberObj+"").length()==0)
							break;
						double number=0;
						double money=0;
						try {
							number=Double.parseDouble(""+numberObj);
						}
						catch(Exception ex) {
							ex.printStackTrace();
						}						
						if(row==2)
							money=number*0.7;
						else if(row==3)
							money=number*0.8;
						//修改金额的数据
						tableModel.setValueAt(reservedDigits.getDoubleNumber(money), row, 3);						
					}
				}
				//判断电费和水费都已经进行了信息填入
				if(table.getValueAt(2, 3).getClass().getName().equals("java.lang.Double") && table.getValueAt(3, 3).getClass().getName().equals("java.lang.Double")) {
					double totalMoney=(Double)table.getValueAt(0, 3)+(Double)table.getValueAt(1, 3)+(Double)table.getValueAt(2, 3)+(Double)table.getValueAt(3, 3);
					//限制小数后1位
					NumberFormat format = NumberFormat.getNumberInstance() ;
				    format.setMaximumFractionDigits(1);
				    String result = format.format(totalMoney) ;					
					label7.setText(result);
					label6.setText(toChinese.getNumber(result));
			        insidePanel.updateUI();
				}
			}
		});				
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setSize(450, 225);
        scrollPane.setLocation(5, 50);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);
        insidePanel.add(scrollPane);
        
        JButton btn1=new JButton("确定");
        btn1.setSize(100, 50);
        btn1.setLocation(270,410);
        btn1.setBackground(Color.WHITE);
        btn1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String property=table.getValueAt(0, 3)+"";
				String clean=table.getValueAt(1, 3)+"";
				String water=table.getValueAt(2, 3)+"";
				String electricity=table.getValueAt(3, 3)+"";
				String state="未缴";
				String[] column= {"property","clean","water","electricity","time_stamp","house_id","state","staff_id"};
				String[] content= {property,clean,water,electricity,time,houseID,state,cs.getID()};
				insert.insertSet(cs.getUser(), cs.getPass(), "bill", column, content);
				isInsert=true;
				chargeSuccess.showFailedWindow(jf);
			}
		});
        panel.add(btn1);
        JButton btn2=new JButton("打印");
        btn2.setSize(100, 50);
        btn2.setLocation(380,410);
        btn2.setBackground(Color.WHITE);
        btn2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//确定已经填好了信息才能进行截图操作
				if(isInsert) {
			        // 创建需要截取的矩形区域
			        Rectangle rect = new Rectangle(jf.getX()+27, jf.getY()+55, 465, 385);
			        // 截屏操作
			        BufferedImage bufImage = null;
					try {
						bufImage = new Robot().createScreenCapture(rect);
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String imageName=houseID+time;
			        // 保存截取的图片
			        try {
						ImageIO.write(bufImage, "PNG", new File(imageName+".png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
        });
			
        panel.add(btn2);

        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);

	}

	public static void main(String[] args) {
		display("A0101", "张全蛋", new chargeStaff("001"));
	}
}
