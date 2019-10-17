package net.mguenther.todo.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ConcludedItemSummaryRepr {

  @JsonProperty("itemId")
  private String itemId;

  @JsonProperty("concluded")
  private Long concluded;
}
