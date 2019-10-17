package net.mguenther.gtd.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mguenther.gtd.api.ConcludeItemCommand;
import net.mguenther.gtd.api.CreateItemCommand;
import net.mguenther.gtd.api.ItemConcludedEvent;
import net.mguenther.gtd.api.ItemCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

  @AggregateIdentifier
  private String itemId;

  // We could actually omit the description member in this aggregate as we only have to keep the state that is
  // required for validating incoming commands against the state of the aggregate
  private String description;

  private boolean concluded;

  @CommandHandler
  public Item(final CreateItemCommand command) {
    final ItemCreatedEvent event = new ItemCreatedEvent(command.getItemId(), command.getDescription());
    // It is possible to pass along an instance of type org.axonframework.messaging.MetaData.
    // MetaData typically closes around data that does not belong to the actual domain, e.g. the ID
    // of the user who submitted the command.
    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void onCommand(final ConcludeItemCommand command) {

    // This should actually never happen, so this check is superfluous I guess (have a look at the Axon
    // example applications).
    if (!command.getItemId().equals(itemId)) {
      log.info("Received a 'ConcludeItemCommand' for item with ID '{}' that was routed to item with ID '{}'.",
          command.getItemId(), itemId);
      return;
    }

    if (concluded) {
      final String message = "The item with ID '%s' has already been concluded. Dropping the 'ConcludeItemCommand' request.";
      throw new IllegalStateException(String.format(message, command.getItemId()));
    }

    final ItemConcludedEvent event = new ItemConcludedEvent(command.getItemId());
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void onEvent(final ItemCreatedEvent event) {
    this.itemId = event.getItemId();
    this.description = event.getDescription();
    this.concluded = false;
  }

  @EventSourcingHandler
  public void onEvent(final ItemConcludedEvent event) {
    this.concluded = true;
  }
}
