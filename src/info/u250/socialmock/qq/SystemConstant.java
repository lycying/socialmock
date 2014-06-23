package info.u250.socialmock.qq;


public class SystemConstant {
	public final static int express_counts = 107;
	public static String imageURLfromQQ(String qq){
		if(qq==null && "".equals(qq)) return null;
		String leftImage = "http://q.qlogo.cn/headimg_dl?bs=qq&dst_uin=" + qq + "&src_uin=baidu.com&fid=web&spec=100";
		return leftImage;
	}
}
