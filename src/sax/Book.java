package sax;

/**
 * @author houdehong
 * @date 2019/5/14
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class Book {
    private String bookName;
    private String bookId;
    private long price;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [bookName:"+this.bookName+ ",bookId:"+this.bookId+", price:"+price+" ] ";
    }
}
