package com.example.ndy.iscustomcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ndy.database.DatabaseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by NDY on 4/11/2018.
 */

public class CalendarCustomView extends LinearLayout {
    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private GridView calendarGridView;
    private Button addEventbutton;
    private static final int MAX_CALENdAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private GridAdapter  mAdapter;
    private DatabaseQuery mQuery;

    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }

    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout(){
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.calendar_layout, this);
        previousButton = (ImageView) view.findViewById(R.id.previous_month);
        nextButton = (ImageView) view.findViewById(R.id.next_month);
        currentDate = (TextView) view.findViewById(R.id.display_current_date);
        //addEventbutton = (Button) view.findViewById(R.id.add_calendar_event);
        calendarGridView = (GridView)view.findViewById(R.id.calendar_grid);
    }

    private void setPreviousButtonClickEvent(){
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.MONTH, - 1);
                setUpCalendarAdapter();
            }
        });
    }


    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(context, "Click " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpCalendarAdapter(){
        List<Date> dayValueInCells = new ArrayList<Date>();
        //mQuery= new DatabaseQuery(context);
        //List<EventObjects> mEvents = mQuery.getAllFutureEvents();
        Calendar mCal = (Calendar) cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK )- 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENdAR_COLUMN) {
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
        mAdapter = new GridAdapter(context, dayValueInCells, cal, null/*mEvents*/);
        calendarGridView.setAdapter(mAdapter);

    }
}
