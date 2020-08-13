import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, true, image);
    }

    public void ai() {
        Random random = new Random();
        // 1/20 機率 移動
        if (random.nextInt(20) ==1) {
            // 先清空 dirs
            dirs = new boolean[4];

            // 1/2 機率停止
            if (random.nextInt(2)==1){
                return;
            }
            getNewDirection();
        }
        //開火 1/50 機率
        if (random.nextInt(50)==1){
            fire();
        }

    }

    public void getNewDirection(){
        Random random = new Random();

        // 依亂數取得0-7 數字 (可代表方向)
        // 0-Up 1-Down 2-Left 3-Right
        int dir = random.nextInt(Direction.values().length);

        // <= 3 表示單一方向鍵
        if (dir <=Direction.RIGHT.ordinal()){
            dirs[dir] = true;
        }else {
            //複向鍵
            if (dir == Direction.UP_LEFT.ordinal()){
                dirs[0] = true;
                dirs[2] = true;
            }else if (dir == Direction.UP_RIGHT.ordinal()){
                dirs[0] = true;
                dirs[3] = true;
            }else if (dir == Direction.DOWN_LEFT.ordinal()){
                dirs[1] = true;
                dirs[2] = true;
            }else if (dir == Direction.DOWN_RIGHT.ordinal()){
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }

    @Override
    public boolean collision() {
        if (super.collision()) {
            getNewDirection();
            return true;
        }
        return false;
    }
}
