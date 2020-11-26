package com.example.project_1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //BottomNavigation 관련 변수
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private MyprofileFragment myprofileFragment;
    private SearchAdressFragment searchAdressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //BottomNavigation
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        setFrag(0);
                        break;
                    case R.id.chat:
                        setFrag(1);
                        break;
                    case R.id.profile:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        chatFragment = new ChatFragment();
        myprofileFragment = new MyprofileFragment();
        setFrag(0);

        searchAdressFragment = new SearchAdressFragment();


    }

    //프래그먼트 교체
    public void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, homeFragment);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.mainFrame, chatFragment);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.mainFrame, myprofileFragment);
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.mainFrame, searchAdressFragment);
                ft.commit();
                break;

        }
    }
}