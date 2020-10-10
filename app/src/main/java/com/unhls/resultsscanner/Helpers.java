package com.unhls.resultsscanner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

public class Helpers {
    public static void scanner(Activity activity) {

        Log.e("sent pack name", "" + Constants.SCANNER_APP);

        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(Constants.SCANNER_APP);
        if (intent != null) {
            Intent intentScan = new Intent(
                    "com.google.zxing.client.android.SCAN");
            intentScan.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
            activity.startActivityForResult(intentScan, 10);
        } else {
            MaterialDialog InfoDialog = new MaterialDialog.Builder(activity)
                    .title("Information")
                    .content("Do you want to go to Google play store to install?")
                    .positiveText("Install")
                    .onPositive((dialog, which) -> {
                        intent.setData(Uri.parse("market://details?id=" + Constants.SCANNER_APP));
                        activity.startActivity(intent);
                    })
                    .show();

        }
    }

}
