package net.mguenther.gtd.rest.representation;

import net.mguenther.gtd.query.items.concluded.ConcludedItem;
import net.mguenther.gtd.query.items.detail.ItemDetails;
import net.mguenther.gtd.query.items.overview.ActiveItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemReprConverter {

  public List<ActiveItemSummaryRepr> convertActiveItems(final List<ActiveItem> activeItems) {
    return activeItems
        .stream()
        .map(activeItem -> new ActiveItemSummaryRepr(activeItem.getItemId(), activeItem.getDescription(), activeItem.getCreated()))
        .collect(Collectors.toList());
  }

  public List<ConcludedItemSummaryRepr> convertConcludedItems(final List<ConcludedItem> concludedItems) {
    return concludedItems
        .stream()
        .map(concludedItem -> new ConcludedItemSummaryRepr(concludedItem.getItemId(), concludedItem.getConcluded()))
        .collect(Collectors.toList());
  }

  public ItemDetailsRepr convertItemDetails(final ItemDetails itemDetails) {
    return new ItemDetailsRepr(
        itemDetails.getItemId(),
        itemDetails.getDescription(),
        itemDetails.getCreated(),
        itemDetails.getConcluded(),
        itemDetails.getConcludedAt());
  }
}
