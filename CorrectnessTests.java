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

    @Test(timeout=5)
    public void treeShouldRotateLeftAfterThreeAscendingInserts() {
        AVLTree tree = new AVLTree();
        Object anObject = new Object();

        tree.add(0, anObject);
        assertEquals(tree.toString(), "0");
        tree.add(1, anObject);
        assertEquals(tree.toString(), "0[1]");
        tree.add(2, anObject);
        assertEquals(tree.toString(), "1(0)[2]");
    }

    @Test(timeout=5)
    public void treeShouldRotateRightAfterThreeDescendingInserts() {
        AVLTree tree = new AVLTree();
        Object anObject = new Object();

        tree.add(2, anObject);
        assertEquals(tree.toString(), "2");
        tree.add(1, anObject);
        assertEquals(tree.toString(), "2(1)");
        tree.add(0, anObject);
        assertEquals(tree.toString(), "1(0)[2]");
    }

    @Test(timeout=10)
    public void treeShouldReturnCorrectValueAfter100AscendingInserts() {
        String specialValue = "this is a special value";
        for (int specialIndex = 0; specialIndex < 100; specialIndex++){
            AVLTree tree = new AVLTree();
            Object anObject = new Object();

            for(int i = 0; i < 100; i++){
                if(i == specialIndex){
                    tree.add(specialIndex, specialValue);
                } else{
                    tree.add(i, anObject);
                }
            }
            assertEquals(tree.get(specialIndex), specialValue);
        }
    }

    @Test(timeout=10)
    public void treeShouldReturnNullAfterRemovingValue() {
        String specialValue = "this is a special value";
        for (int specialIndex = 0; specialIndex < 100; specialIndex++){
            AVLTree tree = new AVLTree();
            Object anObject = new Object();

            for(int i = 0; i < 100; i++){
                if(i == specialIndex){
                    tree.add(specialIndex, specialValue);
                } else{
                    tree.add(i, anObject);
                }
            }

            tree.remove(specialIndex);
            assertEquals(tree.get(specialIndex), null);
        }
    }

    @Test(timeout=5)
    public void treeShouldHaveCorrectStructureAfter100AscendingInserts() {
        AVLTree tree = new AVLTree();
        Object anObject = new Object();

        for(int i = 0; i < 100; i++){
            tree.add(i, anObject);
        }
        assertEquals(tree.toString(), "63(31(15(7(3(1(0)[2])[5(4)[6]])[11(9(8)[10])[13(12)[14]]])[23(19(17(16)[18])[21(20)[22]])[27(25(24)[26])[29(28)[30]]]])[47(39(35(33(32)[34])[37(36)[38]])[43(41(40)[42])[45(44)[46]]])[55(51(49(48)[50])[53(52)[54]])[59(57(56)[58])[61(60)[62]]]]])[79(71(67(65(64)[66])[69(68)[70]])[75(73(72)[74])[77(76)[78]]])[95(87(83(81(80)[82])[85(84)[86]])[91(89(88)[90])[93(92)[94]]])[97(96)[98[99]]]]]");
    }
}