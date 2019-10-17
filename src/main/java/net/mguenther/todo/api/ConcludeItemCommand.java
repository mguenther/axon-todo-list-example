package net.mguenther.todo.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@RequiredArgsConstructor
public class ConcludeItemCommand {

  @TargetAggregateIdentifier
  private final String itemId;
}
