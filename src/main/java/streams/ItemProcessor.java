package streams;

public interface ItemProcessor<T> {

    void process(T t, int index);

}
