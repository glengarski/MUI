This holds the GWT JARs.

Note that these JARs are actually NOT NEEDED at runtime!

Vaadin's WidgetsetCompiler, which in turns calls GWT's Compiler, turn the custom Widgets
plus GWT into JS etc. in the respective modules web/VAADIN/widgetsets directory. 

This is only here for the WidgetSetCompiler.launch configuration, needed to rebuild, when new Add-On are used.

It will also be useful if in the future custom GWT development is done, to look up GWT Java sources, etc.
