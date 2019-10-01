
public class Person {
  private String name;

  private int age;

  private String hairColor;



  public Person(String name, int age, String hairColor) {
    this.name = name;

    this.age = age;

    this.hairColor = hairColor;


  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public String toString() {
    return name + " " + age + " " + hairColor;
   
  }


  public static void main(String[] args) {
    Person p1 = new Person("Jake", 18, "Brown");

    Person p2 = p1;
    
    p2.setName("Bob");
    
    System.out.println(p1);
    
    System.out.println(p2.getName());
    
    System.out.println(p1.getName());


  }


}
