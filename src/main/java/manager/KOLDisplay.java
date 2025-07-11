package manager;

import model.KOL;

import java.util.List;

public class KOLDisplay {
    public KOLDisplay() {
    }

    public <T> boolean handleEmptyList(List<T> list, String message) {
        if (list.isEmpty()) {
            System.out.println(message);
            return true; // Trả về true để báo hiệu đã xử lý và cần return ở hàm gọi
        }
        return false; // Trả về false nếu danh sách không rỗng
    }

    public void displayKOLs(List<KOL> kolList, String emptyMsg) {
        if (handleEmptyList(kolList, emptyMsg)) {
            return; // Dừng lại nếu danh sách rỗng
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-18s | %-10s | %-10s | %-12s | %-10s\n","KOL ID","Name","Phone","Platform","Followers","Commission");
        System.out.println("-------------------------------------------------------------------------------------");
        for (KOL k : kolList) System.out.println(k);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void displayKOLDetails(KOL kol) {
        System.out.println("KOL Details: ");
        System.out.println("---------------------------------------------------");
        System.out.println(kol.KOLDetail());
        System.out.println("---------------------------------------------------");
    }
}
