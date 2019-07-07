package me.snowshadow.customerlogs.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_logs_list.*
import me.snowshadow.customerlogs.R
import me.snowshadow.customerlogs.utils.BaseFragment
import me.snowshadow.customerlogs.view.adapters.CustomerLogsAdapter

class CustomersLogListFragment : BaseFragment() {

    private val compositeDisposable = CompositeDisposable()
    private val adapter by lazy { CustomerLogsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_customer_logs_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        records_recycler_view.layoutManager = LinearLayoutManager(this.context)
        records_recycler_view.adapter = adapter

        fab_add_record
            .setOnClickListener(
                Navigation
                    .createNavigateOnClickListener(R.id.action_customersLogListFragment_to_customerLogInputFragment)
            )
    }


    override fun onResume() {
        super.onResume()
        compositeDisposable.add(mainViewModel.customersLog.subscribe(adapter::submitList))
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }

}
