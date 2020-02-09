import java.awt.Component;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class PlayerFrame extends Component {
	private static final long serialVersionUID = 1L;
	
	Semaphore go = new Semaphore(0);
	long current = 0;
	long old;
	long now;
	
	long duration;
	
	LinkedList<Flash> flashList = new LinkedList<Flash>();

}
