package liufarui;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/20 11:24 上午
 */
public class RedisJava {
    @Test
    public void test() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        // jedis.auth("123456");
        System.out.println("连接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        // 设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

        // 存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (String s : list) {
            System.out.println("列表项为: " + s);
        }

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        // 使用完关闭连接
        jedis.close();
    }
}
