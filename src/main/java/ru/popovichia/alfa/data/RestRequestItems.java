package ru.popovichia.alfa.data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
public class RestRequestItems {
    
    @NotNull
    @Min(value = 0)
    private Integer box;

    @NotNull
    @NotEmpty
    private String color;

    public RestRequestItems() {
    }

    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RestRequestItems{box=").append(box);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
    
}
