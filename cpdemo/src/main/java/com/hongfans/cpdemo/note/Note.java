package com.hongfans.cpdemo.note;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * TODO
 * Created by MEI on 2018/4/2.
 */

public class Note {

    private int _id;
    private String content;
    private long pubDate;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }

    public interface Table extends BaseColumns {

        int DATABASE_VERSION = 2;
        String DATABASE_NAME = "note.db";
        String TABLE_NAME = "note";

        String AUTHORITY = "com.hongfans.cpdemo.note.NoteProvider";
        String PATH_NOTES = "notes";
        String PATH_NOTE = "notes/#";
        Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
        Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build();

        String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.spring.demo";
        String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.spring.demo";

        String NOTE_ID = "_id";
        String NOTE_CONTENT = "content";
        String NOTE_PUB_DATE = "pubDate";
    }
}
