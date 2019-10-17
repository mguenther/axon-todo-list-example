package net.mguenther.gtd.rest;

import lombok.RequiredArgsConstructor;
import net.mguenther.gtd.api.AllActiveItemsQuery;
import net.mguenther.gtd.api.ConcludedItemsQuery;
import net.mguenther.gtd.api.ShowDetailsForItemQuery;
import net.mguenther.gtd.query.items.concluded.ConcludedItem;
import net.mguenther.gtd.query.items.detail.ItemDetails;
import net.mguenther.gtd.query.items.overview.ActiveItem;
import net.mguenther.gtd.rest.representation.ActiveItemSummaryRepr;
import net.mguenther.gtd.rest.representation.ConcludedItemSummaryRepr;
import net.mguenther.gtd.rest.representation.ItemDetailsRepr;
import net.mguenther.gtd.rest.representation.ItemReprConverter;
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
public class QueryController {

  private final QueryGateway queryGateway;
  private final ItemReprConverter converter;

  @GetMapping(path = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<List<ActiveItemSummaryRepr>> showActiveItems() {

    return queryGateway
        .query(new AllActiveItemsQuery(), ResponseTypes.multipleInstancesOf(ActiveItem.class))
        .thenApply(converter::convertActiveItems);
  }

  @GetMapping(path = "/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<ItemDetailsRepr> showDetailsForItem(@PathVariable("itemId") final String itemId) {
    return queryGateway
        .query(new ShowDetailsForItemQuery(itemId), ResponseTypes.instanceOf(ItemDetails.class))
        .thenApply(converter::convertItemDetails);
  }

  @GetMapping(path = "/concluded-items", produces = MediaType.APPLICATION_JSON_VALUE)
  public Future<List<ConcludedItemSummaryRepr>> showConcludedItems() {
    return queryGateway
        .query(new ConcludedItemsQuery(), ResponseTypes.multipleInstancesOf(ConcludedItem.class))
        .thenApply(converter::convertConcludedItems);
  }
}
