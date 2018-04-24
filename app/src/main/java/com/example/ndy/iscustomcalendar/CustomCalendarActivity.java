package com.example.ndy.iscustomcalendar;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomCalendarActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CustomCalendarFragment();
    }
}
