package bhapps.utilitools.notifications.kotlin

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import java.util.*

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            val type = intent.extras.getString("type").toString()
            val title = intent.extras.getString("title").toString()
            val message = intent.extras.getString("message").toString()
            val color = intent.extras.getInt("color")
            val icon = intent.extras.getInt("icon")

            val activty = intent.extras.getString("activity").toString()
            val targetActivity =
                Class.forName(activty).asSubclass(
                    Activity::class.java
                )

            if (type != null) {

                    val notificationIntent = Intent(context, targetActivity)
                    notificationIntent.putExtra("query", "paramsValues")
                    val stackBuilder =
                        TaskStackBuilder.create(context)
                    stackBuilder.addParentStack(targetActivity)
                    stackBuilder.addNextIntent(notificationIntent)
                    val pendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
                    val r = Random()
                    val intValue = r.nextInt(99)
                    val builder =
                        NotificationCompat.Builder(context)
                    val notification =
                        builder.setContentTitle("$title($intValue)")
                            .setContentText(message)
                            .setTicker(message)
                            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                            .setColor(context.resources.getColor(color))
                            .setSmallIcon(icon)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent).build()
                    val notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(0, notification)

            }
        } catch (error: Exception) {


        }
    }
}