package me.snowshadow.customerlogs.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.snowshadow.customerlogs.repo.database.DataBase
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    @Singleton
    fun providesDataBase(context: Context): DataBase {

        val room = Room
            .databaseBuilder(context, DataBase::class.java, "customer_records.db")
        return with(room) {
            allowMainThreadQueries()
            build()
        }
    }

}
