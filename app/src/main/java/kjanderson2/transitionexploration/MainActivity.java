package kjanderson2.transitionexploration;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener{

    private LinearLayout activity1Layout;
    private LinearLayout activity2Layout;
    private LinearLayout activity3Layout;
    private LinearLayout activity4Layout;
    private LinearLayout activity5Layout;
    private LinearLayout activity6Layout;
    private LinearLayout activity7Layout;
    private final int ANIM_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=21) {
            setupWindowAnimations(7);
        }
        setContentView(R.layout.activity_main);

        // Initialize the clickable containers
        activity1Layout = (LinearLayout) findViewById(R.id.Activity1selector);
        activity2Layout = (LinearLayout) findViewById(R.id.Activity2selector);
        activity3Layout = (LinearLayout) findViewById(R.id.Activity3selector);
        activity4Layout = (LinearLayout) findViewById(R.id.Activity4selector);
        activity5Layout = (LinearLayout) findViewById(R.id.Activity5selector);
        activity6Layout = (LinearLayout) findViewById(R.id.Activity6selector);
        activity7Layout = (LinearLayout) findViewById(R.id.Activity7selector);

        // Set onClickListeners on each container
        activity1Layout.setOnClickListener(this);
        activity2Layout.setOnClickListener(this);
        activity3Layout.setOnClickListener(this);
        activity4Layout.setOnClickListener(this);
        activity5Layout.setOnClickListener(this);
        activity6Layout.setOnClickListener(this);
        activity7Layout.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()){

            // Activity 1 is basic Content Transition
            case(R.id.Activity1selector):
                Intent intent1 = new Intent(MainActivity.this, Activity1.class);
                ActivityOptions option1 = ActivityOptions.makeSceneTransitionAnimation(this, null);
                startActivity(intent1, option1.toBundle());
                break;

            // Activity 2 is basic Content Transition with different return transition
            case(R.id.Activity2selector):
                Intent intent2 = new Intent(MainActivity.this, Activity2.class);
                ActivityOptions option2 = ActivityOptions.makeSceneTransitionAnimation(this, null);
                startActivity(intent2, option2.toBundle());
                break;

            // Activity 3 is Shared Element transition with single element
            case(R.id.Activity3selector):
                Intent intent3 = new Intent(MainActivity.this, Activity3.class);
                ActivityOptions option3 = ActivityOptions.makeSceneTransitionAnimation(this,
                        findViewById(R.id.imageActivity3), "img3");
                startActivity(intent3, option3.toBundle());
                break;

            // Activity 4 is Shared Element transition with multiple elements
            case(R.id.Activity4selector):
                Intent intent4 = new Intent(MainActivity.this, Activity4.class);
                ActivityOptions option4 = ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create(findViewById(R.id.imageActivity4), "img4"),
                        Pair.create(findViewById(R.id.textActivity4), "text4"));
                startActivity(intent4, option4.toBundle());
                break;

            // Activity 5 is circular reveal transition with unintuitive y-placement
            case(R.id.Activity5selector):
                Intent intent5 = new Intent(MainActivity.this, Activity5.class);
                ActivityOptions option5 = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(findViewById(R.id.imageActivity5), "img5"));
                startActivity(intent5, option5.toBundle());
                break;

            // Activity 6 is circular reveal transition with intuitive y-placement
            case(R.id.Activity6selector):
                Intent intent6 = new Intent(MainActivity.this, Activity6.class);
                ActivityOptions option6 = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(findViewById(R.id.imageActivity6), "img6"),
                        Pair.create(findViewById(R.id.textActivity6), "text6"));
                startActivity(intent6, option6.toBundle());
                break;

            // Activity 7 has no transition specified
            case(R.id.Activity7selector):
                Intent intent7 = new Intent(MainActivity.this, Activity7.class);
                startActivity(intent7);
                break;
        }
    }

    private void setupWindowAnimations(int activityNumber){
        switch(activityNumber){
            case(1):
                Slide slide = new Slide();
                slide.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(slide);
                break;
            case(2):
                Explode explode = new Explode();
                explode.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(explode);

                Fade fade = new Fade();
                fade.setDuration(ANIM_DURATION);
                getWindow().setReenterTransition(fade);
                break;
            case(3):
                Slide slide3 = new Slide();
                slide3.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(slide3);
                getWindow().setReenterTransition(slide3);
                break;
            case(4):
                Explode explode4 = new Explode();
                explode4.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(explode4);

                Slide slide4 = new Slide();
                slide4.setDuration(ANIM_DURATION);
                getWindow().setReenterTransition(slide4);


                break;
            case(5):
                Slide slide5 = new Slide();
                slide5.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(slide5);
                break;
            case(6):
                Fade fade6 = new Fade();
                fade6.setDuration(ANIM_DURATION);
                getWindow().setExitTransition(fade6);
                break;
            case(7):
                break;
        }
    }


    // This method handles the clicking of the "Hide all/Show all" button created to demonstrate
    // transitions when not between activities.
    public void toggleClicked(View v){
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.activity_container);
        Slide slide = new Slide();
        slide.setDuration(ANIM_DURATION);
        TransitionManager.beginDelayedTransition(viewGroup, slide);
        toggleVisibility(activity1Layout, activity2Layout, activity3Layout, activity4Layout,
                activity5Layout, activity6Layout, activity7Layout);
    }

    // Helper method for toggleClicked to change visibility of items.
    private void toggleVisibility(View... views){
        for(View current : views){
            if(current.getVisibility() == View.VISIBLE){
                current.setVisibility(View.INVISIBLE);
            } else {
                current.setVisibility(View.VISIBLE);
            }
        }
    }


}
