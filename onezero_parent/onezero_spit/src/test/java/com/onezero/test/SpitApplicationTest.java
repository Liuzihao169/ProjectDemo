package com.onezero.test;

import com.mongodb.client.result.DeleteResult;
import com.onezero.spit.SpitApplication9006;
import com.onezero.spit.pojo.Spit;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hao
 * @create 2019-07-18 ${TIM}
 * springboot环境测试  还有一个测试是在 onezer_common包下的 MongoTest 最原生的java代码操作。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpitApplication9006.class )
public class SpitApplicationTest {

    @Autowired
    private MongoTemplate template;

    /**
     * 查询所有
     */
    @Test
    public void testfindAll(){
        List<Spit> all = template.findAll(Spit.class);
        for (Spit spit : all) {
            System.out.println(spit);
        }
    }

    /**
     * 查询
     */
    @Test
    public void testQuery(){
        List<Spit> spits = template.find(new BasicQuery("{visits:{$gte:1014}}"), Spit.class);
        for (Spit spit : spits) {
            System.out.println(spit);
        }
    }

    /**
     * 条件组合+模糊查询
     */
    @Test
    public void testQuery2(){
        List<Spit> spits = template.find(new BasicQuery("{visits:{$gte:1225},content:/是/}"), Spit.class);
        for (Spit spit : spits) {
            System.out.println(spit);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void test3(){
        List<Spit> spits = template.find(new BasicQuery("{\"content\":/流量/}"), Spit.class);
        for (Spit spit : spits) {
            System.out.println(spit);
        }
    }

    /**
     * doucment对象查询
     */
    @Test
    public void testDocument(){
        List<Spit> spits = template.find(new BasicQuery(new Document("nickname", "凯撒")), Spit.class);
        for (Spit spit1 : spits) {
            System.out.println(spit1);
        }
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate(){
        //有两种查询方式:
        //  1、书写代码 原生查询
        // 2、用封装Criteria查询对象的方式查询
        Map<String,Object> map = new HashMap<>();
        Criteria criteria = Criteria.where("nickname").is("父亲节点1");
        template.updateMulti(new Query().addCriteria(criteria),new Update().set("nickname","父亲节点001"),"spit");
    }

    /**
     * 根据条件删除
     */
    @Test
    public void testDelete(){
        DeleteResult spit = template.remove(new BasicQuery("{nickname:\"liuizhoa\"}"), "spit");
        System.out.println("count ==>"+spit.getDeletedCount());
    }
    
    @Test
    public void testupdate(){
        // 也可以用对象 Criteria 封装查询对象
        template.updateMulti(new BasicQuery("{_id:\"1151808160076533760\"}"),new BasicUpdate("{$inc:{visits:NumberInt(1)}}"),"spit");
    }
}
