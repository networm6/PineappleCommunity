package com.cyanmango.app.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BitmapUtils
{
	public static Bitmap doBlur(Bitmap sentBitmap, float radius) {
		int ty=5;
        if(sentBitmap==null) return null;
        if (radius <= 0 || radius > 25) radius = 25f;//范围在1-25之间
		Bitmap bitmap = Bitmap.createScaledBitmap(sentBitmap, sentBitmap.getWidth()/ty,sentBitmap.getHeight()/ty,false);//先缩放图片，增加模糊速度
		final RenderScript rs = RenderScript.create(UtilsControl.getCon());
		final Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,Allocation.USAGE_SCRIPT);
		final Allocation output = Allocation.createTyped(rs, input.getType());
		final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
		script.setRadius(radius);
		script.setInput(input);
		script.forEach(output);
		output.copyTo(bitmap);
		rs.destroy();
		return bitmap;
    }
	public static Bitmap bimapRound(Bitmap mBitmap,float index){
        Bitmap bitmap =//mBitmap;
			Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        RectF rectf = new RectF(rect);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectf, index, index, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, rect, rect, paint);
        return bitmap;
    }
}
