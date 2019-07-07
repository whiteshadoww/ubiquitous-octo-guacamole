package me.snowshadow.customerlogs.activities

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.snowshadow.customerlogs.repo.CustomerRecord
import me.snowshadow.customerlogs.repo.database.DataBase
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainViewModel @Inject constructor(private val db: DataBase) : ViewModel() {
    fun saveRecord(customerRecord: CustomerRecord) {
        db.records().insertRecord(customerRecord)
    }

    var customersLog: Observable<PagedList<CustomerRecord>> =
        RxPagedListBuilder(db.records().allRecords, 50).buildObservable()
            .observeOn(AndroidSchedulers.mainThread())

}