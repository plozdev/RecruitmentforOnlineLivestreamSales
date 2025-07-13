package manager;

import model.KOL;

import java.util.List;

/**
 * Utility class for displaying KOL information and lists.
 */
public class KOLDisplay {
    /**
     * Default constructor.
     */
    public KOLDisplay() {
    }

    /**
     * Handles empty list display logic.
     * @param list List to check
     * @param message Message to display if empty
     * @return true if list is empty, false otherwise
     */
    public <T> boolean handleEmptyList(List<T> list, String message) {
        if (list.isEmpty()) {
            System.out.println(message);
            return true; // Trả về true để báo hiệu đã xử lý và cần return ở hàm gọi
        }
        return false; // Trả về false nếu danh sách không rỗng
    }

    /**
     * Displays a formatted list of KOLs.
     * @param kolList List of KOLs
     * @param emptyMsg Message if list is empty
     */
    public void displayKOLs(List<KOL> kolList, String emptyMsg) {
        if (handleEmptyList(kolList, emptyMsg)) {
            return; // Dừng lại nếu danh sách rỗng
        }

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-10s | %-10s | %-15s | %-10s \n","KOL ID","Name","Phone","Platform","Followers","Commission");
        System.out.println("--------------------------------------------------------------------------------------");
        for (KOL k : kolList) System.out.println(k);
        System.out.println("--------------------------------------------------------------------------------------");
    }

    /**
     * Displays detailed information for a single KOL.
     * @param kol The KOL to display
     */
    public void displayKOLDetails(KOL kol) {
        System.out.println("KOL Details: ");
        System.out.println("---------------------------------------------------");
        System.out.println(kol.KOLDetail());
        System.out.println("---------------------------------------------------");
    }
}
