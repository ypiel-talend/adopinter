package org.github.ypiel.adopinter.http;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CleanerClient {

    private static final String GOUV_DIP_PAYS_URL = "https://www.diplomatie.gouv.fr/fr/adopter-a-l-etranger/les-conditions-de-l-adoption-internationale/les-fiches-pays-de-l-adoption-internationale/fiches-pays-adoption/";

    private static final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();


    public static TagNode getPaysResume() throws IOException, SAXException, ParserConfigurationException {
        final Content content = Request.Get(GOUV_DIP_PAYS_URL).execute().returnContent();

        HtmlCleaner hc = new HtmlCleaner();
        return hc.clean(content.asStream());
    }

}
