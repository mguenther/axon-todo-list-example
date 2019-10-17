package net.mguenther.gtd.model;

import net.mguenther.gtd.api.ConcludeItemCommand;
import net.mguenther.gtd.api.CreateItemCommand;
import net.mguenther.gtd.api.ItemConcludedEvent;
import net.mguenther.gtd.api.ItemCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

  private AggregateTestFixture<Item> fixture;

  @BeforeEach
  void prepareTest() {
    fixture = new AggregateTestFixture<>(Item.class);
  }

  @Test
  @DisplayName("a CreateItemCommand should produce an ItemCreatedEvent")
  void aCreateItemCommandShouldProduceItemCreatedEvent() {
    fixture
        .givenNoPriorActivity()
        .when(new CreateItemCommand("some-id", "go shopping"))
        .expectEvents(new ItemCreatedEvent("some-id", "go shopping"));
  }

  @Test
  @DisplayName("a ConcludeItemCommand should produce an ItemConcludedEvent on an active item")
  void aConcludeItemCommandShouldProduceAnItemConcludedEventOnActiveItem() {
    fixture
        .given(new ItemCreatedEvent("some-id", "go shopping"))
        .when(new ConcludeItemCommand("some-id"))
        .expectEvents(new ItemConcludedEvent("some-id"));
  }

  @Test
  @DisplayName("a ConcludeItemCommand should not produce an ItemConcludedEvent on an already concluded item")
  void aConcludeItemCommandShouldNotProduceAnItemConcludedEventOnAnAlreadyConcludedItem() {
    fixture
        .given(
            new ItemCreatedEvent("some-id", "go shopping"),
            new ItemConcludedEvent("some-id"))
        .when(new ConcludeItemCommand("some-id"))
        .expectException(IllegalStateException.class)
        .expectNoEvents();
  }
}
