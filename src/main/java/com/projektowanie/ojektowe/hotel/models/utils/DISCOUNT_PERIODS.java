package com.projektowanie.ojektowe.hotel.models.utils;

public enum DISCOUNT_PERIODS {
    AUTUMN(1,9 ,1 ,3 , 5);

    private Integer startDay;
    private Integer startMonth;
    private Integer endDay;
    private Integer endMonth;
    private Integer discountSize;

    DISCOUNT_PERIODS(Integer startDay, Integer startMonth, Integer endDay, Integer endMonth, Integer discountSize){
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.discountSize = discountSize;
    }

    public Integer getDiscountSize() {
        return discountSize;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public Integer getStartMonth() {
        return startMonth;
    }
}
