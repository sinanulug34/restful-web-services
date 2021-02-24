package com.rest.webservices.restfulwebservices;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel("All details about the user.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3, message = "User name should have at least 3 chars")
    @ApiModelProperty(notes = "Name should be at least 3 chars")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birth date should be in the past")
    private Date birthadate;

    public User(Integer id, String name, Date birthadate) {
        this.id = id;
        this.name = name;
        this.birthadate = birthadate;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthadate() {
        return birthadate;
    }

    public void setBirthadate(Date birthadate) {
        this.birthadate = birthadate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthadate=" + birthadate +
                '}';
    }
}
