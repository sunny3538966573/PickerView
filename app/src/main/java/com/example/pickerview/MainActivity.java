package com.example.pickerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.airsaid.pickerviewlibrary.TimePickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button btn_time;
    private Button btn_city;
    private Button btn_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }


    /**
     * 初始化view
     */
    private void initView() {
        btn_time = findViewById(R.id.btn_time);
        btn_city = findViewById(R.id.btn_city);
        btn_option = findViewById(R.id.btn_option);
        //时间
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePV();
            }
        });
        //城市
        btn_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPV();
            }
        });
        //选项
        btn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionPV();
            }
        });
    }

    /**
     * 时间选择
     */
    private void timePV() {
        TimePickerView mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        // 设置是否循环
        mTimePickerView.setCyclic(true);
        // 设置滚轮文字大小
        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
        // 设置时间可选范围(结合 setTime 方法使用,必须在)
        //Calendar calendar = Calendar.getInstance();
        // mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
        // 设置选中时间
        // mTimePickerView.setTime(new Date());
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                //时期格式
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
            }
        });
        //显示
        mTimePickerView.show();
    }

    /**
     * 城市选择
     */
    private void cityPV() {
        CityPickerView mCityPickerView = new CityPickerView(this);
        // 设置点击外部是否消失
        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
        mCityPickerView.setTextSize(20f);
        // 设置标题
        mCityPickerView.setTitle("选择城市");
        // 设置取消文字
        mCityPickerView.setCancelText("取消");
        // 设置取消文字颜色
        mCityPickerView.setCancelTextColor(Color.RED);
        // 设置取消文字大小
        mCityPickerView.setCancelTextSize(15f);
        // 设置确定文字
        mCityPickerView.setSubmitText("确定");
        // 设置确定文字颜色
        mCityPickerView.setSubmitTextColor(Color.BLUE);
        // 设置确定文字大小
        mCityPickerView.setSubmitTextSize(15f);
        // 设置头部背景
        mCityPickerView.setHeadBackgroundColor(Color.GRAY);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {
            @Override
            public void onCitySelect(String prov, String city, String area) {
                // 省、市、区 分开获取
                Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
            }

            @Override
            public void onCitySelect(String str) {
                // 一起获取
                Toast.makeText(MainActivity.this, "选择了：" + str, Toast.LENGTH_SHORT).show();
            }
        });
        mCityPickerView.show();


    }

    /**
     * 选项选择
     */
    private void optionPV() {
        OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        // 设置数据
        mOptionsPickerView.setPicker(list);
        // 设置选项单位
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String sex = list.get(option1);
                Toast.makeText(MainActivity.this, sex, Toast.LENGTH_SHORT).show();
            }
        });
        mOptionsPickerView.show();
    }
}
