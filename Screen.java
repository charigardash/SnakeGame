import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable,KeyListener{
    private static final long SerialVersionUID=1l;
    public static final int WIDTH=400,HEIGHT=400;
    private Thread thread;
    private boolean running =false;
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;
    private int xcoor=10,ycoor=10;
    private int size=5;
    private boolean right=true,left=false,up=false,down=false;
    private int ticks=0;
    public Screen() {
    	setFocusable(true);
    	addKeyListener(this);
    	setPreferredSize(new Dimension(WIDTH,HEIGHT));
    	r=new Random();
    	snake=new ArrayList<BodyPart>();
    	apples=new ArrayList<Apple>();
    	start();
    }public void tick() {
    	if(snake.size()==0) {
    		b=new BodyPart(xcoor,ycoor,10);
    		snake.add(b);
    	}if(apples.size()==0) {
    		int xCoor=r.nextInt(39);
    		int yCoor=r.nextInt(39);
    		apple=new Apple(xCoor,yCoor,10);
    		apples.add(apple);
    	}for(int i=0;i<apples.size();i++) {
    		if(xcoor==apples.get(i).getxcoor()&& ycoor==apples.get(i).getycoor()){
    			size++;
    			apples.remove(i);
    			i++;
    		}
    	}for(int i=0;i<snake.size();i++) {
    		if(xcoor==snake.get(i).getxcoor()&& ycoor==snake.get(i).getycoor()){
    			if(i!=snake.size()-1) {
    				stop();
    			}}
    		}if(xcoor<0||xcoor>39||ycoor<0||ycoor>39) {
    			stop();
    		}ticks++;
    		if(ticks>250000) {
    			if(right)xcoor++;
    			if(left)xcoor--;
    			if(up)ycoor--;
    			if(down)ycoor++;
    			ticks=0;
    			b=new BodyPart(xcoor,ycoor,10);
    			snake.add(b);
    			if(snake.size()>size) {
    				snake.remove(0);
    			}
    		}
    }public void paint(Graphics g) {
    	g.clearRect(0, 0, WIDTH, HEIGHT);
    	g.setColor(Color.ORANGE);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	g.setColor(Color.BLACK);
    	for(int i=0;i<HEIGHT/10;i++) {
    		g.drawLine(0,i*10,WIDTH,i*10);
    	}for(int i=0;i<WIDTH/10;i++) {
    		g.drawLine(i*10,0,i*10,HEIGHT);
    	}for(int i=0;i<snake.size();i++) {
    		snake.get(i).draw(g);
    	}for(int i=0;i<apples.size();i++) {
    		apples.get(i).draw(g);
    	}
    }public void start() {
    	running =true;
    	thread=new Thread(this);
    	thread.start();
    }public void stop() {
    	running=false;
    	try {
    		thread.join();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }public void run() {
    	while(running) {
    		tick();
    		repaint();
    	}
    }public void keyPressed(KeyEvent e) {
    	int key=e.getKeyCode();
    	if(key==KeyEvent.VK_RIGHT&&!left) {
    		up=false;down=false;right=true;
    	}if(key==KeyEvent.VK_LEFT&&!right) {
    		up=false;down=false;left=true;
    	}if(key==KeyEvent.VK_UP&&!down) {
    		left=false;right=false;up=true;
    	}if(key==KeyEvent.VK_DOWN&&!left) {
    		left=false;right=false;down=true;
    	}
    }public void keyReleased(KeyEvent arg0) {
    	
    }public void keyTyped(KeyEvent arg0) {
    	
    }
}
