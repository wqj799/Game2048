package ui;

import entity.Cell;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame {
    //单元格的宽度
    private static final int CELL_SIZE = 60;

    //最大行数
    private static final int MAX_ROW = 4;

    //最大列数
    private static final int MAX_COLUMN = 4;

    public MyFrame() {
        //设置窗口标题
        this.setTitle("Game 2048");
        //设置窗口的大小
        this.setSize(MAX_ROW * CELL_SIZE + 10, MAX_COLUMN * CELL_SIZE + 70);
        //设置窗口打开的位置
        this.setLocation(500, 200);
        //添加窗口关闭时的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //窗口可见
        this.setVisible(true);
        //窗口大小不可变
        this.setResizable(false);
    }
}
