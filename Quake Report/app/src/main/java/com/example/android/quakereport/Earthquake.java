package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.time;

/**
 * Created by Administrator on 29/09/2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }
    public double getMagnitude() { return mMagnitude; }
    public String getLocation() { return mLocation; }
    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }
    public String getUrl() { return mUrl; }
}
