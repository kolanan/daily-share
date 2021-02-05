import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.chinamobile.guava.CalculateService;


public class Test {

    private CalculateService calculateService = new CalculateService();


    @org.junit.Test
    public void test() {
        Console.log(StrUtil.hasBlank("123", null));
        Console.log(StrUtil.hasEmpty("123", " "));
        Console.log(StrUtil.removeSuffix("123.txt", ".txt"));
        Console.log(StrUtil.removePrefix("123.txt", "123."));
        Console.log(StrUtil.builder("w234"));
    }
}
