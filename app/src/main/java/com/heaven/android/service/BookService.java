package com.heaven.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import static com.heaven.android.util.HLog.showLog;

public class BookService extends Service {

    private ArrayList<Book> mBookList;

    public BookService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initBookList();
    }

    private void initBookList() {
        mBookList = new ArrayList<>();
        mBookList.add(new Book("《西游记》"));
        mBookList.add(new Book("《三国演义》"));
        mBookList.add(new Book("《What's new》"));
    }

    private final IBookManager.Stub mBookManagerBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public boolean addBook(Book book) throws RemoteException {
            if (mBookList != null) {
                book.setAction("inOut");
                return mBookList.add(book);
            }
            return false;
        }

        @Override
        public boolean addBookIn(Book book) throws RemoteException {
            book.setName("in -> " + book.getName());
            book.setAction("in");
            return mBookList.add(book);
        }

        @Override
        public boolean addBookOut(Book book) throws RemoteException {
            if (!TextUtils.isEmpty(book.getName())) {
                return mBookList.add(book);
            } else {
                book.setName("--->out《服务命名》");
                book.setAction("Out");
                showLog("addBookOut book name is empty");
            }
            return false;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBookManagerBinder;
    }
}
