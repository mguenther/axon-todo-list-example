package net.mguenther.todo.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ItemConcludedEvent {

  private final String itemId;
}
