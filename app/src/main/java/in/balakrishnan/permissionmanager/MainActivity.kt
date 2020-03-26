package `in`.balakrishnan.permissionmanager

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_CODE = 101

    private val TAG = javaClass.name

    private val arrayOf = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions(doSomething(), PERMISSIONS_REQUEST_CODE, arrayOf)

//        whyUserShouldGivePermission()
    }

    private fun whyUserShouldGivePermission() {
        AlertDialog.Builder(this).apply {
            setTitle("This action require Permission")
            setMessage("Explain why the app needs permission")
            setPositiveButton(
                "Allow"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
        }
    }

    private fun doSomething(): () -> Unit = {
        text.text = "Permissions are satisfied"
    }

    private fun onPermissionDeny(): () -> Unit = {
        AlertDialog.Builder(this).apply {
            setMessage("You have denied the permissions earlier, Please allow the Permission going to the Settings")
            setPositiveButton(
                "Ok"
            ) { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            doSomething(),
            onPermissionDeny(),
            PERMISSIONS_REQUEST_CODE
        )
    }

}
