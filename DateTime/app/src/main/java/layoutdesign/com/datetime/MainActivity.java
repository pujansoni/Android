package layoutdesign.com.datetime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvDate, tvTime;
    Button btnDate, btnTime;
    int day,month,myear,hour,minute,second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (TextView)findViewById(R.id.tv_date);
        tvTime = (TextView)findViewById(R.id.tv_time);
        btnDate = (Button)findViewById(R.id.datePicker);
        btnDate.setOnClickListener(this);
        btnTime = (Button)findViewById(R.id.timePicker);
        btnTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        switch (view.getId()){
            case R.id.datePicker:
                day= calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                myear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                tvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },myear,month,day);
                datePickerDialog.show();
                break;
            case R.id.timePicker:
                second = calendar.get(Calendar.SECOND);
                minute = calendar.get(Calendar.MINUTE);
                hour = calendar.get(Calendar.HOUR);
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                                tvTime.setText(mHour + " : " + mMinute);
                            }
                        },hour,minute,true);
                timePickerDialog.show();
                break;
                default:
                    break;
        }
    }
}
