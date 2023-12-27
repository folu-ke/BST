package project4;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Main class used to test BST.java
 * @author  Mofoluwake Adesanya
 * @version 12/20/2023
 */
public class TestBST {
	public static void main(String[] args) throws CloneNotSupportedException {
		// test constructor 1
		BST<Integer> testBST = new BST<> ();
		Integer[] number1 = {43, 50, 51, 53, 54, 67};
		Integer[] number2 = {43, 50, 51, 53, 54, 60, 67};
		Integer[] number3 = {53, 50, 60, 43, 51, 54, 67};
		Character[] strings = {'K', 'D', 'P', 'B', 'J', 'M', 'A', 'L', 'O', 'N'};
		BST<Character> charBST = new BST(strings);
		//new BST (number1);
		BST<Integer> testBST2 = new BST (number2);
		BST<Integer> testBST3 = new BST (number3);
		System.out.println(testBST);
		// test size
		System.out.printf("size: %d \n", testBST.size());
		// test add
		testBST.add​(53);
		testBST.add​(50);
		testBST.add​(60);
		testBST.add​(54);
		// addAll
		System.out.println("// addAll");
		LinkedList<Integer> list = new LinkedList<> ();
		list.add(43); list.add(67); list.add(51);
		System.out.println(list);
		testBST.addAll​(list);
		System.out.println(testBST);
		System.out.printf("size: %d \n", testBST.size());
		//test inorder iter
		System.out.println("// inorder iterator");
		Iterator<Integer> iter = testBST.iterator();
		while ( iter.hasNext() ) {
			 System.out.println(iter.next());
		} // 50 53 54 60
		// test preorder iter
		System.out.println("// preorder iterator");
		Iterator<Integer> iter1 = testBST.preOrderIterator();
		while ( iter1.hasNext() ) {
			 System.out.println(iter1.next());
		} // 53 50 60 54
		// test postorder iter
		System.out.println("// postorder iterator");
		Iterator<Integer> iter2 = testBST.postOrderIterator();
		while ( iter2.hasNext() ) {
			 System.out.println(iter2.next());
		} // 50 54 60 53
		// test toString
		System.out.println("// toString");		
		System.out.println(testBST);
		// toArray
		System.out.println("// toArray");
		for ( Object value : testBST.toArray() ) {
			System.out.println(value);
		}
		// test get
		System.out.println("// get");
		System.out.println(testBST.get​(0));
		System.out.println(testBST.get​(3));
		System.out.println(testBST.get​(6));
		// test size
		System.out.printf("size: %d \n", testBST.size());
		// test first and last
		System.out.println("// first and last");
		System.out.printf("first: %d ", testBST.first());
		System.out.printf("last: %d \n", testBST.last());
		// test height
		System.out.println("// height");
		System.out.println(testBST.height());
		// contains
		System.out.println("// contains");
		System.out.println(testBST.contains​(51));
		System.out.println(testBST.contains​(12));
		System.out.println(testBST.contains​(67));
		// remove
		System.out.println("// remove");
//		testBST.remove​(60);	testBST.remove​(51);
//		testBST.remove​(43);	testBST.remove​(50);
//		testBST.remove​(54); testBST.remove​(53);
//		testBST.remove​(67);		
		System.out.println(testBST);
		System.out.printf("size: %d \n", testBST.size());
		// clone
		System.out.println("// clone");
		BST<Integer> testBST1 = testBST.clone();
		BST<Integer> testBST4 = testBST2.clone();
		// containsAll
		System.out.println("// containsAll");
		LinkedList<Integer> list1 = new LinkedList<> ();
		list1.add(43); list1.add(67); list1.add(51);  list1.add(44);
		System.out.println(list1);
		System.out.println(testBST.containsAll​(list1));
		// ceiling
		System.out.println("// ceiling");
		System.out.println(testBST.ceiling​(66));
		System.out.println(testBST.ceiling​(44));
		System.out.println(testBST.ceiling​(11));
		System.out.println(testBST.ceiling​(81));
		// floor
		System.out.println("// floor");
		System.out.println(testBST.floor​(66));
		System.out.println(testBST.floor​(44));
		System.out.println(testBST.floor​(11));
		System.out.println(testBST.floor​(81));
		// lower
		System.out.println("// lower");
		System.out.println(testBST.lower​(67)); // 60
		System.out.println(testBST.lower​(44)); // 43	
		System.out.println(testBST.lower​(11)); // null
		System.out.println(testBST.lower​(81)); // 67
		// higher
		System.out.println("// higher");
		System.out.println(testBST.higher​(66)); // 67
		System.out.println(testBST.higher​(44)); // 50	
		System.out.println(testBST.higher​(11)); // 43
		System.out.println(testBST.higher​(81)); // null
		// equals 
		System.out.println("// equals");
		System.out.println(testBST.equals(list1));
		System.out.println(testBST.equals(testBST1));
		System.out.println(testBST.equals(testBST2));
		System.out.println(testBST.equals(testBST3));
		System.out.println("// getRange");
		//System.out.println(testBST.getRange​(null, null));
		System.out.println(testBST.getRange​(5, 43));
		System.out.println(testBST.getRange​(54, 81));	
		// clear
		System.out.println("// clear");
		testBST.clear();
		System.out.println(testBST);
		System.out.printf("size: %d \n", testBST.size());
		// toStringTreeFormat
		System.out.println("// toStringTreeFormat");
		System.out.println("TESTBST");
		System.out.println(testBST.toStringTreeFormat());
		System.out.println("TESTBST clone");
		System.out.println(testBST1.toStringTreeFormat());
		System.out.println("TESTBST2");
		System.out.println(testBST2.toStringTreeFormat());
		System.out.println("TESTBST2 clone");
		System.out.println(testBST2.toStringTreeFormat());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}