package org.github.ypiel.adopinter;

import org.github.ypiel.adopinter.gouv.diplomatie.PaysResume;
import org.github.ypiel.adopinter.gouv.diplomatie.PaysResumeCleanerService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdopInter {

    public static void main(String[] args){
        paysResume2Csv();
    }

    public static void paysResume2Csv() {
        final List<PaysResume> paysResumeFromGouvDiplo = PaysResumeCleanerService.getPaysResumeFromGouvDiplo();
        Collections.sort(paysResumeFromGouvDiplo, new Comparator<PaysResume>() {
            @Override
            public int compare(PaysResume o1, PaysResume o2) {
                return o2.getNbAdoptChild() - o1.getNbAdoptChild();
            }
        });

        for(PaysResume pr : paysResumeFromGouvDiplo){
            System.out.println("=> " + pr);
        }
    }

}
