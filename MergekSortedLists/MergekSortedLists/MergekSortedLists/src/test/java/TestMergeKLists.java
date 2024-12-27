import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.ListNode;
import task.MySolution;
import task.Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMergeKLists {

    private Solution solution;

    @BeforeEach
    public void setUp() {
        this.solution = new MySolution();
    }

    @Test
    public void testMySolution() {
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = {list1, list2, list3};

        ListNode mergedList = solution.mergeKLists(lists);

        ListNode expected = new ListNode(1, new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(4,
                        new ListNode(5, new ListNode(6))))))));

        assertListsEqual(mergedList, expected);
    }


    private void assertListsEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            assertEquals(l1.val, l2.val, "Values do not match!");
            l1 = l1.next;
            l2 = l2.next;
        }

        assertEquals(l1, l2, "Lists are not of the same length.");
    }
}
