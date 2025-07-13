package manager;

import model.KOL;
import tools.FileUtil;
import tools.Input;
import tools.ValidationsUtils;
import tools.Workable;

import java.util.*;
//import java.util.logging.Logger;

/******************************
 * Class for handle kol logic
 * Key: KOL ID, Value KOL object
 ******************************/
public class KOLManager extends HashMap<String,KOL> implements Workable {
    private final KOLDisplay display;
    private final FileUtil fileUtil;
//    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Input input;

    /***************
     * Constructor
     **************/
    public KOLManager() {
        //TODO: load data
        this.display = new KOLDisplay();
        this.fileUtil = new FileUtil();
        this.input = new Input();
        readData(); //LOAD KOL List
    }

    //TODO: adding some functions: add, delete, update,...

    /**
     * Add KOL with validation data
     * @return true if adds successful, otherwise false
     */
    @Override
    public boolean add() {
        String id = input.getString("Enter KOL Id: ", ValidationsUtils.ID,"Invalid format!",false);
        id = id.toUpperCase(); //Make sure to valid in any format
        if (this.containsKey(id)) {
            System.out.println("This KOL has not registered yet.");
            return false;
        }

        KOL newKOL = input.getKol(false);
        newKOL.setKolID(id);
        this.put(id,newKOL);
        return true;
    }

    /**
     * Update KOL information
     * If user enter nothing, skip
     * @return true if update successful, otherwise false
     */
    @Override
    public boolean update() {
        String id = input.getString("Enter KOL Id to update: ",ValidationsUtils.ID,
                "Invalid format!", false);
        id = id.toUpperCase();//Make sure to valid in any format
        if (!this.containsKey(id)) {
            System.out.println("This KOL haven't registered yet. Try another id!");
            return false;
        }

        display.displayKOLDetails(this.get(id));

        KOL oldKOL = this.get(id);
        KOL newKOL = input.getKol(true);
        if (newKOL.getName().isEmpty()) newKOL.setName(oldKOL.getName());
        if (newKOL.getPhoneNum().isEmpty()) newKOL.setPhoneNum(oldKOL.getPhoneNum());
        if (newKOL.getEmail().isEmpty()) newKOL.setEmail(oldKOL.getEmail());
        if (newKOL.getPlatform().isEmpty()) newKOL.setPlatform(oldKOL.getPlatform());
        if (newKOL.getFollowers() == -1L) newKOL.setFollowers(oldKOL.getFollowers());
        newKOL.updateRate();
        newKOL.setKolID(id);

        this.put(id,newKOL);

        display.displayKOLDetails(this.get(id));

        return true;
    }

    /**
     * Delete KOL in the list
     * Make sure user want to delete
     * @return true if delete successful, otherwise false
     */
    @Override
    public boolean delete() {
        String id = input.getString("Enter KOL Id to remove: ",ValidationsUtils.ID,
                "Invalid format!", false);
        id = id.toUpperCase(); //Make sure to valid in any format

        if (!this.containsKey(id)) {
            System.out.println("This KOL haven't registered yet. Try another id!");
            return false;
        }
        display.displayKOLDetails(this.get(id));
        System.out.println("Are you sure to delete this KOL? (y/n): ");
        if (input.getYesNo()) {
            this.remove(id);
            return true;
        }
        return false;
    }

    /**
     * List all of KOLs in the system
     */
    @Override
    public void listAll() {
        display.displayKOLs(getKOLList(),"No KOLs have registered yet. Please try later...");
    }

    /**
     * Search any KOLs fit the name or the partial name from user input
     * @return List of KOLs that fit the user input
     */
    public List<KOL> searchingKOL() {
        String name = input.getString("Enter the name or partial name of the KOL: ",null, "Invalid format!",false);
        List<KOL> searchedList = new ArrayList<>();
        for (KOL kol : getKOLList()) {
            if (kol.getName().toLowerCase().contains(name.toLowerCase()))
                searchedList.add(kol);
        }
        return searchedList;
    }

    /**
     * Filter KOls with category code BT, FS, BC,...
     * @return list of KOLs after filtered
     */
    public List<KOL> filterByCategories() {
        String category = input.getString("Enter a category code (BT, FS, BC, GM, TL): ",ValidationsUtils.CATEGORY,"Invalid format!",false);
        List<KOL> filteredList = new ArrayList<>();
        for (KOL kol : getKOLList()) {
            if (kol.getKolID().toLowerCase().contains(category.toLowerCase()))
                filteredList.add(kol);
        }
        return filteredList;
    }

    /**
     * Loading data from file
     */
    @Override
    public void readData() {
        List<KOL> KOLListLoaded = fileUtil.readObjectListFromFile("kol_registrations.dat");
        for (KOL kol : KOLListLoaded) {
            this.put(kol.getKolID(),kol);
        }
//        logger.info("KOLList.dat loaded successfully! - " + this.size());
    }

    /**
     * Saving data
     */
    @Override
    public void saveData() {
        System.out.println("Saving data...");
        try {
            Thread.sleep(1500); // Pauses execution for 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (fileUtil.writeObjectListToFile(new ArrayList<>(this.values()),"kol_registrations.dat"))
            System.out.println("Registration data has been successfully saved to 'kol_registrations.dat'");
        else
            System.err.println("Error while saving registration data!");
    }

    /**
     * @return KOL List
     */
    public List<KOL> getKOLList() {
        return new ArrayList<>(this.values());
    }

}
