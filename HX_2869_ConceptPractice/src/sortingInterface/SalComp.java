package sortingInterface;

import java.util.Comparator;

public class SalComp implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Emp e1 =(Emp) o1;
		Emp e2 = (Emp) o2;
		return e1.sal-e2.sal;
	}

}
