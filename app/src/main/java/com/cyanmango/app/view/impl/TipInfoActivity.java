package com.cyanmango.app.view.impl;
import com.cyanmango.app.Base.BaseActivity;
import android.os.Bundle;
import com.cyanmango.app.R;
import com.cyanmango.app.model.Item_Bean;
import android.widget.TextView;

public class TipInfoActivity extends BaseActivity
{
	Item_Bean IB;
	TextView title,text,more;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		hideActionBar();
		setContentView(R.layout.tip_info);
		IB = (Item_Bean) getIntent().getSerializableExtra("key");
		title = findViewById(R.id.tipinfoTitle);
		text = findViewById(R.id.tipinfotext);
		more = findViewById(R.id.tipinfomore);
		text.setText(IB.getText());
		title.setText(IB.getUsername());
		more.setText("发布时间" + IB.getTime() + "\n点赞" + IB.getThumb() + "\n喜欢" + IB.getFavorite() + "\n分享" + IB.getShare());
	}

}
