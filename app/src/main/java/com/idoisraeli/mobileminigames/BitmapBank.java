package com.idoisraeli.mobileminigames;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap bm;
    private final int resourceId;

    public BitmapBank(Resources resources, int id) {
        resourceId = id; // R.drawable.<id of the resource>
        bm = BitmapFactory.decodeResource(resources, resourceId);
    }

    public Bitmap getBitmap() {
        return bm;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getWidth() {
        return bm.getWidth();
    }

    public int getHeight() {
        return bm.getHeight();
    }
}
