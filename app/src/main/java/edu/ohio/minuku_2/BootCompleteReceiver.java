package edu.ohio.minuku_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import edu.ohio.minuku.Data.DBHelper;
import edu.ohio.minuku_2.service.BackgroundService;

/**
 * Created by Lawrence on 2017/7/19.
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompleteReceiver";
    private static DBHelper dbhelper = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

            try{

                dbhelper = new DBHelper(context);

                dbhelper.getWritableDatabase();
            }finally {

                //here we start the service
                Intent bintent = new Intent(context, BackgroundService.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(bintent);
                } else {
                    context.startService(bintent);
                }

            }

        }

    }
}
