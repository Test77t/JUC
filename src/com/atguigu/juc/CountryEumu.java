package com.atguigu.juc;

public enum CountryEumu {
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),FOUR(4,"韩国"),FIVE(5,"赵国"),SIX(6,"魏国");

    CountryEumu(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    private Integer retCode;
    private String retMessage;

    public static CountryEumu forEach_Country(int index)
    {
        CountryEumu[] values = CountryEumu.values();
        for (CountryEumu element: values
             ) {
            if(index == element.getRetCode())
            {
                return element;
            }
        }
        return null;
    }
}
