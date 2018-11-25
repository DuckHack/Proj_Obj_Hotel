package com.projektowanie.ojektowe.hotel.models.utils;


import java.util.Date;

public enum DISCOUNT_TYPE {
    AUTUMN(0.3), SUMMER_END(0.1);
    private Double discountSize;
    private Date start;
    private Date end;
    DISCOUNT_TYPE(Double discountSize){
        this.discountSize = discountSize;
    }
    public Double getDiscountSize(){return this.discountSize;}
    public void setDiscountSize(Double discountSize){this.discountSize = discountSize;}
}
