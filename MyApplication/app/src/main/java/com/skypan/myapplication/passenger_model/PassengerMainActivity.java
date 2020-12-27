package com.skypan.myapplication.passenger_model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skypan.myapplication.R;
import com.skypan.myapplication.Retrofit.Rate;
import com.skypan.myapplication.Retrofit.profile;
import com.skypan.myapplication.Retrofit.RetrofitManagerAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PassengerMainActivity extends AppCompatActivity {

    public String user_id;
    private Button btn_logout;
    private FloatingActionButton btn_search;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    //黑糖有動的東西如下
    public static String name;
    public static String phone;
    public static Rate rate;
    public static Boolean sex;
    public static int weight;
    //黑糖有動的東西如上

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        Log.d("user_id", user_id);

        //黑糖動的東西如下
        // 把個人資料存入存入public的變數讓personal_informationActivity可以用後端吐回來的東西
        Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nmsl666.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RetrofitManagerAPI retrofitManagerAPI = retrofit.create(RetrofitManagerAPI.class);
        Call<profile> call = retrofitManagerAPI.showData(user_id);
        System.out.println("call信號 : " + call);
        call.enqueue(new Callback<profile>() {
            @Override
            public void onResponse(Call<profile> call, Response<profile> response) {
                if (!response.isSuccessful()) {
                    Log.d("error0", response.message());
                } else {
                    name = response.body().getName();
                    phone = response.body().getPhone();
                    rate = response.body().getRate();
                    sex = response.body().getSex();
                    weight = response.body().getWeight();
                }
            }
            @Override
            public void onFailure(Call<profile> call, Throwable t) {

            }
        });
        //黑糖動的東西如上



        //find views
        btn_search = findViewById(R.id.search);
        btn_logout = findViewById(R.id.btn_logout);
        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerMainActivity.this, SearchEventsActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("isOUMRTLogin", MODE_PRIVATE);
                preferences.edit()
                        .clear()
                        .commit();
                finish();
            }
        });

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.passengerHomeFragment)//頂級目的地(漢寶寶)，非頂級就會變箭頭
                .setDrawerLayout(drawerLayout)
                .build();

        //navController宣告在上面
        navController = Navigation.findNavController(PassengerMainActivity.this, R.id.passenger_nav_host_fragment);//hostFragment必須在當前activity內
        NavigationUI.setupActionBarWithNavController(PassengerMainActivity.this, navController, appBarConfiguration);//設定漢寶寶
        NavigationUI.setupWithNavController(navigationView, navController);//不知道是三小

//        可以跳到其他activity但其他item功能被覆蓋了
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.passengerSwitchToDriver) {
//                    Intent newIntent = new Intent(PassengerMainActivity.this, DriverMainActivity.class);
//                    startActivity(newIntent);
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {//讓漢寶寶有作用
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {//點返回鍵可以讓漢寶寶收回去
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();

            } else {
                doubleBackToExitPressedOnce = true;
                Snackbar.make(findViewById(android.R.id.content), "再點擊一次返回鍵以退出", Snackbar.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }
}