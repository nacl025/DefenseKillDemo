
package edmund.defensekilldemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


public class NotificationMonitor extends NotificationListenerService {
    private static final String TAG = "NotificationMonitor";
    private Context mContext;
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Intent service = new Intent(mContext, RepeatAlarmService.class);
            mContext.startService(service);
            super.handleMessage(msg);
        };
    };
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            if (!DeviceUtil.isNotificationAccessEnabled(mContext)) {
                handler.postDelayed(this, 10*1000);
            } else {
                handler.removeCallbacks(this);
            }

            if (!DeviceUtil.isNotificationAccessEnabled(mContext)) {

                handler.sendEmptyMessage(0);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Log.d(TAG, "Start MDMService");
        Intent intent = new Intent(NotificationMonitor.this, MDMService.class);
        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!DeviceUtil.isNotificationAccessEnabled(mContext)) {
            Intent nitificationIntent = new Intent(NotificationMonitor.this, NotificationAlertActivity.class);
            nitificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            NotificationMonitor.this.startActivity(nitificationIntent);
//        AlarmUtil.registerRepeatAlarm(NotificationMonitor.this, 1000, 1);
            handler.postDelayed(runnable, 50);
        }

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {


    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }



}
