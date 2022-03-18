package amgs.testzone;

import java.awt.*;

public class Testcode {

    // code inside Gun.java
    private void render(Graphics g) {
        /*
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics gRotatedImage = rotatedImage.getGraphics();
        gRotatedImage.drawImage(image, 0, 0, null);
        // rotate the gun in the direction of shoot
        at.rotate(reticle.getAngle() - Math.PI/2,
            rotatedImage.getWidth()/2, rotatedImage.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
         rotatedImage = op.filter(rotatedImage, null);
        g.drawImage(rotatedImage,
			(int)(handler.getDisplay().getWidth()/2 - rotatedImage.getWidth()/2), 
			(int)(handler.getDisplay().getHeight()/2 - rotatedImage.getHeight()/2), 
            rotatedImage.getWidth(), rotatedImage.getHeight(), null);
        */
        /*
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        int x = handler.getDisplay().getWidth()/2 - image.getWidth()/2;
        int y = handler.getDisplay().getHeight()/2 - image.getHeight()/2;
        g2d.rotate(reticle.getAngle() - Math.PI/2, 
            image.getWidth()/2, image.getHeight()/2);
        g2d.dispose();
        g.drawImage(image, x, y, null);

        reticle.render(g);*/
        /*
        Graphics2D g2d = (Graphics2D) g.create();

        double rotation = 0f;

        int width = image.getWidth() - 1;
        int height = image.getHeight() - 1;

        rotation = reticle.getAngle();
        rotation = Math.toDegrees(rotation) + 180;

        g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
        // g2d.translate(handler.getDisplay().getWidth()/2, handler.getDisplay().getHeight()/2);
        g2d.drawImage(image, 0, 0, null);

        int x = width / 2;
        int y = height / 2;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawLine(x, y, x, y - height / 4);
        g2d.dispose();
        */
        // onyr
        /*
        BufferedImage rotatedImage = new BufferedImage(Constants.TILE_WIDTH, Constants.TILE_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D gRotatedImage = (Graphics2D) rotatedImage.getGraphics();

        gRotatedImage.drawImage(image, 0, 0, null);
        gRotatedImage.rotate(Math.toRadians(rotation), image.getWidth()/2, image.getHeight()/2);
        gRotatedImage.dispose();
        g.drawImage(rotatedImage, 
            handler.getDisplay().getWidth()/2 - image.getWidth()/2, 
            handler.getDisplay().getHeight()/2 - image.getHeight()/2, null);
        */
        /*
        BufferedImage rotatedImage = new BufferedImage(Constants.TILE_WIDTH, Constants.TILE_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
        Graphics gRotatedImage = rotatedImage.getGraphics();
        gRotatedImage.drawImage(image, 0, 0, null);
        // rotate the gun in the direction of shoot
        at.rotate(reticle.getAngle() + Math.PI,
            rotatedImage.getWidth()/2, rotatedImage.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage rotatedImage2 = op.filter(rotatedImage, null);
        g.drawImage(rotatedImage2,
			(int)(handler.getDisplay().getWidth()/2 - rotatedImage2.getWidth()/2), 
			(int)(handler.getDisplay().getHeight()/2 - rotatedImage2.getHeight()/2), 
            rotatedImage2.getWidth(), rotatedImage2.getHeight(), null);
        
        reticle.render(g);
        */
    }

}
