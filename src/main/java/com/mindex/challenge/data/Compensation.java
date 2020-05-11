package com.mindex.challenge.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.mindex.challenge.util.*;

public class Compensation {
   private Employee employee;
   private double salary;
   
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
   @JsonDeserialize(using = LocalDateDeserializer.class)
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate effectiveDate;
   
   public Employee getEmployee() {
	  return employee;
   }
    
   public void setEmployee(Employee employee) {
	  this.employee = employee;
   }
   
   public double getSalary() {
	  return salary;
   }
   
   public void setSalary(double salary) {
	  this.salary = salary;
   }

   public void setEffectiveDate(LocalDate effectiveDate) {
	  this.effectiveDate = effectiveDate;
   }

   public LocalDate getEffectiveDate() {
	  return effectiveDate;
   } 
}
