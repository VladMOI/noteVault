package ua.vladmoyseienko.notevault.StartingActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ua.vladmoyseienko.notevault.MainActivity.MainActivity;
import ua.vladmoyseienko.notevault.R;

public class SliderActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String PREFS_NAME = "SliderPrefs";//Название состояния(была открыта или нет)
    private static final String IS_FIRST_RUN = "isFirstRun";//состояние
    private final String TAG = "SliderActivity";

    protected View view;
    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private SliderAdapter mAdapter;

    private int[] mStringResources = {
            R.string.slider_1,
            R.string.slider_2,
            R.string.slider_3,
            R.string.slider_4,
            R.string.slider_5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean isFirstRun = settings.getBoolean(IS_FIRST_RUN, true);
        if(isFirstRun){
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(IS_FIRST_RUN, false);
            editor.apply();
            // To make activity full screen.
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setReference();
        } else
        {
            startActivity(new Intent(this, MainActivity.class));
        }

    }
    public void setReference() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_slider, null);

        intro_images = (ViewPager) view.findViewById(R.id.pager_introduction);
        btnNext = (ImageButton) view.findViewById(R.id.btn_next);
        btnFinish = (ImageButton) view.findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        mAdapter = new SliderAdapter(this, mStringResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setContentView(view);
        setUiPageViewController();
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount() ;
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            if(i == dotsCount){
                dots[i].setVisibility(View.GONE);
            }
            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount)
                        ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_finish:
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Resources.Theme theme = this.getTheme();
        Log.d(TAG, "Position: " + position + "DotsCount: " + dotsCount);
        if(position == dotsCount - 1){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

       dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}