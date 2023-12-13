package com.example.aircraftwar2024.aircraft;



import com.example.aircraftwar2024.activity.GameActivity;
import com.example.aircraftwar2024.supply.AbstractFlyingSupply;
import com.example.aircraftwar2024.supply.BombObserver;

import java.util.List;


/**
 * 敌机父类
 * 敌机：BOSS, ELITE, MOB
 *
 * @author hitsz
 */
public abstract class AbstractEnemyAircraft extends AbstractAircraft implements BombObserver {

    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

    }

    /**
     * 获得敌机分数，击毁敌机时，调用该方法获得分数。
     * @return 敌机的分数
     */
    public abstract int score();

    /**
     * 敌机坠毁时以一定概率掉落道具
     * @return 道具
     */
    public abstract List<AbstractFlyingSupply> generateSupplies();

    /**
     * 敌机向下飞行出界后，标记无效
     */
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= GameActivity.screenHeight) {
            vanish();
        }
    }

    /**
     * 敌机为 Bomb 观察者
     * 除BOSS机外，该方法被调用后飞机标记无效（被摧毁）
     * BOSS机不受 bomb 影响，故应重写该方法为空实现
     */
    @Override
    public void bomb() {
        vanish();
    }

}
