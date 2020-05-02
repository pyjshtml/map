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
    Object[] temp = new Object[size-1];
    for(int i = 0; i < size; i++){
      if(i == index) continue;
      temp[i] = arrayList[i > index ? i-1 : i];
    }
    arrayList = temp;
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
    System.out.println(list);
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
public class Main{
  public static void main(String[] args) {
    try{
      // MyMap<String, Integer> mapping = new MyMap<>();
      // mapping.put("Jack", 14);
      // mapping.put("Sean", 11);
      // System.out.println(mapping);
      Student me = new Student("Jack",14);
      Classroom math = new Classroom("Geometry Honors");
      math.addStudent(me);
      System.out.println(math);
      math.removeStudent(me.name);
      System.out.println(math);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}
