package com.example.tugceeincir;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugceeincir.R;

public class HistoryActivity extends AppCompatActivity {

    private TextView historyView;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyView = findViewById(R.id.historyView);

        // Geçmişi yükle ve göster
        loadHistory();
    }

    private void loadHistory() {
        SharedPreferences prefs = getSharedPreferences("ParkingPrefs", MODE_PRIVATE);  // "ParkingPrefs" kullanılıyor
        String parkingSpot = prefs.getString("parkingSpot", "Veri bulunamadı.");
        String vehicleType = prefs.getString("vehicleType", "Veri bulunamadı.");
        int hours = prefs.getInt("hours", 0);
        int totalPrice = prefs.getInt("totalPrice", 0);

        String history = "Park Yeri: " + parkingSpot + "\nAraç Türü: " + vehicleType +
                "\nSaat Sayısı: " + hours + "\nToplam Fiyat: " + totalPrice + " TL";

        historyView.setText(history);  // Geçmiş verisini göster
    }
}
