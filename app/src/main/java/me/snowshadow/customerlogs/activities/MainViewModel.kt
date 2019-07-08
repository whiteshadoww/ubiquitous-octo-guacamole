package me.snowshadow.customerlogs.activities

import android.content.Context
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import id.zelory.compressor.Compressor
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.snowshadow.customerlogs.repo.CustomerRecord
import me.snowshadow.customerlogs.repo.database.DataBase
import me.snowshadow.customerlogs.utils.md5
import java.io.File
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainViewModel @Inject constructor(private val db: DataBase, private val ctx: Context) :
    ViewModel() {
    fun saveRecord(customerRecord: CustomerRecord) {
        db.records().insertRecord(customerRecord)
    }

    fun compressImage(imgs: String): Flowable<File> {

        return Compressor(ctx)
            .setQuality(75)
            .setDestinationDirectoryPath(Environment.getExternalStorageDirectory().path + "/customerlogs")
            .compressToFileAsFlowable(File(imgs), Date().toGMTString().md5() + ".jpg")
            .map {
                File(imgs).delete()
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    var customersLog: Observable<PagedList<CustomerRecord>> =
        RxPagedListBuilder(db.records().allRecords, 50).buildObservable()
            .observeOn(AndroidSchedulers.mainThread())

}