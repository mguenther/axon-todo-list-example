package net.mguenther.gtd.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ActiveItemSummaryRepr {

  @JsonProperty("itemId")
  private String itemId;

  @JsonProperty("description")
  private String description;

  @JsonProperty("created")
  private Long created;
}
