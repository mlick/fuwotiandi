package com.lxx.util;

import java.util.HashMap;

import com.lxx.jlgy.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

public class PlaySoundPool {

	private Context context;

	// 音效的音量
	private int streamVolume;

	// 定义SoundPool 对象
	private SoundPool soundPool;

	// 定义HASH表
	private HashMap<Integer, Integer> soundPoolMap;

	private static PlaySoundPool playSoundPool;
	private Handler handler;
	long delay1 = 600;
	long delay2 = 300;

	public PlaySoundPool(Context context) {
		this.context = context;

		handler = new Handler();

		initSounds();
	}

	/**
	 * 单例模式
	 * 
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-14 上午10:20:06
	 * @param context
	 * @return
	 * 
	 */
	public static PlaySoundPool initPlaySoundPool(Context context) {

		if (playSoundPool != null) {
			return playSoundPool;
		}

		playSoundPool = new PlaySoundPool(context);
		// 添加声音元素到声音池
		// playSoundPool.loadSfx(R.raw.s0, 0);
		playSoundPool.loadSfx(R.raw.s1, 1);
		playSoundPool.loadSfx(R.raw.s2, 2);
		playSoundPool.loadSfx(R.raw.s3, 3);
		playSoundPool.loadSfx(R.raw.s4, 4);
		playSoundPool.loadSfx(R.raw.s5, 5);
		playSoundPool.loadSfx(R.raw.s6, 6);
		playSoundPool.loadSfx(R.raw.s7, 7);
		playSoundPool.loadSfx(R.raw.s8, 8);
		playSoundPool.loadSfx(R.raw.s9, 9);
		playSoundPool.loadSfx(R.raw.s10, 10);
		return playSoundPool;
	}

	public void initSounds() {
		// 初始化soundPool 对象,第一个参数是允许有多少个声音流同时播放,第2个参数是声音类型,第三个参数是声音的品质
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);

		// 初始化HASH表
		soundPoolMap = new HashMap<Integer, Integer>();

		// 获得声音设备和设备音量
		AudioManager mgr = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	public void loadSfx(int raw, int ID) {
		// 把资源中的音效加载到指定的ID(播放的时候就对应到这个ID播放就行了)
		soundPoolMap.put(ID, soundPool.load(context, raw, ID));
	}

	public void play(int sound, int uLoop) {
		try {
			soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume,
					1, uLoop, 1f);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-13 上午11:44:58
	 * @param sound
	 *            播放声音的id
	 * @param uLoop
	 *            是否循环
	 * @param frtLoad
	 *            播放的优先级
	 * 
	 */
	public void play3(int sound, int uLoop, int frtLoad) {
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume,
				frtLoad, uLoop, 1f);
	}

	// 实现不了
	// public void play4(int sound1, int sound2, int uLoop) {
	// soundPool.play(soundPoolMap.get(sound1), streamVolume, streamVolume, 1,
	// uLoop, 1f);
	// soundPool.play(soundPoolMap.get(10), streamVolume, streamVolume, 1,
	// uLoop, 1f);
	// soundPool.play(soundPoolMap.get(sound2), streamVolume, streamVolume, 1,
	// uLoop, 1f);
	// }
	/**
	 * 播放11-19的数
	 * 
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-14 上午10:09:36
	 * @param sound2
	 * 
	 */
	public void play5(final int sound2) {
		soundPool.play(soundPoolMap.get(10), streamVolume, streamVolume, 1, 0,
				1f);

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				soundPool.play(soundPoolMap.get(sound2), streamVolume,
						streamVolume, 1, 0, 1f);
			}
		}, delay1);
	}

	/**
	 * 播放20 ， 30 的十位数
	 * 
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-14 上午10:10:25
	 * @param sound2
	 * 
	 */
	public void play6(final int sound1) {
		soundPool.play(soundPoolMap.get(sound1), streamVolume, streamVolume, 1,
				0, 1f);

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				soundPool.play(soundPoolMap.get(10), streamVolume,
						streamVolume, 1, 0, 1f);
			}
		}, delay2);
	}

	/**
	 * 播放其它的数据
	 * 
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-14 上午10:16:59
	 * @param sound1
	 * @param sound2
	 * 
	 */
	public void play7(final int sound1, final int sound2) {
		soundPool.play(soundPoolMap.get(sound1), streamVolume, streamVolume, 1,
				0, 1f);

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				soundPool.play(soundPoolMap.get(10), streamVolume,
						streamVolume, 1, 0, 1f);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						soundPool.play(soundPoolMap.get(sound2), streamVolume,
								streamVolume, 1, 0, 1f);
					}
				}, delay1);
			}
		}, delay2);
	}

	// 有异常
	// public void play2(int sound, int uLoop) {
	// soundPool.play(soundPoolMap.get(sound / 10), streamVolume,
	// streamVolume, 1, uLoop, 1f);
	// try {
	// soundPool.wait(500);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// soundPool.play(soundPoolMap.get(10), streamVolume, streamVolume, 1,
	// uLoop, 1f);
	// try {
	// soundPool.wait(500);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// soundPool.play(soundPoolMap.get(sound % 10), streamVolume,
	// streamVolume, 1, uLoop, 1f);
	// }

}
