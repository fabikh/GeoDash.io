public class Bullet{
    int direction;
    int x;
    int y;
    int dmg;
    boolean fired = false;
    Bullet(int dir, int xPos, int yPos, int damage){
        direction = dir;
        x = xPos;
        y = yPos;
        dmg = damage;
    }
}
