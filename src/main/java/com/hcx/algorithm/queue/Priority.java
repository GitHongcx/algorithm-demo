package com.hcx.algorithm.queue;

/**
 * @Title: Priority.java
 * @Package com.hcx.algorithm.queue
 * @Description: 优先级接口
 * @Author: hongcaixia
 * @Date: 2025/1/12 17:32
 * @Version V1.0
 */
public interface Priority {

    /**
     * 返回对象的优先级（数字越大，优先级越高）
     * @return
     */
    int getPriority();
}
