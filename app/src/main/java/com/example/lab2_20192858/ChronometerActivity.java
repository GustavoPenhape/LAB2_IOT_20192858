package com.example.lab2_20192858;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

public class ChronometerActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long timeWhenStopped = 0;
    private boolean isRunning = false;
    private boolean isStopped = false;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        Toast.makeText(this, "Estás en Cronómetro Activity", Toast.LENGTH_SHORT).show();

        chronometer = findViewById(R.id.cronometro_text);
        chronometer.setText("00:00:00");

        TextView iniciar = findViewById(R.id.iniciar_chrono_button);
        TextView pausar = findViewById(R.id.pausar_chrono_button);
        TextView detener = findViewById(R.id.detener_chrono_button);
        TextView despausar = findViewById(R.id.despausar_chrono_button);
        TextView limpiar = findViewById(R.id.limpiar_chrono_button);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                String strElapsedMillis = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(elapsedMillis),
                        TimeUnit.MILLISECONDS.toMinutes(elapsedMillis) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) % TimeUnit.MINUTES.toSeconds(1));
                chronometer.setText(strElapsedMillis);
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning && !isStopped) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    isRunning = true;
                    isPaused = false;
                }
            }
        });

        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    isRunning = false;
                    isPaused = true;
                }
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning || isPaused) {
                    chronometer.stop();
                    timeWhenStopped = 0;
                    isRunning = false;
                    isPaused = false;
                    isStopped = true;
                }
            }
        });

        despausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused && !isRunning && !isStopped) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    isRunning = true;
                    isPaused = false;
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.setText("00:00:00");
                timeWhenStopped = 0;
                isStopped = false;
            }
        });
    }
}
