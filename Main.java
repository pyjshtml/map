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
  public String toString(){
    String output = "[";
    for(int i = 0; i < size-1; i++){
      output += (String) arrayList[i] + ", ";
    }
    output += (String) arrayList[size-1] + "]";
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
  }
  public String toString(){
    String output = "{";
    for(int i = 0; i < list.size(); i++){
      output += list.get(i).getKey().toString() + "=" + list.get(i).getValue().toString();
      output += (i < list.size()-1) ? ", " : "}";
    }
    return output;
  }
}
public class Main{
  public static void main(String[] args) {
    try{
      MyMap<String, Integer> mapping = new MyMap<>();
      mapping.put("Jack", 14);
      mapping.put("Sean", 11);
      System.out.println(mapping);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}
