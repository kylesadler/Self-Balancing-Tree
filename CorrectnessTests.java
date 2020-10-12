// package tests;
/**
* Test cases related to binary search tree correctness.
*
* @author  Kyle Sadler
* @version 1.0
* @since   2018-10-03 
*/

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CorrectnessTests {

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        AVLTree tree = new AVLTree();
        tree.add(1, 2);
        tree.add(1, 10);
        tree.add(0, 3);
        tree.add(4, 9);
        tree.add(7, 10);
    }
}