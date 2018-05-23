package mizuki.uchiyama.kit.pd;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by uchiyan on 2017/11/14.
 */

public class Cloud {
    private final Paint paint=new Paint();
    private Bitmap cloud_img;
    final Rect rect;

    public Cloud(int left,int top,int right,int bottom,Bitmap bitmap){
        //rect =new Rect(left,top,right,bottom);
        this.rect=new Rect(left,top,right,bottom);
        this.cloud_img=bitmap;
    }

    public void draw2(Canvas canvas2){
        canvas2.drawBitmap(cloud_img, rect.left, rect.top, paint);
    }

    public void move(int moveToLeft){
        rect.offset(-moveToLeft,0);
    }
    public boolean isShown(int width,int height){
        return rect.intersects(0,70,width,height);
    }
    public boolean isAvailable(){
        return rect.right>0;
    }
}
