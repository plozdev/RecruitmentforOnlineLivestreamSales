package org;

import tools.FileUtil;
import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI go = new ConsoleUI();
        FileUtil f = new FileUtil();
        System.out.println(f.readKOLList("KOLList.csv").size());
//        go.start();
    }
}