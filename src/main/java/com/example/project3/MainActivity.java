package com.example.project3;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements DropDownFrag.selectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void selected(String sel) {//THIS IS WHERE THE UPDATING WILL HAPPEN
        if(!sel.equals("Select a breed")) {
            System.out.println(sel);
        }

    }
}