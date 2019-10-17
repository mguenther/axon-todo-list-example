package net.mguenther.todo.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(value = "NewItem", description = "Contains the information to create a new todo list item.")
public class NewItemRepr {

  @NotNull
  @NotEmpty
  @JsonProperty(value = "description", required = true)
  @ApiModelProperty(value = "Describes what needs to be done as part of the todo list item.", required = true, example = "Buy groceries")
  private String description;
}