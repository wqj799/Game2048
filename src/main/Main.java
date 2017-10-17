package main;

import ui.MyFrame;
import ui.MyPanel;

public class Main {
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        MyPanel myPanel = new MyPanel();
        myFrame.add(myPanel);
        myPanel.requestFocus();
        myPanel.gameStart();
    }
}
