import com.panickapps.opensimplex.OpenSimplexNoise;

import java.awt.*;
import java.util.Random;

public class MyCanvas extends Canvas {

    public static final int GRID_SIZE  = 512;

    private int tileSize = 0;
    private final int width;
    private final int height;

    public MyCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        int min = Math.min(width, height);
        tileSize = min / 256;
        System.out.println("Tile size: " + tileSize);
    }

    @Override
    public void paint(Graphics g) {
//        int roughness = 5;
//        Random random = new Random(1234);
//        Noise noise = new Noise(random, roughness, GRID_SIZE, GRID_SIZE);
//        noise.setOffsetX(16);
//        noise.setOffsetY(0);
//        noise.initialise();
//        float[][] noiseValues = noise.getGrid();

//        PerlinNoiseGenerator generator = new PerlinNoiseGenerator(1234);
        OpenSimplexNoise noise = new OpenSimplexNoise(1234);
        noise.setOctaveHalf(true);
        noise.setOctaveQuarter(true);
        noise.setOctaveEighth(true);
        noise.setOffsetX(16);
//        noise.setPower(1.0000004f);
        noise.setFeatureSize(20);

        double[][] noiseValues = noise.getNoise2DArray(0, 0, GRID_SIZE, GRID_SIZE);
//
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                noiseValues[i][j] = generator.noise2(i + 0.5F, j + 0.5F);
//            }
//        }



        //Interpolate:
        double[][] interpolatedValues = new double[GRID_SIZE][GRID_SIZE];

        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (noiseValues[i][j] > max) max = noiseValues[i][j];
                if (noiseValues[i][j] < min) min = noiseValues[i][j];
            }
        }

        double maxAbsolute = Math.max(Math.abs(min), Math.abs(max));

        for (int j = 0; j < GRID_SIZE; j++) {
            for (int i = 0; i < GRID_SIZE; i++) {
                interpolatedValues[i][j] = (noiseValues[i][j] / maxAbsolute) * 127.5 + 127.5;

                Color color;

                if (interpolatedValues[i][j] < 80) {
                    color = Color.BLUE;
                }
                else if (interpolatedValues[i][j] < 130) {
                    color = Color.GREEN;
                }
                else if (interpolatedValues[i][j] < 170) {
                    color = Color.YELLOW;
                }
                else if (interpolatedValues[i][j] < 200) {
                    color = Color.GRAY;
                }
                else if (interpolatedValues[i][j] < 230) {
                    color = Color.LIGHT_GRAY;
                }
                else {
                    color = Color.WHITE;
                }

//                Color color = new Color((int) interpolatedValues[i][j], (int) interpolatedValues[i][j], (int) interpolatedValues[i][j]);
                g.setColor(color);
                g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }



    }



}
