package XMLParse;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author houdehong
 * @date 2019/5/13
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class Dom {
    /**
     * DOM��ȫ����Document Object Model��Ҳ���ĵ�����ģ�͡���Ӧ�ó����У�����DOM��XML��������һ��XML�ĵ�ת����һ������ģ�͵ļ��ϣ�ͨ����DOM������
     * Ӧ�ó�������ͨ�����������ģ�͵Ĳ�������ʵ�ֶ�XML�ĵ����ݵĲ�����ͨ��DOM�ӿڣ�Ӧ�ó���������κ�ʱ�����XML�ĵ��е��κ�һ�������ݣ�
     * ��ˣ���������DOM�ӿڵĻ���Ҳ������������ʻ��ơ�
     *
     * DOM�ӿ��ṩ��һ��ͨ���ֲ����ģ��������XML�ĵ���Ϣ�ķ�ʽ����Щ�ֲ����ģ������XML���ĵ��ṹ�γ���һ�ýڵ�����
     * ����XML�ĵ�������������ʲô���͵���Ϣ���������Ʊ����ݡ���Ŀ�б��һ���ĵ�������DOM�����ɵ�ģ�Ͷ��ǽڵ�������ʽ��
     * Ҳ����˵��DOMǿ��ʹ����ģ��������XML�ĵ��е���Ϣ������XML�����Ͼ���һ�ֲַ�ṹ���������������������൱��Ч�ġ�
     *
     * DOM�����ṩ��������ʷ�ʽ��Ӧ�ó���Ŀ��������˺ܴ������ԣ�����������ؿ�������XML�ĵ��е����ݡ�
     * Ȼ��������DOM������������XML�ĵ�ת����DOM���������ڴ��У���ˣ����ĵ��Ƚϴ���߽ṹ�Ƚϸ���ʱ�����ڴ������ͱȽϸߡ�
     * ���ң����ڽṹ���ӵ����ı���Ҳ��һ���ʱ�Ĳ��������ԣ�DOM�������Ի������ܵ�Ҫ��Ƚϸߣ�ʵ��Ч�ʲ�ʮ�����롣
     * ����������DOM�����������õ����ṹ��˼����XML�ĵ��Ľṹ���Ǻϣ�ͬʱ������������������ķ��㣬��ˣ�DOM�����������кܹ㷺��ʹ�ü�ֵ�ġ�
     *
     * �ŵ㣺
     *
     *     1���γ������ṹ�������ڸ��õ���⡢���գ��Ҵ������ױ�д��
     *
     *     2�����������У����ṹ�������ڴ��У������޸ġ�
     *
     * ȱ�㣺
     *
     * ����1�������ļ���һ���Զ�ȡ�����Զ��ڴ�ĺķѱȽϴ�
     *
     * ����2�����XML�ļ��Ƚϴ�����Ӱ����������ҿ��ܻ�����ڴ������
     */

    /**
     * ʹ��DOM��ʽ����XML�ļ������¼�����
     *
     * �����Ǵ���DOM�������涨XML�ļ��е����ݣ���
     *
     * ����DocumentBuilderFactory����
     * ͨ��DocumentBuilderFactory���󴴽�DocumentBuilder����
     * ͨ��DocumentBuilder�����newDocument()��������һ��Document���󣬸ö������һ��XML�ļ�
     * ͨ��Document�����createElement()�����������ڵ�
     * ͨ��Document�����createElement()��������N���ӽڵ㣬��Ϊ���Ǹ�ֵ���ٽ���Щ�ӽڵ���ӵ����ڵ���
     * �����ڵ���ӵ�Document������
     * ����ǽ�DOM��ת��ΪXML�ļ���
     *
     * ����TransformerFactory��Ķ���
     * ͨ��TransformerFactory����Transformer����
     * ʹ��Transformer�����transform()������DOM��ת��ΪXML�ļ���
     * ���÷�����������������һ������ΪԴ���ݣ���Ҫ����DOMSource���󲢽�Document���ص����У�
     * �ڶ�������ΪĿ���ļ�����Ҫ���ɵ�XML�ļ�����Ҫ����StreamResult����ָ��Ŀ���ļ���
     */
    /**
     *  ȱ�㣬��Ҫ����֪���ڵ��λ�ã��������
     */
    public static void main(String[] args) throws Exception{
        ///parseXML();
        //addNode();
        removeNode();
    }
    // ����xml�ļ�
    private static void parseXML() throws Exception{
        //����һ��DocumentBuilderFactory�Ķ���
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //����һ��DocumentBuilder�Ķ���
        //����DocumentBuilder����
        DocumentBuilder db = dbf.newDocumentBuilder();
        //ͨ��DocumentBuilder�����parser��������books.xml�ļ�����ǰ��Ŀ�£�������·������������
        Document document = db.parse("src/resources.xml");
        //��ȡ����book�ڵ�ļ���
        NodeList bookList = document.getElementsByTagName("book");
        //ͨ��nodelist��getLength()�������Ի�ȡbookList�ĳ���
        System.out.println("һ����" + bookList.getLength() + "����");
        //����ÿһ��book�ڵ�
        for (int i = 0; i < bookList.getLength(); i++) {
            System.out.println("=================���濪ʼ������" + (i + 1) + "���������=================");
            //ͨ�� item(i)���� ��ȡһ��book�ڵ㣬nodelist������ֵ��0��ʼ
            Node book = bookList.item(i);
            //��ȡbook�ڵ���������Լ���
            NamedNodeMap attrs = book.getAttributes();
            System.out.println("�� " + (i + 1) + "���鹲��" + attrs.getLength() + "������");
            //����book������
            for (int j = 0; j < attrs.getLength(); j++) {
                //ͨ��item(index)������ȡbook�ڵ��ĳһ������
                Node attr = attrs.item(j);
                //��ȡ������
                System.out.print("��������" + attr.getNodeName());
                //��ȡ����ֵ
                System.out.println("--����ֵ" + attr.getNodeValue());
            }
            //����book�ڵ���ӽڵ�
            NodeList childNodes = book.getChildNodes();
            //����childNodes��ȡÿ���ڵ�Ľڵ����ͽڵ�ֵ
            System.out.println("��" + (i+1) + "���鹲��" +
                    childNodes.getLength() + "���ӽڵ�");
            for (int k = 0; k < childNodes.getLength(); k++) {
                //���ֳ�text���͵�node�Լ�element���͵�node
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                    //��ȡ��element���ͽڵ�Ľڵ���
                    System.out.print("��" + (k + 1) + "���ڵ�Ľڵ�����"+ childNodes.item(k).getNodeName());
                    //��ȡ��element���ͽڵ�Ľڵ�ֵ
                    System.out.println("--�ڵ�ֵ�ǣ�" + childNodes.item(k).getFirstChild().getNodeValue());
                    //System.out.println("--�ڵ�ֵ�ǣ�" + childNodes.item(k).getTextContent());
                }
            }
            System.out.println("======================����������" + (i + 1) + "���������=================");
        }
    }

    private static void addNode() throws Exception{
        Node book  = null;
        Element student = null;
        Element name = null;
        Element num = null;
        Element classes = null;
        Element address = null;
        Element tel = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //����һ��DocumentBuilder�Ķ���
        //����DocumentBuilder����
        DocumentBuilder db = dbf.newDocumentBuilder();
        //ͨ��DocumentBuilder�����parser��������books.xml�ļ�����ǰ��Ŀ�£�������·������������
        Document document = db.parse("src/resources.xml");
        NodeList nodeList = document.getElementsByTagName("bookstore");
        book = nodeList.item(0);
        student = document.createElement("Student");
        student.setAttribute("studentId","0020");
        classes = document.createElement("classes");
        //classes.setAttribute("123","123");
        student.appendChild(classes);
        Text textNode = document.createTextNode("�������");
        student.appendChild(textNode);
        book.appendChild(student);
        saveXML(document,new FileOutputStream(generateFile("src/dom/book.xml")));
        System.out.println("�ɹ�");
    }
    private static void saveXML(Document document,FileOutputStream fos) throws Exception{
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = tff.newTransformer();
        // ���е������������
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(fos));

    }
    private static File generateFile(String path) throws Exception{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }
    private static void removeNode() throws Exception{
        //����һ��DocumentBuilderFactory�Ķ���
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //����һ��DocumentBuilder�Ķ���
        //����DocumentBuilder����
        DocumentBuilder db = dbf.newDocumentBuilder();
        //ͨ��DocumentBuilder�����parser��������books.xml�ļ�����ǰ��Ŀ�£�������·������������
        Document document = db.parse("src/dom/book.xml");
        //��ȡ����book�ڵ�ļ���
        NodeList bookList = document.getElementsByTagName("Student");
        Element element = (Element) bookList.item(0);
        element.getParentNode().removeChild(element);
        saveXML(document,new FileOutputStream(generateFile("src/dom/book1.xml")));
    }


}
