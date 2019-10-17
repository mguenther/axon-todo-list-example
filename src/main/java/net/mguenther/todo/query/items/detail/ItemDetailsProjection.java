package net.mguenther.todo.query.items.detail;

import lombok.RequiredArgsConstructor;
import net.mguenther.todo.api.ItemConcludedEvent;
import net.mguenther.todo.api.ItemCreatedEvent;
import net.mguenther.todo.api.ShowDetailsForItemQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ItemDetailsProjection {

  private final ItemDetailsRepository repository;

  @Transactional
  @EventHandler
  public void onEvent(final ItemCreatedEvent event, final @Timestamp Instant ingestTime) {
    final ItemDetails itemDetails = new ItemDetails(event.getItemId(), event.getDescription(), ingestTime.toEpochMilli());
    repository.save(itemDetails);
  }

  @Transactional
  @EventHandler
  public void onEvent(final ItemConcludedEvent event, final @Timestamp Instant ingestTime) {
    repository
        .findByItemId(event.getItemId())
        .ifPresent(itemDetails -> {
          itemDetails.conclude(ingestTime.toEpochMilli());
          repository.save(itemDetails);
        });
  }

  @QueryHandler
  public ItemDetails onQuery(final ShowDetailsForItemQuery query) {
    return repository
        .findByItemId(query.getItemId())
        .orElseThrow(() -> new IllegalStateException("There is no item with ID " + query.getItemId() + "."));
  }
}
