package com.lcgao.personal.ipc.aidl;

import com.lcgao.personal.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
