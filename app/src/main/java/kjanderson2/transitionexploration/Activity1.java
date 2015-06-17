package kjanderson2.transitionexploration;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kjanderson2 on 6/11/15.
 */
public class Activity1 extends Activity{

    private final int ANIM_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){

            TransitionSet set = new TransitionSet();
            Explode explode = new Explode();
            Fade fade = new Fade();
            set.addTransition(explode);
            set.addTransition(fade);
            set.setDuration(ANIM_DURATION);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setEnterTransition(set);
        }
        setContentView(R.layout.activity1);
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
