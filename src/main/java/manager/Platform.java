package manager;

import tools.FileUtil;
import tools.Workable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for handling platform logic.
 * Key: Platform code, Value: Platform object.
 */
public class Platform extends HashMap<String, Platform> implements Workable {
    private String code, name, des;
    private FileUtil fileUtil = new FileUtil();
    private Logger logger = Logger.getLogger("Platform");

    /**
     * Default constructor. Loads platform data.
     */
    public Platform() {
        readData();
    }

    /**
     * Constructor with fields.
     */
    public Platform(String code, String name, String des) {
        this.code = code;
        this.name = name;
        this.des = des;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    /**
     * Gets the list of platforms.
     * @return List of Platform objects
     */
    public List<Platform> getPlatformList() {
        return new ArrayList<>(this.values());
    }

    /**
     * Gets the platform hashmap.
     * @return HashMap of platforms
     */
    public HashMap<String, Platform> getPlatformHashMap() {
        return this;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void listAll() {

    }

    /**
     * Reads platform data from file.
     */
    @Override
    public void readData() {
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
                this.put(code,new Platform(code,name,des));
            }
            else logger.warning("KOLList.csv is incorrect");
        }
        logger.info("KOLList.csv loaded successfully! - " + this.size());

    }

    @Override
    public void saveData() {

    }
}
