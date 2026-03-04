import io.nayuki.qrcodegen.QrCode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

void main() {
    QrCode qr = QrCode.encodeText("https://vsu.by/en/university/faculties/faculty-of-mathematics-and-it.html", QrCode.Ecc.MEDIUM);
    BufferedImage img = new BufferedImage(qr.size * 10 + 2 * 15, qr.size * 10 + 2 * 15, BufferedImage.TYPE_INT_RGB);
    for(int i = 0; i < img.getHeight(); i++) {
        for(int j = 0; j < img.getWidth(); j++) {
            int color;
            if(i >= 15 && i < img.getHeight() - 15 && j >= 15 && j < img.getWidth() - 15) {
                if(qr.getModule((i - 15) / 10, (j - 15) / 10)) {
                    color = Color.BLACK.getRGB();
                } else {
                    color = Color.WHITE.getRGB();
                }
            } else {
                color = Color.WHITE.getRGB();
            }
            img.setRGB(j, i, color);
        }
    }
    JFrame frame = new JFrame("QR code");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
    frame.pack();
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    JLabel label = new JLabel(new ImageIcon(img));
    frame.add(label);
    frame.setVisible(true);
}