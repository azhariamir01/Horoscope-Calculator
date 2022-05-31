//I state and acknowledge the fact that the code to read/add from/to an XML file in Java is taken from the internet,
//so it might be similar to some other colleagues that also did the DAO assignment
// https://initialcommit.com/blog/how-to-read-xml-file-in-java   -- this one is to read a value from an xml
// https://stackoverflow.com/questions/28803444/how-do-i-insert-value-in-to-xml-tag  -- this one is to add a value to xml

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentsXML implements StudentsInterface {

    private String _file;

    public StudentsXML(String file){
        this._file = file;
    }

    @Override
    public List<Students> getAllStudents() {
        List<Students> allStudents = new ArrayList<>();

        try {
            File xmlFile = new File(this._file);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList studentList = doc.getElementsByTagName("student");

            for(int i = 0; i < studentList.getLength(); i++){
                Node studentNode = studentList.item(i);

                if(studentNode.getNodeType() == Node.ELEMENT_NODE){
                    Element studentElement = (Element) studentNode;

                    String name = studentElement.getElementsByTagName("name").item(0).getTextContent();

                    Students s = new Students();
                    s.setName(name);

                    allStudents.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allStudents;
    }
    @Override
    public void addStudent(String name) {
        try{
            File xmlFile = new File(this._file);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            Element element = doc.getDocumentElement();

            Element nameNode = doc.createElement("name");
            nameNode.setTextContent(name);

            Element nodeElement = doc.createElement("student");
            nodeElement.appendChild(nameNode);

            element.appendChild(nodeElement);
            doc.replaceChild(element, element);

            Transformer tFormer = TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
            Source source = new DOMSource(doc);
            Result result = new StreamResult(xmlFile);

            tFormer.transform(source, result);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}