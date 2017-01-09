package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int counter = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    this.counter++;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String toString() {
	  return ("[Person name:" + name + " "+ "age:" + age + " " + "salary:" + salary + "]");
  }
  
  public void setAge(int age){
	  this.age = age;
	  if(age<0){
		  throw new IllegalArgumentException("age less than zero");
	  }
  }
  
  public void setName(String name){
	  this.name = name;
	  if(name == null){
		  throw new IllegalArgumentException("passed a null String");
	  }
  }
  
  public void setSalary(double salary){
	  this.salary = salary;
  }
  
  public int getAge(){
	  return age;
  }
  
  public String getName(){
	  return name;
  }
  
  public int count() {
	  return counter;
  }
  
  @Override
	public boolean equals(Object obj) {
	  Person person = null;
	  try {
		  person  = (Person) obj;
	  } catch (ClassCastException e) {
		return false;
	  }
	  if(person == null) {
		  return false;
	  }
	  if(this.name.equals(person.name) && this.age == person.age){
		  return true;
	  }else{
		  return false;
	  }
	}
  
  @Override
  public int compareTo(Person o) {
	  Person newPerson = new Person();
	  Double salary1 = newPerson.salary;
	  Double salary2 = o.salary;
	  int var1 = salary1.compareTo(salary2);
	  return -var1;
  }
  
  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
      Integer age1 = o1.age;
      Integer age2 = o2.age;
      return age1.compareTo(age2);
    }
  }
  
  public static ArrayList<Person> getNewardFamily(){
	  ArrayList<Person> list = new ArrayList<Person>();
	  Person person1 = new Person("Ted", 41, 250000);
	  Person person2 = new Person("Charlotte", 43, 150000);
	  Person person3 = new Person("Michael", 22, 10000);
	  Person person4 = new Person("Matthew", 15, 0);
	  
	  list.add(person1);
	  list.add(person2);
	  list.add(person3);
	  list.add(person4);
	  return list;
  }
  
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}