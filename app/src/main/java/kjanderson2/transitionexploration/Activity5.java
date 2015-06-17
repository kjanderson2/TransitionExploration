package kjanderson2.transitionexploration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;


public class Activity5 extends Activity {

    private static final long ANIM_DURATION = 2000;
    private View bgViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeBounds bound = new ChangeBounds();
        bound.setDuration(ANIM_DURATION);
        getWindow().setSharedElementEnterTransition(bound);

        ChangeBounds bound2 = new ChangeBounds();
        bound2.setDuration(ANIM_DURATION);
        getWindow().setSharedElementReturnTransition(bound2);

        //Fade fade = new Fade();
        //fade.setDuration(ANIM_DURATION);
        //getWindow().setReturnTransition(fade);

        Explode expl = new Explode();
        expl.setDuration(ANIM_DURATION);
        getWindow().setReturnTransition(expl);

        setContentView(R.layout.activity5);
        setupLayout();
        setupWindowAnimations();
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

    private void setupLayout() {
        bgViewGroup = findViewById(R.id.backgroundActivity5);
    }

    private void setupWindowAnimations() {
        setupEnterAnimations();
        setupExitAnimations();
    }

    private void setupEnterAnimations() {
        Transition enterTransition = getWindow().getSharedElementEnterTransition();
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {animateRevealShow(bgViewGroup);}

            @Override
            public void onTransitionEnd(Transition transition){}

            @Override
            public void onTransitionCancel(Transition transition) {}

            @Override
            public void onTransitionPause(Transition transition) {}

            @Override
            public void onTransitionResume(Transition transition) {}
        });
    }

    private void setupExitAnimations() {
        Transition returnTransition = getWindow().getReturnTransition();
        returnTransition.setDuration(ANIM_DURATION);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {animateRevealHide(bgViewGroup);
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

    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    private void animateRevealHide(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int initialRadius = viewRoot.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.d("BLAH", "You made it!");
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });

        anim.setDuration(ANIM_DURATION);
        anim.start();
    }
}