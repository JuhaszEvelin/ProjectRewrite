package streams;

public interface ItemFilter<T> {

    public boolean check(T t);
}
