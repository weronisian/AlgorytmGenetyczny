
public class Miejsce {

	private float x;
	private float y;
	private int nr;
	
	public Miejsce(int nr, float x, float y) {
		this.nr = nr;
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Miejsce [" + nr + ": x=" + x + ", y=" + y + "]";
	}
	
}
