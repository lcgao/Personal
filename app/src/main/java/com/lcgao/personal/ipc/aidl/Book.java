package com.lcgao.personal.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public Book() {
    }

    public Book(int bookId, String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }
    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookId);
        parcel.writeString(bookName);
    }
}
