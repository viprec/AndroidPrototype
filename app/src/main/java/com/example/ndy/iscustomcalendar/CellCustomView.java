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
    private Paint paintText;
    private Integer circleColor;
    private String circleText;
    private Integer circleTextColor;
    private Path path;
    private int size;
    private int paddingFromContainerEdge = 30;
    private String rectStr = "";
    private String circleStr = "";
    private boolean drawRect;
    private boolean drawCircle;

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

        drawText(canvas);

        if(isDrawRect())
            drawRect(canvas);
        if(isDrawRect())
            drawSuperScriptCircle(canvas, circleStr);
    }

    private void drawSuperScriptCircle(Canvas canvas, String s) {

        paintCircle.setColor(Color.RED);
        paintCircle.setTextSize(30f);

        int superScriptX = getWidth() - paddingFromContainerEdge;
        int superSCriptY = paddingFromContainerEdge;
        canvas.drawCircle(superScriptX,
                superSCriptY,
                paddingFromContainerEdge-5,
                paintCircle);

        paintCircle.setColor(Color.BLUE);
        paintCircle.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(s, superScriptX, superSCriptY+5, paintCircle);
    }

    private void drawRect(Canvas canvas) {
        path.moveTo(paddingFromContainerEdge, paddingFromContainerEdge);
        path.lineTo(paddingFromContainerEdge, getHeight() - paddingFromContainerEdge);
        path.lineTo(getWidth() - paddingFromContainerEdge, getHeight()-paddingFromContainerEdge);
        path.lineTo(getWidth() - paddingFromContainerEdge, paddingFromContainerEdge);
        path.lineTo(paddingFromContainerEdge, paddingFromContainerEdge);

        canvas.drawPath(path, paintRect);
    }

    private void drawText(Canvas canvas) {
        int centerX = (getWidth() - paddingFromContainerEdge) / 2;
        int centerY = (getHeight() - paddingFromContainerEdge) / 2 +30;

        canvas.drawText(rectStr,centerX, centerY, paintText);
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
        paintRect.setStrokeWidth(2);
        paintRect.setStyle(Paint.Style.STROKE);

        paintCircle = new Paint();

        paintText = new Paint();
        paintText.setTextSize(45);
        paintText.setColor(Color.GRAY);
        paintText.setStyle(Paint.Style.FILL);

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

    public boolean isDrawRect() {
        return drawRect;
    }

    public void setDrawRect(boolean drawRect) {
        this.drawRect = drawRect;
    }

    public boolean isDrawCircle() {
        return drawCircle;
    }

    public void setDrawCircle(boolean drawCircle) {
        this.drawCircle = drawCircle;
    }
}
