/*
 * Created on 2003-9-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.Turbine;
/**
 * @author joeblack
 * @since 2003-9-25 21:55:15
 *
 * Class A4Trim
 */
public final class A4Trim {
    private A4Trim() {
        //Do nothing
    }
    private static class Observer implements ImageObserver {
        private boolean renderFinished = false;
        /** Method imageUpdate()
        * @see java.awt.image.ImageObserver#imageUpdate(java.awt.Image, int, int, int, int, int)
        */
        public boolean imageUpdate(
            Image img,
            int infoflags,
            int x,
            int y,
            int width,
            int height) {
            renderFinished = ((infoflags & FRAMEBITS) > 0);
            return true;
        }
        public boolean isRenderFinished() {
            return renderFinished;
        }
    }
    private static final int DEFAULT_PRODUCT_THUMBNAIL_HEIGHT = 100;
    private static final int DEFAULT_PRODUCT_THUMBNAIL_WIDTH = 180;
    /** Image repository path */
    public static final String IMAGE_REPO =
        Turbine.getConfiguration().getString(
            "a4trim.image.repo",
            "images/repo");
    /** Max height of product thumbnail */
    public static final int PRODUCT_THUMBNAIL_HEIGHT =
        Turbine.getConfiguration().getInt(
            "a4trim.image.product.height",
            DEFAULT_PRODUCT_THUMBNAIL_HEIGHT);
    /** Max width of product thumbnail */
    public static final int PRODUCT_THUMBNAIL_WIDTH =
        Turbine.getConfiguration().getInt(
            "a4trim.image.product.width",
            DEFAULT_PRODUCT_THUMBNAIL_WIDTH);
    private static final long WAITING_INTEVAL = 100L;
    /** Method generateThumbnail() in Class A4Trim
     * @param sourceFile Source image file
     * @param maxWidth Max width of thumbnail image
     * @param maxHeight Max height of thumbnail image
     * @return Generated file object
     * @throws IOException Could be error
     */
    public static final File generateThumbnail(
        File sourceFile,
        int maxWidth,
        int maxHeight)
        throws IOException {
        BufferedImage source = ImageIO.read(sourceFile);
        double wratio = (double) maxWidth / (double) source.getWidth();
        double hratio = (double) maxHeight / (double) source.getHeight();
        double ratio = Math.min(wratio, hratio);
        int width = (int) (ratio * source.getWidth());
        int height = (int) (ratio * source.getHeight());
        Image image =
            source.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        BufferedImage target =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Observer observer = new Observer();
        target.getGraphics().drawImage(image, 0, 0, observer);
        while (!observer.isRenderFinished()) {
            try {
                Thread.sleep(WAITING_INTEVAL);
            } catch (InterruptedException e) {
                break;
            }
        }
        File targetFile =
            new File(
                sourceFile.getParentFile(),
                "thumbnail_"
                    + StringUtils.replace(sourceFile.getName(), ".", "_")
                    + ".jpeg");
        ImageIO.write(target, "jpeg", targetFile);
        return targetFile;
    }
}
