package com.lcgao.personal.ipc.aidl;

import com.lcgao.personal.ipc.aidl.Book;
import com.lcgao.personal.ipc.aidl.IOnNewBookArrivedListener;
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}