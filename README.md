RobolectricSample
=================

A sample android application tested with Robolectric, used to demonstrate an error while trying to test an Activity that contains a SherlockFragment.

Details on the problem
----------------------

I have been reading a lot of links from stackoverflow, github, mailing lists and robolectric blog but couldn't find a working solution yet (already using Robolectric 2.0 alpha 2). 

I was able to test a SherlockFragmentActivity after following this [tip][1], but when I add this fragment, that is a SherlockFragment, to my activity xml:

    <fragment 
      android:name="com.marcelopazzo.fragmentapplication.ExampleFragment" 
      android:id="@+id/example_fragment"
      android:layout_width="fill_parent" 
      android:layout_height="wrap_content" />

The SherlockFragment class:

    public class ExampleFragment extends SherlockFragment {
  
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.example_fragment, container, false);
        }
    }

And this is the layout that is being inflated by the fragment:

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical" >
    
        <TextView
            android:id="@+id/hello_again"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/hello_again" />

    </LinearLayout>

I get the following error:

    java.lang.NullPointerException
        at org.robolectric.shadows.ShadowViewGroup.addView(ShadowViewGroup.java:69)
        at android.view.ViewGroup.addView(ViewGroup.java)
        at org.robolectric.res.builder.LayoutBuilder.constructFragment(LayoutBuilder.java:150)
        at org.robolectric.res.builder.LayoutBuilder.create(LayoutBuilder.java:104)
        at org.robolectric.res.builder.LayoutBuilder.doInflate(LayoutBuilder.java:42)
        at org.robolectric.res.builder.LayoutBuilder.doInflate(LayoutBuilder.java:45)
        at org.robolectric.res.builder.LayoutBuilder.inflateView(LayoutBuilder.java:62)
        at org.robolectric.shadows.ShadowLayoutInflater.inflate(ShadowLayoutInflater.java:50)
        at org.robolectric.shadows.ShadowLayoutInflater.inflate(ShadowLayoutInflater.java:55)
        at android.view.LayoutInflater.inflate(LayoutInflater.java)
        at com.squareup.test.ActionBarSherlockRobolectric.setContentView(ActionBarSherlockRobolectric.java:38)
        at com.actionbarsherlock.app.SherlockFragmentActivity.setContentView(SherlockFragmentActivity.java:262)
        at com.marcelopazzo.fragmentapplication.MainActivity.onCreate(MainActivity.java:13)
        at com.marcelopazzo.fragmentapplication.MainActivityTest.setUp(MainActivityTest.java:33)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
        at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:27)
        at org.robolectric.RobolectricTestRunner$2.evaluate(RobolectricTestRunner.java:110)
        at org.junit.runners.BlockJUnit4ClassRunner.runNotIgnored(BlockJUnit4ClassRunner.java:79)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:71)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:234)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:133)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:114)
        at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:188)
        at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:166)
        at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:86)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:101)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:74)

This is the test class that I am using:

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

The application is working fine on the device.


  [1]:  http://robolectric.blogspot.com.br/2013/03/using-actionbarsherlock-with.html