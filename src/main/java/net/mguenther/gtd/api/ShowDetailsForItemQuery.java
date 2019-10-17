package net.mguenther.gtd.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ShowDetailsForItemQuery {

  private final String itemId;
}
