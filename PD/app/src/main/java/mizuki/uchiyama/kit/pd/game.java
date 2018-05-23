package mizuki.uchiyama.kit.pd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class game extends SurfaceView implements Droid.Callback,
        SurfaceHolder.Callback,Droid.Callback2 {
    private SoundPool mSoundPool;
    private int[] mSoundId = new int[0];

    private static final int START_GROUND_HEIGHT = 50;
    private static final int GROUND_MOVE_TO_LEFT = 10;

    private static final int MAX_TOUCH_TIME = 500; // msec
    private static final long FPS = 60;

    private static final int ADD_GROUND_COUNT = 5;

    private static final int GROUND_WIDTH = 550;//340
    private static final int GROUND_BLOCK_HEIGHT = 100;

    private static final float POWER_GAUGE_HEIGHT = 30;
    private static final Paint PAINT_POWER_GAUGE = new Paint();
    /*protected void onCreate(Bundle savedInstanceState){
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSoundId[0] = mSoundPool.load(getApplicationContext(), R.raw.jump02, 1);
    }*/

    static {
        PAINT_POWER_GAUGE.setColor(Color.RED);
    }
    //private Cloud cloud = new Cloud();
    private Bitmap cloud_img;
    private Droid droid;
    private final List<Ground> groundList = new ArrayList<Ground>();
    private final List<Cloud> cloudList = new ArrayList<Cloud>();

    private long touchDownStartTime;

    private DrawThread drawThread;

    private Ground lastGround;
    private Cloud cloud;

    private final Random rand = new Random();

    public interface Callback {
        public void onGameOver();
    }

    public interface Callback2 {
        public void onGameGole();
    }

    private Callback callback;
    private Callback2 callback2;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    public void setCallback2(Callback2 callback2){
        this.callback2=callback2;
    }

    private final Handler handler;
    private final Handler handler2;
    private boolean isGameOver;
    private boolean isGameGole;

    public game(Context context) {
        super(context);
        handler = new Handler();
        handler2 = new Handler();
        getHolder().addCallback(this);
        //Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
    }

    public void drawGame(Canvas canvas,Canvas canvas2) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Resources r = getResources();
        canvas.drawColor(Color.argb(255, 0, 150, 200));

        if (droid == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notti2);//bitmapに画像読み込み
            Bitmap cloud_img = BitmapFactory.decodeResource(r, R.drawable.cloud);
            droid = new Droid(bitmap, 0, 0, this,this);
            //cloud.cloudview(canvas);
            // 開始時に表示される地面
            lastGround = new Ground(0, height - START_GROUND_HEIGHT, width, height);
            cloud=new Cloud(0,60,width,height,cloud_img);
            //cloudList = new Cloud(cloud_img,0,0);
            //cloud.add(cloud);
            groundList.add(lastGround);
            cloudList.add(cloud);
        }

        if (lastGround.isShown(width, height)) {
            for (int i = 0; i < ADD_GROUND_COUNT; i++) {
                int left = lastGround.rect.right;
                //if (i % 2 == 0) {
                int groundHeight = rand.nextInt(height / GROUND_BLOCK_HEIGHT) * GROUND_BLOCK_HEIGHT / 2 + START_GROUND_HEIGHT;
                lastGround = new Ground(left, height - groundHeight, left + GROUND_WIDTH, height);
                cloud = new Cloud(left,65,left+GROUND_WIDTH,height,cloud_img);
                //Cloud = new Cloud();
                //} else {
                //lastGround = new Blank(left, height - 1, left + GROUND_WIDTH, height);
                //}
                groundList.add(lastGround);
                cloudList.add(cloud);
                //cloudList.add(cloud);

            }
        }

        for (int i = 0; i < groundList.size(); i++) {
            Ground ground = groundList.get(i);
            Cloud cloud=cloudList.get(i);
            if (ground.isAvailable()) {
                ground.move(GROUND_MOVE_TO_LEFT);
                cloud.move(GROUND_MOVE_TO_LEFT);
                if (ground.isShown(width, height)) {
                    ground.draw(canvas);
                    //cloud.draw2(canvas2);
                }
            else if(i>=10){
                    gamegole();
                }
            } else {
                groundList.remove(ground);
                i++;
            }
        }



        if (touchDownStartTime > 0) {
            float elapsedTime = System.currentTimeMillis() - touchDownStartTime;
            canvas.drawRect(0, 0, width * (elapsedTime / MAX_TOUCH_TIME), POWER_GAUGE_HEIGHT,
                    PAINT_POWER_GAUGE);
        }
        //cloud.cloudview(canvas);
        droid.move();

        droid.draw(canvas);

    }

    @Override
    public int getDistanceFromGround(Droid droid) {
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < groundList.size(); i++) {
            Ground ground = groundList.get(i);
            Cloud cloud=cloudList.get(i);
            if (!ground.isShown(width, height)) {
                continue;
            }

            boolean horizontal = !(droid.rect.left >= ground.rect.right || droid.rect.right <= ground.rect.left);
            if (horizontal) {
                /*if (!ground.isSolid()) {
                    return Integer.MAX_VALUE;
                }*/
                int distanceFromGround = ground.rect.top - droid.rect.bottom + 5;//判定この辺り
                if (distanceFromGround < 0) {
                    gameOver();
                    return Integer.MAX_VALUE;
                }
                return distanceFromGround;
            }
        }

        return Integer.MAX_VALUE;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownStartTime = System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                jumpDroid();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void jumpDroid() {
        float time = System.currentTimeMillis() - touchDownStartTime;
        touchDownStartTime = 0;

        if (getDistanceFromGround(droid) != 0) {
            return;
        }

        if (time > MAX_TOUCH_TIME) {
            time = MAX_TOUCH_TIME;
        }

        droid.jump(time / MAX_TOUCH_TIME);
    }


    private void gameOver() {

        if (isGameOver) {
            return;
        }

        isGameOver = true;

        droid.shutdown();

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGameOver();
            }
        });
    }

    private void gamegole(){

        if (isGameGole) {
            return;
        }
        isGameGole = true;

        droid.shutdown();

        handler2.post(new Runnable() {
            @Override
            public void run() {
                callback2.onGameGole();
            }
        });
        droid.shutdown();
    }

    private class DrawThread extends Thread {
        boolean isFinished;

        @Override
        public void run() {
            SurfaceHolder holder = getHolder();
            SurfaceHolder holder2= getHolder();
            while (!isFinished) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    drawGame(canvas,null);
                    holder.unlockCanvasAndPost(canvas);
                }
                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDrawThread();
    }


    public void startDrawThread() {
        stopDrawThread();

        drawThread = new DrawThread();
        drawThread.start();
    }

    public boolean stopDrawThread() {
        if (drawThread == null) {
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }

    /*class Cloud {
        ArrayList<PointF> cloudXY = new ArrayList<PointF>();

        public Cloud() {
            float X = 50;
            float Y = 50;
            for (int i = 0; i < 5; i++) {
                cloudXY.add(new PointF(X, Y));
                X += 300;
                Y = nextY(Y);
            }
        }

       public void cloudview(Canvas canvas) {
           for (int i = 0; i < cloudXY.size(); i++) {
               canvas.drawBitmap(cloud_img, cloudXY.get(i).x, cloudXY.get(i).y, null);
               if (cloudXY.get(i).x < -219) {
                   cloudXY.add(new PointF(cloudXY.get(cloudXY.size() - 1).x + 300,
                           nextY(cloudXY.get(cloudXY.size() - 1).y)));
                   cloudXY.remove(i);
                   i--;
               }
           }
       }

        private float nextY(float Y) {
            if (Y == 50) return 150;
            if (Y == 150) return 100;
            if (Y == 100) return 50;
            return 0;
        }

    }*/
}
