package jewpigeon.apps.newgrounds.GenericLayouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;

public class GenericArtFragment extends NG_Fragment {
    private View rootView;

    private Dashboard containerDashboard;
    private Dashboard commentDashboard;
    private Dashboard creditsDashboard;
    private ImageView ratingReaction;
    private RatingBar ratingBar;
    private ConstraintLayout ratingLayout;
    private ConstraintLayout authorsLayout;



    private ImageView GenericArtView;


    public static GenericArtFragment newInstance(Bundle args) {
        GenericArtFragment fragment = new GenericArtFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.generic_art_fragment, container, false);
        setSeekerView(rootView);
        containerDashboard = (Dashboard) findViewById(R.id.generic_art_player_container);
        commentDashboard = (Dashboard) findViewById(R.id.generic_art_add_comment);
        creditsDashboard = (Dashboard) findViewById(R.id.generic_art_credits);

        ratingReaction = (ImageView) findViewById(R.id.generic_art_rating_reaction);
        ratingBar = (RatingBar) findViewById(R.id.generic_art_rating);

        GenericArtView = (ImageView) findViewById(R.id.generic_art_view);

        ratingLayout = (ConstraintLayout) findViewById(R.id.generic_art_rating_layout);
        if (!getNGActivity().getPreferencesFromStore().isUserLogged()) {
            commentDashboard.setVisibility(View.GONE);
        } else {
            commentDashboard.addChildWithSeparator(ratingLayout);
        }

        authorsLayout = (ConstraintLayout) findViewById(R.id.generic_art_authors);
        creditsDashboard.addChildWithSeparator(authorsLayout);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v == 0) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_0));
                } else if (v == 0.5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_0_5));
                } else if (v == 1) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_1));
                } else if (v == 1.5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_1_5));
                } else if (v == 2) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_2));
                } else if (v == 2.5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_2_5));
                } else if (v == 3) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_3));
                } else if (v == 3.5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_3_5));
                } else if (v == 4) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_4));
                } else if (v == 4.5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_4_5));
                } else if (v == 5) {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_5));
                } else {
                    ratingReaction.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_rating_4));
                }

            }
        });
        return rootView;
    }
}
