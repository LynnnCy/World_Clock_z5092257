package com.example.assignment1_worldclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TimeZone tz;
    Calendar current;
    String[] tzIdReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clock;
        tzIdReader = getResources().getStringArray(R.array.tzId);

        click12();
        for (int i = 0; i < 11; i++) {
            clock = "clock" + String.valueOf(i);

            int layoutID = getResources().getIdentifier(clock, "id", getPackageName());
            View currentLayout = (View) findViewById(layoutID);


            setImage(i, currentLayout);
            setCityName(tzIdReader[i], currentLayout);
        }

        Button btn12 = (Button) findViewById(R.id.HR12);
        btn12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                click12();
            }
        });

        Button btn24 = (Button) findViewById(R.id.HR24);
        btn24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                click24();
            }
        });
    }

    public void click12() {
        tzIdReader = getResources().getStringArray(R.array.tzId);

        for (int i = 0; i < 11; i++) {
            String clock = "clock" + String.valueOf(i);
            current = Calendar.getInstance(TimeZone.getTimeZone(tzIdReader[i]));

            int layoutID = getResources().getIdentifier(clock, "id", getPackageName());
            View currentLayout = (View) findViewById(layoutID);

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone(tzIdReader[i]));
            String time = sdf.format(current.getTime());
            if (current.get(Calendar.HOUR_OF_DAY)>=12){
                TextView timeDisplay = (TextView) currentLayout.findViewById(R.id.time);
                timeDisplay.setText(String.valueOf(time)+" PM");
            }
            else{
                TextView timeDisplay = (TextView) currentLayout.findViewById(R.id.time);
                timeDisplay.setText(String.valueOf(time)+" AM");
            }
        }
        setDisplayText("12HR");
    }

    public void click24() {
        tzIdReader = getResources().getStringArray(R.array.tzId);
        for (int i = 0; i < 11; i++) {
            String clock = "clock" + String.valueOf(i);
            current = Calendar.getInstance(TimeZone.getTimeZone(tzIdReader[i]));

            int layoutID = getResources().getIdentifier(clock, "id", getPackageName());
            View currentLayout = (View) findViewById(layoutID);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone(tzIdReader[i]));
            String time = sdf.format(current.getTime());

            TextView timeDisplay = (TextView) currentLayout.findViewById(R.id.time);
            timeDisplay.setText(String.valueOf(time));
        }
        setDisplayText("24HR");
    }

    public void setImage(int i, View currentLayout){
        ImageView cityLogoDisplay = (ImageView) currentLayout.findViewById(R.id.cityImage);
        int cityImage = getResources().getIdentifier("city" + String.valueOf(i), "drawable", getPackageName());
        cityLogoDisplay.setImageResource(cityImage);
    }

    public void setDisplayText(String timeFormat){
        String format= "Time Format: " + timeFormat;
        TextView timeFormatDisplay =(TextView) findViewById(R.id.TimeFormat);
        timeFormatDisplay.setText(format);
    }

    public void setCityName(String timeZone, View currentLayout){
        TextView cityDisplay = (TextView) currentLayout.findViewById(R.id.cityName);
        cityDisplay.setText(timeZone);
    }
}








