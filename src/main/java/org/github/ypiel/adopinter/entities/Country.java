package org.github.ypiel.adopinter.entities;

public class Country {

    private int id = -1;
    private String name;
    private boolean isLahaye;
    private int nbChildren;
    private int nbMarriedYear;
    private boolean isOpen;
    private String comment;

    public Country(){}

    public Country(String name, boolean isLahaye, int nbChildren, int nbMarriedYear, boolean isOpen, String comment) {
        this.name = name;
        this.isLahaye = isLahaye;
        this.nbChildren = nbChildren;
        this.nbMarriedYear = nbMarriedYear;
        this.isOpen = isOpen;
        this.comment = comment;
    }

    public Country(int id, String name, boolean isLahaye, int nbChildren, int nbMarriedYear, boolean isOpen, String comment) {
        this.id = id;
        this.name = name;
        this.isLahaye = isLahaye;
        this.nbChildren = nbChildren;
        this.nbMarriedYear = nbMarriedYear;
        this.isOpen = isOpen;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLahaye() {
        return isLahaye;
    }

    public void setLahaye(boolean lahaye) {
        isLahaye = lahaye;
    }

    public int getNbChildren() {
        return nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }

    public int getNbMarriedYear() {
        return nbMarriedYear;
    }

    public void setNbMarriedYear(int nbMarriedYear) {
        this.nbMarriedYear = nbMarriedYear;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isLahaye=" + isLahaye +
                ", nbChildren=" + nbChildren +
                ", nbMarriedYear=" + nbMarriedYear +
                ", isOpen=" + isOpen +
                ", comment='" + comment + '\'' +
                '}';
    }
}
