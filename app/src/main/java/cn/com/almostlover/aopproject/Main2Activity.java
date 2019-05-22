package cn.com.almostlover.aopproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cn.com.almostlover.apt_annotation.TestAnnotation;

@TestAnnotation("className是Main2Activity")
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
