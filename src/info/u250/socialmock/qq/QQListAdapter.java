package info.u250.socialmock.qq;

import info.u250.socialmock.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QQListAdapter extends ArrayAdapter<QQChatEntity> {
	private static final int ITEMCOUNT = 2;
	private LayoutInflater mInflater;

	public QQListAdapter(Context context, List<QQChatEntity> coll) {
		super(context, 0, coll);
		mInflater = LayoutInflater.from(context);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getViewTypeCount() {
		return ITEMCOUNT;
	}

	@SuppressLint("SimpleDateFormat")
	public View getView(int position, View convertView, ViewGroup parent) {
		QQChatEntity entity = getItem(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.qq_list_item, null);

			viewHolder = new ViewHolder();
			viewHolder.from = (ImageView)convertView.findViewById(R.id.from_head);
			viewHolder.to = (ImageView)convertView.findViewById(R.id.to_head);
			viewHolder.toName = (TextView)convertView.findViewById(R.id.to_name);
			viewHolder.createDate = (TextView)convertView.findViewById(R.id.create_date);
			viewHolder.count_number = (TextView)convertView.findViewById(R.id.count_number);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.toName.setText(entity.getToName());
		viewHolder.createDate.setText(new SimpleDateFormat("yyy/MM/dd HH:mm:ss").format(new Date(entity.getCreateDate())));
		viewHolder.count_number.setText("("+entity.getChatCount()+")");
		ImageLoader.getInstance().displayImage(SystemConstant.imageURLfromQQ(entity.getLeft()), viewHolder.from);
		ImageLoader.getInstance().displayImage(SystemConstant.imageURLfromQQ(entity.getRight()), viewHolder.to);

		return convertView;
	}

	static class ViewHolder {
		public ImageView from;
		public ImageView to;
		public TextView toName;
		public TextView createDate;
		public TextView count_number;
	}

}
