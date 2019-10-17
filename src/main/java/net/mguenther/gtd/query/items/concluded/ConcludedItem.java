package net.mguenther.gtd.query.items.concluded;

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
@ToString(of = {"itemId", "concluded"})
@Table(name = "CONCLUDED_ITEM")
public class ConcludedItem implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @Column(name = "ITEM_ID", nullable = false, updatable = false)
  private String itemId;

  @Column(name = "ITEM_CONCLUDED", nullable = false, updatable = false)
  private Long concluded;

  public ConcludedItem(final String itemId, final Long concluded) {
    this.itemId = itemId;
    this.concluded = concluded;
  }
}
