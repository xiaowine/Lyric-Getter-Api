# LyricGetterApi
### 这是Lyric Getter 的API
> 使用方法

把本项目当作Git子模块导入到自己的项目中，并在settings.gradle.kts或者settings.gradle中导入模块  
具体请看 [Demo](https://github.com/xiaowine/LyricGetterApiDemo)

> 注意
若开启了 proguard 请保证 API 类不被混淆:
```shrinker_config
-keep class cn.lyric.getter.api.**{*;}
```
