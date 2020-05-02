import java.util.Iterator;
class MyList<T> implements Iterable<T>{
  private int size;
  private Object[] arrayList;
  public MyList(){
    arrayList = new Object[0];
    size = 0;
  }
  public int size(){
    return size;
  }
  public boolean isEmpty(){
    return size==0;
  }
  public Iterator<T> iterator() {
    Iterator<T> it = new Iterator<T>() {
      private int currentIndex = 0;
      public boolean hasNext() {
        return currentIndex < size && arrayList[currentIndex] != null;
      }
      public T next() {
        return (T) arrayList[currentIndex++];
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
    return it;
  }
  @SuppressWarnings("unchecked")
  public T get(int index){
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException();
    }
    return (T) arrayList[index];
  }
  public void add(T value){
    size++;
    Object[] temp = new Object[size];
    for(int i = 0; i < arrayList.length; i++){
      temp[i] = arrayList[i];
    }
    temp[size-1] = value;
    arrayList = temp;
  }
  public void remove(int index){
    size--;
    Object[] temp = new Object[size];
    for(int i = 0; i < arrayList.length; i++){
      if(i == index) continue;
      temp[i >= index ? i-1 : i] = arrayList[i];
    }
    arrayList = temp;
  }
  public int indexOf(T value){
    for(int i = 0; i < size; i++){
      if(arrayList[i].equals(value) || arrayList[i] == value){
        return i;
      }
    }
    return -1;
  }
  public String toString(){
    if(size == 0){
      return "[]";
    }
    String output = "[";
    for(int i = 0; i < size-1; i++){
      output += arrayList[i].toString() + ", ";
    }
    output += arrayList[size-1].toString() + "]";
    return output;
  }
}
class MyMap<K,V>{
  private MyList<Node> list = new MyList<>();
  public int size(){
    return list.size();
  }
  public boolean isEmpty(){
    return !(list.size() == 0);
  }
  public MyMap(){}
  public void put(K k, V v){
    list.add(new Node(k,v));
  }
  public V get(K k) throws Exception{
    for(Node node : list){
      if(k.equals(node.getKey())){
        return (V) node.getValue();
      }
    }
    throw new Exception("Invalid Key");
  }
  public boolean containsKey(K k){
    for(Node node : list){
      if(k.equals(node.getKey())){
        return true;
      }
    }
    return false;
  }
  public void remove(K k){
    int i = 0;
    for(Node node : list){
      if(k.equals(node.getKey())){
        list.remove(i);
      }
      i++;
    }
  }
  class Node<K,V>{
    private K key;
    private V value;
    public Node(K k,V v){
      key = k;
      value = v;
    }
    public K getKey(){
      return key;
    }
    public V getValue(){
      return value;
    }
    public String toString(){
      return ""+key.toString()+"="+value.toString()+"";
    }
  }
  public String toString(){
    if(list.size()==0){
      return "{}";
    }
    String output = "{";
    for(int i = 0; i < list.size(); i++){
      output += list.get(i).getKey().toString() + "=" + list.get(i).getValue().toString();
      output += (i < list.size()-1) ? ", " : "}";
    }
    return output;
  }
}
class Person{
  public String name;
  public int age;
  public Person(String n, int a){
    name = n;
    age = a;
  }
  public String toString(){
    return "{name="+name+", age="+age+"}";
  }
}
class Student extends Person{
  public int year;
  public Student(String n, int a){
    super(n,a);
    year = a-5;
  }
  public Student(String n, int a, int y){
    super(n,a);
    year = y;
  }
}
class Classroom{
  private MyMap<String,Student> students = new MyMap<>();
  private String name;
  private int size;
  private int capacity;
  private final int DEFAULT_CAPACITY = 20;
  public Classroom(String cn){
    name = cn;
    size = 0;
    capacity = DEFAULT_CAPACITY;
  }
  public Classroom(String cn, int cap){
    name = cn;
    size = 0;
    capacity = cap;
  }
  public Classroom(String cn, int s, int cap){
    name = cn;
    size = s;
    capacity = cap;
  }
  public String name(){
    return name;
  }
  //Add Student
  public void addStudent(Student student){
    students.put(student.name,student);
  }
  public void addStudent(String name, Student student){
    students.put(name,student);
  }
  //Remove Student
  public void removeStudent(Student student){
    students.remove(student.name);
  }
  public void removeStudent(String key){
    students.remove(key);
  }
  //toString
  public String toString(){
    return students.toString();
  }
}
class School{
  public int capacity;
  private int size;
  private static final int DEFAULT_CAPACITY = 100;
  private MyList<Classroom> classes = new MyList<>();
  public String name;
  public School(String sn, int cap, int n){
    name = sn;
    capacity = cap;
    size = n;
  }
  public School(String sn, int cap){
    name = sn;
    capacity = cap;
    size = 0;
  }
  public School(String sn){
    name = sn;
    capacity = DEFAULT_CAPACITY;
    size = 0;
  }
  public int size(){
    return size;
  }
  public void addClass(Classroom newClass){
    classes.add(newClass);
  }
  public void remove(Classroom removalClass){
    classes.remove(classes.indexOf(removalClass));
  }
  public void remove(String className){
    for(int i = 0; i < classes.size(); i++){
      if(classes.get(i).name().equals(className)){
        classes.remove(i);
        break;
      }
    }
  }
  public String toString(){
    return classes.toString();
  }
}
public class Main{
  public static void example1() throws Exception{
    Student me = new Student("Jack",14);
    Classroom math = new Classroom("Geometry Honors");
    math.addStudent(new Student("Sean",14));
    math.addStudent(me);
    math.addStudent(new Student("Alex",14));
    System.out.println(math);
    math.removeStudent(me);
    System.out.println(math);
    math.removeStudent("Alex");
    System.out.println(math);
  }
  public static void main(String[] args) {
    try{
      School victor = new School("Victor");
      Classroom math = new Classroom("Geometry Honors");
      Classroom english = new Classroom("Pre-AP English 9");
      Student jack = new Student("Jack",14);
      Student alex = new Student("Alex",14);
      math.addStudent(jack);
      english.addStudent(alex);
      english.addStudent(jack);
      victor.addClass(math);
      victor.addClass(english);
      System.out.println(victor);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}
//REVIEW: student.addClass method
