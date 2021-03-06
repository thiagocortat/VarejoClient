package br.com.thiagocortat.mylibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import br.com.thiagocortat.mylibrary.R;
import br.com.thiagocortat.mylibrary.interfaces.OnLayoutInjectListener;
import br.com.thiagocortat.mylibrary.model.ViewModel;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;


public class BaseActivity extends AppCompatActivity implements OnLayoutInjectListener {


    protected ViewGroup newRoot;
    protected View mEmptyView;
    protected View mEmptyImageView;
    protected View mEmptyContainer;
    protected View mProgressContainer;
    protected int rootCount;
    protected CircularProgressBar mCircularProgressBar;
    private boolean mIsContentEmpty;

    public static final String EXTRA_IMAGE = "br.com.extraImage";
    public static final String EXTRA_TITLE = "br.com.extraTitle";

    public static void navigate(AppCompatActivity activity, View transitionImage, ViewModel viewModel) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, viewModel.getImage());
        intent.putExtra(EXTRA_TITLE, viewModel.getText());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
            onBeforeInjectViews(savedInstanceState);
            ButterKnife.inject(this);
            onAfterInjectViews(savedInstanceState);
        }

    }

//    @Override
//    public void onStart() {
//        try {
//            super.onStart();
//
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
////            FontsUtil.setRobotoFont(this, (ViewGroup) getWindow().getDecorView());
//        }catch (Exception e) {}
//
//    }

    public void showProgress() {

        if (findViewById(R.id.frameLayout) != null) {
           showProgress(R.id.frameLayout);
        } else {
            showProgress(android.R.id.content);
        }

    }

    public void showProgress(@IdRes int resourceId) {

        if (newRoot == null) {
            ViewGroup root;
            if (findViewById(resourceId) != null) {
                root = (ViewGroup) findViewById(resourceId);
            } else {
                root = (ViewGroup) findViewById(android.R.id.content);
            }

            inflateAndRunProgress(root);
        }

    }

    public void hideProgress() {
        try {
            if (newRoot != null && !mIsContentEmpty) {
                View addedView = newRoot.getChildAt(rootCount);
                newRoot.removeView(addedView);
                newRoot = null;
            }
        } catch (Exception e) {
        }
    }


    protected void inflateAndRunProgress(ViewGroup root) {
        rootCount = root.getChildCount();
        newRoot = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_progress, root, true);

        mProgressContainer = newRoot.findViewById(R.id.progress_container);
        mCircularProgressBar = (CircularProgressBar) newRoot.findViewById(R.id.my_animation);
        ((CircularProgressDrawable)mCircularProgressBar.getIndeterminateDrawable()).start();

        mEmptyContainer = newRoot.findViewById(R.id.emptyContainer);
        mEmptyImageView = newRoot.findViewById(R.id.imageEmpty);
        mEmptyView = newRoot.findViewById(android.R.id.empty);
        if (mEmptyContainer != null) {
            mEmptyContainer.setVisibility(View.GONE);
//            mEmptyView.setVisibility(View.GONE);
//            mEmptyImageView.setVisibility(View.GONE);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * The default content for a ProgressFragment has a TextView that can be shown when
     * the content is empty {@link #setContentEmpty(boolean)}.
     * If you would like to have it shown, call this method to supply the text it should use.
     *
     * @param resId Identification of string from a resources
     * @see #setEmptyText(CharSequence)
     */
    public void setEmptyText(int resId) {
        setEmptyText(getString(resId));
    }

    /**
     * The default content for a ProgressFragment has a TextView that can be shown when
     * the content is empty {@link #setContentEmpty(boolean)}.
     * If you would like to have it shown, call this method to supply the text it should use.
     *
     * @param text Text for empty view
     * @see #setEmptyText(int)
     */
    public void setEmptyText(CharSequence text) {
        showProgress();
        if (mEmptyView != null && mEmptyView instanceof TextView) {
            ((TextView) mEmptyView).setText(text);
        } else {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
    }

    /**
     * If the content is empty, then set true otherwise false. The default content is not empty.
     * You can't call this method if the content view has not been initialized before
     * {@link #setContentEmpty(boolean isEmpty)} and content view not null.
     *
     * @param isEmpty true if content is empty else false
     * @see #isContentEmpty()
     */
    public void setContentEmpty(boolean isEmpty) {
        showProgress();
        if (newRoot == null) {
            throw new IllegalStateException("Content view must be initialized before");
        }
        if (isEmpty) {
//            mEmptyView.setVisibility(View.VISIBLE);
//            mEmptyImageView.setVisibility(View.VISIBLE);
            mEmptyContainer.setVisibility(View.VISIBLE);

            mProgressContainer.setVisibility(View.GONE);
        } else {
//            mEmptyView.setVisibility(View.GONE);
//            mEmptyImageView.setVisibility(View.GONE);
            mEmptyContainer.setVisibility(View.GONE);

            mProgressContainer.setVisibility(View.VISIBLE);
        }
        mIsContentEmpty = isEmpty;
    }

    /**
     * Returns true if content is empty. The default content is not empty.
     *
     * @return true if content is null or empty
     * @see #setContentEmpty(boolean)
     */
    public boolean isContentEmpty() {
        return mIsContentEmpty;
    }


    /**
     * Set the ActionBar subtitle
     *
     * @param subtitle with string of title
     */
    public void setActionBarSubtitle(String subtitle){
        getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * Set the ActionBar subtitle
     *
     * @param resSubtitle with id of subtitle
     */
    public void setActionBarSubtitle(int resSubtitle){
        getSupportActionBar().setSubtitle(resSubtitle);
    }

    /**
     * Return root view from Activity
     */
    public View getView() {
        return findViewById(android.R.id.content).getRootView();
    }

    @Override
    public int getLayoutResource() {
        return 0;
    }

    @Override
    public void onBeforeInjectViews(Bundle savedInstanceState) {}

    @Override
    public void onAfterInjectViews(Bundle savedInstanceState) {}
}
