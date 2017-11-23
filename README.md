# StrokeLayouts
// 一系列可以自定义描边的layouts（代码极其简单，但很实用）

---

#### 介绍：

不多说，请看图：

![image](https://github.com/razerdp/StrokeLayouts/blob/master/arts/demo.gif)

  
[![Download](https://api.bintray.com/packages/razerdp/maven/StrokeLayouts/images/download.svg)](https://bintray.com/razerdp/maven/StrokeLayouts/_latestVersion)
#### 依赖（请把{latestVersion}换成上面标签所述版本）：

> compile 'com.github.razerdp:StrokeLayouts:{latestVersion}'

#### 使用方法：

目前只定义了常用的几个Layout：

 - StrokeRelativeLayout
 - StrokeLinearLayout
 - StrokeFrameLayout
 
 使用与平时的方法没什么不同，自定义控件只增加了`边框宽度`、`边框颜色`和`边的排除`方法
 
```attrs
  <com.razerdp.widgets.StrokeFrameLayout
        ...平常的其他配置
        app:stroke_color="@android:color/holo_green_light"
        app:stroke_width="5dp"
        app:exclude_side="top"// 排除的边，这里配置的边将不会绘制，允许标志位相加
            
            etc : app:exclude_side="top|right|bottom"
            
            //exclude_side取值
            {
                exclude_side：
                    left;
                    top;
                    right;
                    bottom;
                    none;
                    all;
            }
        >
```


在代码中使用：

```java
 StrokeRelativeLayout rlStroke;
 rlStroke.setExcludeSide(ExcludeSide.EXCLUDE_LEFT);
 
/*
   more : 
   rlStroke.setExcludeSide(ExcludeSide.EXCLUDE_LEFT+ExcludeSide.EXCLUDE_TOP);
   
   or
   rlStroke.setExcludeSide(ExcludeSide.EXCLUDE_LEFT|ExcludeSide.EXCLUDE_TOP);
*/

 
 //ExcludeSide：
ExcludeSide.EXCLUDE_ALL // 排除所有边
ExcludeSide.EXCLUDE_NONE // 不排除边
ExcludeSide.EXCLUDE_LEFT // 排除左边
ExcludeSide.EXCLUDE_TOP // 排除上边
ExcludeSide.EXCLUDE_RIGHT // 排除右边
ExcludeSide.EXCLUDE_BOTTOM // 排除下边
 }
```

#### License 
MIT



