package jewpigeon.apps.newgrounds.GenericLayouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashLayouts.GenericAuthorLayout;

public class GenericMovieFragment extends NG_Fragment {
    private View rootView;

    private Dashboard playerDashboard;
    private Dashboard commentDashboard;
    private Dashboard creditsDashboard;
    private Dashboard authorCommentsDashboard;


    private ImageView ratingReaction;
    private RatingBar ratingBar;
    private ConstraintLayout ratingLayout;
    private GenericAuthorLayout authorsLayout;

    private TextView AuthorCommentsText;

    private String JerryJackson = "https://uploads.ungrounded.net/alternate/57000/57203_alternate_1371.360p.mp4?f1361463301";

    private ImageView GenericPlayer;


    public static GenericMovieFragment newInstance(Bundle args) {
        GenericMovieFragment fragment = new GenericMovieFragment();
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
        rootView = inflater.inflate(R.layout.generic_movie_fragment, container, false);
        setSeekerView(rootView);
        playerDashboard = (Dashboard) findViewById(R.id.generic_movie_player_container);
        commentDashboard = (Dashboard) findViewById(R.id.generic_movie_add_comment);
        creditsDashboard = (Dashboard) findViewById(R.id.generic_movie_credits);
        authorCommentsDashboard = (Dashboard) findViewById(R.id.generic_movie_author_comments);









        ratingReaction = (ImageView) findViewById(R.id.generic_movie_rating_reaction);
        ratingBar = (RatingBar) findViewById(R.id.generic_movie_rating);

        GenericPlayer = (ImageView) findViewById(R.id.generic_movie_player);

        AuthorCommentsText = (TextView) findViewById(R.id.generic_movie_author_comments_text);


        ratingLayout = (ConstraintLayout) findViewById(R.id.generic_movie_rating_layout);


        if (!getNGActivity().getPreferencesFromStore().isUserLogged()) {
            commentDashboard.setVisibility(View.GONE);
        } else {
            commentDashboard.addChildWithSeparator(ratingLayout);
        }

        authorsLayout = (GenericAuthorLayout) findViewById(R.id.generic_movie_authors);
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



        GenericPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(JerryJackson)));
            }
        });

        return rootView;
    }


}
