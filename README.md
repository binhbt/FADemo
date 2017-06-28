# VegaBaseDemo
Demo for using Vegabase, VegaRequest, VegaRecyclerView at:
https://github.com/binhbt/Vegabase
https://github.com/binhbt/Vegarequest
https://github.com/binhbt/VegaRecyclerView

That support call REST Api using Vegarequest
Multiple Type View Using VegaRecyclerView
Auto manage Rx lifecycle and Auto cancel request when Ui destroyed
Mvp Support
Event Driven Support

If you don't like import 3 lib above as module you can import it via gradle
build.gradle for allprojects

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
add to app build.gradle

dependencies {
    compile 'com.github.binhbt:Vegabase:1.0.1'
}
