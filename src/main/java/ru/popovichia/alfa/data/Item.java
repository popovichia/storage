package ru.popovichia.alfa.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */

@Entity
@Table(name="item")
public class Item {
    
    @Id
    @Column(name="id")
    private Integer id;
    
    @Column(name="color")
    private String color;
    
    @ManyToOne
    @JoinColumn(name="contained_in")
    private Box parentBox;
    
    public Item() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Box getParentBox() {
        return parentBox;
    }

    public void setParentBox(Box parentBox) {
        this.parentBox = parentBox;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item{id=").append(id);
        sb.append(", color=").append(color);
        sb.append(", parentBox=").append(parentBox);
        sb.append('}');
        return sb.toString();
    }
    
}
