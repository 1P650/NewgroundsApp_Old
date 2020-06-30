package ferox.ip.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ferox.ip.newgrounds.Fundamental.NG_Fragment;
import ferox.ip.newgrounds.R;

public class AudioPortal extends NG_Fragment {
    View rootView;
    public static AudioPortal newInstance() {

        Bundle args = new Bundle();

        AudioPortal fragment = new AudioPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_audio, container, false);
        return rootView;
    }
}
