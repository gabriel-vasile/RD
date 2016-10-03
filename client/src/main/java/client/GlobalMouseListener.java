package client;


import common.Message;
import common.MouseEventContainer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListener implements NativeMouseInputListener {

    private ObjectOutputStream sOutput;

    GlobalMouseListener(ObjectOutputStream sOutput) {
        this.sOutput = sOutput;
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
//    	Message cm = new Message(3, e);
//    	try {
//			sOutput.writeObject(cm);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        MouseEventContainer mec = new MouseEventContainer(MouseEventContainer.PRESSEDEVENT, e);
        Message cm = new Message(Message.MOUSEEVENT, mec);
        try {
            sOutput.writeObject(cm);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        MouseEventContainer mec = new MouseEventContainer(MouseEventContainer.RELEASEDEVENT, e);
        Message cm = new Message(Message.MOUSEEVENT, mec);
        try {
            sOutput.writeObject(cm);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        MouseEventContainer mec = new MouseEventContainer(MouseEventContainer.MOVEDEVENT, e);
        Message cm = new Message(Message.MOUSEEVENT, mec);
        try {
            sOutput.writeObject(cm);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        MouseEventContainer mec = new MouseEventContainer(MouseEventContainer.DRAGGEDEVENT, e);
        Message cm = new Message(Message.MOUSEEVENT, mec);
        try {
            sOutput.writeObject(cm);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void init() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        GlobalMouseListener example = new GlobalMouseListener(sOutput);

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
    }
}
