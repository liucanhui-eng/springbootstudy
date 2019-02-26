package com;

import com.elasticsearch.ElasticsearchServer;
import com.model.pro.User;
import com.service.UserService;
import com.util.CollectionUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootstudyApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(SpringbootstudyApplicationTests.class);

    public static Logger getLogger() {
        return logger;
    }
    @Autowired
    UserService userService;

    @Autowired
    ElasticsearchServer elasticsearchServer;


    @Test
    public void testLogger() {
        logger.info("aaa");
    }

    @Test
    public void TestRecruitment() throws Exception{
        List<String> list1 = Arrays.asList("甲", "已", "丙", "丁", "子", "丑", "寅");
        LinkedList<String> list = new LinkedList<>();
        list.addAll(list1);
        List<User> users=new ArrayList<>();
        for (int i=1000;i<2000;i++){
            User user = new User();
            int age=0;
            while (age<15){
                age=new Random().nextInt(50);
            }
            String sex=(i%100!=0)+"";
            String name=i%500==0?"校尉"+list.pollFirst():i%40==0?"大队长"+i:i%9==0?"小队长"+i:"小兵"+i;
            user.build(i,name,age,sex);
            users.add(user);
        }
        userService.recruitment(users);
    }
    @Test
    public void TestPointUser()throws Exception{
        User user = new User();
//        user.setId(1);
        List<User> users= userService.northernExpedition(user);
        users.forEach(res -> {
            System.out.println(res);
        });
    }

    @Test
    public void testEsAPIAdd()throws Exception{
        TransportClient client = elasticsearchServer.getClient();
        User user = new User();
        List<User> list=userService.northernExpedition(user);
        ArrayList<User> users = new ArrayList<>();
        CollectionUtils.listCopy(list,users,0,10);
        System.out.println("插入的数据"+users);
        elasticsearchServer.add(client,users);
    }

    @Test
    public void testEsSearch()throws Exception {
        TransportClient client = elasticsearchServer.getClient();
        elasticsearchServer.query(client);
    }
    @Test
    public void testEsSearchAll()throws Exception {
        TransportClient client = elasticsearchServer.getClient();
        elasticsearchServer.queryAll(client);
    }

    @Test
    public void testEsSearchByExample()throws Exception {
        TransportClient client = elasticsearchServer.getClient();
        //中文最好一个字一个字搜索否则可能返回为空
        elasticsearchServer.queryByExample(client,"age","35","name","刘");
        //elasticsearchServer.queryByExample(client,"age","35","sex","true");
    }



}

