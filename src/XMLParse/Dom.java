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
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class Dom {
    /**
     * DOM的全称是Document Object Model，也即文档对象模型。在应用程序中，基于DOM的XML分析器将一个XML文档转换成一个对象模型的集合（通常称DOM树），
     * 应用程序正是通过对这个对象模型的操作，来实现对XML文档数据的操作。通过DOM接口，应用程序可以在任何时候访问XML文档中的任何一部分数据，
     * 因此，这种利用DOM接口的机制也被称作随机访问机制。
     *
     * DOM接口提供了一种通过分层对象模型来访问XML文档信息的方式，这些分层对象模型依据XML的文档结构形成了一棵节点树。
     * 无论XML文档中所描述的是什么类型的信息，即便是制表数据、项目列表或一个文档，利用DOM所生成的模型都是节点树的形式。
     * 也就是说，DOM强制使用树模型来访问XML文档中的信息。由于XML本质上就是一种分层结构，所以这种描述方法是相当有效的。
     *
     * DOM树所提供的随机访问方式给应用程序的开发带来了很大的灵活性，它可以任意地控制整个XML文档中的内容。
     * 然而，由于DOM分析器把整个XML文档转化成DOM树放在了内存中，因此，当文档比较大或者结构比较复杂时，对内存的需求就比较高。
     * 而且，对于结构复杂的树的遍历也是一项耗时的操作。所以，DOM分析器对机器性能的要求比较高，实现效率不十分理想。
     * 不过，由于DOM分析器所采用的树结构的思想与XML文档的结构相吻合，同时鉴于随机访问所带来的方便，因此，DOM分析器还是有很广泛的使用价值的。
     *
     * 优点：
     *
     *     1、形成了树结构，有助于更好的理解、掌握，且代码容易编写。
     *
     *     2、解析过程中，树结构保存在内存中，方便修改。
     *
     * 缺点：
     *
     * 　　1、由于文件是一次性读取，所以对内存的耗费比较大。
     *
     * 　　2、如果XML文件比较大，容易影响解析性能且可能会造成内存溢出。
     */

    /**
     * 使用DOM方式生成XML文件有如下几步：
     *
     * 首先是创建DOM树（即规定XML文件中的内容）：
     *
     * 创建DocumentBuilderFactory对象
     * 通过DocumentBuilderFactory对象创建DocumentBuilder对象
     * 通过DocumentBuilder对象的newDocument()方法创建一个Document对象，该对象代表一个XML文件
     * 通过Document对象的createElement()方法创建根节点
     * 通过Document对象的createElement()方法创建N个子节点，并为他们赋值，再将这些子节点添加到根节点下
     * 将根节点添加到Document对象下
     * 其次是将DOM树转换为XML文件：
     *
     * 创建TransformerFactory类的对象
     * 通过TransformerFactory创建Transformer对象
     * 使用Transformer对象的transform()方法将DOM树转换为XML文件。
     * （该方法有两个参数，第一个参数为源数据，需要创建DOMSource对象并将Document加载到其中；
     * 第二个参数为目的文件，即要生成的XML文件，需要创建StreamResult对象并指定目的文件）
     */
    /**
     *  缺点，需要事先知道节点的位置，不够灵活
     */
    public static void main(String[] args) throws Exception{
        ///parseXML();
        //addNode();
        removeNode();
    }
    // 解析xml文件
    private static void parseXML() throws Exception{
        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下，可以是路径，可以是流
        Document document = db.parse("src/resources.xml");
        //获取所有book节点的集合
        NodeList bookList = document.getElementsByTagName("book");
        //通过nodelist的getLength()方法可以获取bookList的长度
        System.out.println("一共有" + bookList.getLength() + "本书");
        //遍历每一个book节点
        for (int i = 0; i < bookList.getLength(); i++) {
            System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
            //通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
            Node book = bookList.item(i);
            //获取book节点的所有属性集合
            NamedNodeMap attrs = book.getAttributes();
            System.out.println("第 " + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
            //遍历book的属性
            for (int j = 0; j < attrs.getLength(); j++) {
                //通过item(index)方法获取book节点的某一个属性
                Node attr = attrs.item(j);
                //获取属性名
                System.out.print("属性名：" + attr.getNodeName());
                //获取属性值
                System.out.println("--属性值" + attr.getNodeValue());
            }
            //解析book节点的子节点
            NodeList childNodes = book.getChildNodes();
            //遍历childNodes获取每个节点的节点名和节点值
            System.out.println("第" + (i+1) + "本书共有" +
                    childNodes.getLength() + "个子节点");
            for (int k = 0; k < childNodes.getLength(); k++) {
                //区分出text类型的node以及element类型的node
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                    //获取了element类型节点的节点名
                    System.out.print("第" + (k + 1) + "个节点的节点名："+ childNodes.item(k).getNodeName());
                    //获取了element类型节点的节点值
                    System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                    //System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                }
            }
            System.out.println("======================结束遍历第" + (i + 1) + "本书的内容=================");
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
        //创建一个DocumentBuilder的对象
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下，可以是路径，可以是流
        Document document = db.parse("src/resources.xml");
        NodeList nodeList = document.getElementsByTagName("bookstore");
        book = nodeList.item(0);
        student = document.createElement("Student");
        student.setAttribute("studentId","0020");
        classes = document.createElement("classes");
        //classes.setAttribute("123","123");
        student.appendChild(classes);
        Text textNode = document.createTextNode("三年二班");
        student.appendChild(textNode);
        book.appendChild(student);
        saveXML(document,new FileOutputStream(generateFile("src/dom/book.xml")));
        System.out.println("成功");
    }
    private static void saveXML(Document document,FileOutputStream fos) throws Exception{
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = tff.newTransformer();
        // 换行但不会进行缩进
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
        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        //创建DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下，可以是路径，可以是流
        Document document = db.parse("src/dom/book.xml");
        //获取所有book节点的集合
        NodeList bookList = document.getElementsByTagName("Student");
        Element element = (Element) bookList.item(0);
        element.getParentNode().removeChild(element);
        saveXML(document,new FileOutputStream(generateFile("src/dom/book1.xml")));
    }


}
