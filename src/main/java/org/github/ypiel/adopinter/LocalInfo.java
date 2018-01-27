package org.github.ypiel.adopinter;

import org.github.ypiel.adopinter.db.DerbyLocalInfo;
import org.github.ypiel.adopinter.entities.Country;
import org.github.ypiel.adopinter.gouv.diplomatie.PaysResume;
import org.github.ypiel.adopinter.gouv.diplomatie.PaysResumeCleanerService;

import java.util.List;

public class LocalInfo {

    public static void main(String[] args){
        DerbyLocalInfo db = DerbyLocalInfo.getInstance();

        final List<PaysResume> paysResumeFromGouvDiplo = PaysResumeCleanerService.getPaysResumeFromGouvDiplo();
        for(PaysResume pr : paysResumeFromGouvDiplo){
            db.insertCountry(new Country(pr.getCountry(), pr.isLahaye(), pr.getNbAdoptChild(), 0, true, ""));
        }

        final List<Country> allCountries = db.getAllCountries();
        for(Country c : allCountries){
            System.out.println("==> " + c);
        }
    }

}
