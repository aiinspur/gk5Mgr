package com.tigerj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_customer")
public class Customer {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    private String firstName;
    
    @Column(name="last_name", nullable=false, length=512)
    private String lastName;
    
    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
