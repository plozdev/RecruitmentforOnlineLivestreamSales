package model;

/**
 * Class for Platform data
 */
public class Platform  {
    private String code, name, des;

    /**
     * Default constructor.
     */
    public Platform() {
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

}
