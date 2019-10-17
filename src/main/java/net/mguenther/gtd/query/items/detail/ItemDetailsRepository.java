package net.mguenther.gtd.query.items.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Long> {

  Optional<ItemDetails> findByItemId(String itemId);
}
