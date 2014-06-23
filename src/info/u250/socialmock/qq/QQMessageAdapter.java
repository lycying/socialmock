package info.u250.socialmock.qq;

import info.u250.socialmock.R;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class QQMessageAdapter extends ArrayAdapter<QQMessageEntity> {
	private static final int ITEMCOUNT = 2;
	private LayoutInflater mInflater;

	public QQMessageAdapter(Context context, List<QQMessageEntity> coll) {
		super(context, 0, coll);
		mInflater = LayoutInflater.from(context);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getViewTypeCount() {
		return ITEMCOUNT;
	}
	private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
	public View getView(int position, View convertView, ViewGroup parent) {
		QQMessageEntity entity = getItem(position);

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.qq_msg_text, null);

			viewHolder = new ViewHolder();
			viewHolder.left = convertView.findViewById(R.id.qq_msg_text_left);
			viewHolder.right = convertView.findViewById(R.id.qq_msg_text_right);
			viewHolder.name = (TextView) convertView.findViewById(R.id.tv_username);
			viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_userhead);
			viewHolder.message = (TextView) convertView.findViewById(R.id.tv_chatcontent);
			viewHolder.message.setOnCreateContextMenuListener(null);// contentView
			viewHolder.error = convertView.findViewById(R.id.error);
			
			viewHolder.name_2 = (TextView) convertView.findViewById(R.id.tv_username_2);
			viewHolder.icon_2 = (ImageView) convertView.findViewById(R.id.iv_userhead_2);
			viewHolder.message_2 = (TextView) convertView.findViewById(R.id.tv_chatcontent_2);
			viewHolder.message_2.setOnCreateContextMenuListener(null);// contentView
			viewHolder.error_2 = convertView.findViewById(R.id.error_2);
			viewHolder.qq_msg_time = convertView.findViewById(R.id.qq_msg_time);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if(entity.isDate()){
			viewHolder.qq_msg_time.setVisibility(View.VISIBLE);
			viewHolder.left.setVisibility(View.GONE);
			viewHolder.right.setVisibility(View.GONE);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(entity.getCreateDate());
//	        int mYear = c.get(Calendar.YEAR);
	        int mMonth = c.get(Calendar.MONTH);
	        int mDay = c.get(Calendar.DAY_OF_MONTH);
	        int mHour = c.get(Calendar.HOUR_OF_DAY);
	        int mMinute = c.get(Calendar.MINUTE);
	        StringBuilder builder = new StringBuilder();
	        builder.append(pad(mMonth+1));
	        builder.append("-");
	        builder.append(pad(mDay));
	        builder.append(" ");
	        builder.append(pad(mHour%12));
	        builder.append(":");
	        builder.append(pad(mMinute));
	        builder.append(" ");
	        if(mHour>=12){
	        	builder.append("下午");
	        }else{
	        	builder.append("上午");
	        }
			TextView.class.cast(viewHolder.qq_msg_time).setText(builder.toString());

		}else{
			viewHolder.qq_msg_time.setVisibility(View.GONE);
			if(entity.isLeft()){
				viewHolder.left.setVisibility(View.VISIBLE);
				viewHolder.right.setVisibility(View.GONE);
				viewHolder.name.setText(entity.getName());
				SpannableString spannableString = ExpressionUtil.getExpressionString(convertView.getContext(), entity.getMessage());
				viewHolder.message.setText(spannableString);
				ImageLoader.getInstance().displayImage(SystemConstant.imageURLfromQQ(entity.getImg()), viewHolder.icon);
			}else{
				viewHolder.left.setVisibility(View.GONE);
				viewHolder.right.setVisibility(View.VISIBLE);
				viewHolder.name_2.setText(entity.getName());
				SpannableString spannableString = ExpressionUtil.getExpressionString(convertView.getContext(), entity.getMessage());
				viewHolder.message_2.setText(spannableString);
				ImageLoader.getInstance().displayImage(SystemConstant.imageURLfromQQ(entity.getImg()), viewHolder.icon_2);
			}
			if(entity.isError()){
				viewHolder.error.setVisibility(View.VISIBLE);
				viewHolder.error_2.setVisibility(View.VISIBLE);
			}else{
				viewHolder.error.setVisibility(View.GONE);
				viewHolder.error_2.setVisibility(View.GONE);
			}
		}
		return convertView;
	}

	static class ViewHolder {
		public View left;
		public View right;
		
		public TextView name;
		public TextView message;
		public ImageView icon;
		
		public TextView name_2;
		public TextView message_2;
		public ImageView icon_2;
		
		public View error;
		public View error_2;
		public View qq_msg_time;
	}

}
