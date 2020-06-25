package com.szubov.android_hw_43;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mBtnChooseStartDate;
    private Button mBtnChooseEndDate;
    private CalendarView mClndrViewStartDate;
    private CalendarView mClndrViewEndDate;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        mBtnChooseStartDate = findViewById(R.id.btnChooseStartDate);
        mBtnChooseEndDate = findViewById(R.id.btnChooseEndDate);
        mClndrViewStartDate = findViewById(R.id.clndrViewStartDate);
        mClndrViewEndDate = findViewById(R.id.clndrViewEndDate);

        mClndrViewStartDate.setVisibility(View.GONE);
        mClndrViewEndDate.setVisibility(View.GONE);


        mClndrViewStartDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                mStartDateTxt = getText(R.string.btn_start_date).toString() + ": " +
                        year + "-" + month + "-" + dayOfMonth;
                mBtnChooseStartDate.setText(mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mStartDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

        mClndrViewEndDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                mEndDateTxt =getText(R.string.btn_end_date).toString() + ": " +
                        year + "-" + month + "-" + dayOfMonth;
                mBtnChooseEndDate.setText(mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year,month,dayOfMonth);
                mEndDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

    }


    public void btnChooseStartDateOnClick(View view) {
        mClndrViewStartDate.setVisibility(View.VISIBLE);
        mClndrViewEndDate.setVisibility(View.GONE);
    }

    public void btnChooseEndDateOnClick(View view) {
        mClndrViewEndDate.setVisibility(View.VISIBLE);
        mClndrViewStartDate.setVisibility(View.GONE);
    }


    public void btnTaskOkOnClick(View view) {
        while (true) {
            if (mStartDateTxt == null || mEndDateTxt == null) {
                Toast.makeText(this, getText(R.string.btn_date_not_selected).toString(),
                        Toast.LENGTH_LONG).show();
                break;
            } else if (mStartDate > mEndDate) {
                Toast.makeText(this, getText(R.string.btn_task_ok_err).toString(),
                        Toast.LENGTH_LONG).show();
                resetDateValues();

                break;
            } else {
                Toast.makeText(this, mBtnChooseStartDate.getText().toString() + "\n" +
                        mBtnChooseEndDate.getText().toString(), Toast.LENGTH_LONG).show();
                resetDateValues();
            }
            break;
        }
    }

    private void resetDateValues() {
        mBtnChooseStartDate.setText(getText(R.string.btn_start_date).toString());
        mBtnChooseEndDate.setText(getText(R.string.btn_end_date).toString());
        mStartDateTxt = null;
        mEndDateTxt = null;
        mStartDate = 0;
        mEndDate = 0;
    }
}