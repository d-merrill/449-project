package com.donmerrill.cravely;

public class Food {
    private int foodID;
    private String foodName;
    private String origin;
    private String description;

    public Food(int foodID, String foodName, String origin, String description){
        this.foodID = foodID;
        this.foodName = foodName;
        this.origin = origin;
        this.description = description;
    }

    public int getFoodID(){
        return foodID;
    }

    public String getFoodName(){
        return foodName;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDescription(){
        return description;
    }
}
