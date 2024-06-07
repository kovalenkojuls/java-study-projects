import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamMapReduceFilter {
    private static final Logger logger = LoggerFactory.getLogger(StreamMapReduceFilter.class);

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 16, 25);

        logger.info("map_source:{}", list);
        logger.info("map_1:{}", myMap(list, new MySqrtClass()));
        logger.info("map_2:{}", myMap(list, val -> Math.sqrt(val)));
        logger.info("map_3:{}", myMap(list, (Function<Integer, Double>) Math::sqrt));

        Function<Integer, Double> func = StreamMapReduceFilter::mySqrtFunc;
        logger.info("func:{}", myMap(list, func));

        logger.info("reduce:{}", myReduce(list, Integer::sum, 0));
        logger.info("filter:{}", myFilter(list, a -> a % 2 == 0));

    }

    static class MySqrtClass implements Function<Integer, Double> {
        @Override
        public Double apply(Integer val) {
            return Math.sqrt(val);
        }
    }

    static Double mySqrtFunc(Integer val) {
        return Math.sqrt(val);
    }

    static <T, R> Collection<R> myMap(List<T> listSrc, Function<T, R> op) {
        List<R> listRes = new ArrayList<>();
        for (T t : listSrc) {
            listRes.add(op.apply(t));
        }
        return listRes;
    }

    static <T, R> R myReduce(List<T> listSrc, BiFunction<T, R, R> op, R zero) {
        R result = zero;
        for (T t : listSrc) {
            result = op.apply(t, result);
        }
        return result;
    }

    static <R> Collection<R> myFilter(List<R> listSrc, Predicate<R> pred) {
        List<R> listRes = new ArrayList<>();
        for (R t : listSrc) {
            if (pred.test(t)) {
                listRes.add(t);
            }
        }
        return listRes;
    }
}