package DNS;
public enum Type{
    A(0), NS(1), CN(2), R(3), V(4);

    private final int value;
    private static final Type[] values = Type.values();

    private Type(int value){
        this.value = value;
    }

    public static Type fromInt(int x){
        return values[x];
    }
    public int toInt(){
        return value;
    }
}