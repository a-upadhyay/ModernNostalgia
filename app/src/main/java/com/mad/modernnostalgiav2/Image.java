package com.mad.modernnostalgiav2;

/**
 * Constructor class for individual image in Photo activity with getter and setter for image
 */
public class Image {

    private int imageID;

    public Image() {
    }

    public Image(int imageID) {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
