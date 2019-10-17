package net.mguenther.todo.query.items.concluded;

import lombok.RequiredArgsConstructor;
import net.mguenther.todo.api.ItemConcludedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ConcludedItemsProjection {

  private final ConcludedItemRepository repository;

  @Transactional
  @EventHandler
  public void onEvent(final ItemConcludedEvent event, final @Timestamp Instant ingestTime) {
    final ConcludedItem concludedItem = new ConcludedItem(event.getItemId(), ingestTime.toEpochMilli());
    repository.save(concludedItem);
  }
}
