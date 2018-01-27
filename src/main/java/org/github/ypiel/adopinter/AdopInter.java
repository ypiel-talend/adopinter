package org.github.ypiel.adopinter;

import org.github.ypiel.adopinter.gouv.diplomatie.PaysResume;
import org.github.ypiel.adopinter.gouv.diplomatie.PaysResumeCleanerService;

import java.util.List;

public class AdopInter {

    public static void main(String[] args){
        paysResume2Csv();
    }

    public static void paysResume2Csv() {
        final List<PaysResume> paysResumeFromGouvDiplo = PaysResumeCleanerService.getPaysResumeFromGouvDiplo();
        for(PaysResume pr : paysResumeFromGouvDiplo){
            System.out.println("=> " + pr);
        }
    }

}
