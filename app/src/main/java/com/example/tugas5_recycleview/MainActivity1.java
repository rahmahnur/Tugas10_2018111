package com.example.tugas5_recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tugas5_recycleview.databinding.ActivityMain1Binding;
import com.example.tugas5_recycleview.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity1 extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle abdt;
    private ActivityMain1Binding binding;

    RecyclerView recylerView;

    String s1[], s2[], s3[];
    int images[] = {R.drawable.batik1, R.drawable.batik2, R.drawable.batik3, R.drawable.jas1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork(
                        "Notifikasi", ExistingWorkPolicy.REPLACE,
                        request);
            }
        });
        //setContentView(R.layout.activity_main1);
        drawer = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        drawer.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_alarm) {
                    Intent a = new Intent(MainActivity1.this,
                            MainActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_home) {
                    Intent b = new Intent(MainActivity1.this,
                            MainActivity1.class);
                    startActivity(b);
                } else if (id == R.id.nav_chat) {
                    Intent c = new Intent(MainActivity1.this,
                            MainActivity2.class);
                    startActivity(c);
                } else if (id == R.id.nav_profile) {
                    Intent d = new Intent(MainActivity1.this,
                            MainActivity3.class);
                    startActivity(d);
                } else if (id == R.id.nav_sql) {
                    Intent e = new Intent(MainActivity1.this,
                            MainActivity4.class);
                    startActivity(e);
                } else if (id == R.id.nav_api) {
                    Intent f = new Intent(MainActivity1.this,
                            ApiActivity.class);
                    startActivity(f);
                }
                else if (id == R.id.nav_logout) {
                    Intent e = new Intent(MainActivity1.this,
                            ProfileActivity.class);
                    startActivity(e);
                }
                return true;
            }
        });
        recylerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.produk);
        s2 = getResources().getStringArray(R.array.star);
        s3 = getResources().getStringArray(R.array.harga);
        ProdukAdapter appAdapter = new ProdukAdapter(this, s1, s2, s3, images);
        recylerView.setAdapter(appAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager((this), RecyclerView.HORIZONTAL, false);
        recylerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}

