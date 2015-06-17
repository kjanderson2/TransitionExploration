package kjanderson2.transitionexploration;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by kjanderson2 on 6/11/15.
 */
public class Activity2 extends Activity {

    private final int ANIM_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Explode explode = new Explode();
        explode.setDuration(ANIM_DURATION);
        getWindow().setEnterTransition(explode);

        Fade fade = new Fade();
        fade.setDuration(ANIM_DURATION);
        getWindow().setReturnTransition(fade);

        setContentView(R.layout.activity2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case(android.R.id.home):
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
