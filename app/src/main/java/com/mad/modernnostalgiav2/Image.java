package com.mad.modernnostalgiav2;

/**
 * Constructor class for individual image in the Gallery screen (Photo activity)
 * With getter and setter methods for image
 */
public class Image {

    private int imageID; //ImageView placeholder

    public Image() { }

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
