package com.tz.roleassigner;

/**
 * @author Timofey
 * @since 31.07.2021
 */
public interface MessageListener <T>{
    void listen (T message);
}
