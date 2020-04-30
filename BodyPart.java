import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {
private int xcoor,ycoor,width,height;
public BodyPart(int xcoor,int ycoor,int t) {
	this.xcoor=xcoor;
	this.ycoor=ycoor;
	this.width=t;
	height=t;
}public void draw(Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(xcoor*width, ycoor*height, width, height);
}public int getxcoor() {
	return xcoor;
}public void setxcoor(int xcoor) {
	this.xcoor=xcoor;
}public int getycoor() {
	return ycoor;
}public void setycoor(int ycoor) {
	this.ycoor=ycoor;
}
}
