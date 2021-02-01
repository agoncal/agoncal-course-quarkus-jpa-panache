package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id = null;

  @Column(length = 100)
  @NotNull
  @Size(min = 1, max = 100)
  protected String title;

  @Column(length = 3000)
  @Size(max = 3000)
  protected String description;

  @Column(name = "unit_cost")
  @Min(1)
  protected Float unitCost;

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(Float unitCost) {
    this.unitCost = unitCost;
  }


  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (description != null ? !description.equals(item.description) : item.description != null) return false;
    if (id != null ? !id.equals(item.id) : item.id != null) return false;
    if (!title.equals(item.title)) return false;
    if (unitCost != null ? !unitCost.equals(item.unitCost) : item.unitCost != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + title.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (unitCost != null ? unitCost.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Item{");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", unitCost=").append(unitCost);
    sb.append('}');
    return sb.toString();
  }
}
