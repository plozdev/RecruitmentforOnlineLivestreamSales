package model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class KOL implements Serializable {
    private static final long serialVersionUID = 1L;
    String kolID, name, phoneNum, email, platform;
    long followers;
    int rate;

    public KOL() {
        this.kolID = this.name = this.phoneNum = this.email = this.platform = "";
        this.followers = 0;
        this.rate = 20; //default
    }

    public KOL(String kolID, String name, String phoneNum, String email, String platform, long followers) {
        this.kolID = kolID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.platform = platform;
        this.followers = followers;
        updateRate();
    }

    public KOL(String name, String phoneNum, String email, String platform, long followers) {
        this.kolID = "N/A";
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.platform = platform;
        this.followers = followers;
        updateRate();
    }

    public String getKolID() {
        return kolID;
    }

    public void setKolID(String kolID) {
        this.kolID = kolID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public int getRate() {
        return rate;
    }

    public void updateRate() {
        this.rate = (this.followers >= 1000000) ? 25 : 20;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getInstance();
        return String.format("%-10s | %-15s | %-10s | %-10s | %-15s | %-10s ",
                kolID, name, phoneNum,platform,formatter.format(followers), rate+"%");
    }

    public String KOLDetail() {
        NumberFormat formatter = NumberFormat.getInstance();
        return String.format("KOL ID    : %s\n" +
                "Name      : %s\n" +
                "Phone     : %s\n" +
                "Platform  : %s\n" +
                "Followers : %s\n" +
                "Commission: %s",
                kolID,name,phoneNum,platform, formatter.format(followers), rate+"%");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KOL kol = (KOL) o;
        return Objects.equals(kolID, kol.kolID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(kolID);
    }
}
