package net.mguenther.todo.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@ApiModel(value = "ConcludedItemSummary", description = "Represents a brief summary on a todo list item that has been concluded.")
public class ConcludedItemSummaryRepr {

  @JsonProperty(value = "itemId", required = true)
  @ApiModelProperty(value = "This is the (business) ID of the todo list item.", required = true, example = "c4bc75bc-f0b6-11e9-8cfe-5f80fe6b7cda")
  private String itemId;

  @JsonProperty(value = "concluded", required = true)
  @ApiModelProperty(value = "This timestamp refers to the point in time when this item has been concluded (UTC, milliseconds).", required = true, example = "1571300370779")
  private Long concluded;
}
