package ru.projectx.clicker.client.utils;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.SinglePixelPackedSampleModel;
import java.nio.IntBuffer;

public class ImageUtils {

    public static WritableImage convertToFxImageJava8(BufferedImage image) {
        int bw = image.getWidth();
        int bh = image.getHeight();
        switch (image.getType()) {
            case BufferedImage.TYPE_INT_ARGB:
            case BufferedImage.TYPE_INT_ARGB_PRE:
                break;
            default:
                BufferedImage converted = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_ARGB_PRE);
                Graphics2D graphics2D = converted.createGraphics();
                graphics2D.drawImage(image, 0, 0, null);
                graphics2D.dispose();
                image = converted;
                break;
        }
        WritableImage writableImage = new WritableImage(bw, bh);
        DataBufferInt raster = (DataBufferInt) image.getRaster().getDataBuffer();
        int scan = image.getRaster().getSampleModel() instanceof SinglePixelPackedSampleModel
                ? ((SinglePixelPackedSampleModel) image.getRaster().getSampleModel()).getScanlineStride() : 0;
        PixelFormat<IntBuffer> pf = image.isAlphaPremultiplied() ?
                PixelFormat.getIntArgbPreInstance() :
                PixelFormat.getIntArgbInstance();
        writableImage.getPixelWriter().setPixels(0, 0, bw, bh, pf, raster.getData(), raster.getOffset(), scan);
        return writableImage;
    }
}
