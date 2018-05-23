package mizuki.uchiyama.kit.pd;

/**
 * Created by uchiyan on 2017/10/17.
 */
import android.graphics.Bitmap;/*https://techacademy.jp/magazine/4771*/
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Droid {
    private static final float GRAVITY=0.8f;
    private static final float WEIGHT=GRAVITY*60;

    private static final int COLLISION_MARGIN_LEFT=35;
    private static final int COLLISION_MARGIN_RIGHT=15;

    private final  Paint paint=new Paint();

    private Bitmap bitmap;

    final Rect rect;
    private final Rect drawRect=new Rect();

    public interface Callback{
        public int getDistanceFromGround(Droid droid);
    }
    public interface Callback2{
        public int getDistanceFromGround(Droid droid);
    }
    private final Callback callback;
    private final Callback2 callback2;

    private float acceleration = 0;

    public Droid(Bitmap bitmap,int left,int top,Callback callback,Callback2 callback2){
        int rectLeft =left + COLLISION_MARGIN_LEFT;
        int rectReght=left+bitmap.getWidth()-COLLISION_MARGIN_RIGHT;
        this.rect=new Rect(rectLeft,top,rectReght,top+bitmap.getHeight());
        this.bitmap=bitmap;
        this.callback=callback;
        this.callback2=callback2;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    public void jump(float time){
        acceleration =time*WEIGHT;//加速度設定
    }


    public void move(){
        acceleration-=GRAVITY;
        int distanceFromGround=callback.getDistanceFromGround(this);
        if(acceleration<0&&acceleration<- distanceFromGround){
            acceleration=-distanceFromGround;
        }
        rect.offset(0,-Math.round(acceleration));
    }

    public void shutdown(){
        acceleration=0;
    }
}
