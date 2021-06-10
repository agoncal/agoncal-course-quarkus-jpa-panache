package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_purchase_order_lines")
public class OrderLine extends PanacheEntity {

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "item_fk")
  public Item item;

  @Column(nullable = false)
  public Integer quantity;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "purchase_order_fk")
  @JsonbTransient
  public PurchaseOrder purchaseOrder;

  @Column(name = "created_date", nullable = false)
  public Instant createdDate = Instant.now();

}
