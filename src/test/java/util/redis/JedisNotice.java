package util.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisNotice {

	private Jedis jedis;
	@Before
	public void JedisBefore() {
		jedis = new Jedis("localhost");
	}
	
	@Test
	public void testSubsribe() {
		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println(channel);
				System.out.println(message);
			}
		};
		jedis.subscribe(jedisPubSub, "channel1");
	}
	//发布
	@Test
	public void testPublish(){
		String channel="channel1";
		String message="test publish substribe1";
		jedis.publish(channel, message);
	}
	
	
	@Test
	public void testBlockedQueue(){
		while(true){
			List<String> brpop = jedis.brpop(3,"queue");
			for (String string : brpop) {
				System.out.println(string);
			}
		}
	}
	
    @Test
	public void testLpush(){
		jedis.lpush("queue", "queue_message1","queue_message2");
	}
    
    @Test
    public void testSharePool() {
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo("localhost", 6379);
        si.setPassword("foobared");
        shards.add(si);
        si = new JedisShardInfo("localhost", 6380);
        si.setPassword("foobared");
        shards.add(si);
        ShardedJedis jedis = new ShardedJedis(shards);
        jedis.set("a", "foo");
        jedis.disconnect();
        
        ShardedJedisPool pool = new ShardedJedisPool(new GenericObjectPoolConfig(), shards);
        ShardedJedis jedis1 = pool.getResource();
        jedis1.set("a", "foo");
        jedis1.close();
        ShardedJedis jedis2 = pool.getResource();
        jedis2.set("z", "bar");
        jedis2.close();
        pool.destroy();
    }
}
