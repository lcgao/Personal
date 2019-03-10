package com.lcgao.music_module.music.data.source.local;

import android.provider.BaseColumns;

public final class MusicsPersistenceContract {
    public static abstract class MusicEntry implements BaseColumns {
        public static final String TABLE_NAME = "musics";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_ARTIST = "artist";
        public static final String COLUMN_NAME_ALBUM = "album";
        public static final String COLUMN_NAME_ALBUM_ID = "album_id";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_PATH = "path";
        public static final String COLUMN_NAME_FILE_NAME = "file_name";
        public static final String COLUMN_NAME_FILE_SIZE = "file_size";
    }
}
