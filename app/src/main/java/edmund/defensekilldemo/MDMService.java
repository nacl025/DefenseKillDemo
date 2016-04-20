package edmund.defensekilldemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

/**
 * 所有服务在这里控制启动停止ֹ
 *
 * @author zhangyu, anjx
 */
public class MDMService extends Service {
    private static final String TAG = "MDMService";

    private Context mContext;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG,"MDMService OnCreate.....");
        //TODO something
        //TODO
    }

    @Override
    public void onDestroy() {
        super.onDestroy();



    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"MDMService OnStart.....");
    }



}
