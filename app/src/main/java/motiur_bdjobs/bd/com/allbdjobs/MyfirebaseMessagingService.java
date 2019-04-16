//package motiur_bdjobs.bd.com.allbdjobs;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
///**
// * Created by user on 5/3/18.
// */
//
//public class MyfirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "MyfirebaseMessSerice";
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(TAG,"From:"+ remoteMessage.getFrom());
//
//        //Check if the message contain data
//
//        if(remoteMessage.getData().size()>0){
//            Log.d(TAG,"MessageData: "+remoteMessage.getData());
//        }
//
//        //Check if the message contains notification
//
//        if(remoteMessage.getNotification()!=null){
//            Log.d(TAG, "MessageBody: "+remoteMessage.getNotification().getBody());
//            sendNotificatoin(remoteMessage.getNotification().getBody());
//
//        }
//    }
//
//
//
//
//
//    private void sendNotificatoin(String body){
//
//        Intent intent = new Intent(this, BDJobsActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        //PendingIntent pendingIntent = PendingIntent.getActivities(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
//
//            // Configure the notification channel.
//            notificationChannel.setDescription("Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
//
//        notificationBuilder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setSound(notificationSound)
//                .setTicker("All BD Jobs")
//                .setPriority(Notification.PRIORITY_MAX)
//                .setContentTitle("All BD Jobs")
//                .setContentText(body)
//                .setContentIntent(pendingIntent)
//                .setContentInfo("Info");
//        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
//
//
//    }
//}
