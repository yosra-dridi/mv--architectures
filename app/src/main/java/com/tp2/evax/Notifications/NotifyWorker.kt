package com.tp2.evax.Notifications

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tp2.evax.Data.Person
import com.tp2.evax.Data.Vaccination
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.tp2.evax.R
import com.tp2.evax.View.PersonDetailsActivity
import java.util.*


class NotifyWorker(private val mContext: Context,private val params: WorkerParameters) : Worker(mContext, params) {
companion object {
    private const val PERSON_ID="PERSON_ID"

    private const val PERSON_NAME= "PERSON_NAME"
    private const val  NOTIFICATION_CHANNEL_ID = "VaccinationReminder"

    private const val VACCINATION_NAME= "VACCINATION_NAME"

    fun buildData(person: Person, vaccination: Vaccination):Data{
      return Data.Builder().putLong(PERSON_ID, person.id)
            .putString(PERSON_NAME,person.name)
            .putString(VACCINATION_NAME,vaccination.name)
            .build()

    }
}
    override fun doWork(): Result {
        triggerNotification()

        return Result.success()

    }


    private fun triggerNotification() {

        val personId = params.inputData.getLong(PERSON_ID, -1)
        val personName = params.inputData.getString(PERSON_NAME)
        val vaccinationName = params.inputData.getString(VACCINATION_NAME)
        /**Creates an explicit intent for an Activity in your app */
        val resultIntent = PersonDetailsActivity.getStartIntent(mContext,personId)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val resultPendingIntent = PendingIntent.getActivity(
            mContext,
            0 /* Request code */, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val mBuilder = NotificationCompat.Builder(mContext,NOTIFICATION_CHANNEL_ID)
        val title = mContext.getString(R.string.vaccination_notification_title, personName)
        val message = mContext.getString(R.string.vaccination_notification_message,vaccinationName)
        mBuilder.setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.outline_add_white_36)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)

        val mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "Vaccinations", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        mNotificationManager.notify((title+message).hashCode(), mBuilder.build())

    }


}