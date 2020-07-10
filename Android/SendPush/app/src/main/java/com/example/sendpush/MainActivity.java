package com.example.sendpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button mostrarNotificacion;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = (TextView) findViewById(R.id.texto);
        mostrarNotificacion = (Button) findViewById(R.id.btnMostrarNotificacion);
        mostrarNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //Crear canal de notificacion para android > 8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    NotificationChannel channel = new NotificationChannel("NOTIFICACION_ID", "alert", NotificationManager.IMPORTANCE_HIGH);
                    channel.setDescription("Alerta");
                    channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    channel.enableVibration(true);

                    mNotifyMgr.createNotificationChannel(channel);
                }

                //Para que al presionar la push vaya a otra pantalla
                Intent i = new Intent(MainActivity.this, MensajeActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                //Crear estilo de notificacion
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this,"NOTIFICACION_ID");
                mBuilder.setTicker("Alerta");
                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle("Pues claro");
                mBuilder.setContentText("Recibiste una push");
                mBuilder.setVibrate(new long[] {100, 250, 100, 500});
                mBuilder.setAutoCancel(true);
                mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                //Se envia la notificacion
                mNotifyMgr.notify(1,mBuilder.build());

                tx.setText("Enviado!!!");
            }
        });
    }

}