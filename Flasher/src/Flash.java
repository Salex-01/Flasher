public class Flash {
    int time;
    boolean metronome;
    Flash parent;
    int inter;
    int n;

    public Flash(int t, boolean m, Flash p, int i, int n1) {
        time = t;
        metronome = m;
        parent = p;
        inter = i;
        n = n1;
    }
}
