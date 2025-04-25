package gfc.microedition.lcdui.game;

import gfc.microedition.lcdui.*;


/**
 * class Sprite
 * 
 * Replacement of javax.microedition.lcdui.game.Sprite
 * 
 * @author Alex Knorr
 *  
 */
public class Sprite extends Layer {

    /** source image */
    private Image sourceImage;

    /** sequence of animation frames */
    private int[] frameSequence = null;

    /** current index into animation sequence */
    private int sequenceIndex = 0;

    /** collision rectangle x-position */
    private int collisionRectX = 0;

    /** collision rectangle y-position */
    private int collisionRectY = 0;

    /** collision rectangle width */
    private int collisionRectWidth;

    /** collision rectangle height */
    private int collisionRectHeight;

    /** dereference pixel x-position */
    private int dRefX = 0;

    /** dereference pixel y-position */
    private int dRefY = 0;

    /**
     * Creates a new non-animated Sprite using the provided Image.
     * 
     * @param image
     */
    public Sprite(Image image) {
        super(image.getWidth(), image.getHeight());

        this.sourceImage = image;
        this.collisionRectWidth = image.getWidth();
        this.collisionRectHeight = image.getHeight();
    }

    /**
     * Creates a new animated Sprite using frames contained in the provided
     * Image.
     * 
     * @param image
     * @param frameWidth
     * @param frameHeight
     */
    public Sprite(Image image, int frameWidth, int frameHeight) {
        super(frameWidth, frameHeight);

        this.sourceImage = image;
        this.collisionRectWidth = frameWidth;
        this.collisionRectHeight = frameHeight;
    }

    /**
     * Creates a new Sprite from another Sprite.
     * 
     * @param s
     */
    public Sprite(Sprite s) {
        super(s.width, s.height);

        this.x = s.x;
        this.y = s.y;
        this.sourceImage = s.sourceImage;
        this.frameSequence = s.frameSequence;
        this.sequenceIndex = s.sequenceIndex;
        this.collisionRectX = s.collisionRectX;
        this.collisionRectY = s.collisionRectY;
        this.collisionRectWidth = s.collisionRectWidth;
        this.collisionRectHeight = s.collisionRectHeight;
        this.dRefX = s.dRefX;
        this.dRefY = s.dRefY;
    }

    /**
     * Checks for a collision between this Sprite and the specified Image with
     * its upper left corner at the specified location.
     * 
     * @param image
     * @param x
     * @param y
     * @param pixelLevel
     * @return - true or false
     */
    public final boolean collidesWith(Image image, int x, int y,
            boolean pixelLevel) {
        
        // this sprite must be visible
        if (!this.visible) {
            return (false);
        }

        // translate x to this sprite큦 collision-rect coordinate system
        x -= this.x + this.collisionRectX;

        // check for overlap in x-axis
        if ((x + image.getWidth()) <= 0 || x >= this.collisionRectWidth) {
            return (false);
        }

        // translate y to this sprite큦 collision-rect coordinate system
        y -= this.y + this.collisionRectY;

        // check for overlap in y-axis
        if ((y + image.getHeight()) <= 0 || y >= this.collisionRectHeight) {
            return (false);
        }

        return (true);
    }

    /**
     * Checks for a collision between this Sprite and the specified Sprite.
     * 
     * @param s
     * @param pixelLevel
     * @return
     */
    public final boolean collidesWith(Sprite s, boolean pixelLevel) {
        
        // both sprites must be visible
        if (!this.visible || !s.visible) {
            return (false);
        }

        // translate x to this sprite큦 collision-rect coordinate system
        int x = (s.x + s.collisionRectX) - (this.x + this.collisionRectX);

        // check for overlap in x-axis
        if ((x + s.collisionRectWidth) <= 0 || x >= this.collisionRectWidth) {
            return (false);
        }

        // translate y to this sprite큦 collision-rect coordinate system
        int y = (s.y + s.collisionRectY) - (this.y + this.collisionRectY);

        // check for overlap in y-axis
        if ((y + s.collisionRectHeight) <= 0 || y >= this.collisionRectHeight) {
            return (false);
        }

        return (true);
    }

    /**
     * Checks for a collision between this Sprite and the specified TiledLayer.
     * 
     * @param t
     * @param pixelLevel
     * @return
     */
    public final boolean collidesWith(TiledLayer t, boolean pixelLevel) {
        
        // this sprite must be visible
        if (!this.visible) {
            return (false);
        }

        return t.collidesWithRect(this.x + this.collisionRectX, this.y
                + this.collisionRectY, this.collisionRectWidth,
                this.collisionRectHeight);
    }

    /**
     * Defines the Sprite's bounding rectangle that is used for collision
     * detection purposes.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void defineCollisionRectangle(int x, int y, int width, int height) {

        this.collisionRectX = x;
        this.collisionRectY = y;
        this.collisionRectWidth = width;
        this.collisionRectHeight = height;
    }

    /**
     * Defines the reference pixel for this Sprite. The pixel is defined by its
     * location relative to the upper-left corner of the Sprite's un-transformed
     * frame, and it may lay outside of the frame's bounds.
     * <p>
     * When a transformation is applied, the reference pixel is defined relative
     * to the Sprite's initial upper-left corner before transformation. This
     * corner may no longer appear as the upper-left corner in the painter's
     * coordinate system under current transformation.
     * <p>
     * By default, a Sprite's reference pixel is located at (0,0), that is the
     * pixel in the upper-left corner of the raw frame.
     * 
     * @param x -
     *            the horizontal location of the reference pixel, relative to
     *            the left edge of the un-transformed frame
     * @param y -
     *            the vertical location of the reference pixel, relative to the
     *            top edge of the un-transformed frame
     */
    public void defineReferencePixel(int x, int y) {
        this.dRefX = x;
        this.dRefY = y;
    }

