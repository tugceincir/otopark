package com.example.tugceeincir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingActivity extends AppCompatActivity {

    private int totalPrice = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        EditText etParkingSpot = findViewById(R.id.etParkingSpot);
        RadioGroup rgVehicleType = findViewById(R.id.rgVehicleType);
        EditText etHours = findViewById(R.id.etHours);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnrec = findViewById(R.id.btnrecords);

        // SharedPreferences başlatıyoruz
        sharedPreferences = getSharedPreferences("ParkingPrefs", Context.MODE_PRIVATE);

        btnCalculate.setOnClickListener(v -> {
            int selectedId = rgVehicleType.getCheckedRadioButtonId();
            int hours = Integer.parseInt(etHours.getText().toString());
            if (selectedId == R.id.rbMotor) totalPrice = hours * 20;
            else if (selectedId == R.id.rbCar) totalPrice = hours * 30;
            else if (selectedId == R.id.rbTruck) totalPrice = hours * 40;
            Toast.makeText(this, "Toplam Fiyat: " + totalPrice + " TL", Toast.LENGTH_SHORT).show();
        });

        btnSave.setOnClickListener(v -> {
            String parkingSpot = etParkingSpot.getText().toString();
            int hours = Integer.parseInt(etHours.getText().toString());
            int selectedId = rgVehicleType.getCheckedRadioButtonId();
            String vehicleType = selectedId == R.id.rbMotor ? "Motor" : (selectedId == R.id.rbCar ? "Otomobil" : "Kamyon");

            // SharedPreferences'e kaydediyoruz
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("parkingSpot", parkingSpot);
            editor.putString("vehicleType", vehicleType);
            editor.putInt("hours", hours);
            editor.putInt("totalPrice", totalPrice);
            editor.apply();  // Değişiklikleri kaydet

            Toast.makeText(this, "Kaydedildi!", Toast.LENGTH_SHORT).show();
        });

        btnrec.setOnClickListener(v ->{
            Intent intent = new Intent(ParkingActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
