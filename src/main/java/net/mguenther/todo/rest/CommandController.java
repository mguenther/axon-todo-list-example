package net.mguenther.todo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.mguenther.todo.api.ConcludeItemCommand;
import net.mguenther.todo.api.CreateItemCommand;
import net.mguenther.todo.rest.representation.NewItemRepr;
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
@Api(value = "Command Controller", description = "Provides the means to create todo list items and manipulate them.")
public class CommandController {

  private final CommandGateway commandGateway;

  @ApiOperation(value = "Creates a new todo list item.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The todo list item has been successfully created."),
      @ApiResponse(code = 400, message = "The data describing the new todo list item is invalid."),
      @ApiResponse(code = 500, message = "An internal error occured while processing the create item request.")
  })
  @PostMapping(path = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Future<Void> createItem(@ApiParam(value = "NewItem", required = true) @RequestBody @Valid final NewItemRepr newItemRepr) {
    // The IdentifierFactory can be used to assign a new aggregate ID for an aggregate
    // that we are about to create. Please note though that Axon comes only with a
    // default implementation of the IdentifierFactory (cf. DefaultIdentifierFactory),
    // which is only capable of producing IDs based on the java.util.UUID class.
    // In a distributed setting, something like Flake IDs might be more appropriate
    // than this.
    final String itemId = IdentifierFactory.getInstance().generateIdentifier();
    final CreateItemCommand createItemCommand = new CreateItemCommand(itemId, newItemRepr.getDescription());
    return commandGateway.send(createItemCommand);
  }

  @ApiOperation(value = "Concludes the todo list item with the given (business) ID.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The todo list item with the given ID has been succcessfully concluded."),
      @ApiResponse(code = 500, message = "An internal error occured while processing conclude item request.")
  })
  @DeleteMapping(value = "/items/{itemId}")
  public Future<Void> concludeItem(@ApiParam(value =" itemId", required = true) @PathVariable final String itemId) {
    final ConcludeItemCommand concludeItemCommand = new ConcludeItemCommand(itemId);
    return commandGateway.send(concludeItemCommand);
  }
}
