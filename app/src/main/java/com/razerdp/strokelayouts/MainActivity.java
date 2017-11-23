package com.razerdp.strokelayouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.widget.CompoundButton;

import com.razerdp.widgets.ExcludeSide;
import com.razerdp.widgets.StrokeFrameLayout;
import com.razerdp.widgets.StrokeLinearLayout;
import com.razerdp.widgets.StrokeRelativeLayout;

public class MainActivity extends AppCompatActivity {

    private AppCompatCheckBox checkExcludeLeft;
    private AppCompatCheckBox checkExcludeTop;
    private AppCompatCheckBox checkExcludeRight;
    private AppCompatCheckBox checkExcludeBottom;
    private StrokeRelativeLayout rlStroke;
    private StrokeLinearLayout llStroke;
    private StrokeFrameLayout frameStroke;

    private int excludeSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        checkExcludeLeft = findViewById(R.id.check_exclude_left);
        checkExcludeTop = findViewById(R.id.check_exclude_top);
        checkExcludeRight = findViewById(R.id.check_exclude_right);
        checkExcludeBottom = findViewById(R.id.check_exclude_bottom);
        rlStroke = findViewById(R.id.rl_stroke);
        llStroke = findViewById(R.id.ll_stroke);
        frameStroke = findViewById(R.id.frame_stroke);
        int exclude = ExcludeSide.EXCLUDE_LEFT + ExcludeSide.EXCLUDE_RIGHT + ExcludeSide.EXCLUDE_TOP + ExcludeSide.EXCLUDE_BOTTOM;
        Log.i("result", "" + (ExcludeSide.EXCLUDE_LEFT | ExcludeSide.EXCLUDE_RIGHT | ExcludeSide.EXCLUDE_TOP | ExcludeSide.EXCLUDE_BOTTOM));

        checkExcludeLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    excludeSide = add(ExcludeSide.EXCLUDE_LEFT);
                } else {
                    excludeSide = sub(ExcludeSide.EXCLUDE_LEFT);
                }
                applyExcludeSide();
            }
        });

        checkExcludeTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    excludeSide = add(ExcludeSide.EXCLUDE_TOP);
                } else {
                    excludeSide = sub(ExcludeSide.EXCLUDE_TOP);
                }
                applyExcludeSide();
            }
        });

        checkExcludeRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    excludeSide = add(ExcludeSide.EXCLUDE_RIGHT);
                } else {
                    excludeSide = sub(ExcludeSide.EXCLUDE_RIGHT);
                }
                applyExcludeSide();
            }
        });

        checkExcludeBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    excludeSide = add(ExcludeSide.EXCLUDE_BOTTOM);
                } else {
                    excludeSide = sub(ExcludeSide.EXCLUDE_BOTTOM);
                }
                applyExcludeSide();
            }
        });
    }

    private int add(int a) {
        return excludeSide + a;
    }

    private int sub(int a) {
        return excludeSide - a;
    }

    private void applyExcludeSide() {
        rlStroke.setExcludeSide(excludeSide);
        llStroke.setExcludeSide(excludeSide);
        frameStroke.setExcludeSide(excludeSide);
    }
}
