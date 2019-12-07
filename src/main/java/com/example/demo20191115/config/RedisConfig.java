package com.example.demo20191115.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 配置类
 */
public class RedisConfig
{
    /**
     * 注入Redis data
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存入缓存
     * @param key  key值
     * @param object  values 值
     * @return
     */
    public boolean set(String key, Object object)
    {
        boolean result=true;
        try {
            redisTemplate.opsForValue().set(key,object);
            result = true;
        } catch (Exception e)
        {
            e.printStackTrace();
              result=false;
        }
        return result;

    }


    /**
     * 读取缓存
     * @param key  Redis对应的缓存key值
     * @return  返回对应的缓存 value 值
     * 使用final方法的原因主要有两个：
     *
     * 　　(1) 把方法锁定，以防止继承类对其进行更改。
     *
     * 　　(2) 效率，在早期的java版本中，会将final方法转为内嵌调用。但若方法过于庞大，可能在性能上不会有多大提升。因此在最近版本中，不需要final方法进行这些优化了。
     *
     * final方法意味着“最后的、最终的”含义，即此方法不能被重写
     */
    public Object get(final String key) {
        Object result = null;
        try{
            result= (Object)redisTemplate.opsForValue().get(key);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}
