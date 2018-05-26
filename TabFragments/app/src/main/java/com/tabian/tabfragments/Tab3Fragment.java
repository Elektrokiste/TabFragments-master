package com.tabian.tabfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";
    WebView webView;
    private Button btnTEST;
    private boolean i = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);




        btnTEST = (Button) view.findViewById(R.id.Load);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Lade Termine...",Toast.LENGTH_SHORT).show();
                webView = (WebView) getView().findViewById(R.id.webView);
                //webView.loadUrl("https://www.pietsmiet.de/news/uploadplan/4212-upload-plan-am-16-05-2018");
                //https://www.pietsmiet.de/news/uploadplan/4212-upload-plan-am-16-05-2018
                webView.loadUrl("https://elektrokiste.000webhostapp.com/SLmanager/Term.html");
            }
        //webView = (WebView) getView().findViewById(R.id.webView);
        //webView.loadUrl("https://elektrokiste.000webhostapp.com/SLmanager/Plan.html");
        });
        return view;
    }
}
