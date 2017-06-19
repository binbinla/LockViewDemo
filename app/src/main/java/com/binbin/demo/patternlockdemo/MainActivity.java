package com.binbin.demo.patternlockdemo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.health.PackageHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PatternLockView patternLockView;
    private ImageView imageView;

    private PatternLockViewListener patternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(),"pattern begin");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(),"pattern progress:"+ PatternLockUtils.patternToString(patternLockView,progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(),"pattern complete:"+PatternLockUtils.patternToString(patternLockView,pattern));
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
            patternLockView.setViewMode(PatternLockView.PatternViewMode.AUTO_DRAW);
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(),"pattern has been cleared");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.img_hello_bin);
        patternLockView = (PatternLockView) findViewById(R.id.pattern_lock);
        patternLockView.setDotCount(3);
        patternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this,R.dimen.pattern_lock_dot_size));
        patternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this,R.dimen.pattern_lock_dot_selected_size));
        patternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this,R.dimen.pattern_lock_path_width));
        patternLockView.setAspectRatioEnabled(true);
        //设置宽高比(纵横比)
        patternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
        //设置View的模式(AUTO_DRWA/CORRECT/WRONG)
     //   patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        patternLockView.setDotAnimationDuration(100);
        patternLockView.setDotAnimationDuration(90);
        patternLockView.setCorrectStateColor(ResourceUtils.getColor(this,R.color.correct_col));
        patternLockView.setWrongStateColor(ResourceUtils.getColor(this,R.color.wrong_col));
        patternLockView.setNormalStateColor(ResourceUtils.getColor(this,R.color.white));
        patternLockView.setTactileFeedbackEnabled(true);
        patternLockView.setInputEnabled(true);
        //设置绘画路径可不可见
        patternLockView.setInStealthMode(false);
        patternLockView.addPatternLockListener(patternLockViewListener);

    }
}
