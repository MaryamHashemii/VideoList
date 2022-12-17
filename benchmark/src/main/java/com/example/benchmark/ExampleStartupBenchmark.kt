package com.example.benchmark

import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startUpModeNone() = startup(CompilationMode.None())

    @Test
    fun startUpModePartial() = startup(CompilationMode.Partial())

    @Test
    fun scrollAndNavigationModeNone() = scrollAndNavigation(CompilationMode.None())

    @Test
    fun scrollAndNavigationModePartial() = scrollAndNavigation(CompilationMode.Partial())

    private fun startup(mode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.videoList.test",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode
    ) {
        pressHome()
        startActivityAndWait()
    }

    private fun scrollAndNavigation(mode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.videoList.test",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode

    ) {
        pressHome()
        startActivityAndWait()

        scroll()
    }

}

fun MacrobenchmarkScope.scroll() {
    device.waitForIdle(20000)
    val list = device.findObject(By.res("VIDEO_List"))
    device.waitForIdle()
    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.DOWN)
    device.findObject(By.res("VIDEO_ITEM")).click()
    device.wait(Until.hasObject(By.res("VIDEO_DETAIL")), 5000)
}