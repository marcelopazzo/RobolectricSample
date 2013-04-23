package com.marcelopazzo.fragmentapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ActionBarSherlockNative;
import com.squareup.test.ActionBarSherlockRobolectric;

import android.widget.TextView;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	
	private MainActivity activity;
	private TextView textView;
	
	public static final String GREETINGS = "Hello world!";  
	
	@Before
    public void setUp() {
		
		ActionBarSherlock.registerImplementation(ActionBarSherlockRobolectric.class);
		ActionBarSherlock.unregisterImplementation(ActionBarSherlockNative.class);
		ActionBarSherlock.unregisterImplementation(ActionBarSherlockCompat.class);
		
		activity = new MainActivity();
        activity.onCreate(null);

        textView = (TextView) activity.findViewById(R.id.hello);
	}
	
	@Test
	public void shouldGreet() {
		assertEquals(GREETINGS, textView.getText());
	}
	

}
