# Assignment1
每个人都有自己的教务账号，现在请你用类似于课上讲解的爬虫方法将你自己在教务网站上的成绩表格以HTML代码的形式爬取下来，并写入到一个.html文档中，HTTP请求所要用到的辅助包HttpRequest已经上传至Assignment 1的repository下。该包的使用说明见：https://github.com/kevinsawicki/http-request
最后HTML结果显示应如下图所示（每个人对应自己的成绩单，故每人的结果应该均不相同）：
![screenshot](https://github.com/OOP-JAVA-WHUISS/Assignment1/blob/master/screenshot.png)
#####要求：
1. 独立完成，严禁抄袭，一旦被查重系统MOSS判定为抄袭并无法做出合理解释者，第一次予以警告并记此次作业0分。若再被发现，直接挂科。
2. 最终请将你所写的Java代码以及爬取结果一同打包至.zip压缩包中，命名格式：学号-姓名-Assignment1。
3. 所有作业请提交到Github上Assignment1 repository下的submit-homework文件夹下，所有提交至其他地方的作业视作无效。
4. Deadline：10月1日 0:00
5. 

常见疑难杂症：    
1. 眼瞎，各种url写错，正确形式如下：  
String url = "http://210.42.121.241/servlet/GenImg";  
2. 看不到输出，不知道运行成功没有？  
```java
if(response.ok()){  
	response.receive(new File(fName));  
	System.out.println("ok");//这里加上输出信息  
}
```
3. eclipse里面看不到下载的图片啊，怎么办？  
	1. 去项目文件夹下看  
	2. 刷新下左边的project就会出来  
	3. 建议加个img文件夹存储，记得刷新查看，eclipse缓存很严重: 
```	
String fName = "img/test"+Integer.toString(counter)+".png";  
```
4. 怎么模拟登录啊，有验证码呢？  
（0）明显没去好好听课嘛，王爷的课怎么能不好好听呢；  荧神你真是够了(╯‵□′)╯︵┻━┻
（1）提示：通过cookie实现模拟登录，不给代码了，这个告诉大家了就等于我们帮你做作业了。大家自己去HttpRequest翻文档吧。
（2）cookie如何查看就不用我再讲了吧，不会的拖出去打一顿，另外再说明一下，chrome、firefox、IE都是可以F12打开调试窗口查看network的，但是建议用chrome，至于360、猎豹、百度什么的拖出去打死好了  
5. 关于教务部网站崩了的若干问题处理  
（1）可以去爬其他网站，不用在教务部网站这棵树上吊死，比如去祸害什么csdn、优酷、爱奇艺什么的  
（2）别去爬一样的网站首页，去爬一些标识性比较强的页面，这样不容易让我们认为你是copy的  
6. url编码问题：  
url里面如果有中文字符和空格符，请不要直接使用，这需要服务器支持才行，所以请把此类url输入到浏览器中，再复制回来，可以就得到encode的url了，详情你们可以百度URL编码
7. 之后你们有问题可以写在readme文件里面通过作业提交！
