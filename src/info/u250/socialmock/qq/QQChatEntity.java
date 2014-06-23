package info.u250.socialmock.qq;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "qq_entity")
public class QQChatEntity extends Model{
	@Column
	private String left;
	@Column
	private String right;
	@Column(name="to_name")
	private String toName;
	@Column
	private int device;
	@Column
	private long createDate;
	@Column
	private int chatCount;
	
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public int getDevice() {
		return device;
	}
	public void setDevice(int device) {
		this.device = device;
	}
	
	public int getChatCount() {
		return chatCount;
	}
	public void setChatCount(int chatCount) {
		this.chatCount = chatCount;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	
}
