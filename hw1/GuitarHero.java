import synthesizer.GuitarString;

public class GuitarHero {
    private GuitarString[] gu = new GuitarString[37];
    private String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        GuitarHero guitar = new GuitarHero();
        for (int i = 0; i < guitar.gu.length; i++) {
            double k = (i - 24) / 12;
            guitar.gu[i] = new GuitarString(440 * Math.pow(2, k));
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int flag = guitar.keyboard.indexOf(key);
                if (flag < 0 || flag >= 37) {
                    flag = 0;
                }
                guitar.gu[flag].pluck();
            }
            double sample = 0.0;
            for (int i = 0; i < guitar.gu.length; i++) {
                sample += guitar.gu[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < guitar.gu.length; i++) {
                guitar.gu[i].tic();
            }
        }
    }
}
