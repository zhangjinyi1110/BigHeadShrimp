package com.example.zjy.bigheadshrimp.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 仕囵弹 on 2018/12/1.
 * PicHelper
 */

public class PicHelper {

    private final static String TAG = PicHelper.class.getSimpleName();
    private static LruCache<String, Bitmap> lruCache;
    private static DiskLruCache diskLruCache;
    private Context context;
    private OnErrorListener errorListener;
    private int errorImage = -1;
    private int prepareImage = -1;
    private Object url;

    private PicHelper() {
        if (lruCache == null) {
            lruCache = new LruCache<String, Bitmap>(((int) Runtime.getRuntime().freeMemory() / 1024)) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight() / 1024;
                }
            };
        }
        if (diskLruCache == null) {
            try {
                File cachePath = getDiskLruCachePath();
                if (!cachePath.exists()) {
                    cachePath.mkdirs();
                }
                diskLruCache = DiskLruCache.open(cachePath, getAppVersion(), 1, 10 * 1024 * 1024);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "PicHelper: " + e.toString());
            }
        }
    }

    private File getDiskLruCachePath() {
        String cachePath;
        if ((Environment.MEDIA_MOUNTED.startsWith(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
                && context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath);
    }

    public PicHelper with(Context context) {
        this.context = context;
        return new PicHelper();
    }

    public PicHelper load(Object url) {
        this.url = url;
        return this;
    }

    public PicHelper error(@DrawableRes int res) {
        this.errorImage = res;
        return this;
    }

    public PicHelper prepare(@DrawableRes int res) {
        this.prepareImage = res;
        return this;
    }

    public PicHelper into(final ImageView imageView) {
        if (imageView == null) {
            throw new NullPointerException("The imageView is null");
        }
        if (url == null) {
            throw new NullPointerException("The url is null");
        }
        if (prepareImage != -1) {
            imageView.setImageResource(prepareImage);
        }
        setImageBitmap(imageView);
        return this;
    }

    private void setImageBitmap(ImageView imageView) {
        Bitmap bitmap = null;
        try {
            bitmap = getCacheBitmap();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "setImageBitmap: " + e.toString());
            if (errorListener != null) {
                errorListener.onErrorListener(imageView, e);
            }
            if (errorImage != -1) {
                imageView.setImageResource(errorImage);
            }
            return;
        }
        if (bitmap == null) {
//            getNetBitmap(imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap getCacheBitmap() throws Exception {
        Bitmap bitmap;
        bitmap = getLruCacheBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = getDiskLruCacheBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }

    private void getNetBitmap(ImageView imageView) {

    }

    private Bitmap getDiskLruCacheBitmap() {
        if (diskLruCache == null) {
            return null;
        }
        String key = null;
        String path;
        if (url instanceof String) {
            path = (String) url;
            key = MD5String(path);
        } else if (url instanceof Uri) {
            path = getUri((Uri) url);
            key = MD5String(path);
        }
        if (key == null)
            return null;
        else {
            try {
                DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                if (snapshot != null) {
                    InputStream stream = snapshot.getInputStream(0);
                    return BitmapFactory.decodeStream(stream);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "getDiskLruCacheBitmap: " + e.toString());
//                return null;
            }
            return null;
        }
    }

    private Bitmap getLruCacheBitmap() {
        String key = null;
        if (url instanceof String) {
            key = MD5String((String) url);
        } else if (url instanceof Integer) {
            key = MD5String(String.valueOf(url));
            lruCache.put(key, BitmapFactory.decodeResource(context.getResources(), (Integer) url));
        } else if (url instanceof Uri) {
            String uri = getUri((Uri) url);
            key = MD5String(uri);
        }
        if (key == null)
            return null;
        else
            return lruCache.get(key);
    }

    public PicHelper setOnErrorListener(OnErrorListener listener) {
        this.errorListener = listener;
        return this;
    }

    private String getUri(Uri uri) {
        return null;
    }

    private String MD5String(String key) {
        return key;
    }

    private int getAppVersion() {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "getAppVersion: " + e.toString());
        }
        return 1;
    }

    public interface OnErrorListener {
        void onErrorListener(ImageView imageView, Throwable throwable);
    }

}
