package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Artist {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id = null;

  @Column(name = "first_name", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  protected String firstName;

  @Column(name = "last_name", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  protected String lastName;

  @Column(length = 5000)
  @Size(max = 5000)
  protected String bio;

  @Column(name = "date_of_birth")
  @Temporal(TemporalType.DATE)
  @Past
  protected Date dateOfBirth;

  @Transient
  protected Integer age;

  // ======================================
  // =     Lifecycle Callback Methods     =
  // ======================================

  @PostLoad
  @PostPersist
  @PostUpdate
  public void calculateAge() {
    if (dateOfBirth == null) {
      age = null;
      return;
    }

    Calendar birth = new GregorianCalendar();
    birth.setTime(dateOfBirth);
    Calendar now = new GregorianCalendar();
    now.setTime(new Date());
    int adjust = 0;
    if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
      adjust = -1;
    }
    age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Artist artist = (Artist) o;

    if (age != null ? !age.equals(artist.age) : artist.age != null) return false;
    if (bio != null ? !bio.equals(artist.bio) : artist.bio != null) return false;
    if (dateOfBirth != null ? !dateOfBirth.equals(artist.dateOfBirth) : artist.dateOfBirth != null) return false;
    if (!firstName.equals(artist.firstName)) return false;
    if (id != null ? !id.equals(artist.id) : artist.id != null) return false;
    if (!lastName.equals(artist.lastName)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + (bio != null ? bio.hashCode() : 0);
    result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
    result = 31 * result + (age != null ? age.hashCode() : 0);
    return result;
  }
}
