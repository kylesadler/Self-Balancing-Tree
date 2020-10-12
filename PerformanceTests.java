// package tests;
/**
* Test cases related to binary search tree performance.
*
* @author  Kyle Sadler
* @version 1.0
* @since   2018-10-03 
*/

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PerformanceTests {

    @Test(timeout=5)
    public void Adding10000NumbersWorstCaseShouldTakeLessThan5Ms() {
        AVLTree tree = new AVLTree();

        Object anObject = new Object();

        for(int i = 0; i < 10000; i++){
            tree.add(i, anObject);
        }
    }
}