package com.example.checkers;

public class DBObjects {
    private int ID;
    private String Name;
    private int EasyWin;
    private int EasyLose;
    private int MediumWin;
    private int MediumLose;
    private int HardWin;
    private int HardLose;

    public DBObjects(int id, String name, int easyWin, int easyLose, int mediumWin, int mediumLose, int hardWin, int hardLose){
        ID = id;
        Name = name;
        EasyWin = easyWin;
        EasyLose = easyLose;
        MediumWin = mediumWin;
        MediumLose = mediumLose;
        HardWin = hardWin;
        HardLose = hardLose;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getEasyWin() {
        return EasyWin;
    }

    public void setEasyWin(int easyWin) {
        EasyWin = easyWin;
    }

    public int getEasyLose() {
        return EasyLose;
    }

    public void setEasyLose(int easyLose) {
        EasyLose = easyLose;
    }

    public int getMediumWin() {
        return MediumWin;
    }

    public void setMediumWin(int mediumWin) {
        MediumWin = mediumWin;
    }

    public int getMediumLose() {
        return MediumLose;
    }

    public void setMediumLose(int mediumLose) {
        MediumLose = mediumLose;
    }

    public int getHardWin() {
        return HardWin;
    }

    public void setHardWin(int hardWin) {
        HardWin = hardWin;
    }

    public int getHardLose() {
        return HardLose;
    }

    public void setHardLose(int hardLose) {
        HardLose = hardLose;
    }
}
