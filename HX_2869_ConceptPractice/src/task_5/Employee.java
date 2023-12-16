package task_5;
import task_5.Gross;
class Employee{
public static void main(String [] args){
int eid = Integer.parseInt(args[0]);
String ename = args[1];
double basic_sal= Double.parseDouble(args[2]);

double hra = basic_sal*0.12;
double ta = basic_sal*0.05;
double da= basic_sal*0.08;

Gross gross = new Gross();
System.out.println("Employee Id: "+eid);
System.out.println("Employee Name: "+ename);
System.out.println("Employee Basic Sal: "+basic_sal);

System.out.println("Gross Salary: "+gross.calculate_gross(basic_sal,hra,ta,da));
double gross_sal= gross.calculate_gross(basic_sal,hra,ta,da);
if(gross_sal>=25000){
System.out.println("Grade A Employee");
}else{
System.out.println("Grade B Employee");
}

}
}
