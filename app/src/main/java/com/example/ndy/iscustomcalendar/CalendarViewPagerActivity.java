package com.example.ndy.iscustomcalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.joda.time.DateTime;
import org.joda.time.Months;

import java.util.Calendar;

public class CalendarViewPagerActivity extends AppCompatActivity {

    private final DateTime _minimum_date
            = new DateTime(1900, 1, 1, 0, 1);
    private final DateTime _maximum_date =
            new DateTime(2100, 12, 31, 23, 59);

    public CalendarViewPagerActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view_pager);

        ViewPager viewPager = findViewById(R.id.calendar_viewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                DateTime newDate = _minimum_date.plusMonths(position);
                return CustomCalendarFragment.newInstance(newDate.getMillis());
            }

            @Override
            public int getCount() {
                return Months.monthsBetween(_minimum_date, _maximum_date).getMonths();
            }
        });

        //Setting pager to correct date
        DateTime now = new DateTime();
        int index = Months.monthsBetween(_minimum_date, now).getMonths();
        viewPager.setCurrentItem(index);

    }
}
