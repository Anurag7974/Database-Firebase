package com.example.databasefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private EditText editText;
    private TextView textView;

    private FirebaseDatabase Database;
    private DatabaseReference Reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubmitButton = findViewById(R.id.mSubmitButton);
        editText = findViewById(R.id.mInputText);
        textView = findViewById(R.id.mOutputText);


        initViews();
        Database = FirebaseDatabase.getInstance();
//        Reference = Database.getReference();
        Reference = Database.getReference("user");

        this.mSubmitButton.setOnClickListener(this::runCodeView);

    }

    private void initViews() {
    }

    public void runCodeView(View view) {
        String data = editText.getText().toString();
        Reference.setValue(data);

        Toast.makeText(this, "Data inserted...", Toast.LENGTH_SHORT).show();
    }

    public void readData(View view) {
        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                textView.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}