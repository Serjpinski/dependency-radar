package radar.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

public class XPathUtils {

    private static final XPath XPATH = XPathFactory.newInstance().newXPath();

    public static List<String> xpath(Document document, String xpath) {

        List<String> results = new ArrayList<>();

        try {
            XPathExpression nameXPath = XPATH.compile(xpath);
            NodeList nodes = (NodeList) nameXPath.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                results.add(nodes.item(i).getNodeValue());
            }
        }
        catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}
