package com.example.ndy.iscustomcalendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ndy.entity.EventObjects;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {

    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<EventObjects> allEvents;

    public GridAdapter(Context context, List<Date> monthlyDates,
            Calendar currentDate, List<EventObjects> allEvents) {
        super(context, R.layout.sincle_cell_custom_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        //todo perhaps this should be an instance variable to avoid costly instantiation
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) +1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) +1;
        int currentYear = currentDate.get(Calendar.YEAR);

        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.sincle_cell_custom_layout, parent, false);
        }



        //Add day to calendar
        /*TextView cellNumber = (TextView) cellCustView.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));*/
        CellCustomView cellCustView = (CellCustomView) view.findViewById(R.id.myCellCustomView);
        cellCustView.setRectStr(String.valueOf(dayValue));
        if(dayValue % 2 == 0){
            cellCustView.setDrawRect(true);
            cellCustView.setDrawCircle(true);
            cellCustView.setCircleStr(String.valueOf(dayValue));
        }


        if (displayMonth == currentMonth && displayYear == currentYear) {
            //cellCustView.setBackgroundColor(Color.parseColor("#FF5733"));
        } else{
           /* cellCustView.setBackgroundColor(Color.parseColor("#cccccc"));
            cellNumber.setTextColor(Color.parseColor("#C0C0C0"));*/
           cellCustView.setBackgroundColor(Color.parseColor("#cccccc"));
           cellCustView.setRectStr(String.valueOf(dayValue));
        }

        //addEvents to the calendar
        //TextView eventIndicator = (TextView) cellCustView.findViewById(R.id.event_id);
        //Calendar eventCalendar = Calendar.getInstance();

      /*  for (int i =0; i<allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH)
                    && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)){
                eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
            }
        }*/
        return view;
    }

    @Override
    public int getCount(){
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }
}
