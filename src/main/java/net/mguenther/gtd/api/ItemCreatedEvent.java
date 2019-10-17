package net.mguenther.gtd.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ItemCreatedEvent {

  private final String itemId;
  private final String description;
}
