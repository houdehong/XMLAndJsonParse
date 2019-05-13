package XMLParse;

/**
 * @author houdehong
 * @date 2019/5/13
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class Sax {
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

}
