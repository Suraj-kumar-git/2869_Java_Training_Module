package task_4;

class Student {
	public static void main(String[] args) {
		int sid = Integer.parseInt(args[0]);
		String sname = args[1];
		double m1 = Double.parseDouble(args[2]);
		double m2 = Double.parseDouble(args[3]);
		double m3 = Double.parseDouble(args[4]);

		Avg avg = new Avg();
		System.out.println("Student Id: " + sid);
		System.out.println("Student Name: " + sname);
		System.out.println("Student Marks1: " + m1);
		System.out.println("Student Marks2: " + m2);
		System.out.println("Student Marks3: " + m3);
		System.out.println("Total Marks: " + (double) (m1 + m2 + m3));
		System.out.println("Average Marks: " + avg.calculate_avg(m1, m2, m3));
		double average = avg.calculate_avg(m1, m2, m3);
		if (average > 60) {
			System.out.println("1st Class student");
		} else if (average >= 50 && average < 60) {
			System.out.println("2nd Class student");
		} else {
			System.out.println("3rd Class student");
		}

	}
}
