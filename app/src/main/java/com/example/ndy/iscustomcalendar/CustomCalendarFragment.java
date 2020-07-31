package com.example.ndy.iscustomcalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ndy.database.DatabaseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendarFragment extends Fragment {


        private static final String TAG =
                CustomCalendarFragment.class.getSimpleName();
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

    private static final String DATE_IN_MS = "date_in_ms";


    public CustomCalendarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long dateInMili = (long) getArguments().getSerializable(DATE_IN_MS);
        cal.setTimeInMillis(dateInMili);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup container,
                             Bundle savedInstancState){
        View view = layoutInflater.inflate(R.layout.calendar_layout, container, false);
        this.context = getActivity();
        initializeUILayout(view);
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();

        return view;
    }

        /*public void calendarSetup(Context context, AttributeSet attributeSet) {
            this.context = context;
            initializeUILayout();
            setUpCalendarAdapter();
            setPreviousButtonClickEvent();
            setNextButtonClickEvent();
            setGridCellClickEvents();
            Log.d(TAG, "I need to call this method");
        }*/

    public static CustomCalendarFragment newInstance(long dateInMilisecons) {
        Bundle args = new Bundle();
        args.putSerializable(DATE_IN_MS, dateInMilisecons);
        CustomCalendarFragment customCalendarFragment = new CustomCalendarFragment();
        customCalendarFragment.setArguments(args);
        return customCalendarFragment;
    }


        private void initializeUILayout(View view){
            previousButton = (ImageView) view.findViewById(R.id.previous_month);
            nextButton = (ImageView) view.findViewById(R.id.next_month);
            currentDate = (TextView) view.findViewById(R.id.display_current_date);
            //addEventbutton = (Button) view.findViewById(R.id.add_calendar_event);
            calendarGridView = (GridView)view.findViewById(R.id.calendar_grid);
        }

        private void setPreviousButtonClickEvent(){
            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cal.add(Calendar.MONTH, - 1);
                    setUpCalendarAdapter();
                }
            });
        }


        private void setNextButtonClickEvent(){
            nextButton.setOnClickListener(new View.OnClickListener() {
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
