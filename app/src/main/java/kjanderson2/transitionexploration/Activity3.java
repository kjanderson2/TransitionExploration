package kjanderson2.transitionexploration;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * Created by kjanderson2 on 6/11/15.
 */
public class Activity3 extends Activity{

    private final int ANIM_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(ANIM_DURATION);
        bounds.setInterpolator(new AccelerateDecelerateInterpolator());
        Fade fade = new Fade();
        fade.setDuration(ANIM_DURATION);
        getWindow().setEnterTransition(fade);
        getWindow().setSharedElementEnterTransition(bounds);

        setContentView(R.layout.activity3);
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
