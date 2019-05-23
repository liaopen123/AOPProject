package cn.com.almostlover.aopproject.factory;


import cn.com.almostlover.apt_annotation.Food;

@Food(value = "面条",type = Meal.class)
public class Noodles implements  Meal{
    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public String getName() {
        return "面条";
    }
}
