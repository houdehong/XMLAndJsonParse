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
 * @org 北京信安世纪科技股份有限公司
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
            //DocumentHelper提供了创建Document对象的方法
            Document document = DocumentHelper.createDocument();
            //添加节点信息
            Element rootElement = document.addElement("modules");
            //这里可以继续添加子节点，也可以指定内容
            rootElement.setText("这个是module标签的文本信息");
            Element element = rootElement.addElement("module");

            Element nameElement = element.addElement("name");
            Element valueElement = element.addElement("value");
            Element descriptionElement = element.addElement("description");
            nameElement.setText("名称");
            //为节点添加属性值
            nameElement.addAttribute("language", "java");
            valueElement.setText("值");
            valueElement.addAttribute("language", "c#");
            descriptionElement.setText("描述");
            descriptionElement.addAttribute("language", "sql server");
            // 设置XML文档格式
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
            outputFormat.setEncoding("GBK");
            //outputFormat.setSuppressDeclaration(true); //是否生产xml头
            //设置是否缩进
            outputFormat.setIndent(true);
            //以四个空格方式实现缩进
            outputFormat.setIndent("    ");
            //设置是否换行
            outputFormat.setNewlines(true);

            //将document文档对象直接转换成字符串输出
            System.out.println(document.asXML());
            Writer fileWriter = new FileWriter("src/dom4j/gen.xml");
            //dom4j提供了专门写入文件的对象XMLWriter
            XMLWriter xmlWriter = new XMLWriter(fileWriter,outputFormat);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
            System.out.println("xml文档添加成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void parseXml(){
        try{
            //将src下面的xml转换为输入流
            //InputStream inputStream = this.getClass().getResourceAsStream("src/dom4j/module03.xml");
            //创建SAXReader读取器，专门用于读取xml
            SAXReader saxReader = new SAXReader();
            //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
            Document document = saxReader.read(new File("src/dom4j/module03.xml"));
            Element rootElement = document.getRootElement();
            if(rootElement.elements("module") != null ){
                //因为第一个module标签只有内容没有子节点，直接.iterator()就java.lang.NullPointerException了, 所以需要分开实现
                List<Element> elementList = rootElement.elements("module");
                for (Element element : elementList) {
                    if(!element.getTextTrim().equals("")){
                        System.out.println("【1】" + element.getTextTrim());
                    }else{
                        Element nameElement = element.element("name");
                        System.out.println("   【2】" + nameElement.getName() + ":" + nameElement.getText());
                        Element valueElement = element.element("value");
                        System.out.println("   【2】" + valueElement.getName() + ":" + valueElement.getText());
                        Element descriptElement = element.element("descript");
                        System.out.println("   【2】" + descriptElement.getName() + ":" + descriptElement.getText());
                        List<Element> subElementList = element.elements("module");
                        for (Element subElement : subElementList) {
                            if(!subElement.getTextTrim().equals("")){
                                System.out.println("      【3】" + subElement.getTextTrim());
                            }else{
                                Element subnameElement = subElement.element("name");
                                System.out.println("      【3】" + subnameElement.getName() + ":" + subnameElement.getText());
                                Element subvalueElement = subElement.element("value");
                                System.out.println("      【3】" + subvalueElement.getName() + ":" + subvalueElement.getText());
                                Element subdescriptElement = subElement.element("descript");
                                System.out.println("      【3】" + subdescriptElement.getName() + ":" + subdescriptElement.getText());
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
