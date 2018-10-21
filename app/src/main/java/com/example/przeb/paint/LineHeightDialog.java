package com.example.przeb.paint;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by przeb on 19/01/2018.
 */

public class LineHeightDialog extends DialogFragment {
    private ImageView lineHeightImageView;
    private Paint linePaint;
    private SeekBar widthSeekBar;
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getActivity().getLayoutInflater().inflate(R.layout.line_height_fragment, null);
        mBuilder.setView(mView);
        mBuilder.setTitle("Grubosc linii");

        widthSeekBar = (SeekBar)
                mView.findViewById(R.id.seekBar_line);

        lineHeightImageView = (ImageView) mView.findViewById(R.id.imageView_height);

        lineHeightImageView.setImageBitmap(setLineHeight(paint_activity.widthLine, paint_activity.color));
        widthSeekBar.setOnSeekBarChangeListener(changeLineHeight);

        mBuilder.setPositiveButton("Wybierz grubosc linii", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                paint_activity.widthLine = widthSeekBar.getProgress();
                paint_activity.mPaint.setStrokeWidth(paint_activity.widthLine);
            }
        });
        return mBuilder.create();
    }
    private final SeekBar.OnSeekBarChangeListener changeLineHeight= new SeekBar.OnSeekBarChangeListener() {
        final Bitmap bitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Paint p = new Paint();
            p.setColor(paint_activity.color);
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setStrokeWidth(i);
            bitmap.eraseColor(Color.parseColor("#80FFFFFF"));
            canvas.drawLine(30, 50, 370, 50, p);
            lineHeightImageView.setImageBitmap(bitmap);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    public Bitmap setLineHeight(int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setColor(color);
        p.setStrokeWidth(height);
        widthSeekBar.setProgress(height);
        canvas.drawLine(30, 50, 370, 50, p);
        return bitmap;
    }
}
