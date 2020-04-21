package com.cyanmango.app.view;
import com.cyanmango.app.model.Item_Bean;
import java.util.List;

public interface RVview extends BaseView<Item_Bean>
{
	void showRecyclerView(List<Item_Bean> tList);
	
	void onRefresh()
}
