//package rvr.magenative.animationUtil;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.animation.Animation;
//import android.view.animation.Transformation;
//
//public class FlipAnimation  extends Animation {
//
//    private float mFromDegreesX;
//    private float mFromDegreesY;
//    private float mToDegreesX;
//    private float mToDegreesY;
//
//    private int mPivotXType = ABSOLUTE;
//    private int mPivotYType = ABSOLUTE;
//    private float mPivotXValue = 0.0f;
//    private float mPivotYValue = 0.0f;
//
//    private float mPivotX;
//    private float mPivotY;
//
//
//
//    public FlipAnimation(Context context, AttributeSet attributeSet){
//        super(context,attributeSet);
//
//        @SuppressLint("Recycle")
//        TypedArray a = context.obtainStyledAttributes(attributeSet,
//                R.styleable.FlipAnimation);
//
//        mFromDegreesX = a.getFloat(R.styleable.FlipAnimation_FromDegreesX,0.0f);
//        mFromDegreesY = a.getFloat(R.styleable.FlipAnimation_FromDegreesY,0.0f);
//        mToDegreesX = a.getFloat(R.styleable.FlipAnimation_ToDegreesX,0.0f);
//        mToDegreesY = a.getFloat(R.styleable.FlipAnimation_ToDegreesY,0.0f);
//        mPivotX = a.getFloat(R.styleable.FlipAnimation_PivotX,0.0f);
//        mPivotY = a.getFloat(R.styleable.FlipAnimation_PivotY,0.0f);
//
//
//        initializePivotPoint();
//
//    }
//
//    public FlipAnimation(float mFromDegreesX, float mToDegreesX, float mPivotX, float mPivotY) {
//        this.mFromDegreesX = mFromDegreesX;
//        this.mToDegreesX = mToDegreesX;
//        this.mPivotX = mPivotX;
//        this.mPivotY = mPivotY;
//    }
//
//    public FlipAnimation(float mFromDegreesX, float mFromDegreesY, float mToDegreesX, float mToDegreesY, float mPivotX, float mPivotY) {
//        this.mFromDegreesX = mFromDegreesX;
//        this.mFromDegreesY = mFromDegreesY;
//        this.mToDegreesX = mToDegreesX;
//        this.mToDegreesY = mToDegreesY;
//        this.mPivotX = mPivotX;
//        this.mPivotY = mPivotY;
//    }
//
//
//    public FlipAnimation(float mFromDegreesX, float mToDegreesX) {
//        this.mFromDegreesX = mFromDegreesX;
//        this.mToDegreesX = mToDegreesX;
//    }
//
//
//
//    private void initializePivotPoint() {
//        if (mPivotXType == ABSOLUTE) {
//            mPivotX = mPivotXValue;
//        }
//        if (mPivotYType == ABSOLUTE) {
//            mPivotY = mPivotYValue;
//        }
//    }
//
//    @Override
//    public void initialize(int width, int height, int parentWidth, int parentHeight) {
//        super.initialize(width, height, parentWidth, parentHeight);
//        mPivotX = resolveSize(mPivotXType, mPivotXValue, width, parentWidth);
//        mPivotY = resolveSize(mPivotYType, mPivotYValue, height, parentHeight);
//    }
//
//    @Override
//    protected void applyTransformation(float interpolatedTime, Transformation t) {
//        super.applyTransformation(interpolatedTime, t);
//
//        if (mFromDegreesX != 0.0f){
//            float degrees = mFromDegreesX + ((mToDegreesX - mFromDegreesX) * interpolatedTime);
//            float scale = getScaleFactor();
//
//            if (mPivotX == 0.0f && mPivotY == 0.0f) {
//                t.getMatrix().setRotate(degrees);
//            } else {
//                for (int i =0; i <=scale ; i++){
//                    t.getMatrix().setSkew(mPivotX * i, mPivotY * i);
//                }
//                t.getMatrix().setRotate(degrees);
//            }
//
//        }else if (mFromDegreesY !=0.0f){
//
//            float degrees = mFromDegreesY + ((mToDegreesY - mFromDegreesY) * interpolatedTime);
//            float scale = getScaleFactor();
//
//            if (mPivotX == 0.0f && mPivotY == 0.0f) {
//                t.getMatrix().setRotate(degrees);
//            } else {
//                for (int i =0; i <=scale ; i++){
//                    t.getMatrix().setSkew(mPivotX * i, mPivotY * i);
//                }
//                t.getMatrix().setRotate(degrees);
//            }
//
//        }
//
//    }
//
//
//}
