package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {
    final List<Productsmodel> RecycleList = new ArrayList<>();

    RecyclerView recycler;
    RecyclerView.Adapter adapterv2;
    RecyclerView.LayoutManager layoutManager;
    TextView noMeetingTexts;
    String name, userId, getCurrentDate;

    FirebaseFirestore db;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        /*Toolbar configuration and back button start */
//        getSupportActionBar().setTitle("Registration");
        /*Toolbar configuration and back button End */

        recycler = findViewById(R.id.recycler);
        loadinformation();
    }

    private void loadinformation() {

        db = FirebaseFirestore.getInstance();
        db.collection("Orders")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = (String) document.getData().get("name");
                                String price = (String) document.getData().get("price");
                                String documentID = (String) document.getId();



                                //String room_img_url = (String) document.getData().get("room_img_url");
                                RecycleList.add(new Productsmodel(name,price,documentID));
                                setProdItemRecycler(RecycleList);
//                                System.out.println("Hello" + document.getId() + " => " + document.getData() + "==> " + RecycleList.toString());
                            }
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void setProdItemRecycler(List<Productsmodel> recycleList) {

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterv2 = new ProductsAdapter(this, RecycleList);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapterv2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}