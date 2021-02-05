import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.List;

public class GuavaTest {



    @Test
    public void test3() {
        List s = Lists.newArrayList(4, 2, 3);
        Ordering<String> naturalOrdering = Ordering.natural();
        List list = naturalOrdering.sortedCopy(s);
        System.out.println(list);
    }
}
