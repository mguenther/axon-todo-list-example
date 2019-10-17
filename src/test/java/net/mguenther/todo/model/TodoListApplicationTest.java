package net.mguenther.todo.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TodoListApplicationTest {

  @Test
  @DisplayName("application context should be properly configure and load")
  void applicationContextShouldLoad() {
  }
}
