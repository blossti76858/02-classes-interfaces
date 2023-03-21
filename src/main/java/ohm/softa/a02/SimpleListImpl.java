package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private static class Element {
        private final Object item;
        private Element next = null;

        public Element(Object item) {
            this.item = item;
        }

        public Object getItem() {
            return item;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    private int size = 0;
    private Element head = null;

    @Override
    public void add(Object o) {
        Element element = new Element(o);
        if(head == null) {
            head = element;
        }
        else {
            Element cur = head;
            while(cur.getNext() != null) {
                cur = cur.getNext();
            }
            cur.setNext(element);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    private class SimpleIteratorImpl implements Iterator<Object> {
        private Element cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Object next() {
            Object o = cur.getItem();
            cur = cur.getNext();
            return  o;
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList simpleList = new SimpleListImpl();
        for(Object o : this) {
            if(filter.include(o)) {
                simpleList.add(o);
            }
        }
        return simpleList;
    }
}
