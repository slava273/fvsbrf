package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {
    GoogleMap googleMap;
    private GoogleMap mMap;

    DatabaseReference mesto1 = FirebaseDatabase.getInstance().getReference().child("id/001");
    DatabaseReference mesto2 = FirebaseDatabase.getInstance().getReference().child("id/002");

    String value1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mesto2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value2 = dataSnapshot.child("levelwater").getValue().toString();
                String crit = dataSnapshot.child("levelcrit").getValue().toString();


                LatLng sydney1 = new LatLng(63.75, 121.618);
                mMap.addMarker(new MarkerOptions()
                        .position(sydney1)
                        .draggable(true)
                        .title("р.Лена (Пригородный)")
                        .snippet("Уровень воды: " + value2 + "см" + " Критический уровень воды: " +crit+ "см")
            );
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mpa));
        mesto1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value1 = dataSnapshot.child("levelwater").getValue().toString();
                String crit1 = dataSnapshot.child("levelcrit").getValue().toString();


                LatLng sydney1 = new LatLng(63.76, 121.619);
                mMap.addMarker(new MarkerOptions()
                        .position(sydney1)
                        .draggable(true)
                        .title("Вилюйск")
                        .snippet("Уровень воды: " + value1 + "см" + " Критический уровень воды:" +crit1+ "см"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney1, 10f));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();


    }

    private void enableMyLocation() {
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "моё местоположение", Toast.LENGTH_SHORT)
                .show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }
    public void onClickRead(View view){
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i); //нажатие кнопки направляет в пучину бездны

    }
}