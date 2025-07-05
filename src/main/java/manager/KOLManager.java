package manager;

import model.KOL;
import model.Platform;
import tools.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/******************************
 * Class for handle kol logic
 ******************************/
public class KOLManager {
    private final HashMap<String, KOL> KOLList; //Key: kolid, Value: KOL object
    private final HashMap<String, Platform> platformList; //Key: platform Code, Value: platform objet
    private final FileUtil fileUtil;
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    /***************
     * Constructor
     **************/
    public KOLManager() {
        //TODO: load data
        this.KOLList = new HashMap<>();
        this.platformList = new HashMap<>();
        this.fileUtil = new FileUtil();

        loadKOLList();
        loadPlatformList();

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
                String code = parts[0];
                String name = parts[1];
                String des = parts[2];
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

    //TODO: adding some functions: add, delete, update,...

    





}
