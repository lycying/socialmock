package info.u250.socialmock.qq;

import info.u250.socialmock.MyClipboardManager;
import info.u250.socialmock.R;
import info.u250.socialmock.ScreenShot;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.activeandroid.query.Select;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.nostra13.universalimageloader.core.ImageLoader;


@SuppressLint("HandlerLeak")
public class QQActivity extends Activity {
	private List<QQMessageEntity> mDataArrays = new ArrayList<QQMessageEntity>();
	QQMessageAdapter mAdapter;
	TextView lblTitleName ;
	View clickableBasicSetting ;
	View clickableMessageEditor;
	View partHeader ;
	View partBottom ;
	BootstrapButton btnOkBasicSetting ;
	BootstrapButton btnOkMessageEditor ;
	BootstrapButton btnOkMessageEditor2 ;
	
	BootstrapEditText txtQQNumberLeft ;
	BootstrapEditText txtQQNumberRight;
	BootstrapEditText txtNickName;
	BootstrapEditText txtMessage ;
	BootstrapButton btnShowImageGrids;
	BootstrapButton btn_type_type;
	boolean btn_type_type_TextMode = true;
	CheckBox cbxIfIsLeft;
	CheckBox cbxIfIsError;
	QQChatEntity qQChatEntity;
	View message_editor_txt_mode;
	View message_editor_time_mode;
	TextView mDateDisplay;
	