    /**
     * Gets the current index in the frame sequence.
     * 
     * @return
     */
    public final int getFrame() {
        return (this.sequenceIndex);
    }

    /**
     * Gets the number of elements in the frame sequence.
     * 
     * @return
     */
    public int getFrameSequenceLength() {
        return ((frameSequence != null) ? frameSequence.length
                : getRawFrameCount());
    }

    /**
     * Gets the number of raw frames for this Sprite.
     * 
     * @return
     */
    public int getRawFrameCount() {
        return (sourceImage.getWidth() / width * sourceImage.getHeight() / height);
    }

    /**
     * Gets the horizontal position of this Sprite's reference pixel in the
     * painter's coordinate system.
     * 
     * @return
     */
    public int getRefPixelX() {
        return (this.x + this.dRefX);
    }

    /**
     * Gets the vertical position of this Sprite's reference pixel in the
     * painter's coordinate system.
     * 
     * @return
     */
    public int getRefPixelY() {
        return (this.y + this.dRefY);
    }

    /**
     * Selects the next frame in the frame sequence.
     */
    public void nextFrame() {

        if (sequenceIndex < (getFrameSequenceLength() - 1)) {
            sequenceIndex++;
        } else {
            sequenceIndex = 0;
        }
    }

    /**
     * Draws the Sprite.
     * 
     * @param g
     */
    public final void paint(Graphics g) {

        if (!visible) {
            return;
        }

        int x = this.x;
        int y = this.y;

        if (getRawFrameCount() > 1) {

            // save clip
            int cx = g.getClipX();
            int cy = g.getClipY();
            int cw = g.getClipWidth();
            int ch = g.getClipHeight();

            // clip to sprite area
            g.clipRect(x, y, this.width, this.height);

            // get frame index
            int frame = (frameSequence != null) ? this.frameSequence[this.sequenceIndex]
                    : this.sequenceIndex;

            // calculate frame position within the sourceImage
            int framesHorz = this.sourceImage.getWidth() / this.width;
            x = x - (frame % framesHorz) * this.width;
            y = y - (frame / framesHorz) * this.height;

            // draw frame
            g.drawImage(this.sourceImage, x, y);

            // restore clip
            g.setClip(cx, cy, cw, ch);

        } else {

            // draw whole source image
            g.drawImage(this.sourceImage, x, y);
        }
    }

    /**
     * Selects the previous frame in the frame sequence.
     */
    public void prevFrame() {

        if (sequenceIndex > 0) {
            sequenceIndex--;
        } else {
            sequenceIndex = getFrameSequenceLength() - 1;
        }
    }

    /**
     * Selects the current frame in the frame sequence.
     * 
     * @param sequenceIndex
     * @throws IndexOutOfBoundsException
     */
    public void setFrame(int sequenceIndex) throws IndexOutOfBoundsException {
        
        if ((sequenceIndex < 0) || sequenceIndex >= getFrameSequenceLength()) {
            throw new IndexOutOfBoundsException ();
        }
        
        this.sequenceIndex = sequenceIndex;
    }

    /**
     * Gets the number of raw frames for this Sprite. The value returned
     * reflects the number of frames, it does not reflect the length of the
     * Sprite's frame sequence. However, these two values will be the same if
     * the default frame sequence is used.
     * 
     * @param sequence
     */
    public void setFrameSequence(int[] sequence) {

        if (sequence.length == 0) {
            throw new IllegalArgumentException();
        }

        int rawFrameCount = getRawFrameCount();

        for (int i = sequence.length - 1; i >= 0; i--) {
            if (sequence[i] < 0 || sequence[0] >= rawFrameCount) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        
        this.sequenceIndex = 0;
        this.frameSequence = sequence;
    }

    /**
     * Changes the Image containing the Sprite's frames.
     * 
     * @param img
     * @param frameWidth
     * @param frameHeight
     */
    public void setImage(Image img, int frameWidth, int frameHeight) {

        int oldRawFrameCount = getRawFrameCount();

        this.sourceImage = img;
        this.width = frameWidth;
        this.height = frameHeight;
        this.collisionRectWidth = frameWidth;
        this.collisionRectHeight = frameHeight;

        if (getRawFrameCount() < oldRawFrameCount) {

            this.sequenceIndex = 0;
            this.frameSequence = null;
        }
    }

    /**
     * Sets this Sprite's position such that its reference pixel is located at
     * (x,y) in the painter's coordinate system.
     * 
     * @param x
     * @param y
     */
    public void setRefPixelPosition(int x, int y) {
        this.x = x - this.dRefX;
        this.y = y - this.dRefY;
    }

    /**
     * Sets the transform for this Sprite.
     * 
     * @param transform
     */
    public void setTransform(int transform) {
        // not implemented
    }
}