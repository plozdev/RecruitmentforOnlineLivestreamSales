package model;

public class StatObject {
    private String platformName;
    private String platformCode;
    private int numOfKOLs;
    private int totalRate;
    private double aveRate;

    public StatObject(String platformName, String platformCode) {
        this.platformName = platformName;
        this.platformCode = platformCode;
        this.numOfKOLs = 0;
        this.totalRate = 0;
    }

    public void addKOL(int rate) {
        this.numOfKOLs++;
        this.totalRate += rate;
    }

    public void calculateAverage() {
        if (numOfKOLs > 0) {
            this.aveRate = (double) totalRate / numOfKOLs;
        }
    }


    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public int getNumOfKOLs() {
        return numOfKOLs;
    }

    public void setNumOfKOLs(int numOfKOLs) {
        this.numOfKOLs = numOfKOLs;
    }

    public double getAveRate() {
        return aveRate;
    }

    public void setAveRate(double aveRate) {
        this.aveRate = aveRate;
    }
}
