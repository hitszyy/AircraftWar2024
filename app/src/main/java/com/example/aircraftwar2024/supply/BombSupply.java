package com.example.aircraftwar2024.supply;


import java.util.LinkedList;
import java.util.List;

/**
 * 炸弹道具，自动触发
 * <p>
 * 使用效果：清除界面上除BOSS机外的所有敌机（包括子弹）
 * <p>
 * 【观察者模式】
 *
 * @author hitsz
 */
public class BombSupply extends AbstractFlyingSupply {

    private List<BombObserver> observers;

    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        observers = new LinkedList<>();
    }

    @Override
    public void activate() {
        notifyAllBomb();
        System.out.println("BombSupply active");
    }


    /**
     * 注册观察者
     *
     * @param bombObservers 被记录的观察者列表
     */
    public void attachAll(List<? extends BombObserver> bombObservers) {
        observers.addAll(bombObservers);
    }

    /**
     * 通知所有观察者，响应 bomb
     */
    public void notifyAllBomb() {
        observers.forEach(BombObserver::bomb);
        observers.clear();
    }

}
