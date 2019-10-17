package net.mguenther.gtd.query.items.overview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActiveItemRepository extends JpaRepository<ActiveItem, Long> {

  Optional<ActiveItem> findByItemId(String itemId);

  List<ActiveItem> findAllByOrderByCreated();
}
