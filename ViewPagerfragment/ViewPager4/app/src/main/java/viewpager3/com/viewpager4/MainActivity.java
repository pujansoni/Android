package viewpager3.com.viewpager4;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    private static int currentPage=0;
    private static int numPages=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viewpager code
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
        //circle indicator code
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE)
                {
                    int pagecount=4;
                    if(currentPage==0){
                        viewPager.setCurrentItem(pagecount-1,false);
                    }
                    else if(currentPage==pagecount-1){
                        viewPager.setCurrentItem(0,false);
                    }
                }
            }
        });
        //slide show with timer
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage==numPages){
                    currentPage=0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipe = new Timer();
        swipe.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },1000,1000);

    }
}
