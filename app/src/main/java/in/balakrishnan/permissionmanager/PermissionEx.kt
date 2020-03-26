package `in`.balakrishnan.permissionmanager

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by BalaKrishnan
 */

fun Fragment.setupPermissions(
    doSomething: () -> Unit,
    PERMISSIONS_REQUEST_CODE: Int,
    permissionArray: Array<String>
) {
    activity?.setupPermissions(doSomething, PERMISSIONS_REQUEST_CODE, permissionArray)
}


fun Activity.setupPermissions(
    doSomething: () -> Unit,
    PERMISSIONS_REQUEST_CODE: Int,
    permissionArray: Array<String>
) {
    permissionArray.all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) != PackageManager.PERMISSION_GRANTED
    }

    if (permissionArray.isNotEmpty()) {
        ActivityCompat.requestPermissions(
            this,
            permissionArray,
            PERMISSIONS_REQUEST_CODE
        )
    } else doSomething()
}

fun Fragment.onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray,
    doSomething: () -> Unit,
    onPermissionDenied: () -> Unit,
    PERMISSIONS_REQUEST_CODE: Int
) {
    activity?.onRequestPermissionsResult(
        requestCode,
        permissions,
        grantResults,
        doSomething,
        onPermissionDenied,
        PERMISSIONS_REQUEST_CODE
    )
}

fun Activity.onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray,
    doSomething: () -> Unit,
    onPermissionDenied: () -> Unit,
    PERMISSIONS_REQUEST_CODE: Int
) {
    when (requestCode) {
        PERMISSIONS_REQUEST_CODE -> {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                onPermissionDenied()
            } else doSomething()
        }
    }
}