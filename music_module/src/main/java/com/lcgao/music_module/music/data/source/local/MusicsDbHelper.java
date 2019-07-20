package com.lcgao.music_module.music.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MusicsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Musics.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String INT_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MusicsPersistenceContract.MusicEntry.TABLE_NAME + " (" +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_ENTRY_ID + INT_TYPE + " PRIMARY KEY," +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_ARTIST + TEXT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_ALBUM + TEXT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_ALBUM_ID + INT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_DURATION + INT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_PATH + TEXT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_FILE_NAME + TEXT_TYPE + COMMA_SEP +
                    MusicsPersistenceContract.MusicEntry.COLUMN_NAME_FILE_SIZE + INT_TYPE +
                    " )";


    public MusicsDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
