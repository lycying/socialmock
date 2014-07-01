package info.u250.socialmock.qq;

import info.u250.socialmock.MainActivity;

import java.util.Date;

public class MockData {
	private MockData() {
	}
	static QQChatEntity add1(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("1511810745");
		e.setRight("1511810734");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(6);
		e.setToName("小小阿童木");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("不好意思，上次买菜借你的钱差点忘还了");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("你看你，这点小事还记得啊，好像我多抠门似的，我都忘了。");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("借钱一定要还的。咦，多少钱来着？");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("6块3毛5，上个月七号中午十二点十分你买茄子借的，我给了你一张五块一张一块，一张五毛，老板找了两毛给你优惠了五分。其实这事我真没放在心上，你不提醒我都快忘了，呵呵。");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("呵呵");
			msg.save();
		}
		return e;
	}
	static QQChatEntity add2(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("10086");
		e.setRight("1540710904");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(5);
		e.setToName("在线客服");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("丢失卡的话，需要尽快补卡使用哦");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("请问卡掉了怎么办");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("需要到营业厅补办哦");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("我不可以捡起来吗?");
			msg.save();
		}
		return e;
	}
	
	static QQChatEntity add3(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("2765172091");
		e.setRight("1511812745");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(7);
		e.setToName("考试-望月");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("同学 你哪个学校的");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("理工的");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("哦，我是清华的");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("你好厉害");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("其实也不算难啦~你当初要认真学习，也能考上的。对了，你是大连理工还是北京理工?");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("麻省的");
			msg.save();
		}
		return e;
	}
	static QQChatEntity add4(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("1529478804");
		e.setRight("1540010904");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(5);
		e.setToName("_老婆_");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("老婆大人我明天去上海出差，给我弄个鸡嫖。");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setError(true);
			msg.setMessage("机票");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setError(true);
			msg.setMessage("机票");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("嫖你妈逼，今天别回来了，以后也别回来了");
			msg.save();
		}
		return e;
	}
	static QQChatEntity add5(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("1511319231");
		e.setRight("1513619253");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(5);
		e.setToName("女神");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("周末有空么");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("想要约我出去么,我想要说...");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("Yes");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("非常yes!");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("人家就喜欢你一个[f069]");
			msg.save();
		}
		return e;
	}
	
	static QQChatEntity add6(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("1513659243");
		e.setRight("1511310231");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(8);
		e.setToName("樱桃大丸子");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("hello美女");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("Hi");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("见过大爷手淫没");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("[f040]");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("没...");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("建国大业首映没");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("见过");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("...");
			msg.save();
		}
		return e;
	}
	static QQChatEntity add7(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("1549778004");
		e.setRight("1549778104");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(5);
		e.setToName("老妹");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("哥");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("嗯？");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("你比爸强多了[f003]");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("恩，妈也这么说");
			msg.save();
		}
		return e;
	}

	static QQChatEntity add8(){
		QQChatEntity e = new QQChatEntity();
		e.setLeft("2513659343");
		e.setRight("2523789549");
		e.setCreateDate(new Date().getTime());
		e.setDevice(0);
		e.setChatCount(8);
		e.setToName("羊鸵鸵");
		e.save();
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setDate(true);
			msg.setCreateDate(new Date().getTime());
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("来句，呵呵");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("独怜幽草涧边生");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("上有黄鹂深树鸣");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("春潮带雨晚来急!");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("野渡无人舟自横");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(false);
			msg.setMessage("[f055]");
			msg.save();
		}
		{
			QQMessageEntity msg = new QQMessageEntity();
			msg.setParent(e.getId());
			msg.setLeft(true);
			msg.setMessage("[f054]");
			msg.save();
		}
		return e;
	}
	static void notityMain(final MainActivity activity,final QQChatEntity e){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activity.qqChatEntitys.add(e);
				activity.qqListAdapter.notifyDataSetChanged();
			}
		});
	}
	public static void addData(final MainActivity activity){
		notityMain(activity,add1());
		notityMain(activity,add2());
		notityMain(activity,add3());
		notityMain(activity,add4());
		notityMain(activity,add5());
		notityMain(activity,add6());
		notityMain(activity,add7());
		notityMain(activity,add8());
	}
}