	// date and time
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    
    static final int TIME_12_DIALOG_ID = 0;
    static final int TIME_24_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qq_chat);
		setupShake();
		
		final ListView mListView = (ListView) findViewById(R.id.listview);
		mAdapter = new QQMessageAdapter(this, mDataArrays);
		System.out.println(mListView+"@"+mAdapter);
		mListView.setAdapter(mAdapter);
		registerForContextMenu(mListView);
		
		lblTitleName = (TextView)findViewById(R.id.name_label);
		clickableBasicSetting = findViewById(R.id.basic_setting);
		partHeader = findViewById(R.id.input_header);
		partBottom = findViewById(R.id.input_bottom);
		clickableMessageEditor = findViewById(R.id.message_editor);
		btnOkBasicSetting = (BootstrapButton)findViewById(R.id.basic_setting_btn);
		btnOkMessageEditor = (BootstrapButton)findViewById(R.id.message_edtor_btn);
		btnOkMessageEditor2 = (BootstrapButton)findViewById(R.id.message_edtor_btn2);
		txtQQNumberLeft = (BootstrapEditText)findViewById(R.id.head_image_right);
		txtQQNumberRight = (BootstrapEditText)findViewById(R.id.head_image_left);
		txtNickName = (BootstrapEditText)findViewById(R.id.nick_name_txt);
		txtMessage = (BootstrapEditText)findViewById(R.id.message_body);
		cbxIfIsLeft = (CheckBox)findViewById(R.id.if_is_left_cbx);
		cbxIfIsError = (CheckBox)findViewById(R.id.if_is_error_cbx);
		btnShowImageGrids = (BootstrapButton)findViewById(R.id.message_edtor_images);
		btn_type_type = (BootstrapButton)findViewById(R.id.btn_type_type);
		message_editor_time_mode = findViewById(R.id.message_editor_time_mode);
		message_editor_txt_mode = findViewById(R.id.message_editor_txt_mode);
		mDateDisplay = (TextView)findViewById(R.id.txt_date);
		
		setDialogOnClickListener(R.id.click_to_setDate, DATE_DIALOG_ID);
        setDialogOnClickListener(R.id.click_to_setTime, TIME_24_DIALOG_ID);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        updateDisplay();
        
		btnShowImageGrids.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				createExpressionDialog();
			}
		});
		
		partHeader.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				openBasicSettingLayer();
			}
		});
		partBottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				openMessageInputLayer(-1);
			}
		});
		btn_type_type.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btn_type_type_TextMode = !btn_type_type_TextMode;
				if(btn_type_type_TextMode){
					message_editor_txt_mode.setVisibility(View.VISIBLE);
					message_editor_time_mode.setVisibility(View.GONE);
					btn_type_type.setText("点击进入时间模式");
				}else{
					message_editor_txt_mode.setVisibility(View.GONE);
					message_editor_time_mode.setVisibility(View.VISIBLE);
					btn_type_type.setText("点击进入文字模式");
				}
			}
		});
		btnOkBasicSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clickableBasicSetting.setVisibility(View.GONE);
				String toName = txtNickName.getText().toString();
				lblTitleName.setText(toName);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.hideSoftInputFromWindow(btnOkBasicSetting.getWindowToken(), 0);
				String leftImage = txtQQNumberRight.getText().toString() ;
				String rightImage =  txtQQNumberLeft.getText().toString();
				for(QQMessageEntity e :mDataArrays){
					if(e.isLeft()){
						e.setImg(leftImage);
					}else{
						e.setImg(rightImage);
					}
				}
				qQChatEntity.setLeft(leftImage);
				qQChatEntity.setRight(rightImage);
				qQChatEntity.setToName(toName);
				qQChatEntity.setDevice(0);
				qQChatEntity.setChatCount(mDataArrays.size());
				qQChatEntity.save();
				mAdapter.notifyDataSetChanged();
			}
		});
		btnOkMessageEditor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clickableMessageEditor.setVisibility(View.GONE);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.hideSoftInputFromWindow(txtMessage.getWindowToken(), 0);
				String leftImage = txtQQNumberRight.getText().toString();
				String rightImage = txtQQNumberLeft.getText().toString();
				if(to_edit_message_index>=0){
					QQMessageEntity item = mDataArrays.get(to_edit_message_index);
					item.setLeft(cbxIfIsLeft.isChecked());
					item.setError(cbxIfIsError.isChecked());
					item.setName("");
					item.setMessage(txtMessage.getText().toString());
					if(item.isLeft()){
						item.setImg(leftImage);
					}else{
						item.setImg(rightImage);
					}
					item.setDate(false);
					item.save();
				}else{
					QQMessageEntity item = dbCreateOne();
					item.setError(cbxIfIsError.isChecked());
					item.setLeft(cbxIfIsLeft.isChecked());
					item.setName("");
					item.setMessage(txtMessage.getText().toString());
					if(item.isLeft()){
						item.setImg(leftImage);
					}else{
						item.setImg(rightImage);
					}
					item.setDate(false);
					mDataArrays.add(item);
					item.save();
				}
				
				mAdapter.notifyDataSetChanged();
			}
		});
		btnOkMessageEditor2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clickableMessageEditor.setVisibility(View.GONE);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.hideSoftInputFromWindow(txtMessage.getWindowToken(), 0);
				String leftImage = txtQQNumberRight.getText().toString();
				String rightImage = txtQQNumberLeft.getText().toString();
				if(to_edit_message_index>=0){
					QQMessageEntity item = mDataArrays.get(to_edit_message_index);
					item.setLeft(cbxIfIsLeft.isChecked());
					item.setError(cbxIfIsError.isChecked());
					item.setName("");
					item.setMessage(txtMessage.getText().toString());
					if(item.isLeft()){
						item.setImg(leftImage);
					}else{
						item.setImg(rightImage);
					}
					item.setDate(true);
					Calendar c = Calendar.getInstance();
					c.set(mYear, mMonth, mDay, mHour, mMinute);
					item.setCreateDate(c.getTimeInMillis());
					item.save();
				}else{
					QQMessageEntity item = dbCreateOne();
					item.setError(cbxIfIsError.isChecked());
					item.setLeft(cbxIfIsLeft.isChecked());
					item.setName("");
					item.setMessage(txtMessage.getText().toString());
					if(item.isLeft()){
						item.setImg(leftImage);
					}else{
						item.setImg(rightImage);
					}
					item.setDate(true);
					Calendar c = Calendar.getInstance();
					c.set(mYear, mMonth, mDay, mHour, mMinute);
					item.setCreateDate(c.getTimeInMillis());
					mDataArrays.add(item);
					item.save();
				}
				
				mAdapter.notifyDataSetChanged();
			}
		});
		findViewById(R.id.basic_setting_btn_cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clickableMessageEditor.setVisibility(View.GONE);
				clickableBasicSetting.setVisibility(View.GONE);
			}
		});
		setUpData();
	}
	private int imageIds[] = ExpressionUtil.getExpressRcIds();	//保存所有表情资源的id
	private int to_edit_message_index = -1;
	private void setUpData(){
		Intent intent = this.getIntent();
		long id = intent.getLongExtra("id",0);
		qQChatEntity = new Select().from(QQChatEntity.class).where("id=?",id).executeSingle();
		lblTitleName.setText(qQChatEntity.getToName());
		txtQQNumberRight.setText(qQChatEntity.getLeft());
		txtQQNumberLeft.setText(qQChatEntity.getRight());
		txtNickName.setText(qQChatEntity.getToName());		
		List<QQMessageEntity> messages = new Select().from(QQMessageEntity.class).where("parent=?",id).orderBy("id asc").execute();
		if(null!=messages){
			for(QQMessageEntity msg:messages){
				if(msg.isLeft()){
					msg.setImg(qQChatEntity.getLeft());
				}else{
					msg.setImg(qQChatEntity.getRight());
				}
			}
			mDataArrays.addAll(messages);
			mAdapter.notifyDataSetChanged();
		}
	}
	public QQMessageEntity dbCreateOne(){
		QQMessageEntity item = new QQMessageEntity();
		item.setParent(qQChatEntity.getId());
		item.save();
		qQChatEntity.setChatCount(mDataArrays.size());
		qQChatEntity.save();
		return item;
	}
	public void dbDeleteOne(QQMessageEntity item){
		item.delete();
		qQChatEntity.setChatCount(mDataArrays.size());
		qQChatEntity.save();
	}
	
	private void openBasicSettingLayer(){
		if(clickableBasicSetting.getVisibility() == View.VISIBLE){
			clickableBasicSetting.setVisibility(View.GONE);
			clickableMessageEditor.setVisibility(View.GONE);
    		return;
    	}
		clickableMessageEditor.setVisibility(View.GONE);
		clickableBasicSetting.setVisibility(View.VISIBLE);
		txtNickName.setText(lblTitleName.getText());
	}
    private void openMessageInputLayer(int index){
    	if(clickableMessageEditor.getVisibility() == View.VISIBLE){
    		clickableBasicSetting.setVisibility(View.GONE);
			clickableMessageEditor.setVisibility(View.GONE);
    		return;
    	}
    	
    	clickableMessageEditor.setVisibility(View.VISIBLE);
		clickableBasicSetting.setVisibility(View.GONE);
		
		to_edit_message_index = index;
		if(index>=0){
			QQMessageEntity e = mDataArrays.get(index);
		    SpannableString spannableString = ExpressionUtil.getExpressionString(this,e.getMessage()); 
			txtMessage.setText(spannableString);
			cbxIfIsLeft.setChecked(e.isLeft());
			cbxIfIsError.setChecked(e.isError());
			
			btn_type_type_TextMode = !e.isDate();
			
			Calendar c = Calendar.getInstance();
			if(e.getCreateDate()!=0){
				c.setTimeInMillis(e.getCreateDate());
			}
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        mHour = c.get(Calendar.HOUR_OF_DAY);
	        mMinute = c.get(Calendar.MINUTE);
	        updateDisplay();
		}else{
			txtMessage.setText("");
			final Calendar c = Calendar.getInstance();
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        mHour = c.get(Calendar.HOUR_OF_DAY);
	        mMinute = c.get(Calendar.MINUTE);
	        updateDisplay();
		}
		if(btn_type_type_TextMode){
			message_editor_txt_mode.setVisibility(View.VISIBLE);
			message_editor_time_mode.setVisibility(View.GONE);
			btn_type_type.setText("点击进入时间模式");
		}else{
			message_editor_txt_mode.setVisibility(View.GONE);
			message_editor_time_mode.setVisibility(View.VISIBLE);
			btn_type_type.setText("点击进入文字模式");
		}
		
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	if (v.getId()==R.id.listview) {
    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    		menu.setHeaderTitle("作用于:"+mDataArrays.get(info.position).getMessage());
    		String[] menuItems = getResources().getStringArray(R.array.menu_for_qq_item); 
    		for (int i = 0; i<menuItems.length; i++) {
    			menu.add(Menu.NONE, i, i, menuItems[i]);
			}
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
	    switch(menuItemIndex){
	    case 0:
	    	openMessageInputLayer(info.position);
	    	break;
	    case 1:
	    	QQMessageEntity message = mDataArrays.get(info.position);
	    	message.delete();
	    	mDataArrays.remove(message);
	    	mAdapter.notifyDataSetChanged();
	    	break;
	    case 2:
	    	MyClipboardManager.copyToClipboard(this, mDataArrays.get(info.position).getMessage());
	    	break;
	    }
    	return true;
    }
    
    private Dialog builder; 
	private void createExpressionDialog() {
		if(builder!=null){
			builder.show();
			return;
		}
		builder = new Dialog(QQActivity.this);
		GridView gridView = createGridView();
		builder.setContentView(gridView);
		builder.setTitle("默认表情");
		builder.show();
		gridView.setOnItemClickListener(new OnItemClickListener() {	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeResource(getResources(), imageIds[arg2 % imageIds.length]);
				ImageSpan imageSpan = new ImageSpan(QQActivity.this, bitmap);
				String str = null;
				if(arg2 < 10){
					str = "[f00"+arg2+"]";
				}else if(arg2 < 100){
					str = "[f0"+arg2+"]";
				}else{
					str = "[f"+arg2+"]";;
				}
				SpannableString spannableString = new SpannableString(str);
				spannableString.setSpan(imageSpan, 0, str.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				txtMessage.append(spannableString);
				builder.dismiss();
			}
		});
	}
	
	private GridView createGridView() {
		
		GridView gridView  = new GridView(this);
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < 107; i++){
			try {
				if(i<10){
					Field field = R.drawable.class.getDeclaredField("f00" + i);
					int resourceId = Integer.parseInt(field.get(null).toString());
					imageIds[i] = resourceId;
				}else if(i<100){
					Field field = R.drawable.class.getDeclaredField("f0" + i);
					int resourceId = Integer.parseInt(field.get(null).toString());
					imageIds[i] = resourceId;
				}else{
					Field field = R.drawable.class.getDeclaredField("f" + i);
					int resourceId = Integer.parseInt(field.get(null).toString());
					imageIds[i] = resourceId;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	        Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.qq_expression_cell, new String[]{"image"}, new int[]{R.id.image});
		gridView.setAdapter(simpleAdapter);
		gridView.setNumColumns(6);
		gridView.setBackgroundColor(Color.rgb(214, 211, 214));
		gridView.setHorizontalSpacing(1);
		gridView.setVerticalSpacing(1);
		gridView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		gridView.setGravity(Gravity.CENTER);
		return gridView;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(clickableMessageEditor.getVisibility() == View.VISIBLE){
				clickableMessageEditor.setVisibility(View.GONE);
				return false;
			}
			if(clickableBasicSetting.getVisibility() == View.VISIBLE){
				clickableBasicSetting.setVisibility(View.GONE);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void setDialogOnClickListener(int buttonId, final int dialogId) {
        BootstrapButton b = (BootstrapButton) findViewById(buttonId);
        b.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
			public void onClick(View v) {
                showDialog(dialogId);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_12_DIALOG_ID:
            case TIME_24_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, id == TIME_24_DIALOG_ID);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            mDateSetListener,
                            mYear, mMonth, mDay);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case TIME_12_DIALOG_ID:
            case TIME_24_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }    

    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" ")
                    .append(pad(mHour)).append(":")
                    .append(pad(mMinute)));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear,
                        int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    updateDisplay();
                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    
    private SensorManager sensorManager;  
    private Vibrator vibrator;  
    private static final int SENSOR_SHAKE = 10;  
    private Handler handler = new Handler() {  
    	  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            switch (msg.what) {  
            case SENSOR_SHAKE:  
                Toast.makeText(QQActivity.this, "正在截图，请稍后！", Toast.LENGTH_SHORT).show();  
                saveBitmap();
                break;  
            }  
        }  
  
    };  
    long shakePart = 0;
    private SensorEventListener sensorEventListener = new SensorEventListener() {  
        @Override  
        public void onSensorChanged(SensorEvent event) {  
            // 传感器信息改变时执行该方法  
            float[] values = event.values;  
            float x = values[0]; // x轴方向的重力加速度，向右为正  
            float y = values[1]; // y轴方向的重力加速度，向前为正  
            float z = values[2]; // z轴方向的重力加速度，向上为正  
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。  
            int medumValue = 19;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了  
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {  
            	long current = System.currentTimeMillis();
            	if(current-shakePart>1000*3){
            		shakePart = current;
            		vibrator.vibrate(200);  
                    Message msg = new Message();  
                    msg.what = SENSOR_SHAKE;  
                    handler.sendMessage(msg);  
            	}
            }  
        }  
  
        @Override  
        public void onAccuracyChanged(Sensor sensor, int accuracy) {  
  
        }  
    };  
    void setupShake(){
    	sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  
    }
    protected void onResume() {  
    	Toast.makeText(QQActivity.this, "晃动手机既可截屏！", Toast.LENGTH_SHORT).show();  
        super.onResume();  
        if (sensorManager != null) {// 注册监听器  
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);  
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率  
        }  
    }  
  
    @Override  
    protected void onPause() {  
        super.onPause();  
        if (sensorManager != null) {// 取消监听器  
            sensorManager.unregisterListener(sensorEventListener);  
        }  
    }  
    @SuppressWarnings("deprecation")
	void saveBitmap(){
    	File file = new File(ImageLoader.getInstance().getDiskCache().getDirectory(),System.nanoTime()+".png");
    	ScreenShot.shoot(this,file);
    	Intent intent=new Intent(Intent.ACTION_SEND);   
        intent.setType("image/*");   
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");   
        intent.putExtra(Intent.EXTRA_TEXT, "意淫大师：截个屏，留个念，你不来看怎么办");    
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///" + file.getAbsolutePath()));    
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        startActivity(Intent.createChooser(intent, getTitle()));   
    }
}
