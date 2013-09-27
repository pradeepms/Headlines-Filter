#Headlines-Filter


##Description:
A simple news application which uses [Spring for Android](http://www.springsource.org/spring-android) rest client 
framework, and Jackson library for deserialization (JSON - Java object)<br />
And this application fetches news headlines about chosen topics from [Daylife.com] (http://Daylife.com)

#### JSON Sample
<pre>
<code>
{
    "response": {
        "message": "Success",
        "code": 2002,
        "payload": {
            "article": [
                {
                    "timestamp_epoch": 1378121520,
                    "search_score": 10,
                    "headline": "Windows Phone is a 'real adversary' to Android and iOS",
                    "daylife_url": "http://www.daylife.com/article/063X24ub9z20k",
                    "related_article_count": 0,
                    "excerpt": "...Kantar Worldpanel Comtech, it appears that Microsoft's operating system has established itself as a \"real adversary\" to Android and iOS. That appears to be no small feat. Between May and July 2013, Windows Phones accounted for 8.2 percent of all smartphone...",
                    "source": {
                        "name": "BetaNews",
                        "url": "http://www.betanews.com/",
                        "daylife_url": "http://www.daylife.com/source/BetaNews",
                        "rank": 3,
                        "favicon_url": "http://favicon.daylife.com/imageserve/04Xbd7E3eHeuJ/favicon.png",
                        "source_id": "04Xbd7E3eHeuJ",
                        "type": "MAINSTREAM"
                    },
                    "url": "http://feeds.betanews.com/~r/bn/~3/1KN2ozEOMeY/",
                    "timestamp": "2013-09-02 11:32:00",
                    "article_id": "063X24ub9z20k"
                },
                {
                    "timestamp_epoch": 1378174980,
                    ...
                    ...
</code>
</pre>

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

###Screen-shots
![Alt text](http://pradeepms.github.io/images/Readme_images/news1.png "Headlines UI")
![Alt text](http://pradeepms.github.io/images/Readme_images/news2.png "Webkit UI")

###Credits
Thanks to my friend [Akash Manohar] (https://twitter.com/HashNuke), for this app idea.
