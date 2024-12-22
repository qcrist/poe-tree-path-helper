package net.qcrist.poetool2024.util;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class SimpleUI {
    @RequiredArgsConstructor
    private static class SupplierInstance {
        private final int width;
        private final int height;
        private final BiConsumer<Graphics2D, Graphics2D> supplier;
        private CompletableFuture<Pair<BufferedImage, BufferedImage>> cache;

        public Pair<BufferedImage, BufferedImage> getImages() {
            if (cache != null) {
                return cache.join();
            }
            CompletableFuture<Pair<BufferedImage, BufferedImage>> future = new CompletableFuture<>();
            cache = future;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphics = (Graphics2D) img.getGraphics();
            BufferedImage mImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D mGfx = (Graphics2D) mImg.getGraphics();
            supplier.accept(graphics, mGfx);
            graphics.dispose();
            mGfx.dispose();
            Pair<BufferedImage, BufferedImage> result = Pair.of(img, mImg);
            future.complete(result);
            return result;
        }

        public BufferedImage getCurrentDrawImage() {
            return getImages().getLeft();
        }

        public BufferedImage getCurrentMouseImage() {
            return getImages().getRight();
        }

        public void invalidateCache() {
            this.cache = null;
        }
    }

    private final JFrame frame;
    private SupplierInstance currentImageSupplier = new SupplierInstance(1, 1, (g, m) -> {
    });

    private BufferedImage currentMouseImage = null;
    private int drawOffsetX = 0;
    private int drawOffsetY = 0;

    @Setter
    private UIListener uiListener = null;
    private int redraw_index = 0;

    private boolean debugUseMouseImage = false;

    public interface UIListener {
        void onClick(int button, int target);

        void onKeyDown(KeyEvent e);
    }

    public SimpleUI(String name) {
        this.frame = new JFrame(name) {
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                Pair<BufferedImage, BufferedImage> images = currentImageSupplier.getImages();
                Insets is = frame.getInsets();
                BufferedImage img = debugUseMouseImage ? images.getRight() : images.getLeft();
                int ox = (getWidth() - img.getWidth()) / 2;
                int oy = (getHeight() - img.getHeight()) / 2;
                drawOffsetX = is.left + ox;
                drawOffsetY = is.top + oy;
                g.drawImage(img, drawOffsetX, drawOffsetY, null);
                currentMouseImage = images.getRight();
            }
        };
        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];
        Rectangle bounds = screen.getDefaultConfiguration().getBounds();
        this.frame.setBounds(bounds);
        this.frame.setUndecorated(true);
        this.frame.setVisible(true);

//        this.frame.setSize(800, 600);
        this.frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (uiListener == null) return;
                Insets is = frame.getInsets();
                int x = e.getX() - drawOffsetX;
                int y = e.getY() - drawOffsetY;
                if (x < 0 || x > frame.getWidth() || y < 0 || y > frame.getHeight()) {
                    return;
                }
                int rgb = currentMouseImage.getRGB(x, y);
//                System.out.printf("CLICK, x=%d, y=%d, rgb=%x\n", x, y, rgb);
                uiListener.onClick(e.getButton(), rgb & 0xFFFFFF);
            }
        });
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F3 -> {
                        debugUseMouseImage = !debugUseMouseImage;
                        redraw();
                    }

                    case KeyEvent.VK_SPACE -> {
                        CompletableFuture<Void> prev = space_future;
                        space_future = new CompletableFuture<>();
                        prev.complete(null);
                    }

                    default -> {
                        if (uiListener != null)
                            uiListener.onKeyDown(e);
                    }
                }
            }
        });
    }

    public void show(int width, int height, BiConsumer<Graphics2D, Graphics2D> supplier) {
        this.currentImageSupplier = new SupplierInstance(width, height, supplier);
        Insets is = frame.getInsets();
//        this.frame.setSize(width + is.left + is.right, height + is.top + is.bottom);
        this.frame.repaint();
    }

    public void redraw() {
        if (currentImageSupplier == null) {
            throw new Error("currentImageSupplier should not be null");
        }
        currentImageSupplier.invalidateCache();
        redraw_index++;
        frame.repaint();
    }

    private CompletableFuture<Void> space_future = new CompletableFuture<>();

    public void waitForSpace() {
        space_future.join();
    }
}
