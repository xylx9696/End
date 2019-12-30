import com.alibaba.fastjson.JSONArray;
import com.wlf.App;
import com.wlf.dao.AdminDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class test
 * Date  2019/12/17 19:52
 * Author 王龙飞
 */

@SpringBootTest("classes= App.class")
@RunWith(SpringRunner.class)
public class test {
//    @Autowired
//    private AdminDAO adminDAO1;

    @Test
    public void test1(){
        List<Integer> list=new ArrayList<>();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        String s = jsonArray.toJSONString();
        /*GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9783a893192440dd9d83b1f35792109d");
        goEasy.publish("123",s);*/
    }
}
