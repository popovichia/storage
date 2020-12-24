package ru.popovichia.alfa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.popovichia.alfa.data.Item;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    
    Item save(Item item);
    
    @Query(
            value = "SELECT i.id FROM Item i WHERE i.parentBox.id in (:listBoxesIds) and i.color = :color"
    )
    List<Integer> findItemsByBoxesIdsAndItemColor(@Param("listBoxesIds") List<Integer> listBoxesIds, @Param("color") String color);

}
