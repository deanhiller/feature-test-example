package org.webpieces.app.example1.model.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS",
    indexes = {
        @Index(name = "email", columnList = "email", unique = true)
    }
)
@NamedQueries({
    @NamedQuery(name = "findAllUsers", query = "select u from UserDbo as u"),
    @NamedQuery(name = "findByEmailId", query = "select u from UserDbo as u where u.email=:email"),
    @NamedQuery(name = "findByName", query = "select u from UserDbo as u where u.name=:name")
})
public class UserDbo {
  @Id
  @SequenceGenerator(name = "user_id_gen", sequenceName = "user_sequence", initialValue = 1, allocationSize = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
  private Integer id;

  @Column(unique = true)
  private String email;

  private String phone;
  private String name;
  private String firstName;
  private String lastName;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserDbo manager;

  @OneToMany(mappedBy = "manager")
  private List<UserDbo> employees = new ArrayList<UserDbo>();

  public Integer getId() {
    return id;
  }

  public List<UserDbo> getEmployees() {
    return employees;
  }

  public void setEmployees(List<UserDbo> employees) {
    this.employees = employees;
  }

  public void addEmployee(UserDbo employee) {
    this.employees.add(employee);
  }

  public void deleteEmployee(UserDbo employee) {
    this.employees.remove(employee);
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public UserDbo getManager() {
    return manager;
  }

  public void setManager(UserDbo manager) {
    this.manager = manager;
  }

  @SuppressWarnings("unchecked")
  public static List<UserDbo> findAll(EntityManager mgr) {
    Query query = mgr.createNamedQuery("findAllUsers");
    return query.getResultList();
  }

  public static UserDbo findByEmailId(EntityManager mgr, String email) {
    Query query = mgr.createNamedQuery("findByEmailId");
    query.setParameter("email", email);
    try {
      return (UserDbo) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public static UserDbo findByName(EntityManager mgr, String name) {
    Query query = mgr.createNamedQuery("findByName");
    query.setParameter("name", name);
    try {
      return (UserDbo) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
