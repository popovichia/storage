package ru.popovichia.alfa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.popovichia.alfa.data.Box;

/**
 *
 * @author Igor Popovich, email: popovichia, phone: +7 913 902 36 36, company:
 * popovichia@gmail.com
 */
@Repository
public interface BoxesRepository extends JpaRepository<Box, Integer> {
    
    Box save(Box box);
    Box findById(int id);
    
    @Query(
            value = "SELECT b.id from Box as b WHERE b.parentBox.id in (:listParentBoxesIds)"
    )
    List<Integer> findListChildBoxesIdsByListParentBoxesIds(@Param("listParentBoxesIds") List<Integer>listParentBoxesIds);
    
}
