package org.github.ypiel.adopinter.gouv.diplomatie;

import org.github.ypiel.adopinter.http.CleanerClient;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaysResumeCleanerService {

    private final static String PAYS_RESUME_TABLE_CRITERI = "nombre d’enfants adoptés";
    private final static String IMG_COUPLE_SANS_ENFANT = "IMG/png/cple.png";
    private final static String IMG_COUPLE_AVEC_ENFANT = "IMG/png/folks02.png";
    private final static String IMG_CELIB = "IMG/png/girl1.png";
    private final static String TD_HEADERS_C1 = "_c1";
    private final static String TD_HEADERS_C2 = "_c2";
    private final static String ISLAHAYE_VALUE = "oui";


    public static List<PaysResume> getPaysResumeFromGouvDiplo() {
        List<PaysResume> prs = new ArrayList<>();
        try {
            final TagNode paysResumeTG = CleanerClient.getPaysResume();
            final List tables = paysResumeTG.getElementListByName("table", true);

            boolean found = false;
            TagNode ttn = null;
            for (Object t : tables) {
                ttn = (TagNode) t;
                found = ttn.getAttributeByName("summary").contains(PAYS_RESUME_TABLE_CRITERI);
                if (found) {
                    break;
                }
            }

            if (!found) {
                System.out.println("Impossible detrouver le tableau résumé des pays");
                return new ArrayList<>();
            }

            final List trs = ttn.getElementListByName("tr", true);
            for (Object tr : trs) {
                TagNode trtn = (TagNode) tr;
                PaysResume pr = new PaysResume();

                final List ths = trtn.getElementListByName("th", true);
                if (ths.size() <= 0) {
                    System.out.println("Can't find country name, skip...");
                    break;
                }
                String name = ((TagNode) ths.get(0)).getText().toString();
                pr.setCountry(name);
                prs.add(pr);

                final List imgs = trtn.getElementListByName("img", true);
                for (Object o : imgs) {
                    TagNode tn = (TagNode) o;
                    if (((TagNode) o).getAttributeByName("src").contains(IMG_CELIB)) {
                        pr.setCelibAccepted(true);
                    }
                    if (((TagNode) o).getAttributeByName("src").contains(IMG_COUPLE_SANS_ENFANT)) {
                        pr.setMarriedNochilderAccpeted(true);
                    }
                    if (((TagNode) o).getAttributeByName("src").contains(IMG_COUPLE_AVEC_ENFANT)) {
                        pr.setMarriedWithChildAccepted(true);
                    }
                }

                final List tds = trtn.getElementListByName("td", true);
                for (Object o : tds) {
                    TagNode td = (TagNode) o;
                    if (((TagNode) o).getAttributeByName("headers").contains(TD_HEADERS_C1)) {
                        String snb = td.getText().toString();
                        try {
                            final int nb = Integer.parseInt(snb);
                            pr.setNbAdoptChild(nb);
                        } catch (Exception e) {
                            System.out.println("Can't get nb children from : " + snb);
                        }
                    }
                    if (((TagNode) o).getAttributeByName("headers").contains(TD_HEADERS_C2)) {
                        String slh = td.getText().toString();
                        pr.setLahaye(ISLAHAYE_VALUE.equals(slh.toLowerCase()));
                    }
                }
            }


        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return prs;
    }

}
