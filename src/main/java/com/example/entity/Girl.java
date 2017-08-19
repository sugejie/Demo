package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 如果需要在repository中使用nativeQuery，@Entity一定要明确name=girl
 */
@Entity(name = "girl")
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;

    private String cupSize;

    @NotNull(message = "金额必传")
    private Double money;

    @Min(value = 18, message = "未成年少女禁止入内")
    private Integer age;

    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "id:" + id + ",cupSize:" + cupSize + ",money:" + money;
    }
}
