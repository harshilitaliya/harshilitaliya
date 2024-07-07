package com.example.contactmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {

    private Button signIn;
    private TextInputEditText etUserNumber;
    private DatabaseReference database;
    static final String KEY = "com.example.contactmanagerapp.number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signIn = findViewById(R.id.signIn);
        etUserNumber = findViewById(R.id.etUserNumber);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNumber = etUserNumber.getText().toString();
                if (!userNumber.isEmpty()) {
                    readData(userNumber);
                }
                else {
                    Toast.makeText(SignIn.this, "Please Enter User ID", Toast.LENGTH_SHORT).show();
                }
            }
            private void readData(String userNumber) {
                database = FirebaseDatabase.getInstance().getReference("Users");
                database.child(userNumber).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        dataSnapshot.child("number").getValue(String.class);
                        Intent intent = new Intent(SignIn.this, MainScreen.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignIn.this, "Failed, Error in DataBase", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}