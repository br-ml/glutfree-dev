package eu.glutfree.glutfree.model.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {


  private long id;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", nullable = false, updatable = false)
  public long getId() {
    return id;
  }

  public BaseEntity setId(long id) {
    this.id = id;
    return this;
  }
}
