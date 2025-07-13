package manager;

import model.Platform;
import tools.FileUtil;
import tools.Workable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Key: code, Value: code, name, des
 */
public class PlatformManager implements Workable {
    private final HashMap<String,Platform> platforms;
    private final FileUtil fileUtil;
    private final Logger logger;

    /**
     * Default Constructor + Load data
     */
    public PlatformManager(FileUtil fileUtil, Logger logger) {
        this.platforms = new HashMap<>();
        this.fileUtil = fileUtil;
        this.logger = logger;
        readData();
    }


    /**
     * Gets the list of platforms.
     * @return List of Platform objects
     */
    public List<Platform> getPlatformList() {
        return new ArrayList<>(platforms.values());
    }

    /**
     * Gets the platform hashmap.
     * @return HashMap of platforms
     */
    public HashMap<String, Platform> getPlatformHashMap() {
        return platforms;
    }

    /**
     * Gets the platform code by platform name
     * @return HashMap of platform name - code
     */
    public HashMap<String,String> getPlatformCode() {
        HashMap<String,String> map = new HashMap<>();
        for (Platform platform : platforms.values()) {
            map.put(platform.getName(),platform.getCode());
        }
        return map;
    }

    /**
     * Gets the platform name by platform code
     * @return HashMap of platform code - name
     */
    public HashMap<String,String> getPlatformName() {
        HashMap<String,String> map = new HashMap<>();
        for (Platform platform : platforms.values()) {
            map.put(platform.getCode(),platform.getName());
        }
        return map;
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
                platforms.put(code,new Platform(code,name,des));
            }
//            else logger.warning("KOLList.csv is incorrect");
        }
//        logger.info("KOLList.csv loaded successfully! - " + platforms.size());

    }

    @Override
    public void saveData() {

    }
}
