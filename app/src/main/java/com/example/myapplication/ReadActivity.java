package com.example.myapplication;
import android.os.Bundle;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.Iterator;


public class ReadActivity extends AppCompatActivity {

    // создание переменной для
    // нашей базы данных Firebase
    FirebaseDatabase firebaseDatabase;

    // создаем переменную для нашей
// Ссылки на базу данных для Firebase.
    DatabaseReference databaseReference;

    // переменная для просмотра текста.
    private TextView TEXT;


    DatabaseReference mesto1 = FirebaseDatabase.getInstance().getReference().child("id/001");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        // строка ниже используется для получения экземпляра
        // из нашей базы данных Firebase.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // строка ниже используется для получения
        // ссылка на нашу базу данных.
        databaseReference = firebaseDatabase.getReference("ID");

        // инициализируем переменную нашего объектного класса.
        TEXT = findViewById(R.id.idTVRetrieveData);
        // levelwater.setValue(12);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Read from the database
        mesto1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("levelwater").getValue().toString();
                TEXT.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        mesto1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("levelwater").getValue().toString();
                TEXT.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }
}


