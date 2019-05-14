package XMLParse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * @author houdehong
 * @date 2019/5/13
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class Dom4j {
    public static void main(String[] args) {
        //generateXml();
        parseXml();
    }
    private static void generateXml(){
        try {
            //DocumentHelper�ṩ�˴���Document����ķ���
            Document document = DocumentHelper.createDocument();
            //��ӽڵ���Ϣ
            Element rootElement = document.addElement("modules");
            //������Լ�������ӽڵ㣬Ҳ����ָ������
            rootElement.setText("�����module��ǩ���ı���Ϣ");
            Element element = rootElement.addElement("module");

            Element nameElement = element.addElement("name");
            Element valueElement = element.addElement("value");
            Element descriptionElement = element.addElement("description");
            nameElement.setText("����");
            //Ϊ�ڵ��������ֵ
            nameElement.addAttribute("language", "java");
            valueElement.setText("ֵ");
            valueElement.addAttribute("language", "c#");
            descriptionElement.setText("����");
            descriptionElement.addAttribute("language", "sql server");
            // ����XML�ĵ���ʽ
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            // ����XML���뷽ʽ,������ָ���ı��뷽ʽ����XML�ĵ����ַ���(String),����Ҳ����ָ��ΪGBK����ISO8859-1
            outputFormat.setEncoding("GBK");
            //outputFormat.setSuppressDeclaration(true); //�Ƿ�����xmlͷ
            //�����Ƿ�����
            outputFormat.setIndent(true);
            //���ĸ��ո�ʽʵ������
            outputFormat.setIndent("    ");
            //�����Ƿ���
            outputFormat.setNewlines(true);

            //��document�ĵ�����ֱ��ת�����ַ������
            System.out.println(document.asXML());
            Writer fileWriter = new FileWriter("src/dom4j/gen.xml");
            //dom4j�ṩ��ר��д���ļ��Ķ���XMLWriter
            XMLWriter xmlWriter = new XMLWriter(fileWriter,outputFormat);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
            System.out.println("xml�ĵ���ӳɹ���");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void parseXml(){
        try{
            //��src�����xmlת��Ϊ������
            //InputStream inputStream = this.getClass().getResourceAsStream("src/dom4j/module03.xml");
            //����SAXReader��ȡ����ר�����ڶ�ȡxml
            SAXReader saxReader = new SAXReader();
            //����saxReader��read��д������֪���ȿ���ͨ��inputStream����������ȡ��Ҳ����ͨ��file��������ȡ
            Document document = saxReader.read(new File("src/dom4j/module03.xml"));
            Element rootElement = document.getRootElement();
            if(rootElement.elements("module") != null ){
                //��Ϊ��һ��module��ǩֻ������û���ӽڵ㣬ֱ��.iterator()��java.lang.NullPointerException��, ������Ҫ�ֿ�ʵ��
                List<Element> elementList = rootElement.elements("module");
                for (Element element : elementList) {
                    if(!element.getTextTrim().equals("")){
                        System.out.println("��1��" + element.getTextTrim());
                    }else{
                        Element nameElement = element.element("name");
                        System.out.println("   ��2��" + nameElement.getName() + ":" + nameElement.getText());
                        Element valueElement = element.element("value");
                        System.out.println("   ��2��" + valueElement.getName() + ":" + valueElement.getText());
                        Element descriptElement = element.element("descript");
                        System.out.println("   ��2��" + descriptElement.getName() + ":" + descriptElement.getText());
                        List<Element> subElementList = element.elements("module");
                        for (Element subElement : subElementList) {
                            if(!subElement.getTextTrim().equals("")){
                                System.out.println("      ��3��" + subElement.getTextTrim());
                            }else{
                                Element subnameElement = subElement.element("name");
                                System.out.println("      ��3��" + subnameElement.getName() + ":" + subnameElement.getText());
                                Element subvalueElement = subElement.element("value");
                                System.out.println("      ��3��" + subvalueElement.getName() + ":" + subvalueElement.getText());
                                Element subdescriptElement = subElement.element("descript");
                                System.out.println("      ��3��" + subdescriptElement.getName() + ":" + subdescriptElement.getText());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
