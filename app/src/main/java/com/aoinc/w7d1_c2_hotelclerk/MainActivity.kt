package com.aoinc.w7d1_c2_hotelclerk

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readFromProvider()
    }

    private fun readFromProvider() {
        val uri = Uri.parse("content://com.aoinc.w7d1_c_hotel_contentproviders/guests")
        val resolver: ContentResolver = contentResolver
        val cursor = contentResolver.query(uri,
                null, null, null,
                null, null)

        cursor?.moveToPosition(-1)

        val sBuilder = StringBuilder()

        while (cursor?.moveToNext() == true) {
            sBuilder.append(cursor.getString(cursor.getColumnIndex("guest_name")))
            sBuilder.append(" -> Room: ")
            sBuilder.append(cursor.getString(cursor.getColumnIndex("room_number")))
            sBuilder.append("\n")
        }

        findViewById<TextView>(R.id.guests_textView).apply {
            text = sBuilder.toString()
        }

        cursor?.close()
    }
}