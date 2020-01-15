package empty_package;

import processing.core.PApplet;

public class EmptyProject extends PApplet {

    public void settings() {
        size(512, 512);
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"empty_package.EmptyProject"};
        PApplet.main(appletArgs);
    }
}
