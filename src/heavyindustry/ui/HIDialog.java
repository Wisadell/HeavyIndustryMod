package heavyindustry.ui;

import heavyindustry.ui.dialogs.*;

public final class HIDialog {
    public static PowerGraphInfoDialog powerInfoDialog;

    public static void init(){
        powerInfoDialog = new PowerGraphInfoDialog();
    }
}
