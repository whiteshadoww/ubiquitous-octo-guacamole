package me.snowshadow.customerlogs.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//    private var items = ArrayList<CustomerRecord>()
//
//
//    fun setItems(new: ArrayList<CustomerRecord>) {
//        val updates = DiffUtil.calculateDiff(CustomerLogDiff(items, new))
//        items = new
//        updates.dispatchUpdatesTo(this)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerRecordHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_records, parent, false)
        return CustomerRecordHolder(v)
    }

    override fun onBindViewHolder(holder: CustomerRecordHolder, position: Int) {
        holder.bind()
    }

//    override fun getItemCount(): Int = items.size

    inner class CustomerRecordHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bind() {
            val item = getItem(adapterPosition)

            if (item == null) return
            Picasso.get().load(File("")).error(R.drawable.ic_launcher_foreground)
                .into(v.card_image__records)
            v.card_name.text = "${item.firstName} ${item.lastName}"
            v.card_id_no.text = item.idNo.toString()
            v.card_date.text = item.createdOn.formatTime()
        }

    }


    class CustomerLogDiff(
        private val oldList: ArrayList<CustomerRecord>,
        private val newList: ArrayList<CustomerRecord>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]


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
