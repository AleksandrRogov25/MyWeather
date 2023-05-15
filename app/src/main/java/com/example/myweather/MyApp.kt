package com.example.myweather

import android.app.Application
import androidx.room.Room
import com.example.myweather.domain.room.HistoryDB
import com.example.myweather.domain.room.HistoyDao


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        private var db: HistoryDB? = null
        private var appContext: MyApp? = null

        fun getHistoryDao(): HistoyDao {
            if (db == null) {
                if (appContext != null) {
                    db = Room.databaseBuilder(appContext!!, HistoryDB::class.java, "test")
                        .allowMainThreadQueries().build()
                } else {
                    throw IllegalStateException("что-то пошло не так")
                }
            }
            return db!!.historyDao()
        }
    }
}
