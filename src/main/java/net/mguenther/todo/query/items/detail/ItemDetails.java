package net.mguenther.todo.query.items.detail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "itemId")
@ToString(exclude = "id")
@Table(name = "ITEM_DETAILS")
public class ItemDetails implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @Column(name = "ITEM_ID", nullable = false, updatable = false)
  private String itemId;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @Column(name = "CREATED", nullable = false)
  private Long created;

  @Column(name = "CONCLUDED")
  private Boolean concluded;

  @Column(name = "CONCLUDED_AT")
  private Long concludedAt;

  public ItemDetails(final String itemId, final String description, final Long created) {
    this.itemId = itemId;
    this.description = description;
    this.created = created;
    this.concluded = false;
    this.concludedAt = null;
  }

  public void conclude(final Long concludedAt) {
    this.concluded = true;
    this.concludedAt = concludedAt;
  }
}
