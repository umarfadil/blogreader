package com.pratamawijaya.blog.ui.detail;

import android.os.Bundle;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.pratamawijaya.blog.R;
import com.pratamawijaya.blog.base.BaseActivity;
import com.pratamawijaya.blog.model.pojo.Post;
import com.pratamawijaya.blog.presenter.detail.DetailPresenter;
import javax.inject.Inject;
import org.sufficientlysecure.htmltextview.DrawTableLinkSpan;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class DetailArticleActivity extends BaseActivity implements DetailArticleInterface {

  @Bind(R.id.html_textview) HtmlTextView text;

  @Inject DetailPresenter presenter;
  private Post post;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_article);
    getActivityComponent().inject(this);
    ButterKnife.bind(this);
    presenter.attachView(this);
    post = getIntent().getExtras().getParcelable("data");

    getSupportActionBar().setTitle(post.title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    text.setRemoveFromHtmlSpace(true);
    //text.setClickableTableSpan(new ClickableTableSpanImpl());
    DrawTableLinkSpan drawTableLinkSpan = new DrawTableLinkSpan();
    drawTableLinkSpan.setTableLinkText("[tap for table]");
    text.setDrawTableLinkSpan(drawTableLinkSpan);

    presenter.getArticleDetail(post.id, false);
  }

  @Override public void setData(Post post) {
    text.setHtmlFromString(post.content,new HtmlTextView.RemoteImageGetter());
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}