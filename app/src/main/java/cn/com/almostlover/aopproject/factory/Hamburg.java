package cn.com.almostlover.aopproject.factory;


import cn.com.almostlover.apt_annotation.Food;

@Food(value = "汉堡包",type = Meal.class)
public class Hamburg implements Meal {
    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public String getName() {
        return "汉堡包";
    }
}
