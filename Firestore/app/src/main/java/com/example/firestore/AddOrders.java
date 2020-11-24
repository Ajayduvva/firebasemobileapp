package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddOrders extends AppCompatActivity {
    private EditText Name, Price;
    Button order;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        Name = findViewById(R.id.et_Product_name);
        Price = findViewById(R.id.et_product_price);
        order = findViewById(R.id.btn_submit);
        db = FirebaseFirestore.getInstance();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Name.getText().toString();
                String  price = Price.getText().toString();

                savedata(name,price);
            }
        });
    }

    public void savedata(String name, String price){
         Order order = new Order(name,price);

        db.collection("Orders")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddOrders.this, "Order Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddOrders.this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}