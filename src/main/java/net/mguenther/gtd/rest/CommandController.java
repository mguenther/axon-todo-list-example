package net.mguenther.gtd.rest;

import lombok.RequiredArgsConstructor;
import net.mguenther.gtd.api.ConcludeItemCommand;
import net.mguenther.gtd.api.CreateItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
public class CommandController {

  private final CommandGateway commandGateway;

  @PostMapping(path = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Future<Void> createItem(@RequestBody @Valid final NewItem newItem) {
    // The IdentifierFactory can be used to assign a new aggregate ID for an aggregate
    // that we are about to create. Please note though that Axon comes only with a
    // default implementation of the IdentifierFactory (cf. DefaultIdentifierFactory),
    // which is only capable of producing IDs based on the java.util.UUID class.
    // In a distributed setting, something like Flake IDs might be more appropriate
    // than this.
    final String itemId = IdentifierFactory.getInstance().generateIdentifier();
    final CreateItemCommand createItemCommand = new CreateItemCommand(itemId, newItem.getDescription());
    return commandGateway.send(createItemCommand);
  }

  @DeleteMapping(value = "/items/{itemId}")
  public Future<Void> concludeItem(@PathVariable final String itemId) {
    final ConcludeItemCommand concludeItemCommand = new ConcludeItemCommand(itemId);
    return commandGateway.send(concludeItemCommand);
  }
}
