package com.idoisraeli.mobileminigames;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapService {

//    Bitmap bm;
//    private int width;
//    private int height;
//    private final int resourceId;

    public static Bitmap getBitmapFromId(Resources resources, int id) {
        return BitmapFactory.decodeResource(resources, id);
    }

    public static Bitmap getBitmapFromIdScaledToHeight(Resources resources,
                                                       int id,
                                                       int heightToScaleTo) {
        return scaledBitmapToHeight(
                BitmapFactory.decodeResource(resources, id),
                heightToScaleTo
        );
    }

//    public BitmapService(Resources resources, int id) {
//        resourceId = id; // R.drawable.<id of the resource>
//        bm = BitmapFactory.decodeResource(resources, resourceId);
//        width = bm.getWidth();
//        height = bm.getHeight();
//    }
//
//    public BitmapService(Resources resources, int id, int heightToScaleTo) {
//        resourceId = id; // R.drawable.<id of the resource>
//        bm = scaledBitmapToHeight(
//                BitmapFactory.decodeResource(resources, resourceId),
//                heightToScaleTo
//        );
//        width = bm.getWidth();
//        height = bm.getHeight();
//    }

//    public Bitmap getBitmap() {
//        return bm;
//    }
//
//    public int getResourceId() {
//        return resourceId;
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }

    public static Bitmap scaledBitmapToHeight(Bitmap bitmap, int heightToScaleTo) {
        float widthHeightRatio = bitmap.getWidth() / bitmap.getHeight();
        int scaledWidth = (int) widthHeightRatio * heightToScaleTo;
        return Bitmap.createScaledBitmap(bitmap, scaledWidth, heightToScaleTo, false);
    }
}