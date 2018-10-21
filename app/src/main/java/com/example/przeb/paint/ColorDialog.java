package com.example.przeb.paint;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by przeb on 19/01/2018.
 */

public class ColorDialog extends DialogFragment {
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private View colorView;
    private int color;
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getActivity().getLayoutInflater().inflate(R.layout.color_fragment, null);
        mBuilder.setView(mView);
        mBuilder.setTitle("Wybierz Kolor");

        redSeekBar = (SeekBar) mView.findViewById(R.id.seekBar_red);
        greenSeekBar = (SeekBar) mView.findViewById(R.id.seekBar_green);
        blueSeekBar = (SeekBar) mView.findViewById(R.id.seekBar_blue);

        colorView = (View) mView.findViewById(R.id.colorView);
        colorView.setBackgroundColor(paint_activity.color);

        redSeekBar.setOnSeekBarChangeListener(changeColorListener);
        greenSeekBar.setOnSeekBarChangeListener(changeColorListener);
        blueSeekBar.setOnSeekBarChangeListener(changeColorListener);

        redSeekBar.setProgress(Color.red(paint_activity.color));
        greenSeekBar.setProgress(Color.green(paint_activity.color));
        blueSeekBar.setProgress(Color.blue(paint_activity.color));

        mBuilder.setPositiveButton("Wybierz kolor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getActivity(), "Kolor: " + color, Toast.LENGTH_LONG).show();
                color = Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
                paint_activity.color = color;
                paint_activity.mPaint.setColor(paint_activity.color);
            }
        });
        return mBuilder.create();
    }
    private final SeekBar.OnSeekBarChangeListener changeColorListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b) {
                        color = Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
                        colorView.setBackgroundColor(color);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
