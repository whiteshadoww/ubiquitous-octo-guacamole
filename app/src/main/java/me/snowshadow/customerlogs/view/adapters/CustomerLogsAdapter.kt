package me.snowshadow.customerlogs.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_records.view.*
import me.snowshadow.customerlogs.R
import me.snowshadow.customerlogs.repo.CustomerRecord
import me.snowshadow.customerlogs.utils.formatTime
import java.io.File

class CustomerLogsAdapter :
    PagedListAdapter<CustomerRecord, CustomerLogsAdapter.CustomerRecordHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerRecordHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_records, parent, false)
        return CustomerRecordHolder(v)
    }

    override fun onBindViewHolder(holder: CustomerRecordHolder, position: Int) {
        holder.bind()
    }

    inner class CustomerRecordHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bind() {
            val item = getItem(adapterPosition) ?: return

            Picasso.get().load(File(item.photo)).error(R.drawable.ic_launcher_foreground)
                .into(v.card_image__records)
            v.card_name.text = "${item.firstName} ${item.lastName}"
            v.card_id_no.text = item.idNo
            v.card_date.text = item.createdOn.formatTime()
            v.setOnClickListener(
                Navigation
                    .createNavigateOnClickListener(R.id.action_customersLogListFragment_to_customerLogDetailsFragment,
                        Bundle().apply {
                            putParcelable("record", item)
                        }
                    )
            )
        }

    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<CustomerRecord>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: CustomerRecord,
                newConcert: CustomerRecord
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: CustomerRecord,
                newConcert: CustomerRecord
            ) = oldConcert == newConcert
        }
    }
}
