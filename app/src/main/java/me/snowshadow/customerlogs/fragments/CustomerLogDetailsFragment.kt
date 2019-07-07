package me.snowshadow.customerlogs.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_customer_log_input.*
import me.snowshadow.customerlogs.R
import me.snowshadow.customerlogs.repo.CustomerRecord
import me.snowshadow.customerlogs.utils.BaseFragment
import me.snowshadow.customerlogs.utils.disable
import me.snowshadow.customerlogs.utils.formatTime
import me.snowshadow.customerlogs.utils.hide
import java.io.File

class CustomerLogDetailsFragment : BaseFragment() {

    private lateinit var custmoreRecord: CustomerRecord

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_customer_log_input, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvTitle.text = "Customer Record"

        custmoreRecord = arguments?.getParcelable("record")!!

        first_name.setText(custmoreRecord.firstName)
        last_name.setText(custmoreRecord.lastName)
        id_number.setText(custmoreRecord.idNo)
        qr_data.text = "QR Data :\n\t${custmoreRecord.qrData}"
        location.text =
            "Location : \n\tlat -: ${custmoreRecord.lat}\n\tlong -:${custmoreRecord.lng}"
        date_created.text = "Date Created :\n\t${custmoreRecord.createdOn.formatTime()}"


        Picasso.get()
            .load(File(custmoreRecord.photo))
            .into(image)

        first_name.disable()
        last_name.disable()
        id_number.disable()

        pick_image.hide()
        pick_location.hide()
        scan_qr.hide()
    }


}

