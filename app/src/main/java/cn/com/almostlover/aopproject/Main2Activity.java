package cn.com.almostlover.aopproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import cn.com.almostlover.aopproject.factory.Meal;
import cn.com.almostlover.aopproject.factory.MealFactory;
import cn.com.almostlover.apt_annotation.TestAnnotation;

@TestAnnotation("className是Main2Activity")
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//
        MealFactory mealFactory = new MealFactory();
//        Meal food = mealFactory.getFoodInstance("油条");
        Meal food = mealFactory.getFoodInstance("金针菇");
        Toast.makeText(this, food.getName()+food.getPrice(), Toast.LENGTH_SHORT).show();
    }
}
