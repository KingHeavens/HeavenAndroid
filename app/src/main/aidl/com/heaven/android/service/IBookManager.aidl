// IBookManager.aidl
package com.heaven.android.service;

// Declare any non-default types here with import statements
import com.heaven.android.service.Book;
interface IBookManager {
    List<Book> getBookList();
    boolean addBook(inout Book book);
    boolean addBookIn(in Book book);
    boolean addBookOut(out Book book);
}
