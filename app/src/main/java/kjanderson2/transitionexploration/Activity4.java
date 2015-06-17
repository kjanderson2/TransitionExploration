package kjanderson2.transitionexploration;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * Created by kjanderson2 on 6/11/15.
 */
public class Activity4 extends Activity{

    private final int ANIM_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(ANIM_DURATION);
        bounds.setInterpolator(new BounceInterpolator());
        getWindow().setSharedElementEnterTransition(bounds);
        getWindow().setSharedElementReturnTransition(bounds);
        setContentView(R.layout.activity4);
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
