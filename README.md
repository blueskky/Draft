# Draft
草稿本 canvas



##### 草稿本 canvas

1.使用 

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

```groovy
dependencies {
	        implementation 'com.github.blueskky:Draft:V1.0.0'
	}
```



1.1 只在activity中使用，在onCreate中 初始化

```java
Draft draft = new Draft();
//参数1 Context   参数2  是否记录草稿绘图内容，同一界面下次打开回显草稿,不传默认false
draft.attach(this，true); 
```



1.2  在activity中有多个fragment，草稿纸要记录每个fragment上的canvans ,以下二者选其一

```java
//fragment  通过viewpager方式设置 
draft.setViewPager(viewPager);

//fragment 不是通过viewpager方式设置，要动态传入当前fragment 索引
draft.setCurrentFrgIndex(position);
```



