// package tests;
/**
* Test cases related to binary search tree performance.
*
* @author  Kyle Sadler
* @version 1.0
* @since   2018-10-03 
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.lang.Math;

public class PerformanceTests {

    @Test(timeout=10)
    public void Adding10000DescendingNumbersShouldTakeLessThan5Ms() {
        AVLTree tree = new AVLTree();

        Object anObject = new Object();

        for(int i = 10000; i > 0; i--){
            tree.insert(i, anObject);
        }
    }

    @Test(timeout=10)
    public void Adding10000AscendingNumbersShouldTakeLessThan5Ms() {
        AVLTree tree = new AVLTree();

        Object anObject = new Object();

        for(int i = 0; i < 10000; i++){
            tree.insert(i, anObject);
        }
    }

    @Test(timeout=10)
    public void HeightShouldBeLessThanLogNAfterAdding10000AscendingNumbers() {
        AVLTree tree = new AVLTree();

        Object anObject = new Object();

        for(int i = 0; i < 10000; i++){
            tree.insert(i, anObject);
        }

        assertTrue(tree.getHeight() < Math.log(10000)/Math.log(2) + 1);
    }
}