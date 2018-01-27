package org.github.ypiel.adopinter.gouv.diplomatie;

public class PaysResume {

    private String country;
    private int nbAdoptChild;
    private boolean isLahaye;
    private boolean marriedNochilderAccpeted;
    private boolean marriedWithChildAccepted;
    private boolean celibAccepted;

    public PaysResume(){}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNbAdoptChild() {
        return nbAdoptChild;
    }

    public PaysResume setNbAdoptChild(int nbAdoptChild) {
        this.nbAdoptChild = nbAdoptChild;
        return this;
    }

    public boolean isLahaye() {
        return isLahaye;
    }

    public PaysResume setLahaye(boolean lahaye) {
        isLahaye = lahaye;
        return this;
    }

    public boolean isMarriedNochilderAccpeted() {
        return marriedNochilderAccpeted;
    }

    public PaysResume setMarriedNochilderAccpeted(boolean marriedNochilderAccpeted) {
        this.marriedNochilderAccpeted = marriedNochilderAccpeted;
        return this;
    }

    public boolean isMarriedWithChildAccepted() {
        return marriedWithChildAccepted;
    }

    public PaysResume setMarriedWithChildAccepted(boolean marriedWithChildAccepted) {
        this.marriedWithChildAccepted = marriedWithChildAccepted;
        return this;
    }

    public boolean isCelibAccepted() {
        return celibAccepted;
    }

    public PaysResume setCelibAccepted(boolean celibAccepted) {
        this.celibAccepted = celibAccepted;
        return this;
    }

    public String toString(){
        return getCountry() + ": "+isLahaye+" | "+nbAdoptChild+" | "+marriedNochilderAccpeted+" / "+marriedWithChildAccepted+" / "+celibAccepted;
    }
}
