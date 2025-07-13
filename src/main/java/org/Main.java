package org;

import manager.KOLDisplay;
import manager.KOLManager;
import manager.PlatformDisplay;
import manager.PlatformManager;
import tools.FileUtil;
import tools.Input;
import ui.ConsoleUI;
import java.util.logging.Logger;

/**
 * Main point of the program
 */
public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        FileUtil fileUtil = new FileUtil();
        PlatformManager platformManager = new PlatformManager(fileUtil,Logger.getLogger("PlatformManager"));
        PlatformDisplay platformDisplay = new PlatformDisplay();
        KOLDisplay kolDisplay = new KOLDisplay();
        KOLManager kolManager = new KOLManager(platformManager,kolDisplay,fileUtil,input,Logger.getLogger("KOLManager"));

        ConsoleUI go = new ConsoleUI(kolManager,kolDisplay,platformManager,platformDisplay,input);
        go.start();
    }
}