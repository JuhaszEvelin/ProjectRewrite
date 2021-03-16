
package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StreamMain {

    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        //  s.stream().forEach(new MyConsumer());
        s.add("1");
        s.add("2");
        s.add("3");

        MyStream<String> ms = new MyStream<>(s);
        ms.myForEach(new PrintItemProcessor());
        System.out.println(s);

        ms.myFilter(new PrintItemFilter());
        System.out.println(s);

        //ANONYMUS INNER CLASS
        ms.myFilter(new ItemFilter<String>() {
            //létrehoz anonymus inner classt ami csak itt él, így nem kell külön classokat csinálni ha csak egyszer akarom használni
            @Override
            public boolean check(String s) {
                System.out.println(this.getClass().getName());
                return s.endsWith("a");
            }
        });

        //LAMBDA - egyszerűsíti az anonymus inner classt, háttérben ugyanezt csinálja mint a fenti de csak egy metódussal működik
        //csak olyan interfacera aminek egy metódusa van (ItemFilternek ha lenne több methodja nem lehetne)
        //absztrakt osztályra is működik ha egyetlen absztrakt metódusa van!

        ms.myFilter2((item) -> {
            if (item.startsWith("a")) {
                return true;
            } else if (item.startsWith("b")) {
                return false;
            }
            return item.endsWith("a");
        });
        //(bárhogy nevezhetem, ahány paraméter van mindet meg kell adni olyan sorban, zárójelen belül
        // ,-vel elválasztva(alma, i))
        //->  metódus törzse  { között }


        //rövidített LAMBDA ha csak egy paraméter van és egy kifejezés:
        ms.myFilter2(x -> x.endsWith("a"));
        ms.myForEach((s1, index) -> {
            System.out.println(s + " item indexe: " + index);
        });

        //LAMBDA kimenthető változóba ahol az interface a változó típusa
        //ez egy referencia olyan, mintha metódusba tennénk
        ItemProcessor<String> ifil = (s1, index) -> {
            System.out.println(s + " item indexe: " + index);
        };
        ms.myForEach(ifil);

        //METÓDUS REFERENCIA - CSAK egy metódusos interfaceknél működik
        ms.myFilter2((item) -> {
            return item.endsWith("a");
        });

        ms.myFilter2(StreamMain::checkEndsWithA);

        ItemFilter<String> ifil2 = StreamMain::checkEndsWithA;
        ms.myFilter2(ifil2);

        ms.myForEach(StreamMain::processA);
    }

    private static boolean checkEndsWithA(String item) {
        return item.endsWith("a");
    }

    private static void processA(String s, int index) {
        System.out.println(s + " item indexe: " + index);
    }

}

class PrintItemFilter implements ItemFilter<String> {

    @Override
    public boolean check(String s) {
        System.out.println("Printitemfilter started, item: " + s);
        return "2".equals(s);
    }
}


class PrintItemProcessor implements ItemProcessor<String> {
    @Override
    public void process(String s, int index) {
        System.out.println(s + " item indexe: " + index);
    }
}

/*class PintItemProcessor implements ItemProcessor<String> {
    @Override
    public void process(String s) {
        System.out.println("- Print item processor started");
        System.out.println(s);
        System.out.println("- Print item processor ended");
    }
}*/

class MyConsumer implements Consumer<String> {

    @Override
    public void accept(String s) {

    }
}

