package com.marcelopazzo.fragmentapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	
	private MainActivity activity;
	private TextView textView, fragmentTextView;
	
	public static final String GREETINGS = "Hello world!";
	public static final String NEXT_GREETINGS = "Hello from a Fragment!";
	
	@Before
    public void setUp() {
		activity = Robolectric.buildActivity(MainActivity.class).create().get();

        textView = (TextView) activity.findViewById(R.id.hello);
        fragmentTextView = (TextView) activity.findViewById(R.id.hello_again);
        
	}
	
	@Test
	public void shouldGreet() {
		assertEquals(GREETINGS, textView.getText());
	}
	
	@Test
	public void shouldGreetAgain() {
		assertNotNull(fragmentTextView);
		assertEquals(NEXT_GREETINGS, fragmentTextView.getText());
	}
	
	

}
