package amgs.controls;

import amgs.*;
import amgs.display.Display;
import amgs.input.*;

import java.awt.*;

public class ControlManager {

    private KeyManager keyManager;
    private Display display;

    private DisplayMode originalDisplayMode;

    public ControlManager(KeyManager keyManager, Display display) {
        this.keyManager = keyManager;
        this.display = display;
        originalDisplayMode = display.getGDevice().getDisplayMode();

        testPrintAvailableDisplayModes(display.getGDevice());
    }

    public void tick() {
  
        if(keyManager.exit) {
            System.out.println("*** Game exited ***");
            System.exit(0);
        }

        if(keyManager.fullscreen) {
            System.out.println("*** Full screen ***");
            try {
                GraphicsDevice gDevice = display.getGDevice();
                Dimension screenSize = Toolkit
                    .getDefaultToolkit().getScreenSize();

                if(gDevice.isFullScreenSupported()) {
                    display.getGDevice()
                        .setFullScreenWindow(display.getFrame());
                    display.getCanvas().setPreferredSize(screenSize);

                    display.setWidth(screenSize.width);
                    display.setHeight(screenSize.height);
                }
                
                if(gDevice.isDisplayChangeSupported()) {
                    DisplayMode bestDisplayMode =
                        new DisplayMode(1920, 1080, 
                            DisplayMode.BIT_DEPTH_MULTI, 60);
                    gDevice.setDisplayMode(bestDisplayMode);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        if(keyManager.normalscreen) {
            System.out.println("*** Normal screen ***");
            try {
                GraphicsDevice gDevice = display.getGDevice();
                Dimension maxWindowSize = display
                    .getGEnvironment().getMaximumWindowBounds().getSize();

                if(gDevice.isFullScreenSupported()) {
                    display.getGDevice().setFullScreenWindow(null);
                    display.getCanvas().setPreferredSize(maxWindowSize);

                    display.setWidth( (int) maxWindowSize.getWidth());
                    display.setHeight( (int) maxWindowSize.getHeight());
                }

                if(gDevice.isDisplayChangeSupported()) {
                    gDevice.setDisplayMode(originalDisplayMode);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    
    }

    private void testPrintAvailableDisplayModes(GraphicsDevice gDevice) {
        if(Constants.isTest) {
            DisplayMode[] dms = gDevice.getDisplayModes();
            for (int i=0; i<dms.length; i++) {
                System.out.println(dms[i]);
            }
        }
    }

}
