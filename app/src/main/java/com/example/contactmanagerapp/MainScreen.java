package com.example.contactmanagerapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainScreen extends AppCompatActivity {

    private DatabaseReference database;
    private Button add;
    private Button ok;
    private Dialog dialog;
    private TextInputEditText userName;
    private TextInputEditText userEmail;
    private TextInputEditText userMNumber;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainscreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add = findViewById(R.id.add);
        dialog = new Dialog(MainScreen.this);
        dialog.setContentView(R.layout.customize_boxes);
        ok = dialog.findViewById(R.id.ok);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.layout.alert_box));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userMNumber = findViewById(R.id.userMNumber);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String number = userMNumber.getText().toString();

                Contact contact = new Contact(name, email, number);
                database = FirebaseDatabase.getInstance().getReference("Contact");
                database.child(number).setValue(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dialog.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainScreen.this, "Sorry To Say, Try Agin", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
