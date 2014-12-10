package com.example.tgentile42.cliq;


        import java.util.Arrays;
        import java.util.List;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.facebook.Session;
        import com.facebook.SessionState;
        import com.facebook.UiLifecycleHelper;
        import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my, container, false);

        return view;
    }


}
