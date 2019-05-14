package XMLParse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sax.Book;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author houdehong
 * @date 2019/5/13
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class SaxParseService extends DefaultHandler {
    /**
     * Simple API for XML，边扫描，边解析
     */
    /**
     * 使用场景：
     * SAX是快速和高效的，并且它对于状态无关的过滤是有用的。
     * 当遇到元素标记和时，SAX解析器调用一个方法当发现文本时调用不同的方法。
     * SAX比DOM要求更少的内存，因为SAX不像DOM那样创建XML数据的内部树结构。
     */
    /**
     * 使用方法：继承DefaultHandler
     * 缺点：
     *  无法知道当前解析标签（节点）的上层标签，及其嵌套结构，仅仅知道当前解析的标签的名字和属性，要知道其他信息需要程序猿自己编码
     *  只能读取XML，无法修改XML
     *  无法随机访问某个标签（节点）
     */
    /**
     * 解析步骤：
     * 1. 得到xml文件对应的资源，可以是xml的输入流，文件和uri
     * 2. 得到SAX解析工厂（SAXParserFactory）
     * 3. 由解析工厂生产一个SAX解析器（SAXParser）
     * 4. 传入输入流和handler给解析器，调用parse()解析
     */
    private List<Book> books = new ArrayList<>();
    private Book book = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;

    public List<Book> getBooks(File file) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParseService handler = new SaxParseService();
        parser.parse(file, handler);
        return handler.getBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument...");
        super.startDocument();
    }

    /**
     * 开始查找element节点
     * @param uri
     * @param localName 节点名称
     * @param qName 节点名称
     * @param attributes 节点属性
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("startElement....");
        if("book".equals(qName)){
            book = new Book();
            System.out.println("id:"+ attributes.getValue(0));
            book.setBookId(attributes.getValue(0));
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
        System.out.println("preTag:"+ preTag);
        super.startElement(uri, localName, qName, attributes);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters....");
        super.characters(ch, start, length);
        if(preTag != null){
            String content = new String(ch,start,length);
            if("name".equals(preTag)){
                book.setBookName(content);
            }else if("price".equals(preTag)){
                book.setPrice(Long.parseLong(content));
            }
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if("book".equals(qName)){
            books.add(book);
            book = null;
        }
        /**当解析结束时置为空。这里很重要，例如，当图中画3的位置结束后，会调用这个方法
         ，如果这里不把preTag置为null，根据startElement(....)方法，preTag的值还是book，当文档顺序读到图
         中标记4的位置时，会执行characters(char[] ch, int start, int length)这个方法，而characters(....)方
         法判断preTag!=null，会执行if判断的代码，这样就会把空值赋值给book，这不是我们想要的。*/
        preTag = null;
    }
    public static void main(String[] args) throws Exception{
        SaxParseService sax = new SaxParseService();
        List<Book> books = sax.getBooks(new File("src/sax/book.xml"));
        for(Book book : books){
            System.out.println(book.toString());
        }
    }
}