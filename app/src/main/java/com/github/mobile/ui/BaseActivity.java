/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.kevinsawicki.wishlist.ViewFinder;
import com.github.kevinsawicki.wishlist.ViewUtils;
import com.github.mobile.R;
import com.github.mobile.ui.roboactivities.RoboActionBarActivity;
import com.github.mobile.util.HttpImageGetter;
import com.github.mobile.util.SourceEditor;
import com.github.mobile.util.ToastUtils;
import com.google.inject.Inject;

import org.eclipse.egit.github.core.util.EncodingUtils;

import java.io.Serializable;

import org.eclipse.egit.github.core.Blob;

/**
 * Base sherlock activity
 */
public class BaseActivity extends RoboActionBarActivity implements
        LoaderManager.LoaderCallbacks<CharSequence> {
    protected ProgressBar loadingBar;
    protected WebView codeView;
    protected Blob blob;
    protected String renderedMarkdown;
    protected MenuItem markdownItem;
    protected SourceEditor editor;
    protected String file;

    protected static final String ARG_TEXT = "text";
    @Inject
    protected HttpImageGetter imageGetter;
    /**
     * Finder bound to this activity's view
     */
    protected ViewFinder finder;

    @Override
    public Loader<CharSequence> onCreateLoader(int loader, Bundle args) {
        final String raw = args.getString(ARG_TEXT);
        return new MarkdownLoader(this, null, raw, imageGetter, false);
    }

    @Override
    public void onLoadFinished(Loader<CharSequence> loader,
                               CharSequence rendered) {
        if (rendered == null)
            ToastUtils.show(this, R.string.error_rendering_markdown);

        ViewUtils.setGone(loadingBar, true);
        ViewUtils.setGone(codeView, false);

        if (!TextUtils.isEmpty(rendered)) {
            renderedMarkdown = rendered.toString();
            if (markdownItem != null)
                markdownItem.setEnabled(true);
            editor.setMarkdown(true).setSource(file, renderedMarkdown, false);
        }
    }

    @Override
    public void onLoaderReset(Loader<CharSequence> loader) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finder = new ViewFinder(this);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return serializable
     */
    @SuppressWarnings("unchecked")
    protected <V extends Serializable> V getSerializableExtra(final String name) {
        return (V) getIntent().getSerializableExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int
     */
    protected int getIntExtra(final String name) {
        return getIntent().getIntExtra(name, -1);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string
     */
    protected String getStringExtra(final String name) {
        return getIntent().getStringExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string array
     */
    protected String[] getStringArrayExtra(final String name) {
        return getIntent().getStringArrayExtra(name);
    }
    protected void loadMarkdown() {
        ViewUtils.setGone(loadingBar, false);
        ViewUtils.setGone(codeView, true);

        String markdown = new String(
                EncodingUtils.fromBase64(blob.getContent()));
        Bundle args = new Bundle();
        args.putCharSequence(ARG_TEXT, markdown);
        getSupportLoaderManager().restartLoader(0, args, this);
    }
}
