package com.rnthumbhash;

import androidx.annotation.NonNull;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

@ReactModule(name = ThumbHashModule.NAME)
public class ThumbHashModule extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ThumbHashModule";

    public ThumbHashModule(ReactApplicationContext context) {
      super(context);
    }

    @Override
    public String getName() {
      return NAME;
    }

    @ReactMethod
    public void decodeThumbHash(String base64ThumbHash, Promise promise) {
      try {
        byte[] hash = Base64.decode(base64ThumbHash, Base64.DEFAULT);
        ThumbHash.Image image = ThumbHash.thumbHashToRGBA(hash);
        byte[] rgba = image.rgba;
        // rgba 转argb
        int pixelCount = image.width * image.height;
        int[] argb = new int[pixelCount];
        for (int i = 0; i < pixelCount; i++) {
          int r = rgba[i * 4] & 0xFF;
          int g = rgba[i * 4 + 1] & 0xFF;
          int b = rgba[i * 4 + 2] & 0xFF;
          int a = rgba[i * 4 + 3] & 0xFF;
          argb[i] = (a << 24) | (r << 16) | (g << 8) | b;
        }

        // 用 rgba 数组和宽高创建 Bitmap
        Bitmap bitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(argb, 0, image.width, 0, 0, image.width, image.height);
        // 转成 PNG byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] pngBytes = baos.toByteArray();
        // 转 base64 返回
        String encodedPng = Base64.encodeToString(pngBytes, Base64.NO_WRAP);
        promise.resolve(encodedPng);
      } catch (Exception e) {
        Log.e(NAME, "decodeThumbHash failed", e);
        promise.reject("DECODE_ERROR", e);
      }
    }

    @ReactMethod
    public void encodeThumbHash(String base64Image, Promise promise) {
      try {
        // base64 解码为 byte[] 图片数据
        byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
        // 解码为 Bitmap（支持 PNG / JPG）
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        if (bitmap == null) {
          promise.reject("INVALID_IMAGE", "Unable to decode image");
          return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 将 Bitmap 转换为 RGBA byte[]
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        byte[] rgba = new byte[width * height * 4];
        for (int i = 0; i < pixels.length; i++) {
          int c = pixels[i];
          int j = i * 4;
          rgba[j] = (byte) ((c >> 16) & 0xFF); // R
          rgba[j + 1] = (byte) ((c >> 8) & 0xFF); // G
          rgba[j + 2] = (byte) (c & 0xFF); // B
          rgba[j + 3] = (byte) ((c >> 24) & 0xFF); // A
        }
        // 使用 ThumbHash 编码 RGBA 数据
        byte[] hash = ThumbHash.rgbaToThumbHash(width, height, rgba);
        String encoded = Base64.encodeToString(hash, Base64.NO_WRAP);
        promise.resolve(encoded);
      } catch (Exception e) {
        promise.reject("ENCODE_ERROR", e);
      }
    }
}
