package net.mguenther.todo.query.items.overview;

import lombok.RequiredArgsConstructor;
import net.mguenther.todo.api.AllActiveItemsQuery;
import net.mguenther.todo.api.ItemConcludedEvent;
import net.mguenther.todo.api.ItemCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ActiveItemProjection {

  private final ActiveItemRepository repository;

  @Transactional
  @EventHandler
  public void onEvent(final ItemCreatedEvent event, final @Timestamp Instant ingestTime) {
    final ActiveItem activeItem = new ActiveItem(event.getItemId(), event.getDescription(), ingestTime.toEpochMilli());
    repository.save(activeItem);
  }

  @Transactional
  @EventHandler
  public void onEvent(final ItemConcludedEvent event) {
    repository
        .findByItemId(event.getItemId())
        .ifPresent(repository::delete);
  }

  @QueryHandler
  public List<ActiveItem> onQuery(final AllActiveItemsQuery query) {
    return repository.findAllByOrderByCreated();
  }
}
