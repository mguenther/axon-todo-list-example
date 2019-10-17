package net.mguenther.todo.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.mguenther.todo.api.AllActiveItemsQuery;
import net.mguenther.todo.api.ConcludedItemsQuery;
import net.mguenther.todo.api.ShowDetailsForItemQuery;
import net.mguenther.todo.query.items.concluded.ConcludedItem;
import net.mguenther.todo.query.items.detail.ItemDetails;
import net.mguenther.todo.query.items.overview.ActiveItem;
import net.mguenther.todo.rest.representation.ActiveItemSummaryRepr;
import net.mguenther.todo.rest.representation.ConcludedItemSummaryRepr;
import net.mguenther.todo.rest.representation.ItemDetailsRepr;
import net.mguenther.todo.rest.representation.ItemReprConverter;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Api(value = "Query Controller", description = "Provides the means to query for projections on todo list items.")
public class QueryController {

  private final QueryGateway queryGateway;
  private final ItemReprConverter converter;

  @ApiOperation("Retrieves all todo list items that are currently open.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "All currently open todo list items have been successfully retrieved.", response = List.class),
      @ApiResponse(code = 500, message = "An internal error occured while retrieving all currently open todo list items.")
  })
  @GetMapping(path = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<List<ActiveItemSummaryRepr>> showActiveItems() {
    return queryGateway
        .query(new AllActiveItemsQuery(), ResponseTypes.multipleInstancesOf(ActiveItem.class))
        .thenApply(converter::convertActiveItems);
  }

  @ApiOperation("Retrieves a detailed representation of the requested todo list item.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Contains a detailed representation of the requested todo list item.", response = ItemDetailsRepr.class),
      @ApiResponse(code = 500, message = "An internal error occured while retrieving the detailed representation of the requested todo list item.")
  })
  @GetMapping(path = "/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<ItemDetailsRepr> showDetailsForItem(@ApiParam(value = "itemId", required = true) @PathVariable final String itemId) {
    return queryGateway
        .query(new ShowDetailsForItemQuery(itemId), ResponseTypes.instanceOf(ItemDetails.class))
        .thenApply(converter::convertItemDetails);
  }

  @ApiOperation("Retrieves a brief summary of all concluded todo list items.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The list of all concluded items has been successfully retrieved.", response = List.class),
      @ApiResponse(code = 500, message = "An internal error occured while retrieving all concluded todo list items.")
  })
  @GetMapping(path = "/concluded-items", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<List<ConcludedItemSummaryRepr>> showConcludedItems() {
    return queryGateway
        .query(new ConcludedItemsQuery(), ResponseTypes.multipleInstancesOf(ConcludedItem.class))
        .thenApply(converter::convertConcludedItems);
  }
}
