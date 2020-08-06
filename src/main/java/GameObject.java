import javax.swing.*;
import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image[] image;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width=image[0].getWidth(null);
        height=image[0].getHeight(null);
    }

    // 抽象類別一定要有一個抽象方法, 用 draw 當作抽象方法, 使用者必須實作 draw()
    public abstract void draw(Graphics g) ;
}
