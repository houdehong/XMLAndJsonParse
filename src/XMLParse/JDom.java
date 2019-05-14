package XMLParse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author houdehong
 * @date 2019/5/13
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class JDom {
    public static void main(String[] args) {
        try {
            File file = new File("src/JDom/airportLine.xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(file);
            // 解析XML文档
            //parseJDOM(doc);
            // 生成xml文档
            String[] ipAddress = {"172.16.0.1","172.16.0.2"};
            String[] testAddress = {"192.168.1.0/24","192.168.2.0/24"};
            System.out.println("生成 mxl 文件...");
            BuildXMLDoc(2, ipAddress, testAddress);
        } catch (Exception e) {
            System.out.println("Can't read the file");
        }
    }

    // 解析XML文档
    private static void parseJDOM(Document doc) {
        Element root = doc.getRootElement();
        // 也可使用root.getChildren()
        List lineList = root.getChildren("line");
        for (Iterator iter = lineList.iterator(); iter.hasNext();) {
            // 获取<line>元素
            Element lineElement = (Element) iter.next();
            // 获取<line>元素的lid属性值
            String lid = lineElement.getAttributeValue("lid");
            // 获取<line>元素的num属性值
            String num = lineElement.getAttributeValue("num");

            System.out.println("==lid:" + lid);
            System.out.println("==num:" + num);

            // 获得<line>下<id>标签下的子元素
            Element idElement = lineElement.getChild("id");
            // // 获得<line>下<id>标签下的子元素值
            String id = idElement.getText();
            System.out.println("==路线id:" + id);
            // 获得<line>下<station>列表
            List stationList = lineElement.getChildren("station");
            for (Iterator subIter = stationList.iterator(); subIter.hasNext();) {
                // 获取<station>元素
                Element stationElement = (Element) subIter.next();
                // 获得<station>下<sid>标签下的子元素
                Element sidElement = stationElement.getChild("sid");
                // 获得<station>下<sname>标签下的子元素
                Element snameElement = stationElement.getChild("sname");
                // 获得<station>下<sid>标签下的子元素值
                String sid = sidElement.getText();
                // 获得<station>下<sname>标签下的子元素值
                String sname = snameElement.getText();

                System.out.println("==路线sid:" + sid);
                System.out.println("==路线sname:" + sname);
            }
        }
    }
    private static void BuildXMLDoc(int number, String ipAddress[], String testAddress[]) throws IOException, JDOMException {
        //建立根节点
        Element equipments = new Element("equipments");
        //用把根节点创建一个Document
        Document doc = new Document(equipments);
        //创建根节点下的子节点
        Element equipmentsNum = new Element("equipments-number");
        equipmentsNum.setText(Integer.toString(number));
        //把该子节点加入父节点
        equipments.addContent(equipmentsNum);
        //循环创建子节点，并加入到父节点中
        for (int i = 1; i <= number; i++) {
            Element equipment = new Element("equipment");
            //添加节点属性并赋值
            equipment.setAttribute("id", "" + i);

            Element loginInfo = new Element("login-info");
            //创建节点，给节点赋值，然后加入到他的父节点
            loginInfo.addContent(new Element("address").setText(ipAddress[i-1]));
            loginInfo.addContent(new Element("telnet-password").setText("CISCO"));
            loginInfo.addContent(new Element("enable-password").setText("CISCO"));
            equipment.addContent(loginInfo);

            Element testCommands = new Element("test-commands");
            testCommands.addContent(new Element("command").setText("show ip route"));
            equipment.addContent(testCommands);

            Element testReturn = new Element("test-returns");
            testReturn.addContent(new Element("return").setText(testAddress[i-1]));
            equipment.addContent(testReturn);

            equipments.addContent(equipment);
        }
        //new一个XMLOutputter，采用PrettyFormat格式（生成的XML可以自动换行）
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        //将生成的XML文件输出到文件
        xmlOut.output(doc, new FileOutputStream("src/JDom/autoTest.xml"));
    }

}
