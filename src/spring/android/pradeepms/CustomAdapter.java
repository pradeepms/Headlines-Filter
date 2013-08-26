package spring.android.pradeepms;

import java.util.List;

import spring.android.pradeepms.R;
import spring.android.pradeepms.Payload.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Article> {
	private LayoutInflater infalter;
	List<Article> article;

	public CustomAdapter(Context context, List<Article> article) {
		super(context, 0, article);
		// TODO Auto-generated constructor stub
		this.article = article;
		this.infalter = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View row = convertView;
		WeatherHolder holder = null;
		if (row == null) {
			row = infalter.inflate(R.layout.row_item, parent, false);
			holder = new WeatherHolder();

			holder.txtTitle = (TextView) row.findViewById(R.id.rowText);
			row.setTag(holder);
		} else {
			holder = (WeatherHolder) row.getTag();
		}

		holder.txtTitle.setText(article.get(position).getHeadline());

		return row;
	}

	static class WeatherHolder {
		TextView txtTitle;
	}

}
