package me.snowshadow.customerlogs.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationRequest
import com.google.android.material.snackbar.Snackbar
import com.patloew.rxlocation.RxLocation
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_customer_log_input.*
import me.snowshadow.customerlogs.R
import me.snowshadow.customerlogs.activities.QrScannerActivity
import me.snowshadow.customerlogs.repo.CustomerRecord
import me.snowshadow.customerlogs.utils.BaseFragment
import java.util.*


class CustomerLogInputFragment : BaseFragment() {

    private val disposable = CompositeDisposable()
    private val rxLocation by lazy { RxLocation(this.context!!) }
    private val rxpermission by lazy { RxPermissions(this) }
    private val locationRequest by lazy {
        LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
    }

    private var locData: Location? = null
    private var qrData: String? = null
    private var photoData: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_customer_log_input, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pick_image.setOnClickListener {}

        pick_location.setOnClickListener { pickLocation() }

        scan_qr.setOnClickListener {
            rxpermission.request(Manifest.permission.CAMERA)
                .subscribe {
                    if (it) {
                        startActivityForResult(
                            Intent(
                                this.context,
                                QrScannerActivity::class.java
                            ), 1
                        )
                    } else {
                        Snackbar.make(
                            view,
                            "Camera permission Denied",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
        }


    }

    @SuppressLint("MissingPermission")
    private fun pickLocation() {

        disposable.add(rxpermission
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).flatMap {
                rxLocation.settings().checkAndHandleResolution(locationRequest).toObservable()
            }
            .flatMap {
                rxLocation.location().updates(locationRequest)
                    .take(1)
            }.subscribe({

                locData = it
                if (it != null) {

                    location.text = "lat:${it.latitude}\nlng:${it.longitude}"

                    return@subscribe
                } else {
                    location.text = ""
                }

                Snackbar.make(
                    view!!,
                    "Location Request Denied",
                    Snackbar.LENGTH_LONG
                ).show()

            }, {
                Snackbar.make(
                    view!!,
                    "Location Request Denied",
                    Snackbar.LENGTH_LONG
                ).show()

            })
        )
    }


    private fun saveRecord() {

        var error = false

        if (first_name.text.isNullOrEmpty()) {
            first_name.error = "First name is required"
            error = true
        }
        if (last_name.text.isNullOrEmpty()) {
            last_name.error = "Last name is required"
            error = true
        }
        if (id_number.text.isNullOrEmpty()) {
            id_number.error = "ID number is required"
            error = true
        }

        if (qrData.isNullOrEmpty()) {

            AlertDialog.Builder(this.context)
                .setTitle("Error QR Code Missing")
                .setMessage("Scan QR  to proceed")
                .show()
            return
        }

        if (locData == null) {

            AlertDialog.Builder(this.context)
                .setTitle("Error Location Missing")
                .setMessage("Pick location  to proceed")
                .show()
            return
        }
        if (photoData == null) {

            AlertDialog.Builder(this.context)
                .setTitle("Error Photo Missing")
                .setMessage("Take a photo first")
                .show()
            return
        }

        if (error) return

        mainViewModel.saveRecord(
            CustomerRecord(
                0,
                first_name.text.toString(),
                last_name.text.toString(),
                id_number.text.toString().toInt(),
                qrData!!,
                locData!!.latitude,
                locData!!.longitude,
                photoData!!,
                Date()
            )
        )
        activity?.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK && data != null) {
                qrData = data.getStringExtra("data")
                qr_data.text = qrData
            } else {
                qrData = null
                qr_data.text = ""
                Snackbar.make(
                    view!!,
                    "Failed to read QR code",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save_customer_log -> saveRecord()
        }

        return super.onOptionsItemSelected(item)
    }

    private lateinit var menu: Menu

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.customer_log_input, menu)
        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

}
