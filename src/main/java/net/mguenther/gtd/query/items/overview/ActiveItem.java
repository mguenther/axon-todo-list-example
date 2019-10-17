package net.mguenther.gtd.query.items.overview;

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
@ToString(of = {"itemId", "created"})
@Table(name = "ACTIVE_ITEM")
public class ActiveItem implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @Column(name = "ITEM_ID", nullable = false, updatable = false)
  private String itemId;

  @Column(name = "ITEM_DESCRIPTION", nullable = false)
  private String description;

  @Column(name = "ITEM_CREATED", nullable = false, updatable = false)
  private Long created;

  public ActiveItem(final String itemId, final String description, final Long created) {
    this.itemId = itemId;
    this.description = description;
    this.created = created;
  }
}
