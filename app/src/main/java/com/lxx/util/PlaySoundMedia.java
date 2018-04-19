package com.lxx.util;

import com.lxx.jlgy.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class PlaySoundMedia {

	private static MediaPlayer mediaPlayer;

	private static int[] soundResouce = { R.raw.s0, R.raw.s1, R.raw.s2,
			R.raw.s3, R.raw.s4, R.raw.s5, R.raw.s6, R.raw.s7, R.raw.s8,
			R.raw.s9, R.raw.s10 };

	public interface PlayThirdSound {
		public void playThirdSound();
	}

	public static void playMedia(final Context context, int s1,
			final PlayThirdSound playThirdSound) {
		// if (mediaPlayer != null) {
		// mediaPlayer = new MediaPlayer();
		// }
		mediaPlayer = MediaPlayer.create(context, soundResouce[s1]);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				mediaPlayer.reset();
				mediaPlayer = MediaPlayer.create(context, soundResouce[10]);

				mediaPlayer.start();
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer arg0) {
						// mediaPlayer.reset();
						playThirdSound.playThirdSound();
					}
				});

			}
		});

	}

	public static void playMedia2(final Context context,
			final PlayThirdSound playThirdSound) {
		mediaPlayer = MediaPlayer.create(context, soundResouce[10]);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// mediaPlayer.reset();
				playThirdSound.playThirdSound();
			}
		});
	}

	public static void playMedia3(final Context context, int s1,
			final PlayThirdSound playThirdSound) {
		mediaPlayer = MediaPlayer.create(context, soundResouce[s1]);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// mediaPlayer.reset();
				playThirdSound.playThirdSound();
			}
		});
	}
}
