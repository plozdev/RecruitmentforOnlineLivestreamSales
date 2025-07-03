package model;

import java.util.Objects;

public class kol {
    String kolID, name, phoneNum, email, platformCode;
    long followers;
    int rate;

    public kol() {
        this.kolID = this.name = this.phoneNum = this.email = this.platformCode = "";
        this.followers = 0;
        this.rate = 20; //default
    }

    public kol(String kolID, String name, String phoneNum, String email, String platformCode, long followers) {
        this.kolID = kolID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.platformCode = platformCode;
        this.followers = followers;
    }

    public kol(String name, String phoneNum, String email, String platformCode, long followers) {
        this.kolID = "N/A";
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.platformCode = platformCode;
        this.followers = followers;
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

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
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
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        kol kol = (kol) o;
        return Objects.equals(kolID, kol.kolID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(kolID);
    }
}
