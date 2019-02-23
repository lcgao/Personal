package com.lcgao.personal.ipc.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lcgao.personal.R;
import com.lcgao.personal.ipc.aidl.Book;
import com.lcgao.personal.util.LogUtil;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Uri bookUri = Uri.parse("content://com.lcgao.personal.ipc.provider/book");
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while(bookCursor.moveToNext()){
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            LogUtil.d(TAG + "query book:" + book.toString());
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.lcgao.personal.ipc.provider/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while(userCursor.moveToNext()){
            User user = new User();
            user.setUserId(userCursor.getInt(0));
            user.setUserName(userCursor.getString(1));
            user.setMale(userCursor.getInt(2) == 1);
            LogUtil.d(TAG + "query user:" + user.toString());
        }
        userCursor.close();

    }

}
