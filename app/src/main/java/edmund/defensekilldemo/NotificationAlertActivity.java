package edmund.defensekilldemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

public class NotificationAlertActivity extends Activity {

	private Stack<Dialog> mStack = new Stack<Dialog>();
	private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		showDialog(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent == null) {
			this.finish();
			return;
		}
		mStack.clear();
		showDialog(getIntent());
	}

	@Override
	protected void onStop() {
		super.onStop();
		mStack.clear();
	}

	private synchronized void showDialog(Intent intent) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(false)
				.setTitle("提示").setMessage("test")
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
//						AlarmManager.getInstance(getApplicationContext()).stop();
						dialog.dismiss();
						mStack.remove(dialog);
						if (mStack.isEmpty()) {
							NotificationAlertActivity.this.finish();
						}
						if (!DeviceUtil.isNotificationAccessEnabled(getApplicationContext())) {
							Intent nitificationIntent = new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS);
							startActivity(nitificationIntent);
						}

					}
				});
		Dialog dialog = builder.create();
		mStack.add(dialog);
		try {
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
