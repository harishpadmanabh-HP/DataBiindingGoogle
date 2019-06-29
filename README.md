# DataBiindingGoogle
RIP findviewbyId()

Data binding is a hot buzz word among Android developer quite recently. And why not? Because one thing I know about Data binding is that it gonna make us an offer that we can’t refuse. It will definitely change the way we code. Let’s see what does actually mean it.

Data binding is a general technique that binds data sources from the provider and consumer together and synchronizes them. -Wikipedia
What does it offer us?

Make findViewById totally obsolete
Encourage to separate UI logic and business logic
Make easy to synchronize between data sources and UI elements
Provides a way to bind event listener using lambda or method reference from xml
Provides a way to bind custom setters method or rename setters method of view’s attributes (Didn’t get it, don’t worry, we will see later.)
Quite a good deal, right?

How?

Let’s see how can we integrate in our Android app.

In build.gradle file, we will add following gradle property which enabled data binding,

android {
    dataBinding {
        enabled = true
    }
}

In layout file, generally, we have ViewGroup (E.g. RelativeLayout or LinearLayout) as top in View hierarchy. But here, we will make <layout> tag as most parent tag or root tag. After adding it, build system will process it for data binding:

<?xml version="1.0" encoding="utf-8"?>

<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp"
        tools:context="com.androidbytes.databindingdemo.MainActivity">

        <TextView
            android:id="@+id/text_view_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="@android:color/black"
            android:textSize="24sp" />

        <TextView
            android:id="@+id/text_view_occupation"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="@android:color/black"
            android:textSize="16sp" />

    </LinearLayout>
</layout>
                        activity_main.xml
After above step, binding class will be generated based on same name of layout file (e.g. activity_main’s binding class will be generated ActivityMainBinding). We can set setContentView using DataBindingUtil like:


                                 java file
//        Before Data Binding
//        setContentView(R.layout.activity_main);
//
//        TextView textViewName = (TextView) findViewById(R.id.text_view_name);
//        TextView textViewOccupation = (TextView) findViewById(R.id.text_view_occupation);
//
//        textViewName.setText("Elon Musk");
//        textViewOccupation.setText("Entrepreneur, Engineer, Inventor, Investor");


//        After Data Binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.textViewName.setText("Elon Musk");
        binding.textViewOccupation.setText("Entrepreneur, Engineer, Inventor, Investor");
        
        
        MainActivity.java
That’s it. Just run the project and check output. Amazing, isn’t it?
Have you noticed something strange? Yes exactly, now we don’t have to write findViewByIds. No more findViewByIds. Yay…


Let’s see another feature. Bind a data with UI element. So, we can remove setText method of TextView in above code. For that, we have to create a POJO class for that,

public class PersonVO {

    private String name;
    private String occupation;

    public PersonVO(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}



We have to do small change in layout activity_main.xml file:

<?xml version="1.0" encoding="utf-8"?>

<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">


    <!--Step 1-->
    <data>

        <!--Step 2-->
        <variable
            name="personVO"
            type="com.androidbytes.databindingdemo.PersonVO" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp"
        tools:context="com.androidbytes.databindingdemo.MainActivity">

        <!--Step 3-->
        <TextView
            android:id="@+id/text_view_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{personVO.name}" 
            android:textColor="@android:color/black"
            android:textSize="24sp" />

        <TextView
            android:id="@+id/text_view_occupation"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{personVO.occupation}"
            android:textColor="@android:color/black"
            android:textSize="16sp" />

    </LinearLayout>
</layout>

Step 1: Here, <data> element is a way to binding data with UI element.

Step 2: <variable> element is a object of POJO which we bind with UI.

Step 3: We have added “@{personVO.name}” in text property of TextView. Generally, Expressions within the layout are written in the attribute properties using the “@{}" syntax

And last, we have to make small change in MainActivity.java like:

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Before Data Binding
//        setContentView(R.layout.activity_main);
//
//        TextView textViewName = (TextView) findViewById(R.id.text_view_name);
//        TextView textViewOccupation = (TextView) findViewById(R.id.text_view_occupation);
//
//        textViewName.setText("Elon Musk");
//        textViewOccupation.setText("Entrepreneur, Engineer, Inventor, Investor");


//        After Data Binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        PersonVO personVO = new PersonVO("Elon Musk", "Entrepreneur, Engineer, Inventor, Investor");

        binding.setPersonVO(personVO);
    }
}

That’s it. Just think about complex views, we have to write so many lines for only initialization. But now, we don’t have to worry about such things. Data binding will take care of it.
