package task_2;

class AC {
	double area_circle(double r) {
		return 3.14 * r * r;
	}
}

class SI extends AC implements AR, PC {
	public double area_rectangle(double l, double b) {
		return l * b;
	}

	public double perimeter_circle(double r) {
		return 2 * 3.14 * r;
	}

	double simple_interest(double p, double r, double t) {
		return (p * r * t) / 100;
	}

	public static void main(String[] args) {
		SI si = new SI();
		System.out.println("Area of Circle: " + si.area_circle(7));
		System.out.println("Area of rectangle " + si.area_rectangle(4, 7));
		System.out.println("Perimeter of circle: " + si.perimeter_circle(7));
		System.out.println("Simple Interest: " + si.simple_interest(700000, 6.5, 5));
	}
}
