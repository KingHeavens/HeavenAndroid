package com.heaven.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

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
        public boolean addBook(Book name) throws RemoteException {
            if (mBookList != null) {
                return mBookList.add(name);
            }
            return false;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBookManagerBinder;
    }
}
