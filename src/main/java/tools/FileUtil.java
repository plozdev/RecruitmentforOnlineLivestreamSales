package tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/******************************
 * Class for read/write files
 **************************/
public class FileUtil {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //----------- Doc CSV tu Classpath ---------------
    public List<String> readKOLList(String resName){
        List<String> lines = new ArrayList<>();

        //Dung Classloader
        InputStream is = getClass().getClassLoader().getResourceAsStream(resName);
        if (is == null) {
            logger.severe("Resource not found in classpath: " + resName);
            return lines;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.readLine(); //SKIP header
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV resource: " + e);
        }

        return lines;
    }


    // --- Phương thức đọc Object List từ file (dùng cho KOL data) ---
    public <T> List<T> readObjectListFromFile(String fileName) {
        List<T> objects = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            logger.info("Data file not found: " + fileName + ". Starting with empty list.");
            return objects;
        }

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            objects = (List<T>) ois.readObject();
            logger.info("Successfully loaded " + objects.size() + " objects from " + fileName);
        } catch (EOFException e) {
            // File rỗng, không có gì để đọc
            logger.info("Data file " + fileName + " is empty or malformed EOF. Starting with empty list.");
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Error reading object list from file '" + fileName + "': " + e.getMessage());
            throw new RuntimeException("Failed to read object list from file: " + fileName, e);
        }
        return objects;
    }


    // --- Phương thức ghi Object List ra file (dùng cho KOL data) ---
    public <T> void writeObjectListToFile(List<T> objects, String fileName) {
        // Đảm bảo thư mục cha tồn tại nếu đường dẫn có thư mục con
        File file = new File(fileName);
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Tạo thư mục nếu chưa có
            }
        } catch (SecurityException e) {
            logger.severe("Permission denied to create directories for file: " + fileName + " - " + e.getMessage());
            throw new RuntimeException("Permission denied to write file: " + fileName, e);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(objects);
            oos.flush();
            logger.info("Successfully saved " + objects.size() + " objects to " + fileName);
        } catch (IOException e) {
            logger.severe("Error writing object list to file '" + fileName + "': " + e.getMessage());
            throw new RuntimeException("Failed to write object list to file: " + fileName, e);
        }
    }
}
