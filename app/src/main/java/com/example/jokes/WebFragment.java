package com.example.jokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WebFragment extends Fragment {
    private WebView webView;
    private WebViewClient webViewClient;
    private WebChromeClient webChromeClient;
    private final String URL = "http://www.icndb.com/api/";

    public static WebFragment newInstance() {
        WebFragment fragment = new WebFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        webView = view.findViewById(R.id.webView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            webViewClient = new WebViewClient();
            webChromeClient = new WebChromeClient();
            webView.loadUrl(URL);
        } else {
            webView.restoreState(savedInstanceState);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
}
