package kjanderson2.transitionexploration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

// This activity demonstrates a custom circular reveal that reveals outwards from the shared
// elements.
public class Activity6 extends Activity {

    private static final long ANIM_DURATION = 2000;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Animation for shared elements
        ChangeBounds bound = new ChangeBounds();
        bound.setDuration(1000);
        getWindow().setSharedElementEnterTransition(bound);


        setContentView(R.layout.activity6);
        contentView = findViewById(R.id.backgroundActivity6);

        // Set everything up for the reveal
        setupEnterAnimations();
        setupExitAnimations();
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

    // This method sets up the circular reveal when entering the activity. All internal methods
    // are @Override by requirement.
    private void setupEnterAnimations() {
        Transition enterTransition = getWindow().getSharedElementEnterTransition();
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {}

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealShow(contentView);
            }

            @Override
            public void onTransitionCancel(Transition transition) {}

            @Override
            public void onTransitionPause(Transition transition) {}

            @Override
            public void onTransitionResume(Transition transition) {}
        });
    }

    // This method sets up the circular reveal for leaving the activity. A delay must be set so that
    // the animation occurs at the proper timing.
    private void setupExitAnimations() {
        Transition sharedElementReturnTransition = getWindow().getSharedElementReturnTransition();
        sharedElementReturnTransition.setStartDelay(ANIM_DURATION);

        Transition returnTransition = getWindow().getReturnTransition();
        returnTransition.setDuration(ANIM_DURATION);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                animateRevealHide(contentView);
            }

            @Override
            public void onTransitionEnd(Transition transition) {}

            @Override
            public void onTransitionCancel(Transition transition) {}

            @Override
            public void onTransitionPause(Transition transition) {}

            @Override
            public void onTransitionResume(Transition transition) {}
        });
    }

    // This method actually creates the animated reveal by specifying the parameters. Notice that
    // the y parameter (cy) is focused on the shared element image so the reveal ripples out from
    // that element, making a more natural reveal.
    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;   //centered horizontal
        View imgView = findViewById(R.id.imageView);
        int cy = (imgView.getTop() + imgView.getBottom())/2;
        int finalRadius = Math.abs(viewRoot.getBottom() - cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(ANIM_DURATION);  //Duration is only doubled for demonstration's sake
        anim.start();
    }


    // This method actually creates the backwards animated reveal by switching the beginning and
    // ending radii.
    private void animateRevealHide(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        View imgView = findViewById(R.id.imageView);
        int cy = (imgView.getTop() + imgView.getBottom())/2;
        int initialRadius = Math.abs(viewRoot.getBottom() - cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {  //Listens for end of animation to ensure proper timing
                super.onAnimationEnd(animation);
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }
}
