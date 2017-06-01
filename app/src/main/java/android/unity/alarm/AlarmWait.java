package android.unity.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class AlarmWait extends AppCompatActivity {

    int hour;
    int minute;
    String ampm;
    String displayHour;
    String displayMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_wait);
        Intent serviceIntent = new Intent(this, CheckAlarmService.class);
        serviceIntent.putExtras(getIntent().getExtras());
        startService(serviceIntent);
        Bundle timeBundle = getIntent().getExtras();
        displayHour = Integer.toString(timeBundle.getInt("WANTED_ALARM_HOUR"));
        displayMinute = Integer.toString(timeBundle.getInt("WANTED_ALARM_MINUTE"));
        if (timeBundle.getInt("WANTED_ALARM_MINUTE") < 10)
        {
            displayMinute = "0" + displayMinute;
        }
        hour = timeBundle.getInt("WANTED_ALARM_HOUR");
        minute = timeBundle.getInt("WANTED_ALARM_MINUTE");
        if (hour>12)
        {
            ampm = " PM";
            displayHour = Integer.toString(hour - 12);
        }
        else
        {
            ampm = " AM";
        }
        TextView displayAlarm = (TextView) findViewById(R.id.alarmTime);
        displayAlarm.setText(displayHour + ":" + displayMinute + ampm);
    }
    /*public void resetAlarm() *CURRENTLY DOES NOT WORK*
    {
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent alarmCancel = new Intent(this, BackgroundAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmCancel, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pi);
        Intent i = new Intent(this, AlarmClock.class);
        startActivity(i);
    }*/


}
