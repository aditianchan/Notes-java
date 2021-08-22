package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class typeactivity extends AppCompatActivity {
    private EditText edittext;
    private Button butt;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeactivity);
        edittext = (EditText)findViewById(R.id.type);
        butt = (Button)findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String value = edittext.getText().toString();
                Map<String, Object> postMap = new HashMap<>();
                postMap.put("value", value);

                final Task<DocumentReference> documentReferenceTask = firebaseFirestore.collection("post").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(typeactivity.this, "Done", Toast.LENGTH_LONG).show();
                            Intent intt = new Intent(typeactivity.this, MainActivity.class);
                            startActivity(intt);
                            finish();

                        } else {
                            Toast.makeText(typeactivity.this, "Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }// Handle failures

    });// ...
    }
}





