package util.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class redisMain {
	private static  Jedis jedis ;
	static {
		jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
	}
	
	public static void main(String[] args) {
//		hashTest();
//		setTest();
		testZSet();
	}
	
	public static void hashTest() {
		String key="user";
		String field_name="name";
		String field_name_value="tianshouzhi";
		String field_city="city";
		String field_city_value="shanghai";
		jedis.hset(key, field_name, field_name_value);
		jedis.hset(key, field_city, field_city_value);
		
		Map<String, String> map = jedis.hgetAll(key);
		Set<Entry<String,String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	public static void setTest() {
		String key="set_key";
		String[] members=new String[]{"a","b","a","c","e","g","f"};
		jedis.sadd(key, members);
		
		Set<String> smembers = jedis.smembers(key);
		for (String string : smembers) {
			System.out.println(string);
		}
	}
	
	public static void testZSet() {
		String key = "zset_key";
		Map<String,Double> scoreMembers=new HashMap<String, Double>();
		scoreMembers.put("1",22d);
		scoreMembers.put("2",24d);
		scoreMembers.put("3",23d);
		jedis.zadd(key, scoreMembers);
		
		Set<String> zrange = jedis.zrange(key, 0, 2);
		
		for (String member : zrange) {
			System.out.println(member);
			System.out.println(jedis.zscore(key, member));
		}
		
		/*
		 * 输出
		 * tianshouzhi
			1.0
			huhuamin
			2.0
		 */
	}
	
	public static void StringTest() {
         //check whether server is running or not
//         System.out.println(jedis.set("age", "21"));
//         System.out.println("Server is running: "+jedis.get("name"));
//         System.out.println("server :" + jedis.ping());
//         
//         System.out.println(jedis.lpush("price", "20","21"));
//         System.out.println(jedis.lpush("price", "21","22"));
//         System.out.println(jedis.lpop("price"));
//         System.out.println(jedis.lrange("price", 0, 4));
         
         
//         System.out.println(jedis.keys("*"));
	}
}
