package org.learn.datastructure.linkedlist;


import java.util.HashSet;

public class Questions {

    public static <T> T returnNthToLast(LinkedList<T> list, int index) {
       if (list == null || list.getSize() == 0 ) {
            throw new IllegalArgumentException("List is empty");
       } else if (index >= list.getSize() || index < 0) {
           throw new IllegalArgumentException("Invalid value for index");
       } else {
           var current = list.getHead();
           for (int i = 0; i < list.getSize() - index - 1; i++) {
               current = current.next;
           }
           return current.data;
       }
    }

    public static <T> void removeDuplicates(LinkedList<T> list) {
        var seen = new HashSet<T>();
        var current = list.getHead();
        Node<T> prev = null;
        while (current != null) {
            if (!seen.add(current.data)){
                if (current.next != null) {
                    prev.next = current.next;
                } else {
                    list.setTail(prev);
                    prev.next = null;
                }
                list.setSize(list.getSize()-1);
            } else {
                prev = current;
            }
            current = current.next;
        }
    }

    // 4 -> 3 -> 5 -> 7 -> 1 -> 8   , 5
    public static <T extends Number & Comparable<T>> void partitionAroundX(LinkedList<T> list, T x) {
        var current = list.getHead();
        Node<T> prev = null;
        boolean crossed = false;
        while(current != null) {
            if (current.data.compareTo(x) < 0) {
                if (crossed) {
                    var pivot = current;
                    current = current.next;
                    prev.next = current;
                    var temp = list.getHead();
                    list.setHead(pivot);
                    pivot.next = temp;
                    if(current == null) {
                        list.setTail(prev);
                    }
                } else {
                    prev = current;
                    current = current.next;
                }
            } else {
                prev = current;
                current = current.next;
                if (!crossed) {
                    crossed = true;
                }
            }
        }
    }

    // stored in reverse digit way
    // list1 = 7 -> 1 -> 6
    // list2 = 5 -> 9 -> 2
    // listA = 2 -> 1 -> 9
    public static LinkedList<Integer> sumLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        var linkedList = new LinkedList<Integer>();
        var head1 = list1.getHead();
        var head2 = list2.getHead();
        int carryForward = 0;
        while (head1 != null || head2!= null) {
            var data1 = head1 == null ? 0 : head1.data;
            var data2 = head2 == null ? 0 : head2.data;
            var sum = data1 + data2 + carryForward;
            if (sum >= 10) {
                carryForward = 1;
                sum = sum%10;
            } else {
                carryForward = 0;
            }
            linkedList.insert(sum);
            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }
        if (carryForward > 0) {
            linkedList.insert(carryForward);
        }
        return linkedList;
    }


    public static <T> boolean intersection(LinkedList<T> list1, LinkedList<T> list2) {
        var seen = new HashSet<Node<T>>();
        var head = list1.getHead();
        while (head != null) {
            seen.add(head);
            head = head.next;
        }
        head = list2.getHead();
        while (head != null) {
            if (seen.contains(head)) {
                return true;
            }
            seen.add(head);
            head = head.next;
        }
        return false;
    }

}
