package com.feoul.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PieView mZokyView;
    private ArrayList<CircleData> datas = new ArrayList<CircleData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i<20 ; i= i+ 3){
            CircleData circleData = new CircleData("name"+i,i);
            datas.add(circleData);
        }
        mZokyView = (PieView) findViewById(R.id.cv_zoky);
        mZokyView.setData(datas);
        mZokyView.setStartAngle(0);
    }
}
