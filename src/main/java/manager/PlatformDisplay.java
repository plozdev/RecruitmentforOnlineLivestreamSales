package manager;

import model.KOL;

import java.util.HashMap;

/**
 * Utility class for displaying platform statistics.
 */
public class PlatformDisplay extends HashMap<String,PlatformDisplay> {
    private String platformName;
    private String platformCode;
    private int numOfKOLs;
    private double aveRate;

    /**
     * Default constructor.
     */
    public PlatformDisplay() {}

    /**
     * Constructor with fields.
     */
    public PlatformDisplay(String platformName, String platformCode, int numOfKOLs, double aveRate) {
        this.platformName = platformName;
        this.platformCode = platformCode;
        this.numOfKOLs = numOfKOLs;
        this.aveRate = aveRate;
    }

    /**
     * Prepares statistics for each platform.
     * @param kolManager KOL manager
     * @param platform Platform data
     */
    private void preStat(KOLManager kolManager, Platform platform) {
        for (Platform p : platform.getPlatformList()) {
            int num = 0;
            int rate = 0;
            for (KOL kol : kolManager.getKOLList()) {
                if (kol.getPlatform().equalsIgnoreCase(p.getName())) {
                    num++;
                    rate += kol.getRate();
                }
            }
            this.put(p.getName(),new PlatformDisplay(p.getName(),p.getCode(),num,num==0 ? 0 : (double)rate/num));
        }
    }
    /**
     * Displays statistics for all platforms.
     * @param kolManager KOL manager
     * @param platform Platform data
     */
    public void stat(KOLManager kolManager, Platform platform) {
        preStat(kolManager, platform);
        System.out.println("Statistics of Registration by Platform:");
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-18s | %s | %s\n","Platform","Number of KOLs","Avg. Commission Rate");
        System.out.println("----------------------------------------------------------");
        for (PlatformDisplay pd : this.values()) {
            System.out.println(pd);
        }
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Returns a formatted string for platform statistics.
     */
    @Override
    public String toString() {
        return String.format("%-18s | %-14d | %-20s",platformName + " ("+platformCode+")",numOfKOLs,aveRate+"%");
    }
}
