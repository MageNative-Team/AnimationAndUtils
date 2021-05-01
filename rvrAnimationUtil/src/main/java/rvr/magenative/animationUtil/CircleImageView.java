package rvr.magenative.animationUtil;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Objects;

@SuppressLint("AppCompatCustomView")
public class CircleImageView  extends ImageView {


    Paint DEFAULT_BACKGROUND_CIRCLE_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    float DEFAULT_BORDER_CIRCLE_WIDTH = 10.0f;
    Paint sourcePaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint backgroundPaint = DEFAULT_BACKGROUND_CIRCLE_PAINT;
    Bitmap sourceBitmap;
    float defaultBorderWidth = DEFAULT_BORDER_CIRCLE_WIDTH;
    Matrix matrix = new Matrix();
    private static final String TAG = "CircleImageView";
    int src;


    public CircleImageView(Context context) {
        super(context);
        init(null);

    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        init(attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs !=null){
            //Code here For attributes provided from user
            TypedArray attribArray = getContext().obtainStyledAttributes(attrs,R.styleable.CircleImageView);


            backgroundPaint.setColor(Color.GRAY);
            backgroundPaint.setAntiAlias(true);
            backgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            backgroundPaint.setDither(true);
            defaultBorderWidth = attribArray.getDimension(R.styleable.CircleImageView_strokeWidth,DEFAULT_BORDER_CIRCLE_WIDTH);


            getViewTreeObserver().addOnGlobalLayoutListener( () -> getBitmapFromDrawable(getDrawable()));

            attribArray.recycle();
        }
        else {

            DEFAULT_BACKGROUND_CIRCLE_PAINT.setColor(Color.GRAY);
        }

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


        canvas.drawColor(Color.TRANSPARENT);

        getBitmapFromDrawable(getDrawable());

        backgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        if (getWidth() == getHeight())
        canvas.drawCircle(getWidth()/2f,getHeight()/2f,getWidth()/2f,backgroundPaint);
        if (getHeight() > getWidth())
         canvas.drawCircle(getWidth()/2f,getHeight()/2f,getWidth()/2f,backgroundPaint);
        if (getHeight() < getWidth())
        canvas.drawCircle(getWidth()/2f,getHeight()/2f,getHeight()/2f,backgroundPaint);


        if (sourceBitmap != null){
            Log.i(TAG, "onDraw: "+"bangaya");

            BitmapShader bitmapShader = new BitmapShader(sourceBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            bitmapShader.setLocalMatrix(matrix);

            sourcePaint.setShader(bitmapShader);

            if (getWidth() == getHeight())
                canvas.drawCircle(getWidth()/2f,getHeight()/2f,getWidth()/2f - defaultBorderWidth,sourcePaint);
            if (getHeight() > getWidth())
                canvas.drawCircle(getWidth()/2f,getHeight()/2f,getWidth()/2f - defaultBorderWidth,sourcePaint);
            if (getHeight() < getWidth())
                canvas.drawCircle(getWidth()/2f,getHeight()/2f,getHeight()/2f - defaultBorderWidth,sourcePaint);

            getBitmapFromDrawable(getDrawable());

        }
        else {
            getBitmapFromDrawable(getDrawable());
            Log.i(TAG, "onDraw: "+"ghanta");
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i(TAG, "onSizeChanged: "+w +"  "+h);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
       getBitmapFromDrawable(getDrawable());
       postInvalidate();

    }



    @Override
    public void setBackgroundResource(int resid) {
        super.setImageResource(resid);
        getBitmapFromDrawable(getDrawable());
        postInvalidate();
    }



    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        getBitmapFromDrawable(drawable);
        postInvalidate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        sourceBitmap = bm;
        postInvalidate();
    }


    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);

      File f = new File(Objects.requireNonNull(getRealPathFromURI(getContext(), uri)));
      Drawable d = Drawable.createFromPath(f.getAbsolutePath());
      getBitmapFromDrawable(d);

    }


    private Bitmap getBitmapFromDrawable(Drawable drawable) {

        try {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            else if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            }
            getViewTreeObserver().addOnGlobalLayoutListener(() -> getResizedBitmap(bitmap));

            postInvalidate();

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Bitmap getResizedBitmap(Bitmap bitmap){

        matrix = new Matrix();
        RectF destination =  calculateBounds();
        RectF source =new RectF(0 , 0,bitmap.getWidth(),bitmap.getHeight());
        matrix.setRectToRect(source,destination, Matrix.ScaleToFit.CENTER);
        Bitmap result = Bitmap.createBitmap(bitmap);
        sourceBitmap = result;
        postInvalidate();
        return  result;
    }



    private RectF calculateBounds() {

        int availableWidth  = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int sideLength = Math.min(availableWidth, availableHeight);
        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;
        return new RectF(left, top, left + sideLength, top + sideLength);
    }

    public static String getRealPathFromURI(final Context context, final Uri uri) {

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
           if (DocumentsContract.isDocumentUri(context, uri)) {
               if (isExternalStorageDocument(uri)) {
                   final String docId = DocumentsContract.getDocumentId(uri);
                   final String[] split = docId.split(":");
                   final String type = split[0];

                   if ("primary".equalsIgnoreCase(type)) {
                       return Environment.getStorageDirectory().getAbsolutePath() + "/" + split[1];
                   }
               }
               else if (isDownloadsDocument(uri)) {

                   final String id = DocumentsContract.getDocumentId(uri);
                   final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));

                   return getDataColumn(context, contentUri, null, null);
               }
               else if (isMediaDocument(uri)) {
                   final String docId;
                   docId = DocumentsContract.getDocumentId(uri);
                   final String[] split = docId.split(":");
                   final String type = split[0];

                   Uri contentUri = null;
                   if ("image".equals(type)) {
                       contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                   } else if ("video".equals(type)) {
                       contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                   } else if ("audio".equals(type)) {
                       contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                   }

                   final String selection = "_id=?";
                   final String[] selectionArgs = new String[]{
                           split[1]
                   };

                   return getDataColumn(context, contentUri, selection, selectionArgs);
               }
           }
       }else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


}
