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
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class JDom {
    public static void main(String[] args) {
        try {
            File file = new File("src/JDom/airportLine.xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(file);
            // ����XML�ĵ�
            //parseJDOM(doc);
            // ����xml�ĵ�
            String[] ipAddress = {"172.16.0.1","172.16.0.2"};
            String[] testAddress = {"192.168.1.0/24","192.168.2.0/24"};
            System.out.println("���� mxl �ļ�...");
            BuildXMLDoc(2, ipAddress, testAddress);
        } catch (Exception e) {
            System.out.println("Can't read the file");
        }
    }

    // ����XML�ĵ�
    private static void parseJDOM(Document doc) {
        Element root = doc.getRootElement();
        // Ҳ��ʹ��root.getChildren()
        List lineList = root.getChildren("line");
        for (Iterator iter = lineList.iterator(); iter.hasNext();) {
            // ��ȡ<line>Ԫ��
            Element lineElement = (Element) iter.next();
            // ��ȡ<line>Ԫ�ص�lid����ֵ
            String lid = lineElement.getAttributeValue("lid");
            // ��ȡ<line>Ԫ�ص�num����ֵ
            String num = lineElement.getAttributeValue("num");

            System.out.println("==lid:" + lid);
            System.out.println("==num:" + num);

            // ���<line>��<id>��ǩ�µ���Ԫ��
            Element idElement = lineElement.getChild("id");
            // // ���<line>��<id>��ǩ�µ���Ԫ��ֵ
            String id = idElement.getText();
            System.out.println("==·��id:" + id);
            // ���<line>��<station>�б�
            List stationList = lineElement.getChildren("station");
            for (Iterator subIter = stationList.iterator(); subIter.hasNext();) {
                // ��ȡ<station>Ԫ��
                Element stationElement = (Element) subIter.next();
                // ���<station>��<sid>��ǩ�µ���Ԫ��
                Element sidElement = stationElement.getChild("sid");
                // ���<station>��<sname>��ǩ�µ���Ԫ��
                Element snameElement = stationElement.getChild("sname");
                // ���<station>��<sid>��ǩ�µ���Ԫ��ֵ
                String sid = sidElement.getText();
                // ���<station>��<sname>��ǩ�µ���Ԫ��ֵ
                String sname = snameElement.getText();

                System.out.println("==·��sid:" + sid);
                System.out.println("==·��sname:" + sname);
            }
        }
    }
    private static void BuildXMLDoc(int number, String ipAddress[], String testAddress[]) throws IOException, JDOMException {
        //�������ڵ�
        Element equipments = new Element("equipments");
        //�ðѸ��ڵ㴴��һ��Document
        Document doc = new Document(equipments);
        //�������ڵ��µ��ӽڵ�
        Element equipmentsNum = new Element("equipments-number");
        equipmentsNum.setText(Integer.toString(number));
        //�Ѹ��ӽڵ���븸�ڵ�
        equipments.addContent(equipmentsNum);
        //ѭ�������ӽڵ㣬�����뵽���ڵ���
        for (int i = 1; i <= number; i++) {
            Element equipment = new Element("equipment");
            //��ӽڵ����Բ���ֵ
            equipment.setAttribute("id", "" + i);

            Element loginInfo = new Element("login-info");
            //�����ڵ㣬���ڵ㸳ֵ��Ȼ����뵽���ĸ��ڵ�
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
        //newһ��XMLOutputter������PrettyFormat��ʽ�����ɵ�XML�����Զ����У�
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        //�����ɵ�XML�ļ�������ļ�
        xmlOut.output(doc, new FileOutputStream("src/JDom/autoTest.xml"));
    }

}
