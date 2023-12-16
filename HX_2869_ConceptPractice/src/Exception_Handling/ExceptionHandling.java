package Exception_Handling;

public class ExceptionHandling {

	public static void main(String[] args) {
		try {
//			int i = 20, j = 0, k;
//			k = i / j;

//			String name = null;
//			int len = name.length();

			int n = Integer.parseInt("Suraj");

//			int[] arr = { 2, 3, 4, 5, 6 };
//			System.out.println(arr[5]);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.out.println("Arithmetic Exception");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Null Pointer Exception");
		}catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Number Format Exception");
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Array Index Out of Bounds Exception");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Super Class Exception");
		}finally {
			System.out.println("This is finally block");
		}
	}

}
