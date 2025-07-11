package manager;

import model.KOL;
import model.Platform;
import tools.FileUtil;
import tools.Input;
import tools.ValidationsUtils;
import tools.Workable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/******************************
 * Class for handle kol logic
 ******************************/
public class KOLManager implements Workable {
    private final HashMap<String, KOL> KOLList; //Key: kol_id, Value: KOL object
    private final HashMap<String, Platform> platformList; //Key: platform Code, Value: platform objet
    private final KOLDisplay display;
    private final FileUtil fileUtil;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Input input;

    /***************
     * Constructor
     **************/
    public KOLManager() {
        //TODO: load data
        this.KOLList = new HashMap<>();
        platformList = new HashMap<>();
        display = new KOLDisplay();
        this.fileUtil = new FileUtil();
        this.input = new Input();
        readData(); //LOAD KOL List
        loadPlatformList(); //LOAD platform list (Yt01,ig01,...)
    }

    //TODO: adding some functions: add, delete, update,...

    @Override
    public boolean add() {
        String id = input.getString("Enter KOL Id: ", ValidationsUtils.ID,"Invalid format!",false);
        id = id.toUpperCase(); //Make sure to valid in any format
        if (KOLList.containsKey(id)) {
            System.out.println("This KOL has not registered yet.");
            return false;
        }

        KOL newKOL = input.getKol(false);
        newKOL.setKolID(id);
        KOLList.put(id,newKOL);
        return true;
    }

    @Override
    public boolean update() {
        String id = input.getString("Enter KOL Id to update: ",ValidationsUtils.ID,
                "Invalid format!", false);
        id = id.toUpperCase();
        if (!KOLList.containsKey(id)) {
            System.out.println("This KOL haven't registered yet. Try another id!");
            return false;
        }

        KOL oldKOL = KOLList.get(id);
        KOL newKOL = input.getKol(true);
        if (newKOL.getName().isEmpty()) newKOL.setName(oldKOL.getName());
        if (newKOL.getPhoneNum().isEmpty()) newKOL.setPhoneNum(oldKOL.getPhoneNum());
        if (newKOL.getEmail().isEmpty()) newKOL.setEmail(oldKOL.getEmail());
        if (newKOL.getPlatform().isEmpty()) newKOL.setPlatform(oldKOL.getPlatform());
        if (newKOL.getFollowers() == -1L) newKOL.setFollowers(oldKOL.getFollowers());
        newKOL.updateRate();
        newKOL.setKolID(id);

        KOLList.put(id,newKOL);
        return true;
    }

    @Override
    public boolean delete() {
        String id = input.getString("Enter KOL Id to remove: ",ValidationsUtils.ID,
                "Invalid format!", false);
        if (!KOLList.containsKey(id)) {
            System.out.println("This KOL haven't registered yet. Try another id!");
            return false;
        }
        display.displayKOLDetails(KOLList.get(id));
        System.out.println("Are you sure to delete this KOL? (y/n): ");
        if (input.getYesNo()) {
            KOLList.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void listAllKOLs() {
        display.displayKOLs(getKOLList(),"No KOLs have registered yet. Please try later...");
    }

    public List<KOL> searchingKOL() {
        String name = input.getString("Enter the name or partial name of the KOL: ",null, "Invalid format!",false);
        List<KOL> searchedList = new ArrayList<>();
        for (KOL kol : getKOLList()) {
            if (kol.getName().toLowerCase().contains(name.toLowerCase()))
                searchedList.add(kol);
        }
        return searchedList;
    }

    public List<KOL> filterByCategories() {
        String category = input.getString("Enter a category code (BT, FS, BC, GM, TL): ",ValidationsUtils.CATEGORY,"Invalid format!",false);
        List<KOL> filteredList = new ArrayList<>();
        for (KOL kol : getKOLList()) {
            if (kol.getKolID().toLowerCase().contains(category.toLowerCase()))
                filteredList.add(kol);
        }
        return filteredList;
    }


    public boolean isEmpty() {
        return KOLList.isEmpty();
    }

    @Override
    public void readData() {
        loadKOLList();
    }

    @Override
    public void saveData() {
        saveKOLList();
    }

    public List<Platform> getPlatformList() {
        return new ArrayList<>(platformList.values());
    }

    public HashMap<String, Platform> getPlatformHashMap() {
        return platformList;
    }

    public List<KOL> getKOLList() {
        return new ArrayList<>(KOLList.values());
    }

    private void loadPlatformList() {
        List<String> lines = fileUtil.readPlatformList("KOLList.csv");
        if (lines.isEmpty()) {
            System.out.println("KOLList.csv is empty");
            return ;
        }
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String code = parts[0].trim();
                String name = parts[1].trim();
                String des = parts[2].trim();
                platformList.put(code,new Platform(code,name,des));
            }
            else logger.warning("KOLList.csv is incorrect");
        }
        logger.info("KOLList.csv loaded successfully! - " + platformList.size());

    }
    private void loadKOLList() {
        List<KOL> KOLListLoaded = fileUtil.readObjectListFromFile("KOLs.dat");
        for (KOL kol : KOLListLoaded) {
            KOLList.put(kol.getKolID(),kol);
        }
        logger.info("KOLList.dat loaded successfully! - " + KOLList.size());
    }
    private void saveKOLList() {
        fileUtil.writeObjectListToFile(new ArrayList<>(KOLList.values()),"KOLs.dat");
    }
}
