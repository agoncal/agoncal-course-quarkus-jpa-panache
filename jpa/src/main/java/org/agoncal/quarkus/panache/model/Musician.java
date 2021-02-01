package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Musician.FIND_ALL, query = "select m from Musician m"),
})
public class Musician extends Artist {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(name = "preferred_instrument")
  private String preferredInstrument;

  @ManyToMany
  @JoinTable(name = "musician_cd", joinColumns = @JoinColumn(name = "musician_fk"), inverseJoinColumns = @JoinColumn(name = "cd_fk"))
  private Set<CD> cds = new HashSet<>();

  // ======================================
  // =              Constant              =
  // ======================================

  public static final String FIND_ALL = "Musician.findAll";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Musician() {
  }

  public Musician(String firstName, String lastName, String bio, Date dateOfBirth, String preferredInstrument) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.dateOfBirth = dateOfBirth;
    this.preferredInstrument = preferredInstrument;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public String getPreferredInstrument() {
    return preferredInstrument;
  }

  public void setPreferredInstrument(String preferredInstrument) {
    this.preferredInstrument = preferredInstrument;
  }

  public Set<CD> getCds() {
    return cds;
  }

  public void setCds(Set<CD> cds) {
    this.cds = cds;
  }


  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Musician musician = (Musician) o;

    if (preferredInstrument != null ? !preferredInstrument.equals(musician.preferredInstrument) : musician.preferredInstrument != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (preferredInstrument != null ? preferredInstrument.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(firstName);
    sb.append(' ');
    sb.append(lastName);
    return sb.toString();
  }
}
