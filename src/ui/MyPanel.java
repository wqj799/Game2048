package ui;

import entity.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

public class MyPanel extends JPanel {
    //单元格的宽度
    private static final int CELL_SIZE = 60;

    //最大行数
    private static final int MAX_ROW = 4;

    //最大列数
    private static final int MAX_COLUMN = 4;

    //使用数组保存所有的单元格
    private Cell[][] cells = new Cell[MAX_ROW][MAX_COLUMN];

    //游戏是否结束的标记
    private boolean isGameOver = false;

    public MyPanel() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //用来判断单元格是否移动过，是否需要重新绘图
                boolean isMoved = false;
                //获取按下按键的代码，左：37 上：38 右：39 下：40
                int code = e.getKeyCode();
                switch (code) {
                    // 左
                    case 37:
                        if (isGameOver()) break;
                        //1.循环遍历所有的单元格
                        for (int i = 0; i < MAX_ROW; i++) {
                            for (int j = 0; j < MAX_COLUMN; j++) {
                                Cell cell = cells[i][j];
                                if (null != cell) {
                                    //2.判断左边的单元格是否为空
                                    for (int k = j; k > 0; k--) {
                                        //2.1当左边的单元格为空时，往左移动一格
                                        if (null == cells[i][k - 1]) {
                                            cells[i][k - 1] = cell;
                                            cells[i][k] = null;
                                            isMoved = true;
                                        } else {
                                            //2.2当左边的单元格不为空时，检查左边的单元格值是否相等
                                            //2.2.1如果相等，则把值相加，但不能超过2048
                                            if (cell.getValue() == cells[i][k - 1].getValue()
                                                    && cell.isMerge() == false && cells[i][k - 1].isMerge() == false) {
                                                cells[i][k - 1].setValue(cells[i][k - 1].getValue() * 2);
                                                cells[i][k - 1].setMerge(true);
                                                cells[i][k] = null;
                                                isMoved = true;
                                            }
                                            //2.2.2如果不相等，则不做处理
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (isMoved) {
                            afterMoved();
                        }
                        break;
                    case 38:
                        if (isGameOver()) break;
                        for (int i = 0; i < MAX_ROW; i++) {
                            for (int j = 0; j < MAX_COLUMN; j++) {
                                Cell cell = cells[i][j];
                                if (null != cell) {
                                    for (int k = i; k > 0; k--) {
                                        if (null == cells[k - 1][j]) {
                                            cells[k - 1][j] = cell;
                                            cells[k][j] = null;
                                            isMoved = true;
                                        } else {
                                            if (cell.getValue() == cells[k - 1][j].getValue()
                                                    && cell.isMerge() == false && cells[k - 1][j].isMerge() == false) {
                                                cells[k - 1][j].setValue(cells[k - 1][j].getValue() * 2);
                                                cells[k - 1][j].setMerge(true);
                                                cells[k][j] = null;
                                                isMoved = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (isMoved) {
                            afterMoved();
                        }
                        break;
                    case 39:
                        if (isGameOver()) break;
                        for (int i = MAX_ROW - 1; i >= 0; i--) {
                            for (int j = MAX_COLUMN - 1; j >= 0; j--) {
                                Cell cell = cells[i][j];
                                if (null != cell) {
                                    for (int k = j; k < MAX_COLUMN - 1; k++) {
                                        if (null == cells[i][k + 1]) {
                                            cells[i][k + 1] = cell;
                                            cells[i][k] = null;
                                            isMoved = true;
                                        } else {
                                            if (cell.getValue() == cells[i][k + 1].getValue()
                                                    && cell.isMerge() == false && cells[i][k + 1].isMerge() == false) {
                                                cells[i][k + 1].setValue(cells[i][k + 1].getValue() * 2);
                                                cells[i][k + 1].setMerge(true);
                                                cells[i][k] = null;
                                                isMoved = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (isMoved) {
                            afterMoved();
                        }
                        break;
                    case 40:
                        if (isGameOver()) break;
                        for (int i = MAX_ROW - 1; i >= 0; i--) {
                            for (int j = MAX_COLUMN - 1; j >= 0; j--) {
                                Cell cell = cells[i][j];
                                if (null != cell) {
                                    for (int k = i; k < MAX_COLUMN - 1; k++) {
                                        if (null == cells[k + 1][j]) {
                                            cells[k + 1][j] = cell;
                                            cells[k][j] = null;
                                            isMoved = true;
                                        } else {
                                            if (cell.getValue() == cells[k + 1][j].getValue()
                                                    && cell.isMerge() == false && cells[k + 1][j].isMerge() == false) {
                                                cells[k + 1][j].setValue(cells[k + 1][j].getValue() * 2);
                                                cells[k + 1][j].setMerge(true);
                                                cells[k][j] = null;
                                                isMoved = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (isMoved) {
                            afterMoved();
                        }
                        break;
                }
            }
        });
    }

    public void gameStart() {
        //开始时随机创建2个单元格
        createCell();
        createCell();
        repaint();
    }


    @Override
    public void paint(Graphics g) {
        g.translate(0, 40);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MAX_ROW * CELL_SIZE, MAX_COLUMN * CELL_SIZE);
        g.drawRect(0, 0, MAX_ROW * CELL_SIZE, MAX_COLUMN * CELL_SIZE);
        drawCell(g);
    }

    /**
     * 根据单元格中的值绘制图像
     */
    private void drawCell(Graphics g) {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                Cell cell = cells[i][j];
                if (null != cell) {
                    StringBuffer bf = new StringBuffer();
                    bf.append(System.getProperty("user.dir"))
                            .append(File.separator)
                            .append("image")
                            .append(File.separator)
                            .append(cell.getValue())
                            .append(".png");
                    Image image = Toolkit.getDefaultToolkit().getImage(bf.toString());
                    g.drawImage(image, j * CELL_SIZE, i * CELL_SIZE, this);
                }
            }
        }
    }

    /**
     * 创建一个单元格
     */
    private void createCell() {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(4);
            int column = random.nextInt(4);
            if (null == cells[row][column]) {
                cells[row][column] = new Cell(2, false);
                break;
            }
        }
    }

    /**
     * 用于判断游戏是否结束
     *
     * @return 游戏是否结束的标记
     */
    private boolean isGameOver() {
        if (valueGreateThan2048()) return isGameOver;

        if (hasNullValue()) return isGameOver;

        return isGameOver;
    }

    /**
     * 判断是否有空的单元格
     *
     * @return 游戏是否结束的标记
     */
    private boolean hasNullValue() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (cells[i][j] == null) {
                    isGameOver = false;
                    return isGameOver;
                }
            }
        }
        isGameOver = true;
        return isGameOver;
    }

    /**
     * 判断单元格的值是否大于2048
     *
     * @return 游戏是否结束的标记
     */
    private boolean valueGreateThan2048() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (cells[i][j] != null && cells[i][j].getValue() >= 2048) {
                    isGameOver = true;
                    return true;
                }
            }
        }
        isGameOver = false;
        return isGameOver;
    }

    /**
     * 重置所有单元格的状态
     */
    private void afterMerged() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                Cell cell = cells[i][j];
                if (null != cell) {
                    cell.setMerge(false);
                }
            }
        }
    }

    /**
     * 初始化
     */
    private void init() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                cells[i][j] = null;
            }
        }
        gameStart();
    }

    private void afterMoved() {
        repaint();
        if (isGameOver()) {
            System.out.println("游戏结束");
            int i = JOptionPane.showConfirmDialog(null, "游戏结束!");
            if (i == 0) {
                init();
            }
        } else {
            createCell();
            afterMerged();
            repaint();
        }
    }
}
