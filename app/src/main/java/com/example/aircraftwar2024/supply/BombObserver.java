package com.example.aircraftwar2024.supply;

/**
 * 炸弹观察者接口
 * <p>
 * 【观察者模式】
 * <p>
 * 当前策略下，应由敌机与所有子弹实现该接口
 *
 * @author hitsz
 */
public interface BombObserver {

    /**
     * BombObserver 需要实现 bomb 方法
     * 以完成对 BombSupply 的响应。
     */
    public abstract void bomb();
}


