package task_3;
class CI{
double calculate_ci(double p, double r, double t){
double amount = p*Math.pow((1+(r/100)),t);
return amount-p;
}
public static void main(String[] args){
double p = Double.parseDouble(args[0]);
double r = Double.parseDouble(args[1]);
double t = Double.parseDouble(args[2]);
double p2 = Double.parseDouble(args[3]);
double r2 = Double.parseDouble(args[4]);
double t2 = Double.parseDouble(args[5]);

CI ci = new CI();
SI si = new SI();
System.out.println("Simple Interest: "+si.calculate_si(p,r,t));
System.out.println("Compund Interest: "+ci.calculate_ci(p2,r2,t2));
}
}