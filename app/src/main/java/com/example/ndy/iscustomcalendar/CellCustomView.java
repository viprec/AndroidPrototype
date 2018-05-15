package com.example.ndy.iscustomcalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CellCustomView extends View {

    private Paint paintRect;
    private Paint paintCircle;
    private Integer circleColor;
    private String circleText;
    private Integer circleTextColor;
    private Path path;
    private int size;
    private int paddingFromContainerEdge = 30;
    private String rectStr = "";
    private String circleStr = "";

    public CellCustomView(Context context) {
        super(context);
        init();
    }

    public CellCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CellCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CellCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawRect(canvas);
        drawSuperScriptCircle(canvas, "5");
    }

    private void drawSuperScriptCircle(Canvas canvas, String s) {

        paintCircle.setColor(Color.RED);
        paintCircle.setTextSize(10f);

        int superScriptX = getWidth() - paddingFromContainerEdge;
        int superSCriptY = paddingFromContainerEdge;
        canvas.drawCircle(superScriptX,
                superSCriptY,
                paddingFromContainerEdge,
                paintCircle);

        paintCircle.setColor(Color.BLUE);
        paintCircle.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(s, superScriptX, superSCriptY, paintCircle);
    }

    private void drawRect(Canvas canvas) {
        path.moveTo(paddingFromContainerEdge, paddingFromContainerEdge);
        path.lineTo(paddingFromContainerEdge, getHeight() - paddingFromContainerEdge);
        path.lineTo(getWidth() - paddingFromContainerEdge, getHeight()-paddingFromContainerEdge);
        path.lineTo(getWidth() - paddingFromContainerEdge, paddingFromContainerEdge);
        path.lineTo(paddingFromContainerEdge, paddingFromContainerEdge);

        canvas.drawPath(path, paintRect);

        int centerX = (getWidth() - paddingFromContainerEdge) / 2;
        int centerY = (getHeight() - paddingFromContainerEdge) / 2;
        paintRect.setTextSize(45);
        paintRect.setColor(Color.BLUE);

        canvas.drawText(rectStr,centerX, centerY, paintRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        size = Math.max(widthSize, heightSize);
        setMeasuredDimension(size, size);
    }

    private void init(){
        paintRect = new Paint();
        paintRect.setColor(Color.BLUE);
        paintRect.setStrokeWidth(10);
        paintRect.setStyle(Paint.Style.STROKE);

        paintCircle = new Paint();

        path = new Path();

    }

    public String getRectStr() {
        return rectStr;
    }

    public void setRectStr(String rectStr) {
        this.rectStr = rectStr;
    }

    public String getCircleStr() {
        return circleStr;
    }

    public void setCircleStr(String circleStr) {
        this.circleStr = circleStr;
    }
}
