/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.amulets.cyclops;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Picture model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Picture
{
    private File dir;

    private File file;

    private boolean generated;

    private int height = -1;

    private File thumbnail;

    private int thumbnailHeight = -1;

    private int thumbnailWidth = -1;

    private int width = -1;

    /**
     * Generate thumbnail file with given max size
     *
     * @param thumbnailFile
     * @param maxWidth
     * @param maxHeight
     * @throws IOException
     */
    public void generateThumbnail(File thumbnailFile, int maxWidth,
            int maxHeight) throws IOException
    {
        BufferedImage sourceImage = ImageIO.read(getFile());
        width = sourceImage.getWidth();
        height = sourceImage.getHeight();

        if (file.isFile()
                && thumbnailFile.lastModified() >= getFile().lastModified())
        {
            return;
        }
        thumbnailFile.getParentFile().mkdirs();
        double xRatio = (double) maxWidth / width;
        double yRatio = (double) maxHeight / height;
        double ratio = Math.min(xRatio, yRatio);
        int w = (int) (ratio * width);
        int h = (int) (ratio * height);
        Image temp = sourceImage.getScaledInstance(w, h,
                Image.SCALE_AREA_AVERAGING);
        BufferedImage targetImage = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        targetImage.getGraphics().drawImage(temp, 0, 0, new ImageObserver()
        {
            public boolean imageUpdate(Image img, int infoflags, int x, int y,
                    int width, int height)
            {
                synchronized (Picture.this)
                {
                    generated = ((infoflags & FRAMEBITS) > 0);
                    Picture.this.notify();
                }
                return true;
            }
        });
        while (!generated)
        {
            try
            {
                synchronized (this)
                {
                    wait();
                }
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
        ImageIO.write(targetImage, "jpeg", thumbnailFile);
    }

    /**
     * Generate the thumbnail file
     *
     * @param path Path of the thumbnail image file
     * @param maxWidth Max thumbnail image width
     * @param maxHeight Max thumbnail image height
     * 
     * @throws IOException Throw it out
     */
    public void generateThumbnail(String path, int maxWidth, int maxHeight)
            throws IOException
    {
        generateThumbnail(new File(path), maxWidth, maxHeight);
    }

    /**
     * Getter method for dir
     *
     * @return Returns the dir.
     */
    public File getDir()
    {
        return dir;
    }

    /**
     * Getter method for file
     *
     * @return Returns the file.
     */
    public File getFile()
    {
        return file;
    }

    /**
     * Get the name of the file
     *
     * @return The name of the file
     */
    public String getFileName()
    {
        return file.getName();
    }

    /**
     * Getter method for height
     *
     * @return Returns the height.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Get relative directory path of source image file
     *
     * @return Relative path
     * @throws IOException Throw it out
     */
    public String getRelativeDir() throws IOException
    {
        return FileUtils.getRelativePath(getFile().getParentFile(), getDir());
    }

    /**
     * Get thumbnail file
     *
     * @return Thumbnail file
     */
    public File getThumbnail()
    {
        return thumbnail;
    }

    /**
     * Getter method for thumbnailHeight
     *
     * @return Returns the thumbnailHeight.
     */
    public int getThumbnailHeight()
    {
        return thumbnailHeight;
    }

    /**
     * Getter method for thumbnailWidth
     *
     * @return Returns the thumbnailWidth.
     */
    public int getThumbnailWidth()
    {
        return thumbnailWidth;
    }

    /**
     * Getter method for width
     *
     * @return Returns the width.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @return If it's generated
     */
    public boolean isGenerated()
    {
        return generated;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param dir
     */
    public void setDir(File dir)
    {
        this.dir = dir;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param path
     */
    public void setDir(String path)
    {
        this.dir = new File(path);
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param file
     */
    public void setFile(File file)
    {
        this.file = file;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param path
     */
    public void setFile(String path)
    {
        setFile(new File(path));
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "Picture: " + file;
    }
}