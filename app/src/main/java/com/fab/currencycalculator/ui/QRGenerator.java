package com.fab.currencycalculator.ui;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRGenerator {

    private QRGEncoder qrgEncoder;

    public QRGenerator (String value, int dimension) {
        this.qrgEncoder = new QRGEncoder(value,
                null,
                QRGContents.Type.TEXT,
                dimension);
    }

    public Bitmap getBitmap () {
        return qrgEncoder.getBitmap();
    }
}
