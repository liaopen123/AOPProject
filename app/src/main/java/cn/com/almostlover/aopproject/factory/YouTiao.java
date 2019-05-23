package cn.com.almostlover.aopproject.factory;


import cn.com.almostlover.apt_annotation.Food;

@Food(value = "油条",type = Meal.class)
public class YouTiao implements Meal {
    @Override
    public int getPrice() {
        return 1;
    }

    @Override
    public String getName() {
        return "油条";
    }
}
