package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragment.AnotherRightFragment;
import com.example.myapplication.fragment.RightFragment;

public class TestFragmentSimpleActivity extends AppCompatActivity implements View.OnClickListener {
    private Button leftSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_simple);
        leftSwitch = findViewById(R.id.left_switch);
        leftSwitch.setOnClickListener(this);
        AnotherRightFragment rightFragment = new AnotherRightFragment();
        System.out.println(rightFragment);
        replaceFragment(new RightFragment());
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_switch:
                replaceFragment(new AnotherRightFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}