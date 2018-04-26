package org.great.util;


public class CleanRun implements Runnable {

	private int time = 0;
	@Override
	public void run() {
		
		while(true)
		{
			if(time == 60)
			{
				time = 0;
				Util.getInstance().remove();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time++;
		}
	}
}
