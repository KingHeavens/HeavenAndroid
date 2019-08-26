package com.heaven.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.heaven.android.service.Book;
import com.heaven.android.service.BookService;
import com.heaven.android.service.IBookManager;

import java.util.List;

import static com.heaven.android.util.HLog.showLog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IBookManager mServiceBookManager;

    private boolean mServiceConnected;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
         @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
             mServiceBookManager = IBookManager.Stub.asInterface(service);
             mServiceConnected = true;
         }

         @Override
         public void onServiceDisconnected(ComponentName name) {
             mServiceConnected = false;
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindBookService();
        findViewById(R.id.btn_add_book).setOnClickListener(this);
        findViewById(R.id.btn_add_book_out).setOnClickListener(this);
        findViewById(R.id.btn_get_book).setOnClickListener(this);
        findViewById(R.id.btn_add_book_in).setOnClickListener(this);
    }

    private void bindBookService() {
        Intent intent = new Intent(getApplicationContext(), BookService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnected) {
            unbindService(mServiceConnection);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_book: {
                try {
                    Book book = new Book("《小红马》");
                    mServiceBookManager.addBook(book);
                    showLog("addBook Action:" + book.getAction());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_get_book: {
                try {
                    List<Book> bookList = mServiceBookManager.getBookList();
                    for (Book book : bookList) {
                        showLog(book.getName() + " ");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_add_book_in: {
                try {
                    Book book = new Book("《In》");
                    mServiceBookManager.addBookIn(book);
                    showLog("addBookIn Action:" + book.getAction());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_add_book_out: {
                try {
                    Book book = new Book("《Out》");
                    mServiceBookManager.addBookOut(book);
                    showLog("addBookOut Action:" + book.getAction());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:{

            }
        }
    }
}
