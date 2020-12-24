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
@Table(name = "box")
public class Box {
    
    @Id
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "contained_in")
    private Box parentBox;
    
    public Box() {    

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        sb.append("Box{id=").append(id);
        sb.append(", parentBox=").append(parentBox);
        sb.append('}');
        return sb.toString();
    }
   

}
