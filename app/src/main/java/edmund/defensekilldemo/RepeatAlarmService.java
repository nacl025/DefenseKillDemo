package edmund.defensekilldemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class RepeatAlarmService extends Service {
	private static final String TAG = "RepeatAlarmService";
	private static final String serviceName = "com.nq.mdm.RepeatAlarmService";
	private Context mContext;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mContext = getApplicationContext();

			if(DeviceUtil.isNotificationAccessEnabled(mContext)) {
//				AlarmUtil.unregisterRepeatAlarm(mContext, 1);
			} else {
				Intent nitificationIntent = new Intent(mContext, NotificationAlertActivity.class);
				nitificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(nitificationIntent);
			}
		return START_NOT_STICKY;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}