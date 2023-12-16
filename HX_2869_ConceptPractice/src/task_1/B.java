package task_1;
class A{
int add(int a, int b){
return a+b;
}
int sub(int a, int b){
return Math.abs(a-b);
}
int mul(int a, int b){
return a*b;
}
int div(int a, int b){
return a/b;
}
}
class B{
public static void main(String[] args){
A a = new A();
System.out.println("Operands are: 20 & 5");
System.out.println("Addition: "+a.add(20,5));
System.out.println("Subtraction: "+a.sub(20,5));
System.out.println("Multiplication: "+a.mul(20,5));
System.out.println("Division: "+a.div(20,5));
}
}