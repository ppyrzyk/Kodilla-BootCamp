package com.kodilla.hibernate.manytomany;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
@NamedQuery(
        name = "Company.searchCompanyByName",
        query = "FROM Company WHERE name LIKE CONCAT(:SEARCHKEY, '%')"
)
@NamedNativeQuery(
        name = "Company.retrieveCompaniesNameStartsWith",
        query = "SELECT * FROM COMPANIES " +
                "WHERE LEFT(company_name, 3) = :BEGINWITH",
        resultClass = Company.class)
@Entity
@Table( name = "COMPANIES")
public class Company {

    private  int id;
    private  String name;
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue
    @NotNull
    @Column( name = "COMPANY_ID", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotNull
    @Column( name = "COMPANY_NAME")
    public String getName() {
        return name;
    }
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companies")
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setName(String name) {
        this.name = name;
    }


    private void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
