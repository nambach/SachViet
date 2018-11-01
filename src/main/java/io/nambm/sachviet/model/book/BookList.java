package io.nambm.sachviet.model.book;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "books")
public class BookList {

    private List<Book> book;

    public List<Book> getBook() {
        if (book == null) {
            return new ArrayList<>();
        }
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
