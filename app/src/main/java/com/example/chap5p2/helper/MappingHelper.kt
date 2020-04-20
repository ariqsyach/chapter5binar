package com.example.chap5p2.helper

import android.database.Cursor
import com.example.chap5p2.Item
import com.example.chap5p2.database.DatabaseContract

object MappingHelper {
    fun mapCursorToList(noteCursor: Cursor?, onComplete : (List<Item>) -> Unit){
        val noteList = mutableListOf<Item>()

        noteCursor?.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val content = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.CONTENT))
                noteList.add(Item(id,title,content))
            }
        }

        onComplete(noteList)
    }

}