import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SizeUpdater implements MouseMotionListener {
    Frame f;
    Interface i;
    String font;
    int oh;
    int ow;

    public SizeUpdater(Frame f1, Interface i1, String fo) {
        f = f1;
        i = i1;
        font = fo;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateSize();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateSize();
    }

    private void updateSize() {
        if (oh != f.getHeight() || ow != f.getWidth()) {
            oh = f.getHeight();
            ow = f.getWidth();
            double sizeFactor = ((double) (f.getHeight())) / 400;
            Font f1 = new Font(font, Font.BOLD, (int) (15 * sizeFactor));
            i.bNew.setBounds(0, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
            i.bNew.setFont(f1);
            i.bLoad.setBounds(f.getWidth() / 8, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
            i.bLoad.setFont(f1);
            i.bSave.setBounds(f.getWidth() / 4, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
            i.bSave.setFont(f1);
            i.bMusic.setBounds((3 * f.getWidth()) / 8, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
            i.bSave.setFont(f1);
            i.bM5.setBounds(0, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
            i.bM5.setFont(f1);
            i.bM1.setBounds(f.getWidth() / 6, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
            i.bM1.setFont(f1);
            i.bP5.setBounds(f.getWidth() / 3, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
            i.bP5.setFont(f1);
            i.bStop.setBounds(0, (int) (100 * sizeFactor), f.getWidth() / 4, (int) (100 * sizeFactor));
            i.bStop.setFont(f1);
            i.bPlayPause.setBounds(f.getWidth() / 4, (int) (100 * sizeFactor), f.getWidth() / 4, (int) (100 * sizeFactor));
            i.bPlayPause.setFont(f1);
            int heightLeft = (int) (f.getHeight() - 200 * sizeFactor) - 39;
            i.bAdd.setBounds(0, (int) (200 * sizeFactor), (int) (100 * sizeFactor), heightLeft / 4);
            i.bAdd.setFont(f1);
            i.bModify.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.25), (int) (100 * sizeFactor), heightLeft / 4);
            i.bModify.setFont(f1);
            i.bDelete.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.5), (int) (100 * sizeFactor), heightLeft / 4);
            i.bDelete.setFont(f1);
            i.bDuration.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.75), (int) (100 * sizeFactor), heightLeft / 4);
            i.bDuration.setFont(f1);
            i.panel.setBounds((int) (100 * sizeFactor), (int) (200 * sizeFactor), (int) (f.getWidth() - 100 * sizeFactor), heightLeft);
            i.flash.setBounds(f.getWidth() / 2, 0, f.getWidth() / 2, (int) (200 * sizeFactor));
            if (Main.e != null) {
                Main.e.draw(i);
            }
        }
    }
}