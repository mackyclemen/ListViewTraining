package com.mackyc.projects.listviewtraining;

public class CostBreakdownItem {

    public static final int CATEGORY_FOOD = 0;
    public static final int CATEGORY_HEALTH = 1;
    public static final int CATEGORY_ESSENTIALS = 2;
    public static final int CATEGORY_SCHOOL = 3;
    public static final int CATEGORY_OTHER = 127;

    private int category;
    private float breakdownPercentage;

    public CostBreakdownItem(int category, float breakdownPercentage) {
        this.category = category;
        this.breakdownPercentage = breakdownPercentage;
    }

    public int getCategory() {
        return category;
    }

    public float getBreakdownPercentage() {
        return breakdownPercentage;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setBreakdownPercentage(float breakdownPercentage) {
        this.breakdownPercentage = breakdownPercentage;
    }
}
