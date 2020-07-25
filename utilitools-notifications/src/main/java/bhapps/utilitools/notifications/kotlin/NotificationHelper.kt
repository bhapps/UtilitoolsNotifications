package bhapps.utilitools.notifications.kotlin

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class NotificationHelper {

    fun sendMessageNotification(
        context: Context,
        message: String,
        notification_frequency: Int
    ) {

            if (message != null) {
                //region notification
                val alarmManager =
                    context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val notificationIntent = Intent("android.media.action.DISPLAY_NOTIFICATION")
                notificationIntent.putExtra("message", message)
                notificationIntent.addCategory("android.intent.category.DEFAULT")
                val broadcast = PendingIntent.getBroadcast(
                    context,
                    100,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                val cal = Calendar.getInstance()
                cal.add(Calendar.SECOND, notification_frequency)
                alarmManager[AlarmManager.RTC_WAKEUP, cal.timeInMillis] = broadcast
                //endregion notification
            }
    }
}