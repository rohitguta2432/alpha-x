package io.rammila.api.config;


import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheLogger implements CacheEventListener<Object,Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.info("Cache event{}, key= {}, old value = {}, new value={}",cacheEvent.getType(),cacheEvent.getKey(),
                cacheEvent.getOldValue(),cacheEvent.getNewValue());
    }
}
