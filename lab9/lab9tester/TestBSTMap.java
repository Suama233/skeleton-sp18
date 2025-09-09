package lab9tester;

import static org.junit.Assert.*;

import lab9.MyHashMap;
import org.junit.Test;
import lab9.BSTMap;

import java.util.Set;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void getKeySetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        Set<String> keySet = b.keySet();
        for (int i = 0; i < 455; i++) {
            assertTrue(keySet.contains("hi" + i));
        }
    }

    @Test
    public void removeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, i);
        }

        for (int i = 0; i < 455; i++) {
            int val = b.remove("hi" + i);
            assertEquals(i,val);
        }
    }

    @Test
    public void removeWithValueTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, i);
        }

        for (int i = 0; i < 455; i++) {
            int val = b.remove("hi" + i, i);
            assertEquals(i,val);
        }
    }

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    @Test
    public void removeEdgeCasesTest() {
        BSTMap<Integer, String> b = new BSTMap<>();

        b.put(5, "root");
        b.put(3, "left");
        b.put(7, "right");
        b.put(2, "left.left");
        b.put(4, "left.right");
        b.put(6, "right.left");

        // 删除叶子节点（2）
        String val = b.remove(2);
        assertEquals("left.left", val);
        assertFalse(b.containsKey(2));

        // 删除只有一个子节点的节点（7 -> 只有左孩子 6）
        val = b.remove(7);
        assertEquals("right", val);
        assertFalse(b.containsKey(7));
        assertTrue(b.containsKey(6));

        // 删除有两个子节点的节点（3 -> 左孩子 null, 右孩子 4）
        val = b.remove(3);
        assertEquals("left", val);
        assertFalse(b.containsKey(3));
        assertTrue(b.containsKey(4));

        // 删除 root（5 -> 现在有左=4，右=6）
        val = b.remove(5);
        assertEquals("root", val);
        assertFalse(b.containsKey(5));
        assertTrue(b.containsKey(4));
        assertTrue(b.containsKey(6));

        // 删除不存在的 key
        val = b.remove(100);
        assertNull(val);
    }



    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
