package com.bon.customview.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.bon.logger.Logger;

/**
 * Created by Dang on 9/14/2015.
 */
@SuppressLint("AppCompatCustomView")
public class ExtImageViewZoom extends ImageView {
    private static final String TAG = ExtImageViewZoom.class.getSimpleName();

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private static final int CLICK = 3;

    private Matrix matrix = new Matrix();
    private int mode = NONE;

    // point
    private PointF last = new PointF();
    private PointF start = new PointF();

    // scale
    private float minScale = 1f;
    private float maxScale = 4f;
    private float[] m;

    private float redundantXSpace, redundantYSpace;
    private float width, height;
    private float saveScale = 1f;
    private float right, bottom, origWidth, origHeight, bmWidth, bmHeight;

    // gesture detector
    private ScaleGestureDetector mScaleDetector;

    @SuppressLint("ClickableViewAccessibility")
    public ExtImageViewZoom(Context context, AttributeSet attr) {
        super(context, attr);
        super.setClickable(true);

        try {
            mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            matrix.setTranslate(1f, 1f);
            m = new float[9];
            setImageMatrix(matrix);
            setScaleType(ScaleType.MATRIX);

            setOnTouchListener((v, event) -> {
                try {
                    mScaleDetector.onTouchEvent(event);
                    matrix.getValues(m);
                    float x = m[Matrix.MTRANS_X];
                    float y = m[Matrix.MTRANS_Y];
                    PointF curr = new PointF(event.getX(), event.getY());

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            last.set(event.getX(), event.getY());
                            start.set(last);
                            mode = DRAG;
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            last.set(event.getX(), event.getY());
                            start.set(last);
                            mode = ZOOM;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (mode == ZOOM || (mode == DRAG && saveScale > minScale)) {
                                float deltaX = curr.x - last.x;
                                float deltaY = curr.y - last.y;
                                float scaleWidth = Math.round(origWidth * saveScale);
                                float scaleHeight = Math.round(origHeight * saveScale);

                                if (scaleWidth < width) {
                                    deltaX = 0;
                                    if (y + deltaY > 0) {
                                        deltaY = -y;
                                    } else if (y + deltaY < -bottom) {
                                        deltaY = -(y + bottom);
                                    }
                                } else if (scaleHeight < height) {
                                    deltaY = 0;
                                    if (x + deltaX > 0) {
                                        deltaX = -x;
                                    } else if (x + deltaX < -right) {
                                        deltaX = -(x + right);
                                    }
                                } else {
                                    if (x + deltaX > 0) {
                                        deltaX = -x;
                                    } else if (x + deltaX < -right) {
                                        deltaX = -(x + right);
                                    }

                                    if (y + deltaY > 0) {
                                        deltaY = -y;
                                    } else if (y + deltaY < -bottom) {
                                        deltaY = -(y + bottom);
                                    }
                                }

                                matrix.postTranslate(deltaX, deltaY);
                                last.set(curr.x, curr.y);
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            mode = NONE;
                            int xDiff = (int) Math.abs(curr.x - start.x);
                            int yDiff = (int) Math.abs(curr.y - start.y);
                            if (xDiff < CLICK && yDiff < CLICK) {
                                performClick();
                            }
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            mode = NONE;
                            break;
                    }

                    setImageMatrix(matrix);
                    invalidate();
                } catch (Exception ex) {
                    Logger.e(TAG, ex);
                }

                return true;
            });
        } catch (Exception ex) {
            Logger.e(TAG, ex);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        try {
            bmWidth = bm.getWidth();
            bmHeight = bm.getHeight();
        } catch (Exception ex) {
            Logger.e(TAG, ex);
        }
    }

    public void setMaxZoom(float x) {
        maxScale = x;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        try {
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = MeasureSpec.getSize(heightMeasureSpec);

            // Fit to screen.
            float scale;
            float scaleX = width / bmWidth;
            float scaleY = height / bmHeight;

            scale = Math.min(scaleX, scaleY);
            matrix.setScale(scale, scale);
            setImageMatrix(matrix);
            saveScale = 1f;

            // Center the image
            redundantYSpace = height - (scale * bmHeight);
            redundantXSpace = width - (scale * bmWidth);
            redundantYSpace /= 2;
            redundantXSpace /= 2;

            matrix.postTranslate(redundantXSpace, redundantYSpace);

            origWidth = width - 2 * redundantXSpace;
            origHeight = height - 2 * redundantYSpace;
            right = width * saveScale - width - (2 * redundantXSpace * saveScale);
            bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

            setImageMatrix(matrix);
        } catch (Exception ex) {
            Logger.e(TAG, ex);
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mode = ZOOM;
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            try {
                float mScaleFactor = detector.getScaleFactor();
                float origScale = saveScale;
                saveScale *= mScaleFactor;

                if (saveScale > maxScale) {
                    saveScale = maxScale;
                    mScaleFactor = maxScale / origScale;
                } else if (saveScale < minScale) {
                    saveScale = minScale;
                    mScaleFactor = minScale / origScale;
                }

                right = width * saveScale - width - (2 * redundantXSpace * saveScale);
                bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

                if (origWidth * saveScale <= width || origHeight * saveScale <= height) {
                    matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);

                    if (mScaleFactor < 1) {
                        matrix.getValues(m);
                        float x = m[Matrix.MTRANS_X];
                        float y = m[Matrix.MTRANS_Y];

                        if (mScaleFactor < 1) {
                            if (Math.round(origWidth * saveScale) < width) {
                                if (y < -bottom) {
                                    matrix.postTranslate(0, -(y + bottom));
                                } else if (y > 0) {
                                    matrix.postTranslate(0, -y);
                                }
                            } else {
                                if (x < -right) {
                                    matrix.postTranslate(-(x + right), 0);
                                } else if (x > 0) {
                                    matrix.postTranslate(-x, 0);
                                }
                            }
                        }
                    }
                } else {
                    matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
                    matrix.getValues(m);

                    float x = m[Matrix.MTRANS_X];
                    float y = m[Matrix.MTRANS_Y];

                    if (mScaleFactor < 1) {
                        if (x < -right) {
                            matrix.postTranslate(-(x + right), 0);
                        } else if (x > 0) {
                            matrix.postTranslate(-x, 0);
                        }

                        if (y < -bottom) {
                            matrix.postTranslate(0, -(y + bottom));
                        } else if (y > 0) {
                            matrix.postTranslate(0, -y);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.e(TAG, ex);
            }

            return true;
        }
    }
}