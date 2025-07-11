package manager;

import model.KOL;
import model.Platform;

import java.util.HashMap;

public class PlatformDisplay extends HashMap<String,PlatformDisplay> {
    private String platformName;
    private String platformCode;
    private int numOfKOLs;
    private double aveRate;

    public PlatformDisplay() {}
    public PlatformDisplay(String platformName, String platformCode, int numOfKOLs, double aveRate) {
        this.platformName = platformName;
        this.platformCode = platformCode;
        this.numOfKOLs = numOfKOLs;
        this.aveRate = aveRate;
    }

    private void preStat(KOLManager kolManager) {
        for (Platform p : kolManager.getPlatformList()) {
            int num = 0;
            int rate = 0;
            for (KOL kol : kolManager.getKOLList()) {
                if (kol.getPlatform().equals(p.getName())) {
                    num++;
                    rate += kol.getRate();
                }
            }
            this.put(p.getName(),new PlatformDisplay(p.getName(),p.getCode(),num,num==0 ? 0 : (double)rate/num));
        }
    }
    public void stat(KOLManager kolManager) {
        preStat(kolManager);
        System.out.println("Statistics of Registration by Platform:");
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-18s | %s | %s\n","Platform","Number of KOLs","Avg. Commission Rate");
        System.out.println("----------------------------------------------------------");
        for (PlatformDisplay pd : this.values()) {
            System.out.println(pd);
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("%-18s | %-14d | %-20s",platformName + "("+platformCode+")",numOfKOLs,aveRate+"%");
    }
}
