package spring.android.pradeepms;

import java.util.List;

import spring.android.pradeepms.Payload.Article;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Article> {
	private LayoutInflater infalter;
	private List<Article> article;
	private Context context;

	public CustomAdapter(Context context, List<Article> article) {
		super(context, 0, article);
		// TODO Auto-generated constructor stub
		this.article = article;
		this.infalter = LayoutInflater.from(context);
		this.context = context;
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
			//holder.timeStamp = (TextView) row.findViewById(R.id.timeStamp);
			row.setTag(holder);
		} else {
			holder = (WeatherHolder) row.getTag();
		}
		/*String []str = article.get(position).getTimestamp().split(" ");
		holder.timeStamp.setText(str[0]);*/
		Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
		holder.txtTitle.setTypeface(myTypeface);
		holder.txtTitle.setText(article.get(position).getHeadline());

		return row;
	}

	static class WeatherHolder {
		TextView txtTitle;
/*		TextView timeStamp;*/
	}

}
