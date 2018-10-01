package radar.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XPathUtils {

    private static final XPath XPATH = XPathFactory.newInstance().newXPath();

    public static Object getOne(Document document, String xpath) {
        List<Object> results = get(document, xpath);
        return results.isEmpty() ? null : results.get(0);
    }

    public static List<Object> get(Document document, String xpath) {

        List<Object> results = new ArrayList<>();

        try {
            XPathExpression xPathExpression = XPATH.compile(xpath);
            NodeList nodes = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                results.add(parseNode(nodes.item(i)));
            }
        }
        catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    private static Object parseNode(Node node) {

        NodeList children = node.getChildNodes();

        if (children.getLength() == 1) { // Only has #text (leaf text)
            return node.getTextContent();
        }

        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < children.getLength(); i++) {

            Node child = children.item(i);

            if (child.hasChildNodes()) { // Not a #text or #comment
                result.put(child.getNodeName(), parseNode(child));
            }
        }

        return result;
    }
}
