public class Flash {
	long time;
	PlayerFrame pf;

	public Flash(long t, PlayerFrame pf1) {
		time = t;
		pf = pf1;
	}

	public boolean change(long t) {
		if (t < 0 || t > pf.duration) {
			return false;
		}
		time = t;
		return true;
	}

	public boolean move(long t) {
		if (time + t < 0 || time + t > pf.duration) {
			return false;
		}
		time += t;
		return true;
	}
}
