package Model;
import java.io.Serializable;
public class Cinema implements Serializable{
	private String cinemaCode;
	private boolean[][] layout;
	private CinemaClass cinClass;
	public String getCinemaCode () {
		return cinemaCode;
	}
	public boolean[][] getLayout() {
		return layout;
	}
	public void setcinemaCode(String c) {
		cinemaCode=c;
	}
	public void setLayout(boolean[][] l) {
		layout=l;
	}
}


