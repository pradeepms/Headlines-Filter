#Headlines-Filter


##Description:
A simple news application which uses [Spring for Android](http://www.springsource.org/spring-android) rest client 
framework, and Jackson library for deserialization (JSON - Java object)<br />
And this application fetches news headlines about chosen topics from [Daylife.com] (http://Daylife.com)


## Library dependencies
1. Grab them using maven.
<pre><code>
&lt;dependency&gt;
&lt;groupId&gt;org.springframework.android&lt;/groupId&gt;
&lt;artifactId&gt;spring-android-rest-template&lt;/artifactId&gt;
&lt;version&gt;1.0.1.RELEASE&lt;/version&gt;
&lt;/dependency&gt;
</code></pre>
<pre><code>
&lt;dependency&gt;
&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
&lt;artifactId&gt;jackson-databind&lt;/artifactId&gt;
&lt;version&gt;2.1.2&lt;/version&gt;
&lt;/dependency&gt;
</pre></code>
or
2. Add jars to project_name/libs folder <br />
<pre>
    [Spring for Android](http://www.springsource.org/spring-community-download) jar. <br />
    [Jackson](http://wiki.fasterxml.com/JacksonDownload) jar.
</pre>

## Screen Shots
![Alt text](http://pradeepms.do.am/GitHub-Images/news1.png "Headlines UI")
![Alt text](http://pradeepms.do.am/GitHub-Images/news2.png "Webkit UI")


###uses-permission
	<uses-permission android:name="android.permission.INTERNET"/>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"
		      
Link to my [Website] (http://pradeepms.do.am)

