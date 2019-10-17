package net.mguenther.gtd.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateItemCommand {

  @TargetAggregateIdentifier
  private final String itemId;
  private final String description;
}
