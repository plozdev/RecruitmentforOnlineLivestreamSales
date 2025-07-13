package manager;

import model.KOL;
import model.StatObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for displaying platform statistics.
 * Key: platform name, Value: Stat object of platform
 */
public class PlatformDisplay {

    /**
     * Default constructor.
     */
    public PlatformDisplay() {}

    /**
     * Displays statistics for all platforms.
     * @param kolManager KOL manager
     * @param platformManager Platform data
     */
    public void stat(KOLManager kolManager, PlatformManager platformManager) {
        Map<String, StatObject> statsMap = new HashMap<>();
        for (KOL kol : kolManager.getKOLList()) {
            String platformName = kol.getPlatform();

            StatObject stat = statsMap.computeIfAbsent(platformName, k -> {
                String platformCode = platformManager.getPlatformCode().get(k); // Lấy code từ tên platform
                return new StatObject(k, platformCode);
            });

            stat.addKOL(kol.getRate());
        }

        for (StatObject stat : statsMap.values()) {
            stat.calculateAverage();
        }

        System.out.println("Statistics of Registration by Platform:");
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-18s | %s | %s\n","Platform","Number of KOLs","Avg. Commission Rate");
        System.out.println("----------------------------------------------------------");

        for (StatObject stat : statsMap.values()) {
            String avgRateFormatted = String.format("%.1f%%", stat.getAveRate());
            System.out.printf("%-18s | %-14d | %s\n",
                    stat.getPlatformName() + " (" + stat.getPlatformCode() + ")",
                    stat.getNumOfKOLs(),
                    avgRateFormatted);
        }
        System.out.println("----------------------------------------------------------");
    }

}
