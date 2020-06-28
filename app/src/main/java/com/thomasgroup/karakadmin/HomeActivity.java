package com.thomasgroup.karakadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout mainlayout;
    private BottomNavigationView bottomNavigationView;
    private DonarFregement donarFregement;
    private RequestFragement requestFragement;
    private UserFragement userFragement;
    private FirebaseAuth Mauth;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Mauth =  FirebaseAuth.getInstance();
        mainlayout = findViewById(R.id.MainFreamLayoutID);
        bottomNavigationView = findViewById(R.id.BottomNavViewID);


        toolbar = findViewById(R.id.HomeToolbarID);
        setSupportActionBar(toolbar);

        donarFregement = new DonarFregement();
        requestFragement = new RequestFragement();
        userFragement = new UserFragement();

        setuppages(donarFregement);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.BloodDonactionID){
                        setuppages(donarFregement);
                }
                if(item.getItemId() == R.id.BloodDonarID){
                    setuppages(requestFragement);
                }
                if(item.getItemId() == R.id.UsersID){
                    setuppages(userFragement);
                }


                return true;
            }


        });

    }

    private void setuppages(Fragment fragement) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.MainFreamLayoutID, fragement);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.LogOutID){
            Mauth.signOut();
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        FirebaseUser Muser = Mauth.getCurrentUser();
        if(Muser == null){
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}