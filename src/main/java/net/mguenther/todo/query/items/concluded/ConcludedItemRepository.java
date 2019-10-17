package net.mguenther.todo.query.items.concluded;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcludedItemRepository extends JpaRepository<ConcludedItem, Long> {

  Optional<ConcludedItem> findByItemId(String itemId);

  List<ConcludedItem> findAllByOrderByConcludedDesc();
}
