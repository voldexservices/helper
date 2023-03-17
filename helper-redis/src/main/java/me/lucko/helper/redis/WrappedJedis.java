package me.lucko.helper.redis;

import me.lucko.helper.redis.plugin.HelperRedisPlugin;
import redis.clients.jedis.*;
import redis.clients.jedis.Module;
import redis.clients.jedis.args.FlushMode;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.args.UnblockType;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.*;
import redis.clients.jedis.resps.KeyedListElement;
import redis.clients.jedis.resps.KeyedZSetElement;
import redis.clients.jedis.util.Slowlog;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class WrappedJedis extends Jedis {
    private UUID uuid = UUID.randomUUID();
    private HelperRedisPlugin plugin;
    private Jedis ourJedis;
    public WrappedJedis(HelperRedisPlugin plugin, Jedis jedis) {
        this.ourJedis = jedis;
        this.plugin = plugin;
        //plugin.getLogger().warning("Initialized wrapped Jedis - " + uuid);
        new RuntimeException("Initialized wrapped Jedis - " + uuid).printStackTrace();
    }

    @Override
    public Boolean copy(String srcKey, String dstKey, int db, boolean replace) {
        return ourJedis.copy(srcKey, dstKey, db, replace);
    }

    @Override
    public Boolean copy(String srcKey, String dstKey, boolean replace) {
        return ourJedis.copy(srcKey, dstKey, replace);
    }

    @Override
    public String ping(String message) {
        return ourJedis.ping(message);
    }

    @Override
    public String set(String key, String value) {
        return ourJedis.set(key, value);
    }

    @Override
    public String set(String key, String value, SetParams params) {
        return ourJedis.set(key, value, params);
    }

    @Override
    public String get(String key) {
        return ourJedis.get(key);
    }

    @Override
    public String getDel(String key) {
        return ourJedis.getDel(key);
    }

    @Override
    public String getEx(String key, GetExParams params) {
        return ourJedis.getEx(key, params);
    }

    @Override
    public Long exists(String... keys) {
        return ourJedis.exists(keys);
    }

    @Override
    public Boolean exists(String key) {
        return ourJedis.exists(key);
    }

    @Override
    public Long del(String... keys) {
        return ourJedis.del(keys);
    }

    @Override
    public Long del(String key) {
        return ourJedis.del(key);
    }

    @Override
    public Long unlink(String... keys) {
        return ourJedis.unlink(keys);
    }

    @Override
    public Long unlink(String key) {
        return ourJedis.unlink(key);
    }

    @Override
    public String type(String key) {
        return ourJedis.type(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return ourJedis.keys(pattern);
    }

    @Override
    public String randomKey() {
        return ourJedis.randomKey();
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return ourJedis.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return ourJedis.renamenx(oldkey, newkey);
    }

    @Override
    public Long expire(String key, long seconds) {
        return ourJedis.expire(key, seconds);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return ourJedis.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(String key) {
        return ourJedis.ttl(key);
    }

    @Override
    public Long touch(String... keys) {
        return ourJedis.touch(keys);
    }

    @Override
    public Long touch(String key) {
        return ourJedis.touch(key);
    }

    @Override
    public Long move(String key, int dbIndex) {
        return ourJedis.move(key, dbIndex);
    }

    @Override
    public String getSet(String key, String value) {
        return ourJedis.getSet(key, value);
    }

    @Override
    public List<String> mget(String... keys) {
        return ourJedis.mget(keys);
    }

    @Override
    public Long setnx(String key, String value) {
        return ourJedis.setnx(key, value);
    }

    @Override
    public String setex(String key, long seconds, String value) {
        return ourJedis.setex(key, seconds, value);
    }

    @Override
    public String mset(String... keysvalues) {
        return ourJedis.mset(keysvalues);
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return ourJedis.msetnx(keysvalues);
    }

    @Override
    public Long decrBy(String key, long decrement) {
        return ourJedis.decrBy(key, decrement);
    }

    @Override
    public Long decr(String key) {
        return ourJedis.decr(key);
    }

    @Override
    public Long incrBy(String key, long increment) {
        return ourJedis.incrBy(key, increment);
    }

    @Override
    public Double incrByFloat(String key, double increment) {
        return ourJedis.incrByFloat(key, increment);
    }

    @Override
    public Long incr(String key) {
        return ourJedis.incr(key);
    }

    @Override
    public Long append(String key, String value) {
        return ourJedis.append(key, value);
    }

    @Override
    public String substr(String key, int start, int end) {
        return ourJedis.substr(key, start, end);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return ourJedis.hset(key, field, value);
    }

    @Override
    public Long hset(String key, Map<String, String> hash) {
        return ourJedis.hset(key, hash);
    }

    @Override
    public String hget(String key, String field) {
        return ourJedis.hget(key, field);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return ourJedis.hsetnx(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return ourJedis.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return ourJedis.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return ourJedis.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return ourJedis.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return ourJedis.hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return ourJedis.hdel(key, fields);
    }

    @Override
    public Long hlen(String key) {
        return ourJedis.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return ourJedis.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return ourJedis.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return ourJedis.hgetAll(key);
    }

    @Override
    public String hrandfield(String key) {
        return ourJedis.hrandfield(key);
    }

    @Override
    public List<String> hrandfield(String key, long count) {
        return ourJedis.hrandfield(key, count);
    }

    @Override
    public Map<String, String> hrandfieldWithValues(String key, long count) {
        return ourJedis.hrandfieldWithValues(key, count);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return ourJedis.rpush(key, strings);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return ourJedis.lpush(key, strings);
    }

    @Override
    public Long llen(String key) {
        return ourJedis.llen(key);
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        return ourJedis.lrange(key, start, stop);
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return ourJedis.ltrim(key, start, stop);
    }

    @Override
    public String lindex(String key, long index) {
        return ourJedis.lindex(key, index);
    }

    @Override
    public String lset(String key, long index, String value) {
        return ourJedis.lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return ourJedis.lrem(key, count, value);
    }

    @Override
    public String lpop(String key) {
        return ourJedis.lpop(key);
    }

    @Override
    public List<String> lpop(String key, int count) {
        return ourJedis.lpop(key, count);
    }

    @Override
    public Long lpos(String key, String element) {
        return ourJedis.lpos(key, element);
    }

    @Override
    public Long lpos(String key, String element, LPosParams params) {
        return ourJedis.lpos(key, element, params);
    }

    @Override
    public List<Long> lpos(String key, String element, LPosParams params, long count) {
        return ourJedis.lpos(key, element, params, count);
    }

    @Override
    public String rpop(String key) {
        return ourJedis.rpop(key);
    }

    @Override
    public List<String> rpop(String key, int count) {
        return ourJedis.rpop(key, count);
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return ourJedis.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long sadd(String key, String... members) {
        return ourJedis.sadd(key, members);
    }

    @Override
    public Set<String> smembers(String key) {
        return ourJedis.smembers(key);
    }

    @Override
    public Long srem(String key, String... members) {
        return ourJedis.srem(key, members);
    }

    @Override
    public String spop(String key) {
        return ourJedis.spop(key);
    }

    @Override
    public Set<String> spop(String key, long count) {
        return ourJedis.spop(key, count);
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return ourJedis.smove(srckey, dstkey, member);
    }

    @Override
    public Long scard(String key) {
        return ourJedis.scard(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return ourJedis.sismember(key, member);
    }

    @Override
    public List<Boolean> smismember(String key, String... members) {
        return ourJedis.smismember(key, members);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return ourJedis.sinter(keys);
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return ourJedis.sinterstore(dstkey, keys);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return ourJedis.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return ourJedis.sunionstore(dstkey, keys);
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return ourJedis.sdiff(keys);
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return ourJedis.sdiffstore(dstkey, keys);
    }

    @Override
    public String srandmember(String key) {
        return ourJedis.srandmember(key);
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return ourJedis.srandmember(key, count);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return ourJedis.zadd(key, score, member);
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return ourJedis.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return ourJedis.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return ourJedis.zadd(key, scoreMembers, params);
    }

    @Override
    public Double zaddIncr(String key, double score, String member, ZAddParams params) {
        return ourJedis.zaddIncr(key, score, member, params);
    }

    @Override
    public Set<String> zdiff(String... keys) {
        return ourJedis.zdiff(keys);
    }

    @Override
    public Set<Tuple> zdiffWithScores(String... keys) {
        return ourJedis.zdiffWithScores(keys);
    }

    @Override
    public Long zdiffStore(String dstkey, String... keys) {
        return ourJedis.zdiffStore(dstkey, keys);
    }

    @Override
    public Set<String> zrange(String key, long start, long stop) {
        return ourJedis.zrange(key, start, stop);
    }

    @Override
    public Long zrem(String key, String... members) {
        return ourJedis.zrem(key, members);
    }

    @Override
    public Double zincrby(String key, double increment, String member) {
        return ourJedis.zincrby(key, increment, member);
    }

    @Override
    public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
        return ourJedis.zincrby(key, increment, member, params);
    }

    @Override
    public Long zrank(String key, String member) {
        return ourJedis.zrank(key, member);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return ourJedis.zrevrank(key, member);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long stop) {
        return ourJedis.zrevrange(key, start, stop);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
        return ourJedis.zrangeWithScores(key, start, stop);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
        return ourJedis.zrevrangeWithScores(key, start, stop);
    }

    @Override
    public String zrandmember(String key) {
        return ourJedis.zrandmember(key);
    }

    @Override
    public Set<String> zrandmember(String key, long count) {
        return ourJedis.zrandmember(key, count);
    }

    @Override
    public Set<Tuple> zrandmemberWithScores(String key, long count) {
        return ourJedis.zrandmemberWithScores(key, count);
    }

    @Override
    public Long zcard(String key) {
        return ourJedis.zcard(key);
    }

    @Override
    public Double zscore(String key, String member) {
        return ourJedis.zscore(key, member);
    }

    @Override
    public List<Double> zmscore(String key, String... members) {
        return ourJedis.zmscore(key, members);
    }

    @Override
    public Tuple zpopmax(String key) {
        return ourJedis.zpopmax(key);
    }

    @Override
    public Set<Tuple> zpopmax(String key, int count) {
        return ourJedis.zpopmax(key, count);
    }

    @Override
    public Tuple zpopmin(String key) {
        return ourJedis.zpopmin(key);
    }

    @Override
    public Set<Tuple> zpopmin(String key, int count) {
        return ourJedis.zpopmin(key, count);
    }

    @Override
    public String watch(String... keys) {
        return ourJedis.watch(keys);
    }

    @Override
    public List<String> sort(String key) {
        return ourJedis.sort(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return ourJedis.sort(key, sortingParameters);
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return ourJedis.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(String key, String dstkey) {
        return ourJedis.sort(key, dstkey);
    }

    @Override
    public String lmove(String srcKey, String dstKey, ListDirection from, ListDirection to) {
        return ourJedis.lmove(srcKey, dstKey, from, to);
    }

    @Override
    public String blmove(String srcKey, String dstKey, ListDirection from, ListDirection to, double timeout) {
        return ourJedis.blmove(srcKey, dstKey, from, to, timeout);
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return ourJedis.blpop(timeout, keys);
    }

    @Override
    public KeyedListElement blpop(double timeout, String... keys) {
        return ourJedis.blpop(timeout, keys);
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return ourJedis.brpop(timeout, keys);
    }

    @Override
    public KeyedListElement brpop(double timeout, String... keys) {
        return ourJedis.brpop(timeout, keys);
    }

    @Override
    public List<String> blpop(String... args) {
        return ourJedis.blpop(args);
    }

    @Override
    public List<String> brpop(String... args) {
        return ourJedis.brpop(args);
    }

    @Override
    public KeyedZSetElement bzpopmax(double timeout, String... keys) {
        return ourJedis.bzpopmax(timeout, keys);
    }

    @Override
    public KeyedZSetElement bzpopmin(double timeout, String... keys) {
        return ourJedis.bzpopmin(timeout, keys);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return ourJedis.blpop(timeout, key);
    }

    @Override
    public KeyedListElement blpop(double timeout, String key) {
        return ourJedis.blpop(timeout, key);
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return ourJedis.brpop(timeout, key);
    }

    @Override
    public KeyedListElement brpop(double timeout, String key) {
        return ourJedis.brpop(timeout, key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return ourJedis.zcount(key, min, max);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return ourJedis.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return ourJedis.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return ourJedis.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return ourJedis.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return ourJedis.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return ourJedis.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return ourJedis.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return ourJedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return ourJedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return ourJedis.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return ourJedis.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return ourJedis.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return ourJedis.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return ourJedis.zremrangeByRank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return ourJedis.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zremrangeByScore(String key, String min, String max) {
        return ourJedis.zremrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zunion(ZParams params, String... keys) {
        return ourJedis.zunion(params, keys);
    }

    @Override
    public Set<Tuple> zunionWithScores(ZParams params, String... keys) {
        return ourJedis.zunionWithScores(params, keys);
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return ourJedis.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return ourJedis.zunionstore(dstkey, params, sets);
    }

    @Override
    public Set<String> zinter(ZParams params, String... keys) {
        return ourJedis.zinter(params, keys);
    }

    @Override
    public Set<Tuple> zinterWithScores(ZParams params, String... keys) {
        return ourJedis.zinterWithScores(params, keys);
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return ourJedis.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return ourJedis.zinterstore(dstkey, params, sets);
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return ourJedis.zlexcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return ourJedis.zrangeByLex(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return ourJedis.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return ourJedis.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return ourJedis.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return ourJedis.zremrangeByLex(key, min, max);
    }

    @Override
    public Long strlen(String key) {
        return ourJedis.strlen(key);
    }

    @Override
    public Long lpushx(String key, String... string) {
        return ourJedis.lpushx(key, string);
    }

    @Override
    public Long persist(String key) {
        return ourJedis.persist(key);
    }

    @Override
    public Long rpushx(String key, String... string) {
        return ourJedis.rpushx(key, string);
    }

    @Override
    public String echo(String string) {
        return ourJedis.echo(string);
    }

    @Override
    public Long linsert(String key, ListPosition where, String pivot, String value) {
        return ourJedis.linsert(key, where, pivot, value);
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return ourJedis.brpoplpush(source, destination, timeout);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return ourJedis.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return ourJedis.setbit(key, offset, value);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return ourJedis.getbit(key, offset);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return ourJedis.setrange(key, offset, value);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return ourJedis.getrange(key, startOffset, endOffset);
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return ourJedis.bitpos(key, value);
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return ourJedis.bitpos(key, value, params);
    }

    @Override
    public List<String> configGet(String pattern) {
        return ourJedis.configGet(pattern);
    }

    @Override
    public String configSet(String parameter, String value) {
        return ourJedis.configSet(parameter, value);
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        ourJedis.subscribe(jedisPubSub, channels);
    }

    @Override
    public Long publish(String channel, String message) {
        return ourJedis.publish(channel, message);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        ourJedis.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        return ourJedis.eval(script, keyCount, params);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return ourJedis.eval(script, keys, args);
    }

    @Override
    public Object eval(String script) {
        return ourJedis.eval(script);
    }

    @Override
    public Object evalsha(String sha1) {
        return ourJedis.evalsha(sha1);
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        return ourJedis.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        return ourJedis.evalsha(sha1, keyCount, params);
    }

    @Override
    public Boolean scriptExists(String sha1) {
        return ourJedis.scriptExists(sha1);
    }

    @Override
    public List<Boolean> scriptExists(String... sha1) {
        return ourJedis.scriptExists(sha1);
    }

    @Override
    public String scriptLoad(String script) {
        return ourJedis.scriptLoad(script);
    }

    @Override
    public List<Slowlog> slowlogGet() {
        return ourJedis.slowlogGet();
    }

    @Override
    public List<Slowlog> slowlogGet(long entries) {
        return ourJedis.slowlogGet(entries);
    }

    @Override
    public Long objectRefcount(String key) {
        return ourJedis.objectRefcount(key);
    }

    @Override
    public String objectEncoding(String key) {
        return ourJedis.objectEncoding(key);
    }

    @Override
    public Long objectIdletime(String key) {
        return ourJedis.objectIdletime(key);
    }

    @Override
    public List<String> objectHelp() {
        return ourJedis.objectHelp();
    }

    @Override
    public Long objectFreq(String key) {
        return ourJedis.objectFreq(key);
    }

    @Override
    public Long bitcount(String key) {
        return ourJedis.bitcount(key);
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return ourJedis.bitcount(key, start, end);
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return ourJedis.bitop(op, destKey, srcKeys);
    }

    @Override
    public List<Map<String, String>> sentinelMasters() {
        return ourJedis.sentinelMasters();
    }

    @Override
    public List<String> sentinelGetMasterAddrByName(String masterName) {
        return ourJedis.sentinelGetMasterAddrByName(masterName);
    }

    @Override
    public Long sentinelReset(String pattern) {
        return ourJedis.sentinelReset(pattern);
    }

    @Override
    public List<Map<String, String>> sentinelSlaves(String masterName) {
        return ourJedis.sentinelSlaves(masterName);
    }

    @Override
    public String sentinelFailover(String masterName) {
        return ourJedis.sentinelFailover(masterName);
    }

    @Override
    public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
        return ourJedis.sentinelMonitor(masterName, ip, port, quorum);
    }

    @Override
    public String sentinelRemove(String masterName) {
        return ourJedis.sentinelRemove(masterName);
    }

    @Override
    public String sentinelSet(String masterName, Map<String, String> parameterMap) {
        return ourJedis.sentinelSet(masterName, parameterMap);
    }

    @Override
    public byte[] dump(String key) {
        return ourJedis.dump(key);
    }

    @Override
    public String restore(String key, long ttl, byte[] serializedValue) {
        return ourJedis.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(String key, long ttl, byte[] serializedValue) {
        return ourJedis.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public String restore(String key, long ttl, byte[] serializedValue, RestoreParams params) {
        return ourJedis.restore(key, ttl, serializedValue, params);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return ourJedis.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return ourJedis.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(String key) {
        return ourJedis.pttl(key);
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return ourJedis.psetex(key, milliseconds, value);
    }

    @Override
    public String clientKill(String ipPort) {
        return ourJedis.clientKill(ipPort);
    }

    @Override
    public String clientGetname() {
        return ourJedis.clientGetname();
    }

    @Override
    public String clientList() {
        return ourJedis.clientList();
    }

    @Override
    public String clientList(long... clientIds) {
        return ourJedis.clientList(clientIds);
    }

    @Override
    public String clientInfo() {
        return ourJedis.clientInfo();
    }

    @Override
    public String clientSetname(String name) {
        return ourJedis.clientSetname(name);
    }

    @Override
    public Long clientId() {
        return ourJedis.clientId();
    }

    @Override
    public Long clientUnblock(long clientId, UnblockType unblockType) {
        return ourJedis.clientUnblock(clientId, unblockType);
    }

    @Override
    public String migrate(String host, int port, String key, int destinationDb, int timeout) {
        return ourJedis.migrate(host, port, key, destinationDb, timeout);
    }

    @Override
    public String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, String... keys) {
        return ourJedis.migrate(host, port, destinationDB, timeout, params, keys);
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        return ourJedis.scan(cursor);
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return ourJedis.scan(cursor, params);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return ourJedis.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return ourJedis.hscan(key, cursor, params);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return ourJedis.sscan(key, cursor);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return ourJedis.sscan(key, cursor, params);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return ourJedis.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return ourJedis.zscan(key, cursor, params);
    }

    @Override
    public String clusterNodes() {
        return ourJedis.clusterNodes();
    }

    @Override
    public String readonly() {
        return ourJedis.readonly();
    }

    @Override
    public String clusterMeet(String ip, int port) {
        return ourJedis.clusterMeet(ip, port);
    }

    @Override
    public String clusterReset(ClusterReset resetType) {
        return ourJedis.clusterReset(resetType);
    }

    @Override
    public String clusterAddSlots(int... slots) {
        return ourJedis.clusterAddSlots(slots);
    }

    @Override
    public String clusterDelSlots(int... slots) {
        return ourJedis.clusterDelSlots(slots);
    }

    @Override
    public String clusterInfo() {
        return ourJedis.clusterInfo();
    }

    @Override
    public List<String> clusterGetKeysInSlot(int slot, int count) {
        return ourJedis.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public String clusterSetSlotNode(int slot, String nodeId) {
        return ourJedis.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return ourJedis.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public String clusterSetSlotImporting(int slot, String nodeId) {
        return ourJedis.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public String clusterSetSlotStable(int slot) {
        return ourJedis.clusterSetSlotStable(slot);
    }

    @Override
    public String clusterForget(String nodeId) {
        return ourJedis.clusterForget(nodeId);
    }

    @Override
    public String clusterFlushSlots() {
        return ourJedis.clusterFlushSlots();
    }

    @Override
    public Long clusterKeySlot(String key) {
        return ourJedis.clusterKeySlot(key);
    }

    @Override
    public Long clusterCountKeysInSlot(int slot) {
        return ourJedis.clusterCountKeysInSlot(slot);
    }

    @Override
    public String clusterSaveConfig() {
        return ourJedis.clusterSaveConfig();
    }

    @Override
    public String clusterReplicate(String nodeId) {
        return ourJedis.clusterReplicate(nodeId);
    }

    @Override
    public List<String> clusterSlaves(String nodeId) {
        return ourJedis.clusterSlaves(nodeId);
    }

    @Override
    public String clusterFailover() {
        return ourJedis.clusterFailover();
    }

    @Override
    public List<Object> clusterSlots() {
        return ourJedis.clusterSlots();
    }

    @Override
    public String asking() {
        return ourJedis.asking();
    }

    @Override
    public List<String> pubsubChannels(String pattern) {
        return ourJedis.pubsubChannels(pattern);
    }

    @Override
    public Long pubsubNumPat() {
        return ourJedis.pubsubNumPat();
    }

    @Override
    public Map<String, String> pubsubNumSub(String... channels) {
        return ourJedis.pubsubNumSub(channels);
    }

    @Override
    public void close() {
        plugin.getLogger().warning("Closed wrapped Jedis - " + uuid);
        ourJedis.close();
    }

    @Override
    public void setDataSource(JedisPoolAbstract jedisPool) {
        ourJedis.setDataSource(jedisPool);
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return ourJedis.pfadd(key, elements);
    }

    @Override
    public long pfcount(String key) {
        return ourJedis.pfcount(key);
    }

    @Override
    public long pfcount(String... keys) {
        return ourJedis.pfcount(keys);
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        return ourJedis.pfmerge(destkey, sourcekeys);
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return ourJedis.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return ourJedis.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Long geoadd(String key, GeoAddParams params, Map<String, GeoCoordinate> memberCoordinateMap) {
        return ourJedis.geoadd(key, params, memberCoordinateMap);
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return ourJedis.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return ourJedis.geodist(key, member1, member2, unit);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return ourJedis.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return ourJedis.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return ourJedis.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return ourJedis.georadiusReadonly(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public Long georadiusStore(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param, GeoRadiusStoreParam storeParam) {
        return ourJedis.georadiusStore(key, longitude, latitude, radius, unit, param, storeParam);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return ourJedis.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
        return ourJedis.georadiusByMemberReadonly(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public Long georadiusByMemberStore(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param, GeoRadiusStoreParam storeParam) {
        return ourJedis.georadiusByMemberStore(key, member, radius, unit, param, storeParam);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusByMemberReadonly(key, member, radius, unit, param);
    }

    @Override
    public String moduleLoad(String path) {
        return ourJedis.moduleLoad(path);
    }

    @Override
    public String moduleUnload(String name) {
        return ourJedis.moduleUnload(name);
    }

    @Override
    public List<Module> moduleList() {
        return ourJedis.moduleList();
    }

    @Override
    public String aclSetUser(String name) {
        return ourJedis.aclSetUser(name);
    }

    @Override
    public String aclSetUser(String name, String... params) {
        return ourJedis.aclSetUser(name, params);
    }

    @Override
    public Long aclDelUser(String name) {
        return ourJedis.aclDelUser(name);
    }

    @Override
    public AccessControlUser aclGetUser(String name) {
        return ourJedis.aclGetUser(name);
    }

    @Override
    public List<String> aclUsers() {
        return ourJedis.aclUsers();
    }

    @Override
    public List<String> aclList() {
        return ourJedis.aclList();
    }

    @Override
    public String aclWhoAmI() {
        return ourJedis.aclWhoAmI();
    }

    @Override
    public List<String> aclCat() {
        return ourJedis.aclCat();
    }

    @Override
    public List<String> aclCat(String category) {
        return ourJedis.aclCat(category);
    }

    @Override
    public List<AccessControlLogEntry> aclLog() {
        return ourJedis.aclLog();
    }

    @Override
    public List<AccessControlLogEntry> aclLog(int limit) {
        return ourJedis.aclLog(limit);
    }

    @Override
    public String aclLog(String options) {
        return ourJedis.aclLog(options);
    }

    @Override
    public String aclGenPass() {
        return ourJedis.aclGenPass();
    }

    @Override
    public String aclLoad() {
        return ourJedis.aclLoad();
    }

    @Override
    public String aclSave() {
        return ourJedis.aclSave();
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        return ourJedis.bitfield(key, arguments);
    }

    @Override
    public List<Long> bitfieldReadonly(String key, String... arguments) {
        return ourJedis.bitfieldReadonly(key, arguments);
    }

    @Override
    public Long hstrlen(String key, String field) {
        return ourJedis.hstrlen(key, field);
    }

    @Override
    public String memoryDoctor() {
        return ourJedis.memoryDoctor();
    }

    @Override
    public Long memoryUsage(String key) {
        return ourJedis.memoryUsage(key);
    }

    @Override
    public Long memoryUsage(String key, int samples) {
        return ourJedis.memoryUsage(key, samples);
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
        return ourJedis.xadd(key, id, hash);
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen, boolean approximateLength) {
        return ourJedis.xadd(key, id, hash, maxLen, approximateLength);
    }

    @Override
    public StreamEntryID xadd(String key, Map<String, String> hash, XAddParams params) {
        return ourJedis.xadd(key, hash, params);
    }

    @Override
    public Long xlen(String key) {
        return ourJedis.xlen(key);
    }

    @Override
    public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end) {
        return ourJedis.xrange(key, start, end);
    }

    @Override
    public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
        return ourJedis.xrange(key, start, end, count);
    }

    @Override
    public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start) {
        return ourJedis.xrevrange(key, end, start);
    }

    @Override
    public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
        return ourJedis.xrevrange(key, end, start, count);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xread(int count, long block, Map.Entry<String, StreamEntryID>... streams) {
        return ourJedis.xread(count, block, streams);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xread(XReadParams xReadParams, Map<String, StreamEntryID> streams) {
        return ourJedis.xread(xReadParams, streams);
    }

    @Override
    public long xack(String key, String group, StreamEntryID... ids) {
        return ourJedis.xack(key, group, ids);
    }

    @Override
    public String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
        return ourJedis.xgroupCreate(key, groupname, id, makeStream);
    }

    @Override
    public String xgroupSetID(String key, String groupname, StreamEntryID id) {
        return ourJedis.xgroupSetID(key, groupname, id);
    }

    @Override
    public long xgroupDestroy(String key, String groupname) {
        return ourJedis.xgroupDestroy(key, groupname);
    }

    @Override
    public Long xgroupDelConsumer(String key, String groupname, String consumerName) {
        return ourJedis.xgroupDelConsumer(key, groupname, consumerName);
    }

    @Override
    public long xdel(String key, StreamEntryID... ids) {
        return ourJedis.xdel(key, ids);
    }

    @Override
    public long xtrim(String key, long maxLen, boolean approximateLength) {
        return ourJedis.xtrim(key, maxLen, approximateLength);
    }

    @Override
    public long xtrim(String key, XTrimParams params) {
        return ourJedis.xtrim(key, params);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block, boolean noAck, Map.Entry<String, StreamEntryID>... streams) {
        return ourJedis.xreadGroup(groupname, consumer, count, block, noAck, streams);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, XReadGroupParams xReadGroupParams, Map<String, StreamEntryID> streams) {
        return ourJedis.xreadGroup(groupname, consumer, xReadGroupParams, streams);
    }

    @Override
    public StreamPendingSummary xpending(String key, String groupname) {
        return ourJedis.xpending(key, groupname);
    }

    @Override
    public List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end, int count, String consumername) {
        return ourJedis.xpending(key, groupname, start, end, count, consumername);
    }

    @Override
    public List<StreamPendingEntry> xpending(String key, String groupname, XPendingParams params) {
        return ourJedis.xpending(key, groupname, params);
    }

    @Override
    public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime, int retries, boolean force, StreamEntryID... ids) {
        return ourJedis.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
    }

    @Override
    public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, XClaimParams params, StreamEntryID... ids) {
        return ourJedis.xclaim(key, group, consumername, minIdleTime, params, ids);
    }

    @Override
    public List<StreamEntryID> xclaimJustId(String key, String group, String consumername, long minIdleTime, XClaimParams params, StreamEntryID... ids) {
        return ourJedis.xclaimJustId(key, group, consumername, minIdleTime, params, ids);
    }

    @Override
    public StreamInfo xinfoStream(String key) {
        return ourJedis.xinfoStream(key);
    }

    @Override
    public List<StreamGroupInfo> xinfoGroup(String key) {
        return ourJedis.xinfoGroup(key);
    }

    @Override
    public List<StreamConsumersInfo> xinfoConsumers(String key, String group) {
        return ourJedis.xinfoConsumers(key, group);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd, String... args) {
        return ourJedis.sendCommand(cmd, args);
    }

    @Override
    public Object sendBlockingCommand(ProtocolCommand cmd, String... args) {
        return ourJedis.sendBlockingCommand(cmd, args);
    }

    @Override
    public String toString() {
        return ourJedis.toString();
    }

    @Override
    public boolean isConnected() {
        return ourJedis.isConnected();
    }

    @Override
    public boolean isBroken() {
        return ourJedis.isBroken();
    }

    @Override
    public void connect() {
        ourJedis.connect();
    }

    @Override
    public void disconnect() {
        ourJedis.disconnect();
    }

    @Override
    public void resetState() {
        ourJedis.resetState();
    }

    @Override
    public int getDB() {
        return ourJedis.getDB();
    }

    @Override
    public Boolean copy(byte[] srcKey, byte[] dstKey, int db, boolean replace) {
        return ourJedis.copy(srcKey, dstKey, db, replace);
    }

    @Override
    public Boolean copy(byte[] srcKey, byte[] dstKey, boolean replace) {
        return ourJedis.copy(srcKey, dstKey, replace);
    }

    @Override
    public String ping() {
        return ourJedis.ping();
    }

    @Override
    public byte[] ping(byte[] message) {
        return ourJedis.ping(message);
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return ourJedis.set(key, value);
    }

    @Override
    public String set(byte[] key, byte[] value, SetParams params) {
        return ourJedis.set(key, value, params);
    }

    @Override
    public byte[] get(byte[] key) {
        return ourJedis.get(key);
    }

    @Override
    public byte[] getDel(byte[] key) {
        return ourJedis.getDel(key);
    }

    @Override
    public byte[] getEx(byte[] key, GetExParams params) {
        return ourJedis.getEx(key, params);
    }

    @Override
    public String quit() {
        return ourJedis.quit();
    }

    @Override
    public Long exists(byte[]... keys) {
        return ourJedis.exists(keys);
    }

    @Override
    public Boolean exists(byte[] key) {
        return ourJedis.exists(key);
    }

    @Override
    public Long del(byte[]... keys) {
        return ourJedis.del(keys);
    }

    @Override
    public Long del(byte[] key) {
        return ourJedis.del(key);
    }

    @Override
    public Long unlink(byte[]... keys) {
        return ourJedis.unlink(keys);
    }

    @Override
    public Long unlink(byte[] key) {
        return ourJedis.unlink(key);
    }

    @Override
    public String type(byte[] key) {
        return ourJedis.type(key);
    }

    @Override
    public String flushDB() {
        return ourJedis.flushDB();
    }

    @Override
    public String flushDB(FlushMode flushMode) {
        return ourJedis.flushDB(flushMode);
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        return ourJedis.keys(pattern);
    }

    @Override
    public byte[] randomBinaryKey() {
        return ourJedis.randomBinaryKey();
    }

    @Override
    public String rename(byte[] oldkey, byte[] newkey) {
        return ourJedis.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(byte[] oldkey, byte[] newkey) {
        return ourJedis.renamenx(oldkey, newkey);
    }

    @Override
    public Long dbSize() {
        return ourJedis.dbSize();
    }

    @Override
    public Long expire(byte[] key, long seconds) {
        return ourJedis.expire(key, seconds);
    }

    @Override
    public Long expireAt(byte[] key, long unixTime) {
        return ourJedis.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(byte[] key) {
        return ourJedis.ttl(key);
    }

    @Override
    public Long touch(byte[]... keys) {
        return ourJedis.touch(keys);
    }

    @Override
    public Long touch(byte[] key) {
        return ourJedis.touch(key);
    }

    @Override
    public String select(int index) {
        return ourJedis.select(index);
    }

    @Override
    public String swapDB(int index1, int index2) {
        return ourJedis.swapDB(index1, index2);
    }

    @Override
    public Long move(byte[] key, int dbIndex) {
        return ourJedis.move(key, dbIndex);
    }

    @Override
    public String flushAll() {
        return ourJedis.flushAll();
    }

    @Override
    public String flushAll(FlushMode flushMode) {
        return ourJedis.flushAll(flushMode);
    }

    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        return ourJedis.getSet(key, value);
    }

    @Override
    public List<byte[]> mget(byte[]... keys) {
        return ourJedis.mget(keys);
    }

    @Override
    public Long setnx(byte[] key, byte[] value) {
        return ourJedis.setnx(key, value);
    }

    @Override
    public String setex(byte[] key, long seconds, byte[] value) {
        return ourJedis.setex(key, seconds, value);
    }

    @Override
    public String mset(byte[]... keysvalues) {
        return ourJedis.mset(keysvalues);
    }

    @Override
    public Long msetnx(byte[]... keysvalues) {
        return ourJedis.msetnx(keysvalues);
    }

    @Override
    public Long decrBy(byte[] key, long decrement) {
        return ourJedis.decrBy(key, decrement);
    }

    @Override
    public Long decr(byte[] key) {
        return ourJedis.decr(key);
    }

    @Override
    public Long incrBy(byte[] key, long increment) {
        return ourJedis.incrBy(key, increment);
    }

    @Override
    public Double incrByFloat(byte[] key, double increment) {
        return ourJedis.incrByFloat(key, increment);
    }

    @Override
    public Long incr(byte[] key) {
        return ourJedis.incr(key);
    }

    @Override
    public Long append(byte[] key, byte[] value) {
        return ourJedis.append(key, value);
    }

    @Override
    public byte[] substr(byte[] key, int start, int end) {
        return ourJedis.substr(key, start, end);
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        return ourJedis.hset(key, field, value);
    }

    @Override
    public Long hset(byte[] key, Map<byte[], byte[]> hash) {
        return ourJedis.hset(key, hash);
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        return ourJedis.hget(key, field);
    }

    @Override
    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        return ourJedis.hsetnx(key, field, value);
    }

    @Override
    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        return ourJedis.hmset(key, hash);
    }

    @Override
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        return ourJedis.hmget(key, fields);
    }

    @Override
    public Long hincrBy(byte[] key, byte[] field, long value) {
        return ourJedis.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(byte[] key, byte[] field, double value) {
        return ourJedis.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(byte[] key, byte[] field) {
        return ourJedis.hexists(key, field);
    }

    @Override
    public Long hdel(byte[] key, byte[]... fields) {
        return ourJedis.hdel(key, fields);
    }

    @Override
    public Long hlen(byte[] key) {
        return ourJedis.hlen(key);
    }

    @Override
    public Set<byte[]> hkeys(byte[] key) {
        return ourJedis.hkeys(key);
    }

    @Override
    public List<byte[]> hvals(byte[] key) {
        return ourJedis.hvals(key);
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key) {
        return ourJedis.hgetAll(key);
    }

    @Override
    public byte[] hrandfield(byte[] key) {
        return ourJedis.hrandfield(key);
    }

    @Override
    public List<byte[]> hrandfield(byte[] key, long count) {
        return ourJedis.hrandfield(key, count);
    }

    @Override
    public Map<byte[], byte[]> hrandfieldWithValues(byte[] key, long count) {
        return ourJedis.hrandfieldWithValues(key, count);
    }

    @Override
    public Long rpush(byte[] key, byte[]... strings) {
        return ourJedis.rpush(key, strings);
    }

    @Override
    public Long lpush(byte[] key, byte[]... strings) {
        return ourJedis.lpush(key, strings);
    }

    @Override
    public Long llen(byte[] key) {
        return ourJedis.llen(key);
    }

    @Override
    public List<byte[]> lrange(byte[] key, long start, long stop) {
        return ourJedis.lrange(key, start, stop);
    }

    @Override
    public String ltrim(byte[] key, long start, long stop) {
        return ourJedis.ltrim(key, start, stop);
    }

    @Override
    public byte[] lindex(byte[] key, long index) {
        return ourJedis.lindex(key, index);
    }

    @Override
    public String lset(byte[] key, long index, byte[] value) {
        return ourJedis.lset(key, index, value);
    }

    @Override
    public Long lrem(byte[] key, long count, byte[] value) {
        return ourJedis.lrem(key, count, value);
    }

    @Override
    public byte[] lpop(byte[] key) {
        return ourJedis.lpop(key);
    }

    @Override
    public List<byte[]> lpop(byte[] key, int count) {
        return ourJedis.lpop(key, count);
    }

    @Override
    public Long lpos(byte[] key, byte[] element) {
        return ourJedis.lpos(key, element);
    }

    @Override
    public Long lpos(byte[] key, byte[] element, LPosParams params) {
        return ourJedis.lpos(key, element, params);
    }

    @Override
    public List<Long> lpos(byte[] key, byte[] element, LPosParams params, long count) {
        return ourJedis.lpos(key, element, params, count);
    }

    @Override
    public byte[] rpop(byte[] key) {
        return ourJedis.rpop(key);
    }

    @Override
    public List<byte[]> rpop(byte[] key, int count) {
        return ourJedis.rpop(key, count);
    }

    @Override
    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        return ourJedis.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long sadd(byte[] key, byte[]... members) {
        return ourJedis.sadd(key, members);
    }

    @Override
    public Set<byte[]> smembers(byte[] key) {
        return ourJedis.smembers(key);
    }

    @Override
    public Long srem(byte[] key, byte[]... member) {
        return ourJedis.srem(key, member);
    }

    @Override
    public byte[] spop(byte[] key) {
        return ourJedis.spop(key);
    }

    @Override
    public Set<byte[]> spop(byte[] key, long count) {
        return ourJedis.spop(key, count);
    }

    @Override
    public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
        return ourJedis.smove(srckey, dstkey, member);
    }

    @Override
    public Long scard(byte[] key) {
        return ourJedis.scard(key);
    }

    @Override
    public Boolean sismember(byte[] key, byte[] member) {
        return ourJedis.sismember(key, member);
    }

    @Override
    public List<Boolean> smismember(byte[] key, byte[]... members) {
        return ourJedis.smismember(key, members);
    }

    @Override
    public Set<byte[]> sinter(byte[]... keys) {
        return ourJedis.sinter(keys);
    }

    @Override
    public Long sinterstore(byte[] dstkey, byte[]... keys) {
        return ourJedis.sinterstore(dstkey, keys);
    }

    @Override
    public Set<byte[]> sunion(byte[]... keys) {
        return ourJedis.sunion(keys);
    }

    @Override
    public Long sunionstore(byte[] dstkey, byte[]... keys) {
        return ourJedis.sunionstore(dstkey, keys);
    }

    @Override
    public Set<byte[]> sdiff(byte[]... keys) {
        return ourJedis.sdiff(keys);
    }

    @Override
    public Long sdiffstore(byte[] dstkey, byte[]... keys) {
        return ourJedis.sdiffstore(dstkey, keys);
    }

    @Override
    public byte[] srandmember(byte[] key) {
        return ourJedis.srandmember(key);
    }

    @Override
    public List<byte[]> srandmember(byte[] key, int count) {
        return ourJedis.srandmember(key, count);
    }

    @Override
    public Long zadd(byte[] key, double score, byte[] member) {
        return ourJedis.zadd(key, score, member);
    }

    @Override
    public Long zadd(byte[] key, double score, byte[] member, ZAddParams params) {
        return ourJedis.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
        return ourJedis.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
        return ourJedis.zadd(key, scoreMembers, params);
    }

    @Override
    public Double zaddIncr(byte[] key, double score, byte[] member, ZAddParams params) {
        return ourJedis.zaddIncr(key, score, member, params);
    }

    @Override
    public Set<byte[]> zrange(byte[] key, long start, long stop) {
        return ourJedis.zrange(key, start, stop);
    }

    @Override
    public Long zrem(byte[] key, byte[]... members) {
        return ourJedis.zrem(key, members);
    }

    @Override
    public Double zincrby(byte[] key, double increment, byte[] member) {
        return ourJedis.zincrby(key, increment, member);
    }

    @Override
    public Double zincrby(byte[] key, double increment, byte[] member, ZIncrByParams params) {
        return ourJedis.zincrby(key, increment, member, params);
    }

    @Override
    public Long zrank(byte[] key, byte[] member) {
        return ourJedis.zrank(key, member);
    }

    @Override
    public Long zrevrank(byte[] key, byte[] member) {
        return ourJedis.zrevrank(key, member);
    }

    @Override
    public Set<byte[]> zrevrange(byte[] key, long start, long stop) {
        return ourJedis.zrevrange(key, start, stop);
    }

    @Override
    public Set<Tuple> zrangeWithScores(byte[] key, long start, long stop) {
        return ourJedis.zrangeWithScores(key, start, stop);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long stop) {
        return ourJedis.zrevrangeWithScores(key, start, stop);
    }

    @Override
    public byte[] zrandmember(byte[] key) {
        return ourJedis.zrandmember(key);
    }

    @Override
    public Set<byte[]> zrandmember(byte[] key, long count) {
        return ourJedis.zrandmember(key, count);
    }

    @Override
    public Set<Tuple> zrandmemberWithScores(byte[] key, long count) {
        return ourJedis.zrandmemberWithScores(key, count);
    }

    @Override
    public Long zcard(byte[] key) {
        return ourJedis.zcard(key);
    }

    @Override
    public Double zscore(byte[] key, byte[] member) {
        return ourJedis.zscore(key, member);
    }

    @Override
    public List<Double> zmscore(byte[] key, byte[]... members) {
        return ourJedis.zmscore(key, members);
    }

    @Override
    public Tuple zpopmax(byte[] key) {
        return ourJedis.zpopmax(key);
    }

    @Override
    public Set<Tuple> zpopmax(byte[] key, int count) {
        return ourJedis.zpopmax(key, count);
    }

    @Override
    public Tuple zpopmin(byte[] key) {
        return ourJedis.zpopmin(key);
    }

    @Override
    public Set<Tuple> zpopmin(byte[] key, int count) {
        return ourJedis.zpopmin(key, count);
    }

    @Override
    public Transaction multi() {
        return ourJedis.multi();
    }

    @Override
    protected void checkIsInMultiOrPipeline() {

    }

    @Override
    public String watch(byte[]... keys) {
        return ourJedis.watch(keys);
    }

    @Override
    public String unwatch() {
        return ourJedis.unwatch();
    }

    @Override
    public List<byte[]> sort(byte[] key) {
        return ourJedis.sort(key);
    }

    @Override
    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        return ourJedis.sort(key, sortingParameters);
    }

    @Override
    public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        return ourJedis.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(byte[] key, byte[] dstkey) {
        return ourJedis.sort(key, dstkey);
    }

    @Override
    public byte[] lmove(byte[] srcKey, byte[] dstKey, ListDirection from, ListDirection to) {
        return ourJedis.lmove(srcKey, dstKey, from, to);
    }

    @Override
    public byte[] blmove(byte[] srcKey, byte[] dstKey, ListDirection from, ListDirection to, double timeout) {
        return ourJedis.blmove(srcKey, dstKey, from, to, timeout);
    }

    @Override
    public List<byte[]> blpop(int timeout, byte[]... keys) {
        return ourJedis.blpop(timeout, keys);
    }

    @Override
    public List<byte[]> blpop(double timeout, byte[]... keys) {
        return ourJedis.blpop(timeout, keys);
    }

    @Override
    public List<byte[]> brpop(int timeout, byte[]... keys) {
        return ourJedis.brpop(timeout, keys);
    }

    @Override
    public List<byte[]> brpop(double timeout, byte[]... keys) {
        return ourJedis.brpop(timeout, keys);
    }

    @Override
    public List<byte[]> blpop(byte[]... args) {
        return ourJedis.blpop(args);
    }

    @Override
    public List<byte[]> brpop(byte[]... args) {
        return ourJedis.brpop(args);
    }

    @Override
    public List<byte[]> bzpopmax(double timeout, byte[]... keys) {
        return ourJedis.bzpopmax(timeout, keys);
    }

    @Override
    public List<byte[]> bzpopmin(double timeout, byte[]... keys) {
        return ourJedis.bzpopmin(timeout, keys);
    }

    @Override
    public String auth(String password) {
        return ourJedis.auth(password);
    }

    @Override
    public String auth(String user, String password) {
        return ourJedis.auth(user, password);
    }

    @Override
    public Pipeline pipelined() {
        return ourJedis.pipelined();
    }

    @Override
    public Long zcount(byte[] key, double min, double max) {
        return ourJedis.zcount(key, min, max);
    }

    @Override
    public Long zcount(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zcount(key, min, max);
    }

    @Override
    public Set<byte[]> zdiff(byte[]... keys) {
        return ourJedis.zdiff(keys);
    }

    @Override
    public Set<Tuple> zdiffWithScores(byte[]... keys) {
        return ourJedis.zdiffWithScores(keys);
    }

    @Override
    public Long zdiffStore(byte[] dstkey, byte[]... keys) {
        return ourJedis.zdiffStore(dstkey, keys);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        return ourJedis.zrangeByScore(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zrangeByScore(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return ourJedis.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return ourJedis.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return ourJedis.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return ourJedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return ourJedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }


    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        return ourJedis.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return ourJedis.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return ourJedis.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return ourJedis.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return ourJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByRank(byte[] key, long start, long stop) {
        return ourJedis.zremrangeByRank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(byte[] key, double min, double max) {
        return ourJedis.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zremrangeByScore(key, min, max);
    }

    @Override
    public Set<byte[]> zunion(ZParams params, byte[]... keys) {
        return ourJedis.zunion(params, keys);
    }

    @Override
    public Set<Tuple> zunionWithScores(ZParams params, byte[]... keys) {
        return ourJedis.zunionWithScores(params, keys);
    }

    @Override
    public Long zunionstore(byte[] dstkey, byte[]... sets) {
        return ourJedis.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return ourJedis.zunionstore(dstkey, params, sets);
    }

    @Override
    public Set<byte[]> zinter(ZParams params, byte[]... keys) {
        return ourJedis.zinter(params, keys);
    }

    @Override
    public Set<Tuple> zinterWithScores(ZParams params, byte[]... keys) {
        return ourJedis.zinterWithScores(params, keys);
    }

    @Override
    public Long zinterstore(byte[] dstkey, byte[]... sets) {
        return ourJedis.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return ourJedis.zinterstore(dstkey, params, sets);
    }

    @Override
    public Long zlexcount(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zlexcount(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zrangeByLex(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return ourJedis.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
        return ourJedis.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return ourJedis.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
        return ourJedis.zremrangeByLex(key, min, max);
    }

    @Override
    public String save() {
        return ourJedis.save();
    }

    @Override
    public String bgsave() {
        return ourJedis.bgsave();
    }

    @Override
    public String bgrewriteaof() {
        return ourJedis.bgrewriteaof();
    }

    @Override
    public Long lastsave() {
        return ourJedis.lastsave();
    }

    @Override
    public String shutdown() {
        return ourJedis.shutdown();
    }

    @Override
    public String info() {
        return ourJedis.info();
    }

    @Override
    public String info(String section) {
        return ourJedis.info(section);
    }

    @Override
    public void monitor(JedisMonitor jedisMonitor) {
        ourJedis.monitor(jedisMonitor);
    }

    @Override
    public String slaveof(String host, int port) {
        return ourJedis.slaveof(host, port);
    }

    @Override
    public String slaveofNoOne() {
        return ourJedis.slaveofNoOne();
    }

    @Override
    public List<byte[]> configGet(byte[] pattern) {
        return ourJedis.configGet(pattern);
    }

    @Override
    public String configResetStat() {
        return ourJedis.configResetStat();
    }

    @Override
    public String configRewrite() {
        return ourJedis.configRewrite();
    }

    @Override
    public byte[] configSet(byte[] parameter, byte[] value) {
        return ourJedis.configSet(parameter, value);
    }

    @Override
    public Long strlen(byte[] key) {
        return ourJedis.strlen(key);
    }

    @Override
    public void sync() {
        ourJedis.sync();
    }

    @Override
    public Long lpushx(byte[] key, byte[]... string) {
        return ourJedis.lpushx(key, string);
    }

    @Override
    public Long persist(byte[] key) {
        return ourJedis.persist(key);
    }

    @Override
    public Long rpushx(byte[] key, byte[]... string) {
        return ourJedis.rpushx(key, string);
    }

    @Override
    public byte[] echo(byte[] string) {
        return ourJedis.echo(string);
    }

    @Override
    public Long linsert(byte[] key, ListPosition where, byte[] pivot, byte[] value) {
        return ourJedis.linsert(key, where, pivot, value);
    }

    @Override
    public String debug(DebugParams params) {
        return ourJedis.debug(params);
    }

    @Override
    public Client getClient() {
        return ourJedis.getClient();
    }

    @Override
    public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
        return ourJedis.brpoplpush(source, destination, timeout);
    }

    @Override
    public Boolean setbit(byte[] key, long offset, boolean value) {
        return ourJedis.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(byte[] key, long offset, byte[] value) {
        return ourJedis.setbit(key, offset, value);
    }

    @Override
    public Boolean getbit(byte[] key, long offset) {
        return ourJedis.getbit(key, offset);
    }

    @Override
    public Long bitpos(byte[] key, boolean value) {
        return ourJedis.bitpos(key, value);
    }

    @Override
    public Long bitpos(byte[] key, boolean value, BitPosParams params) {
        return ourJedis.bitpos(key, value, params);
    }

    @Override
    public Long setrange(byte[] key, long offset, byte[] value) {
        return ourJedis.setrange(key, offset, value);
    }

    @Override
    public byte[] getrange(byte[] key, long startOffset, long endOffset) {
        return ourJedis.getrange(key, startOffset, endOffset);
    }

    @Override
    public Long publish(byte[] channel, byte[] message) {
        return ourJedis.publish(channel, message);
    }

    @Override
    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        ourJedis.subscribe(jedisPubSub, channels);
    }

    @Override
    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        ourJedis.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
        return ourJedis.eval(script, keys, args);
    }

    @Override
    public Object eval(byte[] script, byte[] keyCount, byte[]... params) {
        return ourJedis.eval(script, keyCount, params);
    }

    @Override
    public Object eval(byte[] script, int keyCount, byte[]... params) {
        return ourJedis.eval(script, keyCount, params);
    }

    @Override
    public Object eval(byte[] script) {
        return ourJedis.eval(script);
    }

    @Override
    public Object evalsha(byte[] sha1) {
        return ourJedis.evalsha(sha1);
    }

    @Override
    public Object evalsha(byte[] sha1, List<byte[]> keys, List<byte[]> args) {
        return ourJedis.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(byte[] sha1, int keyCount, byte[]... params) {
        return ourJedis.evalsha(sha1, keyCount, params);
    }

    @Override
    public String scriptFlush() {
        return ourJedis.scriptFlush();
    }

    @Override
    public String scriptFlush(FlushMode flushMode) {
        return ourJedis.scriptFlush(flushMode);
    }

    @Override
    public Long scriptExists(byte[] sha1) {
        return ourJedis.scriptExists(sha1);
    }

    @Override
    public List<Long> scriptExists(byte[]... sha1) {
        return ourJedis.scriptExists(sha1);
    }

    @Override
    public byte[] scriptLoad(byte[] script) {
        return ourJedis.scriptLoad(script);
    }

    @Override
    public String scriptKill() {
        return ourJedis.scriptKill();
    }

    @Override
    public String slowlogReset() {
        return ourJedis.slowlogReset();
    }

    @Override
    public Long slowlogLen() {
        return ourJedis.slowlogLen();
    }

    @Override
    public List<Object> slowlogGetBinary() {
        return ourJedis.slowlogGetBinary();
    }

    @Override
    public List<Object> slowlogGetBinary(long entries) {
        return ourJedis.slowlogGetBinary(entries);
    }

    @Override
    public Long objectRefcount(byte[] key) {
        return ourJedis.objectRefcount(key);
    }

    @Override
    public byte[] objectEncoding(byte[] key) {
        return ourJedis.objectEncoding(key);
    }

    @Override
    public Long objectIdletime(byte[] key) {
        return ourJedis.objectIdletime(key);
    }

    @Override
    public List<byte[]> objectHelpBinary() {
        return ourJedis.objectHelpBinary();
    }

    @Override
    public Long objectFreq(byte[] key) {
        return ourJedis.objectFreq(key);
    }

    @Override
    public Long bitcount(byte[] key) {
        return ourJedis.bitcount(key);
    }

    @Override
    public Long bitcount(byte[] key, long start, long end) {
        return ourJedis.bitcount(key, start, end);
    }

    @Override
    public Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
        return ourJedis.bitop(op, destKey, srcKeys);
    }

    @Override
    public byte[] dump(byte[] key) {
        return ourJedis.dump(key);
    }

    @Override
    public String restore(byte[] key, long ttl, byte[] serializedValue) {
        return ourJedis.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(byte[] key, long ttl, byte[] serializedValue) {
        return ourJedis.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public String restore(byte[] key, long ttl, byte[] serializedValue, RestoreParams params) {
        return ourJedis.restore(key, ttl, serializedValue, params);
    }

    @Override
    public Long pexpire(byte[] key, long milliseconds) {
        return ourJedis.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
        return ourJedis.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(byte[] key) {
        return ourJedis.pttl(key);
    }

    @Override
    public String psetex(byte[] key, long milliseconds, byte[] value) {
        return ourJedis.psetex(key, milliseconds, value);
    }

    @Override
    public byte[] memoryDoctorBinary() {
        return ourJedis.memoryDoctorBinary();
    }

    @Override
    public Long memoryUsage(byte[] key) {
        return ourJedis.memoryUsage(key);
    }

    @Override
    public Long memoryUsage(byte[] key, int samples) {
        return ourJedis.memoryUsage(key, samples);
    }

    @Override
    public byte[] aclWhoAmIBinary() {
        return ourJedis.aclWhoAmIBinary();
    }

    @Override
    public byte[] aclGenPassBinary() {
        return ourJedis.aclGenPassBinary();
    }

    @Override
    public List<byte[]> aclListBinary() {
        return ourJedis.aclListBinary();
    }

    @Override
    public List<byte[]> aclUsersBinary() {
        return ourJedis.aclUsersBinary();
    }

    @Override
    public AccessControlUser aclGetUser(byte[] name) {
        return ourJedis.aclGetUser(name);
    }

    @Override
    public String aclSetUser(byte[] name) {
        return ourJedis.aclSetUser(name);
    }

    @Override
    public String aclSetUser(byte[] name, byte[]... keys) {
        return ourJedis.aclSetUser(name, keys);
    }

    @Override
    public Long aclDelUser(byte[] name) {
        return ourJedis.aclDelUser(name);
    }

    @Override
    public List<byte[]> aclCatBinary() {
        return ourJedis.aclCatBinary();
    }

    @Override
    public List<byte[]> aclCat(byte[] category) {
        return ourJedis.aclCat(category);
    }

    @Override
    public List<byte[]> aclLogBinary() {
        return ourJedis.aclLogBinary();
    }

    @Override
    public List<byte[]> aclLogBinary(int limit) {
        return ourJedis.aclLogBinary(limit);
    }

    @Override
    public byte[] aclLog(byte[] options) {
        return ourJedis.aclLog(options);
    }

    @Override
    public String clientKill(byte[] ipPort) {
        return ourJedis.clientKill(ipPort);
    }

    @Override
    public String clientKill(String ip, int port) {
        return ourJedis.clientKill(ip, port);
    }

    @Override
    public Long clientKill(ClientKillParams params) {
        return ourJedis.clientKill(params);
    }

    @Override
    public byte[] clientGetnameBinary() {
        return ourJedis.clientGetnameBinary();
    }

    @Override
    public byte[] clientListBinary() {
        return ourJedis.clientListBinary();
    }

    @Override
    public byte[] clientListBinary(long... clientIds) {
        return ourJedis.clientListBinary(clientIds);
    }

    @Override
    public byte[] clientInfoBinary() {
        return ourJedis.clientInfoBinary();
    }

    @Override
    public String clientSetname(byte[] name) {
        return ourJedis.clientSetname(name);
    }

    @Override
    public String clientPause(long timeout) {
        return ourJedis.clientPause(timeout);
    }

    @Override
    public List<String> time() {
        return ourJedis.time();
    }

    @Override
    public String migrate(String host, int port, byte[] key, int destinationDb, int timeout) {
        return ourJedis.migrate(host, port, key, destinationDb, timeout);
    }

    @Override
    public String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, byte[]... keys) {
        return ourJedis.migrate(host, port, destinationDB, timeout, params, keys);
    }

    @Override
    public Long waitReplicas(int replicas, long timeout) {
        return ourJedis.waitReplicas(replicas, timeout);
    }

    @Override
    public Long pfadd(byte[] key, byte[]... elements) {
        return ourJedis.pfadd(key, elements);
    }

    @Override
    public long pfcount(byte[] key) {
        return ourJedis.pfcount(key);
    }

    @Override
    public String pfmerge(byte[] destkey, byte[]... sourcekeys) {
        return ourJedis.pfmerge(destkey, sourcekeys);
    }

    @Override
    public Long pfcount(byte[]... keys) {
        return ourJedis.pfcount(keys);
    }

    @Override
    public ScanResult<byte[]> scan(byte[] cursor) {
        return ourJedis.scan(cursor);
    }

    @Override
    public ScanResult<byte[]> scan(byte[] cursor, ScanParams params) {
        return ourJedis.scan(cursor, params);
    }

    @Override
    public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor) {
        return ourJedis.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor, ScanParams params) {
        return ourJedis.hscan(key, cursor, params);
    }

    @Override
    public ScanResult<byte[]> sscan(byte[] key, byte[] cursor) {
        return ourJedis.sscan(key, cursor);
    }

    @Override
    public ScanResult<byte[]> sscan(byte[] key, byte[] cursor, ScanParams params) {
        return ourJedis.sscan(key, cursor, params);
    }

    @Override
    public ScanResult<Tuple> zscan(byte[] key, byte[] cursor) {
        return ourJedis.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(byte[] key, byte[] cursor, ScanParams params) {
        return ourJedis.zscan(key, cursor, params);
    }

    @Override
    public Long geoadd(byte[] key, double longitude, double latitude, byte[] member) {
        return ourJedis.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        return ourJedis.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Long geoadd(byte[] key, GeoAddParams params, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        return ourJedis.geoadd(key, params, memberCoordinateMap);
    }

    @Override
    public Double geodist(byte[] key, byte[] member1, byte[] member2) {
        return ourJedis.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(byte[] key, byte[] member1, byte[] member2, GeoUnit unit) {
        return ourJedis.geodist(key, member1, member2, unit);
    }

    @Override
    public List<byte[]> geohash(byte[] key, byte[]... members) {
        return ourJedis.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(byte[] key, byte[]... members) {
        return ourJedis.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return ourJedis.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return ourJedis.georadiusReadonly(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public Long georadiusStore(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param, GeoRadiusStoreParam storeParam) {
        return ourJedis.georadiusStore(key, longitude, latitude, radius, unit, param, storeParam);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return ourJedis.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return ourJedis.georadiusByMemberReadonly(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public Long georadiusByMemberStore(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param, GeoRadiusStoreParam storeParam) {
        return ourJedis.georadiusByMemberStore(key, member, radius, unit, param, storeParam);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return ourJedis.georadiusByMemberReadonly(key, member, radius, unit, param);
    }

    @Override
    public List<Long> bitfield(byte[] key, byte[]... arguments) {
        return ourJedis.bitfield(key, arguments);
    }

    @Override
    public List<Long> bitfieldReadonly(byte[] key, byte[]... arguments) {
        return ourJedis.bitfieldReadonly(key, arguments);
    }

    @Override
    public Long hstrlen(byte[] key, byte[] field) {
        return ourJedis.hstrlen(key, field);
    }

    @Override
    public List<byte[]> xread(int count, long block, Map<byte[], byte[]> streams) {
        return ourJedis.xread(count, block, streams);
    }

    @Override
    public List<byte[]> xread(XReadParams xReadParams, Map.Entry<byte[], byte[]>... streams) {
        return ourJedis.xread(xReadParams, streams);
    }

    @Override
    public List<byte[]> xreadGroup(byte[] groupname, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
        return ourJedis.xreadGroup(groupname, consumer, count, block, noAck, streams);
    }

    @Override
    public List<byte[]> xreadGroup(byte[] groupname, byte[] consumer, XReadGroupParams xReadGroupParams, Map.Entry<byte[], byte[]>... streams) {
        return ourJedis.xreadGroup(groupname, consumer, xReadGroupParams, streams);
    }

    @Override
    public byte[] xadd(byte[] key, byte[] id, Map<byte[], byte[]> hash, long maxLen, boolean approximateLength) {
        return ourJedis.xadd(key, id, hash, maxLen, approximateLength);
    }

    @Override
    public byte[] xadd(byte[] key, Map<byte[], byte[]> hash, XAddParams params) {
        return ourJedis.xadd(key, hash, params);
    }

    @Override
    public Long xlen(byte[] key) {
        return ourJedis.xlen(key);
    }

    @Override
    public List<byte[]> xrange(byte[] key, byte[] start, byte[] end) {
        return ourJedis.xrange(key, start, end);
    }

    @Override
    public List<byte[]> xrange(byte[] key, byte[] start, byte[] end, int count) {
        return ourJedis.xrange(key, start, end, count);
    }

    @Override
    public List<byte[]> xrevrange(byte[] key, byte[] end, byte[] start) {
        return ourJedis.xrevrange(key, end, start);
    }

    @Override
    public List<byte[]> xrevrange(byte[] key, byte[] end, byte[] start, int count) {
        return ourJedis.xrevrange(key, end, start, count);
    }

    @Override
    public Long xack(byte[] key, byte[] group, byte[]... ids) {
        return ourJedis.xack(key, group, ids);
    }

    @Override
    public String xgroupCreate(byte[] key, byte[] consumer, byte[] id, boolean makeStream) {
        return ourJedis.xgroupCreate(key, consumer, id, makeStream);
    }

    @Override
    public String xgroupSetID(byte[] key, byte[] consumer, byte[] id) {
        return ourJedis.xgroupSetID(key, consumer, id);
    }

    @Override
    public Long xgroupDestroy(byte[] key, byte[] consumer) {
        return ourJedis.xgroupDestroy(key, consumer);
    }

    @Override
    public Long xgroupDelConsumer(byte[] key, byte[] consumer, byte[] consumerName) {
        return ourJedis.xgroupDelConsumer(key, consumer, consumerName);
    }

    @Override
    public Long xdel(byte[] key, byte[]... ids) {
        return ourJedis.xdel(key, ids);
    }

    @Override
    public Long xtrim(byte[] key, long maxLen, boolean approximateLength) {
        return ourJedis.xtrim(key, maxLen, approximateLength);
    }

    @Override
    public Long xtrim(byte[] key, XTrimParams params) {
        return ourJedis.xtrim(key, params);
    }

    @Override
    public List<Object> xpending(byte[] key, byte[] groupname, byte[] start, byte[] end, int count, byte[] consumername) {
        return ourJedis.xpending(key, groupname, start, end, count, consumername);
    }

    @Override
    public Object xpending(byte[] key, byte[] groupname) {
        return ourJedis.xpending(key, groupname);
    }

    @Override
    public List<Object> xpending(byte[] key, byte[] groupname, XPendingParams params) {
        return ourJedis.xpending(key, groupname, params);
    }

    @Override
    public List<byte[]> xclaim(byte[] key, byte[] groupname, byte[] consumername, long minIdleTime, long newIdleTime, int retries, boolean force, byte[]... ids) {
        return ourJedis.xclaim(key, groupname, consumername, minIdleTime, newIdleTime, retries, force, ids);
    }

    @Override
    public List<byte[]> xclaim(byte[] key, byte[] group, byte[] consumername, long minIdleTime, XClaimParams params, byte[]... ids) {
        return ourJedis.xclaim(key, group, consumername, minIdleTime, params, ids);
    }

    @Override
    public List<byte[]> xclaimJustId(byte[] key, byte[] group, byte[] consumername, long minIdleTime, XClaimParams params, byte[]... ids) {
        return ourJedis.xclaimJustId(key, group, consumername, minIdleTime, params, ids);
    }

    @Override
    public StreamInfo xinfoStream(byte[] key) {
        return ourJedis.xinfoStream(key);
    }

    @Override
    public Object xinfoStreamBinary(byte[] key) {
        return ourJedis.xinfoStreamBinary(key);
    }

    @Override
    public List<StreamGroupInfo> xinfoGroup(byte[] key) {
        return ourJedis.xinfoGroup(key);
    }

    @Override
    public List<Object> xinfoGroupBinary(byte[] key) {
        return ourJedis.xinfoGroupBinary(key);
    }

    @Override
    public List<StreamConsumersInfo> xinfoConsumers(byte[] key, byte[] group) {
        return ourJedis.xinfoConsumers(key, group);
    }

    @Override
    public List<Object> xinfoConsumersBinary(byte[] key, byte[] group) {
        return ourJedis.xinfoConsumersBinary(key, group);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd, byte[]... args) {
        return ourJedis.sendCommand(cmd, args);
    }

    @Override
    public Object sendBlockingCommand(ProtocolCommand cmd, byte[]... args) {
        return ourJedis.sendBlockingCommand(cmd, args);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd) {
        return ourJedis.sendCommand(cmd);
    }

    @Override
    public String restore(byte[] key, int ttl, byte[] serializedValue) {
        return ourJedis.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(byte[] key, int ttl, byte[] serializedValue) {
        return ourJedis.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        return ourJedis.expire(key, seconds);
    }

    @Override
    public String setex(byte[] key, int seconds, byte[] value) {
        return ourJedis.setex(key, seconds, value);
    }

    @Override
    public List<byte[]> xrange(byte[] key, byte[] start, byte[] end, long count) {
        return ourJedis.xrange(key, start, end, count);
    }

    @Override
    public String restore(String key, int ttl, byte[] serializedValue) {
        return ourJedis.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(String key, int ttl, byte[] serializedValue) {
        return ourJedis.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public Long expire(String key, int seconds) {
        return ourJedis.expire(key, seconds);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return ourJedis.setex(key, seconds, value);
    }

    @Override
    public int hashCode() {
        return ourJedis.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return ourJedis.equals(obj);
    }
}
