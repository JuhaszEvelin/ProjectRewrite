package streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MyStream<T> {

    private final Collection<T> coll;

    public MyStream(Collection<T> coll) {
        this.coll = coll;
    }

    public void myForEach(ItemProcessor<T> processor) {
        int i = 0;
        for (T item : coll) {
            processor.process(item, i);
            i++;
        }
    }


  /* // we used this before we give 2 args for method process()
  public void forEach(ItemProcessor<T> processor) {
        System.out.println("- Foreach started");
        for (T item : coll) {
            //minden egyes elemre meg kell mondanom hogy a külső kód ezt processálja vagy csináljon vele amit akar
            System.out.println("- processing element: " + item);
            processor.process(item);
            //magát a ciklust ő (MyStream foreach methodja) csinálja de hogy azon belül mit csinál a process adja meg amit mainben írok meg
        }
        System.out.println("- Foreach ended");
    }*/

    public void myFilter(ItemFilter<T> filter) {
        Iterator<T> it = coll.iterator();
        while (it.hasNext()) {
            System.out.println("while started");
            if (filter.check(it.next())) {
                it.remove();
            }
            System.out.println("while ended");
        }
    }

    public MyStream<T> myFilter2(ItemFilter<T> filter) {
//olyan példánnyal térjen vissza amiben a szűrt elemek vannak de az eredeti lista ne változzon

        List<T> newList = new ArrayList<>(); //lehetne new LinkedList és akkor linkedet ad vissza a mystream
        for (T item : coll) {
            boolean keep = filter.check(item);
            if (keep) {
                newList.add(item);
            }
        }
        MyStream<T> res = new MyStream<>(newList); //mehetne a for ciklus elé is, nem változik
        return res;
    }

 /*
 public MyStream<T> myGenericsFilter(ItemFilter<T> filter) {
//olyan példánnyal térjen vissza amiben a szűrt elemek vannak de az eredeti lista ne változzon, bármely típusú listára működjön
        Collection<T> res = coll.stream().collect(Collectors.toCollection(T));
        Iterator<T> it = res.iterator();
        while (it.hasNext()) {
            T item = it.next();
            boolean keep = filter.check(item);
            if (!keep) {
                it.remove();
            }
        }
        return new MyStream<>(res);
    }*/

}


