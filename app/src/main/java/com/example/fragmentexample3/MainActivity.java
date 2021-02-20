package com.example.fragmentexample3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean threePane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if cannot find the second fragment in the layout
        // meas only 1 column - portrait
        // else landscape
        if (findViewById(R.id.fragmentContainerView_land_second) != null){
            threePane = true;
        }

        // loading in whether 1 or 2 fragments based on this boolean
        if (!threePane){
            // portrait
            loadFragment(new FirstFragment(), R.id.fragmentContainer_first);
            Button button_personality = findViewById(R.id.button_personality);
            button_personality.setOnClickListener(v -> launchSecondActivity(v));

            Button button_house_info = findViewById(R.id.button_house_info);
            button_house_info.setOnClickListener(v -> launchThirdActivity(v));
        }
        else {
            loadFragment(new FirstFragment(), R.id.fragmentContainer_land_first);
            loadFragment(new SecondFragment(), R.id.fragmentContainerView_land_second);
            loadFragment(new ThirdFragment(), R.id.fragmentContainerView_land_third);
        }

    }

    public void loadFragment(Fragment fragment, int id){
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a fragment transaction to begin the transaction and replace the fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //replacing the placeholder - fragmentContainterView with the fragment that is passed as parameter
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public void launchSecondActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void launchThirdActivity(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}