package chargeStaffDisplay;

import java.awt.*;
import javax.swing.*;

public class test2 {
	public static void ss() {
		JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);

        // 创建按钮
        JButton btn01 = new JButton("Button01");
        // 设置按钮的坐标
        btn01.setLocation(50, 50);
        // 设置按钮的宽高
        btn01.setSize(100, 50);
        panel.add(btn01);

        // 创建按钮
        JButton btn02 = new JButton("Button02");
        // 设置按钮的界限(坐标和宽高)，设置按钮的坐标为(50, 100)，宽高为 100, 50
        btn02.setBounds(50, 100, 100, 50);
        panel.add(btn02);

        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);

        /*
         * 也可以在 jf.setVisible(true) 之后添加按钮
         *
         * PS_01: jf.setVisible(true) 之后，内容面板才有宽高;
         * PS_02: 使用其他布局时, jf.setVisible(true) 之后添加的组件, 也会被当做是绝对布局来布置该组件（即需要手动指定坐标和宽高）;
         * PS_03: 使用其他布局时, jf.setVisible(true) 之前添加的组件, 如果在 jf.setVisible(true) 之后手动设置该组件的坐标和宽高,
         *        会将该组件当做绝对布局来对待（即设置坐标和宽高会生效）。
         */
        JButton btn03 = new JButton("Button03");
        // 把按钮位置设置在内容面板右下角, 并且设置按钮宽高为 100, 50
        btn03.setBounds(panel.getWidth() - 100, panel.getHeight() - 50, 100, 50);
        panel.add(btn03);
	}
	
	public static void main(String[] args) {
		ss();
	}
}
