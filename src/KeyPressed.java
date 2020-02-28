
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


//FINAL
public class KeyPressed {
int numeroKey = -1;
boolean State =false;
String name = "name";
boolean InitialMoov = false;

public KeyPressed(int numeroKey, String name){
	this.numeroKey= numeroKey;
	this.name = name;
}

public void setState(boolean inon) {
	this.State=inon;
	this.InitialMoov=inon;
}

public boolean getState() {
	return State;
}
}