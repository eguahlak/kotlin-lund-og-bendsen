package javaexamples;

public class Person {
  private final int id;
  private String name;

  public Person(int id, String name) {
    this.id = id;
    setName(name);
    }

  public int getId() { return id; }

  public String getName() { return name; }

  public void setName(String value) {
    if (value == null) throw new RuntimeException();
    name = value;
    }

  }
