package cliq.connie;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;



public class CircleImage extends ProfilePage {

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.circle_layout);
    ImageView img1 = (ImageView) findViewById(R.id.imageView1);
    Bitmap bm = BitmapFactory.decodeResource(getResources(),
            R.drawable.hair_four);
    Bitmap resized = Bitmap.createScaledBitmap(bm, 100, 100, true);
    Bitmap conv_bm = getRoundedRectBitmap(resized, 100);
    img1.setImageBitmap(conv_bm);
}

public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
    Bitmap result = null;
    try {
        result = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        int color = 0xfff5deb3;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, 200, 200);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(50, 50, 50, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

    } catch (NullPointerException e) {
    } catch (OutOfMemoryError o) {
    }
    return result;
}

 }