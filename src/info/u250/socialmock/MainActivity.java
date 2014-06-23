package info.u250.socialmock;

import info.u250.socialmock.qq.MockData;
import info.u250.socialmock.qq.QQActivity;
import info.u250.socialmock.qq.QQChatEntity;
import info.u250.socialmock.qq.QQListAdapter;
import info.u250.socialmock.qq.QQMessageEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.beardedhen.androidbootstrap.BootstrapButton;

public class MainActivity extends Activity {
	ListView listView;
	public QQListAdapter qqListAdapter;
	public List<QQChatEntity> qqChatEntitys = new ArrayList<QQChatEntity>();
	BootstrapButton btnNew;
	View qq_init_data_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listview);
		qqListAdapter = new QQListAdapter(this, qqChatEntitys);
		listView.setAdapter(qqListAdapter);
		qq_init_data_layout = findViewById(R.id.qq_init_data_layout);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				QQChatEntity e = qqChatEntitys.get(position);
				Intent intent = new Intent(MainActivity.this, QQActivity.class);
				intent.putExtra("id", e.getId());
				startActivity(intent);
			}
		});
		registerForContextMenu(listView);
		btnNew = (BootstrapButton) findViewById(R.id.add_new_chat);
		btnNew.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				qq_init_data_layout.setVisibility(View.GONE);
				
				QQChatEntity e = new QQChatEntity();
				e.setLeft("");
				e.setRight("");
				e.setToName("输入名字");
				e.setCreateDate(new Date().getTime());
				e.save();
				qqChatEntitys.add(e);
				qqListAdapter.notifyDataSetChanged();
				Intent intent = new Intent(MainActivity.this, QQActivity.class);
				intent.putExtra("id", e.getId());
				startActivity(intent);
			}
		});
		init();
		if (qqChatEntitys.size() <= 0) {
			qq_init_data_layout.setVisibility(View.VISIBLE);
			final View imageView = findViewById(R.id.qq_btn_image);
			Animation anim = AnimationUtils.loadAnimation(this,
					R.anim.qq_btn_anim);
			imageView.startAnimation(anim);
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
					dialog.setMessage("等一下哦,马上就好...");
					dialog.setIndeterminate(true);
					dialog.setCancelable(false);
					dialog.show();
					new AsyncTask<Void, Void, Void>() {
						@Override
						protected Void doInBackground(Void... arg0) {
							runOnUiThread(new Runnable() {
								public void run() {
									qq_init_data_layout.setVisibility(View.GONE);
								}
							});
							MockData.addData(MainActivity.this);
							return null;
						}
						@Override
						protected void onPostExecute(Void result) {
							super.onPostExecute(result);
							dialog.dismiss();
						}
					}.execute();
					
				}
			});
		} else {
			qq_init_data_layout.setVisibility(View.GONE);
		}
	}

	int fromIndex = 1511310231;
	int toIndex = 1047620786;

	public void init() {
		// for(int i=0;i<500;i++){
		//
		// QQChatEntity e = new QQChatEntity();
		// e.setFrom(""+fromIndex++);
		// e.setTo(""+toIndex++);
		// e.setDevice(0);
		// e.setToName("大美丽"+toIndex);
		// e.setCreateDate(DateFormat.format("yyy/MM/dd",new
		// java.util.Date()).toString());
		// e.setChatCount(2);
		// e.save();
		// }
		List<QQChatEntity> entitys = new Select().from(QQChatEntity.class)
				.execute();
		qqChatEntitys.addAll(entitys);
	}

	@Override
	protected void onResume() {
		super.onResume();
		qqListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.listview) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle("作用于:"
					+ qqChatEntitys.get(info.position).getToName());
			String[] menuItems = getResources().getStringArray(
					R.array.menu_for_qq_chat);
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		final QQChatEntity e = qqChatEntitys.get(info.position);
		switch (menuItemIndex) {
		case 0:
			Intent intent = new Intent(MainActivity.this, QQActivity.class);
			intent.putExtra("id", e.getId());
			startActivity(intent);
			break;
		case 1:
			qqChatEntitys.remove(e);
			List<QQMessageEntity> messages = new Select().from(QQMessageEntity.class).where("parent=?",e.getId()).orderBy("id asc").execute();
			if(null!=messages){
				for(QQMessageEntity msg:messages){
					msg.delete();
				}
			}
			e.delete();
			qqListAdapter.notifyDataSetChanged();
			break;
		}
		return true;
	}
}
