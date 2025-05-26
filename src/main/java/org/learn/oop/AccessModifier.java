package org.learn.oop;

public class AccessModifier {

}

class Test1 {
    public int value;

    @Override
    public int hashCode() {
        return 42;
    }
}

class Test2 {
    public int value;

    @Override
    public int hashCode() {
        return (int)(value^5);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
