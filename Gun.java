public class Gun{
    int x;
    int y;
    int dmg;
    int r;
    int l = 12;
    int w = 5;
    Bullet [] bullets;
    Gun(int damage, int range, int mag){
        dmg = damage;
        r = range;
        bullets = new Bullet[mag];
    }
}
