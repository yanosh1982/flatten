import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flatten {

	public static void main(String[] args) {
		//[[1,2,[3]],4]
		
		Object[] singleElementArray = new Object[1];
		singleElementArray [0] = Integer.valueOf(3);
		
		Object[] threeElementArray = new Object[3];
		threeElementArray [0] = Integer.valueOf(1);
		threeElementArray [1] = Integer.valueOf(2);
		threeElementArray [2] = singleElementArray;
		
		Object[] input = new Object[2];
		input[0] = threeElementArray;
		input[1] = Integer.valueOf(4);
		
		//illegal parameter in source array
		Object[] illegalElementInput = new Object[2];
		illegalElementInput[0] = threeElementArray;
		illegalElementInput[1] = "illegalElement";
		
		String [] secondIllegalElement = new String[2];
		secondIllegalElement[0] = "illegal";
		secondIllegalElement[1] = "very illegal";
		Object[] secondIllegalInput = new Object[2];
		secondIllegalInput[0] = secondIllegalElement;
		secondIllegalInput[1] = 4;
		
		
		//illegal parameter in source array
		Object[] nullElementInput = new Object[2];
		nullElementInput[0] = threeElementArray;
		nullElementInput[1] = null;
		
		System.out.println("********* Input: " + Arrays.deepToString(input) + " *********");
		flatIt(input);
		System.out.println(System.getProperty("line.separator"));
		
		System.out.println("********* Null Input:   *********");
		flatIt(null);
		System.out.println(System.getProperty("line.separator"));
		
		System.out.println("********* Null Element Input: " + Arrays.deepToString(nullElementInput) + " *********");
		flatIt(nullElementInput);
		System.out.println(System.getProperty("line.separator"));
		
		System.out.println("********* Illegal input: " + Arrays.deepToString(illegalElementInput) + " *********");
		try{
			flatIt(illegalElementInput);
		} catch(IllegalArgumentException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		System.out.println(System.getProperty("line.separator"));
		
		System.out.println("*********Second illegal input: " + Arrays.deepToString(secondIllegalInput) + " *********");
		try{
			flatIt(secondIllegalInput);
		} catch(IllegalArgumentException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		System.out.println(System.getProperty("line.separator"));
		
		
	}

	/**
	 * Flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4]<br><br>
	 * 
	 * if the source array is null the returned array will be null.<br><br>
	 * 
	 * if the source array contains an element whose type is neither {@link Integer} nor {@link Integer} array an {@link IllegalArgumentException} will be thrown<br><br>
	 * 
	 * <code>null</code> elements inside the source array will be ignored.
	 * 
	 * @param toFlat the source array to flat. 
	 * @return a flat array of integers, if the source array is not null and valid, <code>null</code> otherwise
	 * @throws IllegalArgumentException if the source array contains a forbidden element type
	 */
	private static int[] flatIt(Object[] toFlat) throws IllegalArgumentException {
		System.out.println("Method: flatIt - start");
		//null check
		if(toFlat == null) {
			System.out.println("Input is null, returning null");
			return null;
		}
		
		List<Integer> temp = new ArrayList<>();
		System.out.println("Processing " + toFlat.length + " elements");
		int index = 0;
		for(Object currentElement: toFlat) {
			if(currentElement == null) {
				System.out.println("Found null element in source array, skipping...");
				//skip null elements
				continue;
			}

			if(currentElement.getClass().isAssignableFrom(Object[].class)) {
				System.out.println("Recursive call to flatIt method on array element type...");
				//currentElement can't be null so temp can't be null
				int[] tempElement = flatIt((Object[])currentElement);
				for(int i: tempElement) {
					System.out.println("Adding primitive int element to temp ArrayList. Value: " + i);
					temp.add(i);
					System.out.println("Primitive int element added");
				}
			} else if(currentElement.getClass().isAssignableFrom(Integer.class)) {
				System.out.println("Processing Integer element type...");
				Integer integerElement = (Integer) currentElement;
				
				System.out.println("Adding Integer element to temp ArrayList. Value: " + integerElement);
				temp.add(integerElement);
				System.out.println("Integer element added to temp ArrayList");
			} else {
				throw new IllegalArgumentException("Found invalid element in source array of type " + currentElement.getClass().getName() + " at index " + index + ". Unable to proceed");
			}
			
			index++;
		}
		
		System.out.println("Elements in temp array after processing:  " + temp.size() + ". Formatting output");

		int[] result = new int[temp.size()];
		for(int i = 0; i < temp.size(); i++) {
			int value = temp.get(i);
			result[i] = value;
			System.out.println("Added formatted value: " + value);
		}
		
		System.out.println("Method: flatIt - end");
		return result;
	}

}
