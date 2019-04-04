package de.thro.inf.prg3.a02;

import java.util.Iterator;
import java.util.List;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

	// TODO: Implement the required methods.

    Element root = null;

    public void add(Object o){
        if(root == null){
            root = new Element(o);
            return;
        }
        Element headlist = root;
        headlist.next = root.next;
        while(headlist.next!= null) {
           headlist = headlist.next;
        }
        headlist.next = new Element(o);

    }
    public int size(){
        int count = 0;
        if(root == null){
            return count;
        }
        if(root.next == null){
            count++;
            return count;
        }
        Element headlist = root;
        count++;
        while(headlist.next != null){
            headlist = headlist.next;
            count++;
        }
        return count;
    }
    public SimpleList filter(SimpleFilter filter){
        SimpleList sl = new SimpleListImpl();

        for(Object o : this){
            if(filter.include(o)){
                sl.add(o);
            }
        }
        return sl;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }

    private static class Element{
        private Object element;
        private Element next;

        public Element(Object o){
            element = o;
            next = null;
        }
    }
    private class SimpleIteratorImpl implements Iterator{
        Element headlist = root;
        public boolean hasNext(){
            return headlist != null;
        }
        public Object next(){
            Object o = headlist.element;
            headlist = headlist.next;
            return o;
        }
    }
}
