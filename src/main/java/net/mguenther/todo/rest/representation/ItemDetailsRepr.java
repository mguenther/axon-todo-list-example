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
@ApiModel(value = "ItemDetails", description = "This is the full detail representation of a todo list item.")
public class ItemDetailsRepr {

  @JsonProperty(value = "itemId", required = true)
  @ApiModelProperty(value = "This is the (business) ID of the todo list item.", required = true, example = "c4bc75bc-f0b6-11e9-8cfe-5f80fe6b7cda")
  private String itemId;

  @JsonProperty(value = "description", required = true)
  @ApiModelProperty(value = "Describes what needs to be done as part of the todo list item.", required = true, example = "Buy groceries")
  private String description;

  @JsonProperty(value = "created", required = true)
  @ApiModelProperty(value = "This timestamp refers to the point in time when this item has been created (UTC, milliseconds).", required = true, example = "1571300370779")
  private Long created;

  @JsonProperty(value = "concluded", required = true)
  @ApiModelProperty(value = "Determines whether this item has been concluded.", required = true, example = "true")
  private Boolean concluded;

  @JsonProperty("concludedAt")
  @ApiModelProperty(value = "This timestamp refers to the point in time when this item has been concluded (UTC, milliseconds).", example = "1571300370779")
  private Long concludedAt;
}
