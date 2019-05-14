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
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class SaxParseService extends DefaultHandler {
    /**
     * Simple API for XML����ɨ�裬�߽���
     */
    /**
     * ʹ�ó�����
     * SAX�ǿ��ٺ͸�Ч�ģ�����������״̬�޹صĹ��������õġ�
     * ������Ԫ�ر�Ǻ�ʱ��SAX����������һ�������������ı�ʱ���ò�ͬ�ķ�����
     * SAX��DOMҪ����ٵ��ڴ棬��ΪSAX����DOM��������XML���ݵ��ڲ����ṹ��
     */
    /**
     * ʹ�÷������̳�DefaultHandler
     * ȱ�㣺
     *  �޷�֪����ǰ������ǩ���ڵ㣩���ϲ��ǩ������Ƕ�׽ṹ������֪����ǰ�����ı�ǩ�����ֺ����ԣ�Ҫ֪��������Ϣ��Ҫ����Գ�Լ�����
     *  ֻ�ܶ�ȡXML���޷��޸�XML
     *  �޷��������ĳ����ǩ���ڵ㣩
     */
    /**
     * �������裺
     * 1. �õ�xml�ļ���Ӧ����Դ��������xml�����������ļ���uri
     * 2. �õ�SAX����������SAXParserFactory��
     * 3. �ɽ�����������һ��SAX��������SAXParser��
     * 4. ������������handler��������������parse()����
     */
    private List<Book> books = new ArrayList<>();
    private Book book = null;
    //�����Ǽ�¼����ʱ����һ���ڵ�����
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
     * ��ʼ����element�ڵ�
     * @param uri
     * @param localName �ڵ�����
     * @param qName �ڵ�����
     * @param attributes �ڵ�����
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
        //�����ڽ����Ľڵ����Ƹ���preTag
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
        /**����������ʱ��Ϊ�ա��������Ҫ�����磬��ͼ�л�3��λ�ý����󣬻�����������
         ��������ﲻ��preTag��Ϊnull������startElement(....)������preTag��ֵ����book�����ĵ�˳�����ͼ
         �б��4��λ��ʱ����ִ��characters(char[] ch, int start, int length)�����������characters(....)��
         ���ж�preTag!=null����ִ��if�жϵĴ��룬�����ͻ�ѿ�ֵ��ֵ��book���ⲻ��������Ҫ�ġ�*/
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